/*
 * @lc app=leetcode id=846 lang=java
 *
 * [846] Hand of Straights
 */

import java.util.*;

// @lc code=start
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }

        Arrays.sort(hand);

        HashMap<Integer, Integer> count = new HashMap<>();
        for (int card: hand) {
            count.put(card, count.getOrDefault(card, 0) + 1);
        }

        for (int card: hand) {
            if (count.get(card) == 0) {
                continue;
            }

            int need = count.get(card);

            for (int i = 0; i < groupSize; i++) {
                int next = card + i;
                if (count.getOrDefault(next, 0) < need) {
                    return false;
                }
                count.put(next, count.get(next) - need);
            }
        }

        return true;
    }
}
// @lc code=end

