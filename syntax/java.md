# Java Cheat Sheet

## Variables & Types

```java
int x = 10;
double y = 3.14;
char c = 'a';
boolean flag = true;
String s = "hello";
```

## Input / Output

```java
import java.util.*;

Scanner sc = new Scanner(System.in);
int x = sc.nextInt();
String s = sc.nextLine();

System.out.println(x);
System.out.printf("%d %s\n", x, s);
```

## Arrays

```java
int[] arr = new int[5];
int[] nums = {1,2,3};

Arrays.sort(nums);
Arrays.fill(arr, 0);
```

## ArrayList

```java
ArrayList<Integer> list = new ArrayList<>();

list.add(1);
list.get(0);
list.set(0, 5);
list.remove(0);
list.size();
```

## Loops

```java
for (int i = 0; i < 10; i++) {}

for (int x : arr) {}

while (x > 0) {}
```

## Conditionals

```java
if (x > 0) {

} else if (x == 0) {

} else {

}
```

## Methods

```java
public static int add(int a, int b) {
    return a + b;
}
```

## Classes

```java
class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    void greet() {
        System.out.println(name);
    }
}
```

## Inheritance

```java
class Animal {
    void speak() {}
}

class Dog extends Animal {
    @Override
    void speak() {
        System.out.println("woof");
    }
}
```

## Interfaces

```java
interface Shape {
    double area();
}
```

## Exception Handling

```java
try {
    int x = 1 / 0;
} catch (Exception e) {
    e.printStackTrace();
} finally {
    System.out.println("done");
}
```

## HashMap

```java
HashMap<String, Integer> map = new HashMap<>();

map.put("a", 1);
map.get("a");
map.getOrDefault("x", 0);
map.containsKey("a");
map.remove("a");
```

## HashSet

```java
HashSet<Integer> set = new HashSet<>();

set.add(1);
set.contains(1);
set.remove(1);
```

## Queue / Stack / Deque

```java
Queue<Integer> q = new LinkedList<>();
q.offer(1);
q.poll();

Stack<Integer> st = new Stack<>();
st.push(1);
st.pop();

Deque<Integer> dq = new ArrayDeque<>();
dq.addFirst(1);
dq.addLast(2);
```

## PriorityQueue (Heap)

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

## Sorting

```java
Arrays.sort(arr);

Collections.sort(list);

list.sort((a, b) -> a - b);
```

## String Methods

```java
s.length();
s.charAt(0);
s.substring(1, 3);
s.indexOf("a");
s.contains("ab");
s.split(" ");
```

## StringBuilder

```java
StringBuilder sb = new StringBuilder();
sb.append("abc");
sb.reverse();
sb.toString();
```

## Generics

```java
class Box<T> {
    T value;
}
```

## Lambda

```java
list.forEach(x -> System.out.println(x));
```

## Streams

```java
list.stream()
    .filter(x -> x % 2 == 0)
    .map(x -> x * 2)
    .forEach(System.out::println);
```

## File I/O

```java
import java.io.*;

BufferedReader br = new BufferedReader(new FileReader("file.txt"));
BufferedWriter bw = new BufferedWriter(new FileWriter("file.txt"));
```

## Useful Imports

```java
import java.util.*;
import java.io.*;
```

## Competitive Programming Fast IO

```java
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
StringTokenizer st = new StringTokenizer(br.readLine());
```

---
