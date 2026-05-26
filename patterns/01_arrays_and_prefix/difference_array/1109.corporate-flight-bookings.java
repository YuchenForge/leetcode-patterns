/*
 * @lc app=leetcode id=1109 lang=java
 *
 * [1109] Corporate Flight Bookings
 */

// @lc code=start
class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n + 1];

        for (int[] booking: bookings) {
            int l = booking[0];
            int r = booking[1];
            int val = booking[2];

            diff[l-1] += val;
            if (r < diff.length) {
                diff[r] -= val;
            }
        }

        int[] arr = new int[n];
        int cur = 0;
        for (int i = 0; i < n; i++) {
            cur += diff[i];
            arr[i] = cur;
        }
        return arr;

    }
}
// @lc code=end

