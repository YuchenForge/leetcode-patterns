/*
 * @lc app=leetcode id=220 lang=java
 *
 * [220] Contains Duplicate III
 */

import java.util.HashMap;

// @lc code=start
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        HashMap<Long, Long> buckets = new HashMap<>();

        long bucketSize = (long) valueDiff + 1;

        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];
            long bucketID = num >= 0
                    ?  num / bucketSize
                    : ((num + 1) / bucketSize) - 1;

            if (buckets.containsKey(bucketID)) {
                return true;
            }
            if (buckets.containsKey(bucketID + 1)) {
                if (Math.abs(num - buckets.get(bucketID+1)) <= valueDiff) {
                    return true;
                }
            }
            if (buckets.containsKey(bucketID - 1)) {
                if (Math.abs(num - buckets.get(bucketID-1)) <= valueDiff) {
                    return true;
                }
            }
            
            buckets.put(bucketID, num);

            if (i >= indexDiff) {
                long oldNum = nums[i - indexDiff];
                long oldBucket = oldNum >= 0
                        ? oldNum / bucketSize
                        : (oldNum + 1) / bucketSize - 1;

                buckets.remove(oldBucket);
            }
        }

        return false;
        
    }
}
// @lc code=end


/**
 * slide window
 * window size indexDiff
 * remove i - indexDiff to maintain window
 * 
 * Group into bucket
 * bucket size = valueDiff + 1
 * bucket id = num / bucket size
 * check current bucket and neighbor bucket
 * a pair cannot be more than 1 bucket apart 
 * 
 * int bucketId = num >= 0 ? num / size : (num + 1) / size - 1;
 */