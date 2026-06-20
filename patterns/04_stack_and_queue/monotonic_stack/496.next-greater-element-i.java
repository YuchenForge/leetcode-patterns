/*
 * @lc app=leetcode id=496 lang=java
 *
 * [496] Next Greater Element I
 */

import java.util.*;

// @lc code=start
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] res = new int[n1];
        Arrays.fill(res, -1);

        Deque<Integer> stack = new ArrayDeque<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n2; i++) {

            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                map.put(nums2[stack.pop()], nums2[i]);
            }

            stack.push(i);
        }

        for (int j = 0; j < n1; j++) {
            if (map.containsKey(nums1[j])) {
                res[j] = map.get(nums1[j]);
            }
        }

        return res;
    }
}

// Time: O(n + m)
// Space: O(m)
// @lc code=end

// When you encounter a number that is greater than the top of the stack
// that number is the next greater element for everything it's greater than
