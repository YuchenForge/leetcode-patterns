/*
 * @lc app=leetcode id=2104 lang=java
 *
 * [2104] Sum of Subarray Ranges
 */

import java.util.*;

// @lc code=start
class Solution {
    public long subArrayRanges(int[] nums) {
        return sumSubarrayMaxs(nums) - sumSubarrayMins(nums);
    }

    public long sumSubarrayMins(int[] arr) {
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

        return result;


    }

    public long sumSubarrayMaxs(int[] arr) {
        Deque<Integer> stackLeft = new ArrayDeque<>();
        Deque<Integer> stackRight = new ArrayDeque<>();

        long result = 0;

        int n = arr.length;

        // # of start position possible extending left while nums[i] stays max
        int[] left = new int[n];
        // # of start position possible extending right while nums[i] stays max
        int[] right = new int[n];

        // previous smaller
        for (int i = 0; i < n; i++) {
            while (!stackLeft.isEmpty() && arr[stackLeft.peek()] < arr[i]) {
                stackLeft.pop();
            }

            left[i] = stackLeft.isEmpty()? (i + 1): (i - stackLeft.peek());
            stackLeft.push(i);
        }

        for (int i = n - 1; i >= 0; i--) {
            while (!stackRight.isEmpty() && arr[stackRight.peek()] <= arr[i]) {
                stackRight.pop();
            }

            right[i] = stackRight.isEmpty()? (n - i): (stackRight.peek() - i);
            stackRight.push(i);
        }

        for (int i = 0; i < n; i++) {
            result += (long) arr[i] * left[i] * right[i];
        }

        return result;


    }
}

// Time: O(n)
// Space: O(n)
// @lc code=end

// sum of ranges = 
// sum of subarray maximums − sum of subarray minimums

// How much each number contributes as a maximum  - How much it contributes as a minimum

// We find:
// nearest greater/smaller element on left
// nearest greater/smaller element on right

// total subarrays = left × right (inclusive of current)
