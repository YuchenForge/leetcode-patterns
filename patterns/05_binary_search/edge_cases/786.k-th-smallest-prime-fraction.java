/*
 * @lc app=leetcode id=786 lang=java
 *
 * [786] K-th Smallest Prime Fraction
 */

// @lc code=start
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        double lo = 0; 
        double hi = 1.0;
        
        while (lo < hi) {
            double mid = (lo + hi) / 2;
            
            double maxFraction = 0.0;
            int total = 0;
            int p = 0;
            int q = 0;

            int j = 1;
        
            for (int i = 0; i < n - 1; i++) {
                while (j < n && arr[i] >= mid * arr[j]) {
                    j++;
                }

                total += (n - j);

                if (j == n) break;

                double fraction = (double) arr[i] / arr[j];

                if (fraction > maxFraction) {
                    p = i;
                    q = j;
                    maxFraction = fraction;
                }
            }

            if (total == k) {
                return new int[]{arr[p], arr[q]};
            } else if (total > k) {
                hi = mid; 
            } else {
                lo = mid; 
            }
        }
        return new int[]{}; 
    }
}
// @lc code=end


// arr[i] / arr[j] <= mid is same as arr[i] <= mid * arr[j]
// for each j, find largest i where arr[i] / arr[j] <= mid

// Time:  O(n log(1/ε))
// Space: O(1)