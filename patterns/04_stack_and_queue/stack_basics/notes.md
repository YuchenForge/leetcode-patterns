# Pattern: stack basics

## When to Use
- **Matching / nesting**: parentheses, tags, HTML — last opened must close first (LIFO).
- **Undo / backtrack**: DFS iterative, path reconstruction, monotonic decisions you may reverse.
- **Evaluate in order**: postfix (RPN), calculator tokens, nested expressions.
- **"Previous smaller/larger"** without full scan → often **monotonic stack** (separate topic).
- **Simulate** collisions, file paths, or sequential removals where order matters.

## Core Idea
- Stack = **last in, first out**. Push on enter, pop when done or when matching.
- For validity: push openers; on closer, pop must match.
- For evaluation: operands on stack; operators pop two, push result.
- For path (Unix): stack of directory names; `..` pops, `.` ignored.
- **Mental model**: the stack is your scratchpad for "things I'm not done with yet." Push when you enter scope, pop when you leave or resolve. Non-empty at the end = something left open.

## Gotchas
- Always check `stack.isEmpty()` before popping — a close bracket with an empty stack is invalid, not a match.
- For path problems: `"//a"` splits into empty tokens — filter with `!part.isEmpty()`.
- Prefer `ArrayDeque` over `Stack` in Java — `Stack` extends `Vector` and is synchronized unnecessarily.

## Templates

### Validity / matching
```java
Deque<Character> stack = new ArrayDeque<>();
for (char c : s.toCharArray()) {
    if (isOpen(c)) {
        stack.push(c);
    } else if (isClose(c)) {
        if (stack.isEmpty() || !matches(stack.pop(), c)) return false;
    }
}
return stack.isEmpty();
```

### Min stack
```java
class MinStack {
    Deque<int[]> st = new ArrayDeque<>(); // {val, minSoFar}
    public void push(int x) {
        int m = st.isEmpty() ? x : Math.min(x, st.peek()[1]);
        st.push(new int[]{x, m});
    }
    public void pop()    { st.pop(); }
    public int top()     { return st.peek()[0]; }
    public int getMin()  { return st.peek()[1]; }
}
```

### Postfix / RPN evaluator
```java
Deque<Integer> st = new ArrayDeque<>();
for (String token : tokens) {
    if (isOperator(token)) {
        int b = st.pop(), a = st.pop();
        st.push(apply(a, token, b));
    } else {
        st.push(Integer.parseInt(token));
    }
}
return st.pop();
```

### Unix path simplification
```java
Deque<String> st = new ArrayDeque<>();
for (String part : path.split("/")) {
    if (part.equals("..")) {
        if (!st.isEmpty()) st.pop();
    } else if (!part.isEmpty() && !part.equals(".")) {
        st.push(part);
    }
}
List<String> dirs = new ArrayList<>(st);
Collections.reverse(dirs);
StringBuilder sb = new StringBuilder();
for (String d : dirs) sb.append("/").append(d);
return sb.length() == 0 ? "/" : sb.toString();
```

## Complexity

| Variant | Time | Space |
|---------|------|-------|
| Scan + stack ops | O(n) | O(n) |
| Min stack | O(1) per op | O(n) |
| RPN evaluator | O(n) | O(n) |
| Monotonic stack | O(n) | O(n) |
| DFS iterative | O(V+E) | O(V) |

## Related Patterns
- Monotonic stack — next greater / histogram / trapped rain water
- Queue — BFS, FIFO; `ArrayDeque` doubles as a queue
- Recursion — implicit call stack; convert to iterative when depth is large
- Two stacks — implement a queue, or handle nested structures (counts + strings)