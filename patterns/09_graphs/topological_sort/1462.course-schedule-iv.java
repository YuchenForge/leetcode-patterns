/*
 * @lc app=leetcode id=1462 lang=java
 *
 * [1462] Course Schedule IV
 */

import java.util.*;

// @lc code=start
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // Build graph
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[numCourses];

        for (int[] pre : prerequisites) {
            int prerequisite = pre[0];
            int course = pre[1];

            graph.get(prerequisite).add(course);
            indegree[course]++;
        }

        // prerequisite[i] = all prereq of course i
        HashSet<Integer>[] prereqs = new HashSet[numCourses];
        for (int i = 0; i < numCourses; i++) {
            prereqs[i] = new HashSet<>();
        }

        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            
            for (int next: graph.get(cur)) {
                prereqs[next].add(cur);
                prereqs[next].addAll(prereqs[cur]);
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        List<Boolean> answer = new ArrayList<>();

        for (int[] query: queries) {
            int u = query[0];
            int v = query[1];

            answer.add(prereqs[v].contains(u));
        }

        return answer;
    }
}
// @lc code=end


// Time: O(V + E)
// Space: O(V + E)