/*
 * @lc app=leetcode id=907 lang=java
 *
 * [907] Sum of Subarray Minimums
 */

import java.util.*;

// @lc code=start
class Solution {
    public int sumSubarrayMins(int[] arr) {
        Deque<Integer> stackLeft = new ArrayDeque<>();
        Deque<Integer> stackRight = new ArrayDeque<>();

        long result = 0;

        int n = arr.length;

        // # of start position possible extending left while nums[i] stays min
        int[] left = new int[n];
        // # of start position possible extending right while nums[i] stays min
        int[] right = new int[n];

        // previous smaller
        for (int i = 0; i < n; i++) {
            while (!stackLeft.isEmpty() && arr[stackLeft.peek()] > arr[i]) {
                stackLeft.pop();
            }

            left[i] = stackLeft.isEmpty()? (i + 1): (i - stackLeft.peek());
            stackLeft.push(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            while (!stackRight.isEmpty() && arr[stackRight.peek()] >= arr[i]) {
                stackRight.pop();
            }

            right[i] = stackRight.isEmpty()? (n - i): (stackRight.peek() - i);
            stackRight.push(i);
        }

        for (int i = 0; i < n; i++) {
            result += (long) arr[i] * left[i] * right[i];
        }

        return (int) (result % 1_000_000_007);


    }
}

// Time: O(n)
// Space: O(n)
// @lc code=end

// How much time each number is contributing as min
// value * number of subarrays

// nearest smaller element on left
// nearest smaller element on right

// total subarrays = left × right (inclusive of current)