/*
 * @lc app=leetcode id=488 lang=java
 *
 * [488] Zuma Game
 */

import java.util.*;

// @lc code=start

// DFS + Memoization
class Solution {

    public int findMinStep(String board, String hand) {
        char[] handArr = hand.toCharArray();
        Arrays.sort(handArr);

        return dfs(board, new String(handArr), new HashMap<>());
    }

    private int dfs(String board, String remainingHand, HashMap<String, Integer> memo) {
        if (board.isEmpty()) return 0;
        if (remainingHand.isEmpty()) return -1;

        String key = board + '#' + remainingHand;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int best = Integer.MAX_VALUE;

        for (int i = 0; i < remainingHand.length(); i++) {
            if (i > 0 && remainingHand.charAt(i) == remainingHand.charAt(i-1)) {
                continue;
            }

            char ball = remainingHand.charAt(i);
            String newHand = remainingHand.substring(0, i) + remainingHand.substring(i + 1);

            for (int pos = 0; pos <= board.length(); pos++) {
                if (!isGoodPlace(board, pos, ball)) {
                    continue;
                }

                String newBoard = board.substring(0, pos) + ball + board.substring(pos);
                String collapsedBoard = collapse(newBoard);
                
                int sub = dfs(collapsedBoard, newHand, memo);
                if (sub != -1) {
                    best = Math.min(best, sub + 1);
                }
            }
        }

        int result = (best == Integer.MAX_VALUE)? -1: best;
        memo.put(key, result);
        return result;
    }

    private boolean isGoodPlace(String board, int pos, char ball) {
        if (pos > 0 && board.charAt(pos - 1) == ball) {
            return true;
        }

        if (pos > 0 && pos < board.length()
                    && board.charAt(pos - 1) == board.charAt(pos)) {
            return true;
        }

        return false;
    }

    private String collapse(String board) {
        int i = 0;
        while (i < board.length()) {
            int j = i;
            while (j < board.length() && board.charAt(j) == board.charAt(i)) {
                j++;
            }

            if (j - i >= 3) {
                String newBoard = board.substring(0, i) + board.substring(j);
                return collapse(newBoard);
            } else {
                i = j;
            }
        }

        return board;
    }
}
// @lc code=end

// Backtrack Brute Force (Failed due to time limit)
class Solution2 {

    private int minInsertions = Integer.MAX_VALUE;

    public int findMinStep(String board, String hand) {
        char[] handArr = hand.toCharArray();
        Arrays.sort(handArr);

        backtrack(board, new String(handArr), 0);

        return minInsertions == Integer.MAX_VALUE ? -1 : minInsertions; 
    }

    private void backtrack(String board, String remainingHand, int insertions) {
        if (board.isEmpty()) {
            minInsertions = Math.min(minInsertions, insertions);
            return;
        }

        if (remainingHand.isEmpty()) {
            return;
        }

        if (insertions >= minInsertions) {
            return;
        }

        for (int i = 0; i < remainingHand.length(); i++) {
            if (i > 0 && remainingHand.charAt(i) == remainingHand.charAt(i-1)) {
                continue;
            }

            char ball = remainingHand.charAt(i);
            String newHand = remainingHand.substring(0, i) + remainingHand.substring(i + 1);

            for (int pos = 0; pos <= board.length(); pos++) {
                String newBoard = board.substring(0, pos) + ball + board.substring(pos);
                String collapsedBoard = collapse(newBoard);
                backtrack(collapsedBoard, newHand, insertions + 1);
            }
        }
    }

    private String collapse(String board) {
        int i = 0;
        while (i < board.length()) {
            int j = i;
            while (j < board.length() && board.charAt(j) == board.charAt(i)) {
                j++;
            }

            if (j - i >= 3) {
                String newBoard = board.substring(0, i) + board.substring(j);
                return collapse(newBoard);
            } else {
                i = j;
            }
        }

        return board;
    }
}