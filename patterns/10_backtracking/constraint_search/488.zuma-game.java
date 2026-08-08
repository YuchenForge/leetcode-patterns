/*
 * @lc app=leetcode id=488 lang=java
 *
 * [488] Zuma Game
 */

import java.util.Arrays;

// @lc code=start
class Solution {

    private int minInsertions = Integer.MAX_VALUE;

    public int findMinStep(String board, String hand) {
        char[] handArr = hand.toCharArray();
        Arrays.sort(handArr);

        backtrack(board, new String(handArr), 0);

        return minInsertions == Integer.MAX_VALUE ? -1 : minInsertions; 
    }

    private void backtrack(String board, String remainingHand, int insertions) {

    }

    private String collapse(String board) {

    }
}
// @lc code=end

