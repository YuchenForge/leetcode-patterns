
import java.util.*;

// @lc code=start
class Solution {
    private static final double EPSILON = 1e-6;

    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (int c: cards) {
            nums.add((double) c);
        }

        return backtrack(nums);
    }

    private boolean backtrack(List<Double> nums) {
        // Base case
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24.0) < EPSILON;
        }

        int n = nums.size();
        // Try every pait (i, j) of numbers to combine
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                // Build the remaining list (everything except i and j)
                List<Double> next = new ArrayList<>();
                for (int index = 0; index < n; index++) {
                    if (index != i && index != j) {
                        next.add(nums.get(index));
                    }
                }

                double a = nums.get(i);
                double b = nums.get(j);

                // Try all operators
                List<Double> candidates = new ArrayList<>();
                candidates.add(a + b);
                candidates.add(a - b);
                candidates.add(a * b);
                if (Math.abs(b) > EPSILON) {
                    candidates.add(a / b);
                }

                for (double result: candidates) {
                    next.add(result);
                    if (backtrack(next)) {
                        return true;
                    }
                    // backtrack
                    next.remove(next.size() - 1);
                }
            }
        }

        return false;
    }
}
// @lc code=end

