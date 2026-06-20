# Pattern: string basics

## When to Use
- **Two pointers** on string — palindrome, reverse, compare.
- **Character frequency** — anagram, isomorphic.
- **Parsing** — split, strip, atoi, roman numerals.
- Building result with `StringBuilder` (not `+` in loops).

## Core Idea
- Strings immutable in Java — use `StringBuilder` for O(n) build.
- `char[] s = str.toCharArray()` for in-place style edits.
- ASCII: `int[26]` or `int[128]` faster than `HashMap<Character>`.

## Template
```java
boolean isPalindrome(String s) {
    int l = 0, r = s.length() - 1;
    while (l < r) {
        while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
        while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
        if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r)))
            return false;
        l++; r--;
    }
    return true;
}
```

## Complexity
| Variant | Time | Space |
|---------|------|-------|
| Scan string | O(n) | O(1) or O(alphabet) |

## Related Patterns
- Sliding window
- Pattern matching DP
