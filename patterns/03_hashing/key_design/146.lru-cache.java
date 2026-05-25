/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 */

import java.util.*;

// @lc code=start
class LRUCache {

    // head <-> LRU <-> ... <-> MRU <-> tail
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }

    private HashMap<Integer, DLinkedNode> cache = new HashMap<>();

    private int capacity;
    private DLinkedNode head, tail;


    public void remove(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void insertToMRU(DLinkedNode node) {
        DLinkedNode prev = tail.prev;

        prev.next = node;
        node.prev = prev;

        node.next = tail;
        tail.prev = node;

    }

    public void removeLRU() {
        DLinkedNode lru = head.next;
        remove(lru);
        cache.remove(lru.key);
    }

    public void moveToMRU(DLinkedNode node) {
        remove(node);
        insertToMRU(node);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        DLinkedNode node = cache.get(key);

        if (node == null) {
            return -1;
        }

        moveToMRU(node);
        return node.value;
    }

    public void put(int key, int value) {
        // update existing key
        if (cache.containsKey(key)) {
            DLinkedNode node = cache.get(key);
            node.value = value;
            moveToMRU(node);
            return;
        } 

        // evict LRU if full
        if (cache.size() >= capacity) {
            removeLRU();
        }

        // add new node
        DLinkedNode node = new DLinkedNode();
        node.key = key;
        node.value = value;

        insertToMRU(node);
        cache.put(key, node);

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

