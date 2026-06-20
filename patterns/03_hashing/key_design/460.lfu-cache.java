/*
 * @lc app=leetcode id=460 lang=java
 *
 * [460] LFU Cache
 */

// @lc code=start

import java.util.*;

class LFUCache {

    // head <-> LRU <-> ... <-> MRU <-> tail
    class DLinkedNode {
        int key;
        int value;
        int freq;

        DLinkedNode prev;
        DLinkedNode next;
    }

    class DLinkedList {
        DLinkedNode head;
        DLinkedNode tail;

        int size;

        public DLinkedList() {
            head = new DLinkedNode();
            tail = new DLinkedNode();

            head.next = tail;
            tail.prev = head;
        }

        public void insertToMRU(DLinkedNode node) {
            DLinkedNode prev = tail.prev;

            prev.next = node;
            node.prev = prev;

            node.next = tail;
            tail.prev = node;

            size++;

        }
        public void remove(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            size--;
        }

        public DLinkedNode removeLRU() {
            DLinkedNode lru = head.next;
            remove(lru);
            return lru;
        }
    }

    private final HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    private final HashMap<Integer, DLinkedList> freqMap = new HashMap<>();

    private final int capacity;
    private int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;

    }
    
    public int get(int key) {
        DLinkedNode node = cache.get(key);

        if (node == null) {
            return -1;
        }
        
        updateFreq(node);

        return node.value;
    }
    
    public void put(int key, int value) {
        // prevent zero-capacity cache evict and insert
        if (capacity == 0) {
            return;
        }
        
        // update existing key
        if (cache.containsKey(key)) {
            DLinkedNode node = cache.get(key);
            node.value = value;
            updateFreq(node);
            return;
        }

        // cache full -> evict LFU/LRU
        if (cache.size() == capacity) {
            DLinkedList minFreqList = freqMap.get(minFreq);
            DLinkedNode nodeToRemove = minFreqList.removeLRU();

            cache.remove(nodeToRemove.key);
        }

        // create new node
        DLinkedNode newNode = new DLinkedNode();
        newNode.key = key;
        newNode.value = value;
        newNode.freq = 1;

        // insert into cache
        cache.put(key, newNode);

        // add to frequency 1 list
        freqMap.putIfAbsent(1, new DLinkedList());
        freqMap.get(1).insertToMRU(newNode);

        // reset min frequency
        minFreq = 1;


    }

    private void updateFreq(DLinkedNode node) {
        int oldFreq = node.freq;

        // remove from old frequency list
        DLinkedList oldList = freqMap.get(oldFreq);
        oldList.remove(node);

        // update minFreq if old list became empty
        if (oldList.size == 0) {
            freqMap.remove(oldFreq);   
            if (oldFreq == minFreq) {
                minFreq++;
            }
        }

        node.freq++;

        // add to new frequency list
        freqMap.putIfAbsent(node.freq, new DLinkedList());
        freqMap.get(node.freq).insertToMRU(node);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

// Time: O(1)
// Space: O(capacity)
// @lc code=end

