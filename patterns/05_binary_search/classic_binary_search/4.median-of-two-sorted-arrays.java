/*
 * @lc app=leetcode id=4 lang=java
 *
 * [4] Median of Two Sorted Arrays
 */

// @lc code=start
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // binary search on smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int n1 = nums1.length;
        int n2 = nums2.length;

        int lo = 0, hi = n1;

        while (lo <= hi) {
            int cut1 = lo + (hi - lo) / 2;
            int cut2 = (n1 + n2 + 1) / 2 - cut1;

            int left1  = (cut1 == 0)? Integer.MIN_VALUE: nums1[cut1 - 1];
            int right1 = (cut1 == n1)? Integer.MAX_VALUE: nums1[cut1];
            int left2  = (cut2 == 0)? Integer.MIN_VALUE: nums2[cut2 - 1];
            int right2 = (cut2 == n2)? Integer.MAX_VALUE: nums2[cut2];

            if (left1 <= right2 && left2 <= right1) {
                int L = Math.max(left1, left2);
                int R = Math.min(right1, right2);
                if ((n1 + n2) % 2 == 0) {
                    return (L + R) / 2.0; // even 
                } else {
                    return L; // odd 
                }
            }
            else if (left1 > right2) {
                hi = cut1 - 1;
            }
            else {
                lo = cut1 + 1;
            }
        }

        return 0.0;
    }
}

// Time: O(log n)
// Space: O(1)
// @lc code=end

/**
nums1: [1, 3, 5, 7]
nums2: [2, 4, 6, 8]

cut nums1 after index 1:  [1, 3 | 5, 7]
cut nums2 after index 1:  [2, 4 | 6, 8]

left half:  [1, 3, 2, 4]  → max = 4
right half: [5, 7, 6, 8]  → min = 5

median = (4 + 5) / 2 = 4.5 ✓


Valid Cut:
nums1[cut1-1] <= nums2[cut2]   
(left of nums1 ≤ right of nums2)

nums2[cut2-1] <= nums1[cut1]   
(left of nums2 ≤ right of nums1)

 */
