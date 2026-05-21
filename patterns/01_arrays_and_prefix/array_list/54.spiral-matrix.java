/*
 * @lc app=leetcode id=54 lang=java
 *
 * [54] Spiral Matrix
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> output = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return output;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // right
            for (int i = left; i <= right; i++) {
                output.add(matrix[top][i]);
            }

            top++;

            // down
            for (int i = top; i <= bottom; i++) {
                output.add(matrix[i][right]);
            }
            
            right--;

            // left
            // avoid double count for single row matrix since top updated
            if (top <= bottom) { 
                for (int i = right; i >= left; i--) {
                    output.add(matrix[bottom][i]);
                }
                
                bottom--;
            }

            // up
            if (left <= right) { 
                for (int i = bottom; i >= top; i--) {
                    output.add(matrix[i][left]);
                }
                
                left++;
            }
        }

        return output;
    }
}
// @lc code=end

