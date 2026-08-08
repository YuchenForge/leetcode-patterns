/*
 * @lc app=leetcode id=51 lang=java
 *
 * [51] N-Queens
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();

        // queens[row] = column of queen in that row
        int[] queens = new int[n];
        boolean[] colsUsed = new boolean[n];
        // (/) row + col
        boolean[] diag1Used = new boolean[2*n - 1];   
        // (\) row - col
        boolean[] diag2Used = new boolean[2*n - 1];

        backtrack(0, n, queens, colsUsed, diag1Used, diag2Used, result);

        return result;
    }

    private void backtrack(int row, int n, int[] queens, 
        boolean[] colsUsed, boolean[] diag1Used, boolean[] diag2Used, 
        List<List<String>> result) {

        if (row == n) {
            List<String> board = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String conf = ".".repeat(n);
                StringBuilder sb = new StringBuilder(conf);
                sb.replace(queens[i], queens[i]+1, "Q");       
                board.add(sb.toString());         
            }
            result.add(board);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (isValid(row, i, n, colsUsed, diag1Used, diag2Used)) {
                queens[row] = i;
                colsUsed[i] = true;
                diag1Used[row + i] = true;
                diag2Used[row - i + (n-1)] = true;
                backtrack(row + 1, n, queens, colsUsed, diag1Used, diag2Used, result);
                queens[row] = -1;
                colsUsed[i] = false;
                diag1Used[row + i] = false;
                diag2Used[row - i + (n-1)] = false;
            }
        }

    }

    private boolean isValid(int row, int col, int n,
        boolean[] colsUsed, boolean[] diag1Used, boolean[] diag2Used) {
        
        return !(colsUsed[col] || diag1Used[row+col] || diag2Used[row-col+(n-1)]);
    }
}
// @lc code=end

// Time: O(n!)
// Space: O(n^2)