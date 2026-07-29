/*
 * @lc app=leetcode id=126 lang=java
 *
 * [126] Word Ladder II
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);

        List<List<String>> list = new ArrayList<>();
        if(!set.contains(endWord)) {
            return list;
        }

        HashMap<String, List<String>> parents = new HashMap<>();

        Deque<String> queue = new ArrayDeque<>();
        queue.add(beginWord);

        HashSet<String> visited = new HashSet<>();
        visited.add(beginWord);

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            HashSet<String> levelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                for(int j = 0; j < word.length(); j++){
                    char[] arr = word.toCharArray();

                    for(char c = 'a'; c <= 'z'; c++){
                        if(arr[j] == c) {
                            continue;
                        }

                        arr[j] = c;

                        String str = new String(arr);
                        if (!set.contains(str)) {
                            continue;
                        }

                        if (!visited.contains(str)) {
                            if (!levelVisited.contains(str)) {
                                queue.offer(str);
                                levelVisited.add(str);
                            }

                            parents.computeIfAbsent(str, x -> new ArrayList<>())
                                .add(word);
                        }

                        if (str.equals(endWord)) {
                            found = true;
                        }
                    }
                }
            }

            visited.addAll(levelVisited);
        }

        if (!found) {
            return list;
        }

        List<String> path = new ArrayList<>();
        path.add(endWord);

        backtrack(endWord, beginWord, parents, path, list);

        return list;
    }

    private void backtrack(String word, String beginWord,
        HashMap<String, List<String>> parents,
        List<String> path, List<List<String>> result) {
        
        if (word.equals(beginWord)) {
            List<String> answer = new ArrayList<>(path);
            Collections.reverse(answer);
            result.add(answer);
            return;
        }

        if (!parents.containsKey(word)) {
            return;
        }

        for (String parent : parents.get(word)) {
            path.add(parent);
            backtrack(parent, beginWord, parents, path, result);
            path.remove(path.size() - 1);
        }
    }
}
// @lc code=end

