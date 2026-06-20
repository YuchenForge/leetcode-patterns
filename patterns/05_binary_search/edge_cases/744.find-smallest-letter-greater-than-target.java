/*
 * @lc app=leetcode id=744 lang=java
 *
 * [744] Find Smallest Letter Greater Than Target
 */

// @lc code=start
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        char res = letters[0];

        int lo = 0;
        int hi = letters.length - 1;
        
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (letters[mid] == target) {
                lo = mid + 1;
            } else if (letters[mid] < target) {
                lo = mid + 1;
            } else {
                res = letters[mid];
                hi = mid - 1;
            }
        }
        return res;
    }
}

// Time: O(log n)
// Space: O(1)
// @lc code=end

