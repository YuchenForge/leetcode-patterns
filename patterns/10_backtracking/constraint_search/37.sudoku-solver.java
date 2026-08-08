/*
 * @lc app=leetcode id=37 lang=java
 *
 * [37] Sudoku Solver
 */

// @lc code=start
class Solution {

    private boolean[][] rowsUsed = new boolean[9][10];
    private boolean[][] colsUsed = new boolean[9][10];
    private boolean[][] boxUsed = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    for (int n = 1; n <= 9; n++) {
                        int num = board[i][j] - '0';
                        int box = (i / 3) * 3 + (j / 3);
                        
                        rowsUsed[i][num] = true;
                        colsUsed[j][num] = true;
                        boxUsed[box][num] = true;
                    }
                }
            }
        }

        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int row, int col) {
        // Move to next row when we pass the last column
        if (col == 9) {
            return backtrack(board, row + 1, 0);
        }

        // All rows processed successfully
        if (row == 9) {
            return true;
        }

        // Skip already-filled cells
        if (board[row][col] != '.') {
            return backtrack(board, row, col + 1);
        }

        int box = (row / 3) * 3 + (col / 3);
        for (int num = 1; num <= 9; num++) {
            if (!rowsUsed[row][num] && !colsUsed[col][num] && !boxUsed[box][num]) {
                place(board, row, col, box, num, true);

                if (backtrack(board, row, col + 1)) {
                    return true;
                }

                // Undo (backtrack)
                place(board, row, col, box, num, false);
            }
        }

        return false;
    }

    private void place(char[][] board, int row, int col, int box, int num, boolean isPlacing) {
        // Decide place or remove
        board[row][col] = isPlacing ? (char) ('0' + num) : '.';
        rowsUsed[row][num] = isPlacing;
        colsUsed[col][num] = isPlacing;
        boxUsed[box][num] = isPlacing;
    }
}
// @lc code=end

// Time: O(9^m)
// Space: O(1)
