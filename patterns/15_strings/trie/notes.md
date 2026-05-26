# Pattern: trie

## When to Use
- Prefix search, autocomplete, word dictionary.
- **Word search II** — prune with trie.
- XOR maximum with prefixes (bit trie).

## Core Idea
- Each node: `children[26]` + `endOfWord` flag (or count).
- Insert O(L), search/prefix O(L).

## Template
```java
class TrieNode {
    TrieNode[] next = new TrieNode[26];
    boolean end;
}
class Trie {
    TrieNode root = new TrieNode();
    void insert(String w) {
        TrieNode cur = root;
        for (char c : w.toCharArray()) {
            int i = c - 'a';
            if (cur.next[i] == null) cur.next[i] = new TrieNode();
            cur = cur.next[i];
        }
        cur.end = true;
    }
    boolean searchPrefix(String p) {
        TrieNode cur = root;
        for (char c : p.toCharArray()) {
            int i = c - 'a';
            if (cur.next[i] == null) return false;
            cur = cur.next[i];
        }
        return true;
    }
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Insert/search L | O(L) | O(nodes) |
| Prefix search | O(L) | O(1) |

## Related Patterns
- String basics
- Backtracking word search II
