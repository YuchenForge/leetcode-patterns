/*
 * @lc app=leetcode id=119 lang=java
 *
 * [119] Pascal's Triangle II
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<Integer> getRow1(int rowIndex) {
        List<Integer> triangle = new ArrayList<>();

        triangle.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            // add 1 to the end
            triangle.add(1);

            for (int j = i-1; j > 0; j--) {
                // row[j] = row[j] + row[j-1]
                int newValue = triangle.get(j) + triangle.get(j - 1);
                triangle.set(j, newValue);
            }
           
        }

        return triangle;
    }
    // Time Complexity: O(n^2) bc two for loops
    // Space COmplexity: O(n) bc single arraylist to store


    // Optimized Solution

    public List<Integer> getRow(int rowIndex) {
        // Pre-allocating capacity based on the known result size 
        List<Integer> row = new ArrayList<>(rowIndex + 1);
        
        long currentTerm = 1; // use long to prevent multiplication overflow
        
        for (int k = 0; k <= rowIndex; k++) {
            row.add((int)currentTerm);
            // nextTerm = currentTerm * (n - k) / (k + 1)
            currentTerm = currentTerm * (rowIndex - k) / (k + 1);
        }
        
        return row;
    }
    // Time Complexity: O(n) bc one for loops
    // Space COmplexity: O(n) bc single arraylist to store

}

// Time: O(n)
// Space: O(n)
// @lc code=end


