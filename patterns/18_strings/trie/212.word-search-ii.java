/*
 * @lc app=leetcode id=212 lang=java
 *
 * [212] Word Search II
 */

import java.util.*;

// @lc code=start

// Optimized Trie + Single DFS Pass Solution

// Time: O(M * N * 4^L)
// M * N = board dimensions
// L = longest word length

// Space: O(sum of all word lengths)
class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // non-null when this node completes a word
    }

    private List<String> result = new ArrayList<>();

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);

        int rows = board.length;
        int cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(r, c, root, board);
            }
        }

        return result;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word: words) {
            TrieNode node = root;
            for (char ch: word.toCharArray()) {
                int idx = ch - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }

                node = node.children[idx];
            }

            node.word = word;
        }

        return root;
    }

    private void dfs(int row, int col, TrieNode node, char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // Out of bound or letter mismatch or already used
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }

        // Already visited or no matching prefix in Trie
        char temp = board[row][col];
        if (temp == '#' || node.children[temp - 'a'] == null) {
            return;
        }
        
        TrieNode next = node.children[temp - 'a'];

        // Found a complete word
        if (next.word != null) {
            result.add(next.word);
            next.word = null;
        }

        // Mark visited
        board[row][col] = '#';

        dfs(row + 1, col, next, board);
        dfs(row - 1, col, next, board);
        dfs(row, col + 1, next, board);
        dfs(row, col - 1, next, board);

        // backtrack
        board[row][col] = temp;
    }
}
// @lc code=end

/**
    Trie: "cat", "car", "card", "dog"

                root
               /    \
              c      d
              |      |
              a      o
             / \      \
            t   r      g*
            *   |
                d
                *
 */


// DFS + Backtrack Solution (Failed due to Time Limit Exceeded)

// Time: O(W * M * N * 4^L)
// W = words 
// M * N = board dimensions
// L = longest word length

// Space: O(L)
class Solution2 {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        for (String word: words) {
            if (exist(word, board)) {
                result.add(word);
            }
        }

        return result;
    }

    private boolean exist(String word, char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == word.charAt(0)) {
                    if (dfs(r, c, word, 0, board)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(int row, int col, String word, int index, char[][] board) {
        // Found the whole word
        if (index == word.length()) {
            return true;
        }

        int rows = board.length;
        int cols = board[0].length;

        // Out of bound or letter mismatch or already used
        if (row < 0 || row >= rows || col < 0 || col >= cols 
            || board[row][col] != word.charAt(index)) {
                return false;
        }

        // Mark current cell visited
        char temp = board[row][col];
        board[row][col] = '#';

        boolean found = 
            dfs(row + 1, col, word, index + 1, board) ||
            dfs(row - 1, col, word, index + 1, board) ||
            dfs(row, col + 1, word, index + 1, board) ||
            dfs(row, col - 1, word, index + 1, board);

        // backtrack
        board[row][col] = temp;

        return found;
    }
}