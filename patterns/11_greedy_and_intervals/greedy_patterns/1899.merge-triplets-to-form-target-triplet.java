/*
 * @lc app=leetcode id=1899 lang=java
 *
 * [1899] Merge Triplets to Form Target Triplet
 */

// @lc code=start
class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        boolean x = false, y = false, z = false;

        for (int[] triplet: triplets) {
            if (triplet[0] > target[0] 
                || triplet[1] > target[1] 
                || triplet[2] > target[2]) {
                continue;
            }

            if (triplet[0] == target[0]) x = true;
            if (triplet[1] == target[1]) y = true;
            if (triplet[2] == target[2]) z = true;
        }

        return x && y && z;
    }
}
// @lc code=end

