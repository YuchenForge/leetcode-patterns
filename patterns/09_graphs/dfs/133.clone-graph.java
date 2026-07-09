/*
 * @lc app=leetcode id=133 lang=java
 *
 * [133] Clone Graph
 */

import java.util.*;

// @lc code=start

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;

        HashMap<Node, Node> cloned = new HashMap<>();

        return dfs(node, cloned);
    }

    private Node dfs(Node node, HashMap<Node, Node> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node newNode = new Node();
        newNode.val = node.val;
        map.put(node, newNode);

        for (Node neighbor: node.neighbors) {
            newNode.neighbors.add(dfs(neighbor, map));
        }

        return newNode;
    }
}
// @lc code=end

