/*
Alien Dictionary

There is a new alien language that uses the English alphabet, but the order 
of the letters is unknown.

You are given a list of strings words from the alien language's dictionary. 
It is claimed that the strings in words are sorted lexicographically by the 
rules of this new language.

If this claim is incorrect, and the given arrangement of strings in words cannot 
correspond to any order of letters, return "".

Otherwise, return a string of the unique letters in the new alien language sorted 
in lexicographically increasing order by the new language's rules. If there are 
multiple solutions, return any of them.

A string a is lexicographically smaller than a string b if either of the following 
is true:

The first letter where they differ is smaller in a than in b.
a is a prefix of b and a.length < b.length.

Example 1:

Input: words = ["z","o"]

Output: "zo"
Explanation:
From "z" and "o", we know 'z' < 'o', so return "zo".


Example 2:

Input: words = ["hrn","hrf","er","enn","rfnn"]

Output: "hernf"
Explanation:

from "hrn" and "hrf", we know 'n' < 'f'
from "hrf" and "er", we know 'h' < 'e'
from "er" and "enn", we know 'r' < 'n'
from "enn" and "rfnn" we know 'e' < 'r'
so one possible solution is "hernf"

Example 3:

Input: words = ["abc","ab"]

Output: ""
Explanation:
The second word is a prefix of the first word, but the first word appears before 
the second. This is impossible in a valid lexicographical ordering, so return "".


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of only lowercase English letters.

*/

import java.util.*;

class Solution {
    public String foreignDictionary(String[] words) {
        // Build graph
        List<List<Character>> graph = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[26];

        for (int i = 0; i < words.length - 1; i++) {
            String firstWord = words[i];
            String secondWord = words[i+1];

            char[] chars1 = firstWord.toCharArray();
            char[] chars2 = secondWord.toCharArray();

            int minLen = Math.min(chars1.length, chars2.length);
            boolean foundDiff = false;
            for (int j = 0; j < minLen; j++) {
                if (chars1[j] != chars2[j]) {
                    char pre = chars1[j];
                    char c = chars2[j];
                    if (!graph.get(pre - 'a').contains(c)) {
                        graph.get(pre - 'a').add(c);
                        indegree[c - 'a']++;
                    }
                    foundDiff = true;
                    break;
                }
            }
            if (!foundDiff && chars1.length > chars2.length) {
                return ""; // invalid prefix order
            }
        }

        HashSet<Character> uniqueLetters = new HashSet<>();
        for (String w : words) {
            for (char c : w.toCharArray()) {
                uniqueLetters.add(c);
            }
        }

        Deque<Character> queue = new ArrayDeque<>();
        for (char c : uniqueLetters) {
            if (indegree[c - 'a'] == 0) {
                queue.offer(c);
            }
        }

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {
            char cur = queue.poll();
            result.append(cur);

            for (char next: graph.get(cur - 'a')) {
                indegree[next - 'a']--;
                if (indegree[next - 'a'] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (result.length() != uniqueLetters.size()) {
            return ""; // cycle detected 
        }

        return result.toString();
    }
}



