/*
 * @lc app=leetcode id=127 lang=java
 *
 * [127] Word Ladder
 */

import java.util.*;

// @lc code=start
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;

        Deque<String> queue = new ArrayDeque<>();
        queue.add(beginWord);

        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);

        int changes = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return changes;
                }

                for(int j = 0; j < word.length(); j++){
                    for(int k = 'a'; k <= 'z'; k++){
                        char[] arr = word.toCharArray();
                        arr[j] = (char) k;

                        String str = new String(arr);
                        if (set.contains(str) && !visited.contains(str)) {
                            queue.add(str);
                            visited.add(str);
                        }
                    }
                }
            }
            
            changes++;
        }

        return 0;
    }
}
// @lc code=end

// Time: O(M^2 * N), where M is size of dequeued word and N is size of word list
// Space: O(M * N)
