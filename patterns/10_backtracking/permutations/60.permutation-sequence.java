/*
 * @lc app=leetcode id=60 lang=java
 *
 * [60] Permutation Sequence
 */

import java.util.*;

// @lc code=start
class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i-1] * i;
        }

        k--; // convert k to zero-based indexing

        StringBuilder result = new StringBuilder();

        for (int remaining = n; remaining > 0; remaining--) {
            int blockSize = factorial[remaining - 1];
            int index = k / blockSize;

            result.append(nums.get(index));
            nums.remove(index);

            k %= blockSize;
        }

        return result.toString();
    }
}
// @lc code=end

/*
1 → permutations 1–6
2 → permutations 7–12
3 → permutations 13–18
4 → permutations 19–24
*/

// Time: O(n^2)
// Space: O(n)