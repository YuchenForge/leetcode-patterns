/*
 * @lc app=leetcode id=278 lang=java
 *
 * [278] First Bad Version
 */

// @lc code=start
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
      
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int lo = 0;
        int hi = n;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isBadVersion(lo) == true && isBadVersion(mid-1) == false) {
                return mid;
            } else if (isBadVersion(mid) == false) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return -1;
    }
}

// Time: O(log n)
// Space: O(1)
// @lc code=end

