/*
 * @lc app=leetcode id=432 lang=java
 *
 * [432] All O`one Data Structure
 */

import java.util.*;

// @lc code=start
class AllOne {

    class DLinkedNode {
        int count;
        HashSet<String> keys;
        DLinkedNode prev;
        DLinkedNode next;

        DLinkedNode (int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }

    }
    // key -> current count
    private HashMap<String, Integer> keyCount;

    // count -> nodes
    private HashMap<Integer, DLinkedNode> countMap;

    private DLinkedNode head, tail;


    public AllOne() {
        keyCount = new HashMap<>();
        countMap = new HashMap<>();

        head = new DLinkedNode(0);
        tail = new DLinkedNode(0);

        head.next = tail;
        tail.prev = head;
    }

    // Insert node after 'prev'
    private void insertAfter(DLinkedNode prev, DLinkedNode node) {
        node.next = prev.next;
        node.prev = prev;
        prev.next.prev = node;
        prev.next = node;
    }

    // Remove node from list (only when empty)
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        countMap.remove(node.count);
    }
    
    public void inc(String key) {
        int oldCount = keyCount.getOrDefault(key, 0);
        int newCount = oldCount + 1;

        // update keyCount
        keyCount.put(key, newCount);

        DLinkedNode oldNode = countMap.getOrDefault(oldCount, head);

        // create bucket for new freq
        if (!countMap.containsKey(newCount)) {
            DLinkedNode newNode = new DLinkedNode(newCount);
            insertAfter(oldNode, newNode);
            countMap.put(newCount, newNode);
        }
        // add key to the hash set keys
        countMap.get(newCount).keys.add(key);

        // remove key from old bucket
        if (oldCount > 0) {
            oldNode.keys.remove(key);
            if (oldNode.keys.isEmpty()) {
                removeNode(oldNode);
            }
        }
    }

    public void dec(String key) {
        int oldCount = keyCount.get(key);
        int newCount = oldCount - 1;

        if (newCount == 0) {
            keyCount.remove(key);
        } else {
            keyCount.put(key, newCount);
        }

        // remove key from current bucket
        DLinkedNode oldNode = countMap.get(oldCount);
        oldNode.keys.remove(key);

        if (newCount > 0) {
            if (!countMap.containsKey(newCount)) {
                DLinkedNode newNode = new DLinkedNode(newCount);
                insertAfter(oldNode.prev, newNode);  
                countMap.put(newCount, newNode);
            }
            countMap.get(newCount).keys.add(key);
        }

        // clean old bucket if empty
        if (oldNode.keys.isEmpty()) {
            removeNode(oldNode);
        }
    }

    // tail.prev = max bucket 
    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }
        return tail.prev.keys.iterator().next();
    }

    // head.next = min bucket 
    public String getMinKey() {
        if (head.next == tail) {
            return "";
        }
        return head.next.keys.iterator().next();
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
// @lc code=end

