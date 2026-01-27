public class SlidingWindowTemplate {
    public static int solve(int[] nums) {
        int left = 0;
        int ans = 0;

        for (int right = 0; right < nums.length; right++) {
            // add nums[right]

            while (false) { // condition broken
                // remove nums[left]
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}

