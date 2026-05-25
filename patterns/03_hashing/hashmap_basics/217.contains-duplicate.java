
import java.util.HashSet;

/*
 * @lc app=leetcode id=217 lang=java
 *
 * [217] Contains Duplicate
 */

// @lc code=start
class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int i: nums) {
            if (set.contains(i)) {
                return true;
            }

            set.add(i);
        }

        return false;
    }
}
// @lc code=end

