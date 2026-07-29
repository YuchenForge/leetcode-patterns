/*
 * @lc app=leetcode id=1203 lang=java
 *
 * [1203] Sort Items by Groups Respecting Dependencies
 */

import java.util.*;
import java.util.stream.Collectors;

// @lc code=start
class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // Assign groups
        int index = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = index;
                index++;
            }
        }

        // Build graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            for (int pre : beforeItems.get(i)) {
                graph.get(pre).add(i);   // pre -> i
                indegree[i]++;
            }
        }

        // Group graph
        int groupCount = index;
        List<HashSet<Integer>> groupGraph = new ArrayList<>();

        for (int i = 0; i < groupCount; i++) {
            groupGraph.add(new HashSet<>());
        }

        int[] groupIndegree = new int[groupCount];

        for (int i = 0; i < n; i++) {
            for (int pre : beforeItems.get(i)) {

                int fromGroup = group[pre];
                int toGroup = group[i];

                if (fromGroup != toGroup) {
                    // check duplicates
                    if (!groupGraph.get(fromGroup).contains(toGroup)) {
                        // process fromGroup first
                        groupGraph.get(fromGroup).add(toGroup);
                        groupIndegree[toGroup]++;
                    }
                }
            }
        }

        // Topo Sort
        List<Integer> itemOrder = topoSort(graph, indegree, n);
        // convert List<HashSet<Integer>> to List<List<Integer>>
        List<Integer> groupOrder = topoSort(groupGraph.stream()
            .map(ArrayList::new)
            .collect(Collectors.toList()), groupIndegree, groupCount);

        if (itemOrder.isEmpty() || groupOrder.isEmpty()) {
            return new int[0]; 
        }

        // Bucket sorted items by group
        HashMap<Integer, List<Integer>> groupedItems = new HashMap<>();
        for (int item : itemOrder) {
            groupedItems.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        // Concatenate group buckets
        int[] result = new int[n];
        int idx = 0;
        for (int g: groupOrder) {
            List<Integer> items = groupedItems.getOrDefault(g, new ArrayList<>());
            for (int item: items) {
                result[idx++] = item;
            }
        }

        return result;

    }

    private List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int numNodes) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numNodes; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int neighbor : graph.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // empty = cycle detected
        return result.size() == numNodes ? result : new ArrayList<>(); 
    }
}
// @lc code=end

// Time: O(n + m + E)
// Space: O(n + m + E)

// n = number of items
// m = number of groups
// E = total number of dependency pairs across all of beforeItems