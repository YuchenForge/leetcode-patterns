
import java.util.HashSet;

/*
 * @lc app=leetcode id=128 lang=java
 *
 * [128] Longest Consecutive Sequence
 */

// @lc code=start
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int max = 0;

        for (int num : nums) {
            set.add(num);
        }

        for (int num: set) {
            if (!set.contains(num-1)) {
                int cur = num;
                int streak = 1;
                while (set.contains(cur + 1)) {
                    cur++;
                    streak++;
                }
                max = Math.max(max, streak);
            }
        }

        return max;
    }
}
// @lc code=end

