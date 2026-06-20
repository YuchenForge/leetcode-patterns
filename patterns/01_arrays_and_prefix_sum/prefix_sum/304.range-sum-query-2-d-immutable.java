/*
 * @lc app=leetcode id=304 lang=java
 *
 * [304] Range Sum Query 2D - Immutable
 */

// @lc code=start
class NumMatrix {

    private int[][] prefix;

    public NumMatrix(int[][] matrix) {
        // prefix[i][j] = sum from [0][0] to [i][j]
        int row = matrix.length;
        int col = matrix[0].length;

        if (matrix.length == 0 || matrix[0].length == 0) return;

        // offset by 1
        prefix = new int[row+1][col+1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                prefix[i][j] = matrix[i-1][j-1] 
                            + prefix[i-1][j] 
                            + prefix[i][j-1] 
                            - prefix[i-1][j-1];
            }
        }
        
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        // offset by 1
        return prefix[row2+1][col2+1] - prefix[row2+1][col1] - prefix[row1][col2+1] + prefix[row1][col1];
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */

// Time: O(m * n)
// Space: O(m * n)
// @lc code=end

