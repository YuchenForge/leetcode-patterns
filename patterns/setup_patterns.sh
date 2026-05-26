#!/usr/bin/env bash

# helper to create subtopic + notes.md
create_subtopic () {
  dir="$1/$2"
  mkdir -p "$dir"
  cat > "$dir/notes.md" <<EOF
# Pattern: ${2//_/ }

## When to Use
- 

## Core Idea
- 

## Template
\`\`\`java
// write template here
\`\`\`

## Common Variations
- 

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| | | |

## Related Patterns
- 
EOF
}

# 01_arrays_and_prefix
create_subtopic 01_arrays_and_prefix prefix_sum
create_subtopic 01_arrays_and_prefix difference_array
create_subtopic 01_arrays_and_prefix basic_array_tricks

# 02_two_pointers_and_windows
create_subtopic 02_two_pointers_and_windows two_pointers
create_subtopic 02_two_pointers_and_windows sliding_window
create_subtopic 02_two_pointers_and_windows fast_slow_pointers

# 03_hashing
create_subtopic 03_hashing hashmap_basics
create_subtopic 03_hashing frequency_counting
create_subtopic 03_hashing set_usage

# 04_stack_and_queue
create_subtopic 04_stack_and_queue stack_basics
create_subtopic 04_stack_and_queue monotonic_stack
create_subtopic 04_stack_and_queue queue_and_deque

# 05_binary_search
create_subtopic 05_binary_search classic_binary_search
create_subtopic 05_binary_search binary_search_on_answer
create_subtopic 05_binary_search edge_cases

# 06_heap
create_subtopic 06_heap top_k
create_subtopic 06_heap kth_element
create_subtopic 06_heap streaming_median

# 07_linked_list (cycle detection → 02 fast_slow_pointers)
create_subtopic 07_linked_list pointer_manipulation
create_subtopic 07_linked_list reversal_patterns

# 08_trees
create_subtopic 08_trees tree_traversal
create_subtopic 08_trees bst
create_subtopic 08_trees path_problems
create_subtopic 08_trees tree_recursion

# 09_graphs
create_subtopic 09_graphs bfs
create_subtopic 09_graphs dfs
create_subtopic 09_graphs topological_sort

# 10_backtracking
create_subtopic 10_backtracking subsets
create_subtopic 10_backtracking permutations
create_subtopic 10_backtracking constraint_search

# 11_greedy_and_intervals
create_subtopic 11_greedy_and_intervals interval_scheduling
create_subtopic 11_greedy_and_intervals merge_intervals
create_subtopic 11_greedy_and_intervals greedy_patterns

# 12_dynamic_programming
create_subtopic 12_dynamic_programming dp_1d
create_subtopic 12_dynamic_programming dp_2d
create_subtopic 12_dynamic_programming knapsack

# 13_advanced_graphs
create_subtopic 13_advanced_graphs union_find
create_subtopic 13_advanced_graphs shortest_path
create_subtopic 13_advanced_graphs minimum_spanning_tree

# 14_advanced_dp
create_subtopic 14_advanced_dp bitmask_dp
create_subtopic 14_advanced_dp interval_dp
create_subtopic 14_advanced_dp tree_dp

# 15_strings
create_subtopic 15_strings string_basics
create_subtopic 15_strings trie
create_subtopic 15_strings pattern_matching

# 16_math_and_misc
create_subtopic 16_math_and_misc combinatorics
create_subtopic 16_math_and_misc probability
create_subtopic 16_math_and_misc bit_manipulation

# 17_design (LRU/LFU → 03_hashing/key_design)
create_subtopic 17_design data_structure_design
create_subtopic 17_design system_design_basics

echo "✅ All folders and notes.md files created."
