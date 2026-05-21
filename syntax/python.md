# Advanced Programming Language Syntax Cheat Sheets

---

# Python Cheat Sheet

## Variables & Types

```python
x = 10                # int
y = 3.14              # float
name = "Alice"       # str
flag = True           # bool
z = None              # NoneType
```

## Type Conversion

```python
int("123")
float("3.14")
str(100)
list("abc")
set([1,2,2])
tuple([1,2])
```

## Input / Output

```python
name = input("Name: ")
age = int(input())

print(name)
print(name, age)
print(f"{name} is {age}")
print("a", "b", sep="-", end="\n")
```

## Arithmetic

```python
+ - * / // % **
```

## Comparison & Logical

```python
== != > < >= <=
and or not
```

## Strings

```python
s = "hello"

s[0]
s[-1]
s[1:4]
s[::-1]

len(s)
s.upper()
s.lower()
s.strip()
s.replace("h", "j")
s.split("e")
"-".join(["a", "b"])

"he" in s
```

## Lists

```python
arr = [1,2,3]

arr.append(4)
arr.extend([5,6])
arr.insert(1, 100)
arr.pop()
arr.pop(0)
arr.remove(2)
arr.sort()
arr.sort(reverse=True)
arr.reverse()

len(arr)
sum(arr)
max(arr)
min(arr)
```

## List Comprehensions

```python
squares = [x*x for x in range(10)]
evens = [x for x in range(10) if x % 2 == 0]
```

## Tuples

```python
point = (1, 2)
a, b = point
```

## Sets

```python
s = {1,2,3}

s.add(4)
s.remove(2)
s.discard(10)

1 in s

A | B   # union
A & B   # intersection
A - B   # difference
```

## Dictionaries

```python
d = {
    "a": 1,
    "b": 2
}

d["a"]
d.get("x", 0)

d["c"] = 3

d.pop("a")

for k, v in d.items():
    print(k, v)
```

## Conditionals

```python
if x > 0:
    print("positive")
elif x == 0:
    print("zero")
else:
    print("negative")
```

## Loops

```python
for i in range(5):
    print(i)

for i in range(1, 10, 2):
    print(i)

while x > 0:
    x -= 1
```

## Enumerate / Zip

```python
for idx, val in enumerate(arr):
    pass

for a, b in zip(list1, list2):
    pass
```

## Functions

```python
def add(a, b=0):
    return a + b

lambda_func = lambda x: x * 2
```

## *args / **kwargs

```python
def func(*args, **kwargs):
    print(args)
    print(kwargs)
```

## Classes

```python
class Person:
    species = "human"

    def __init__(self, name):
        self.name = name

    def greet(self):
        return f"Hi {self.name}"

p = Person("Alice")
```

## Inheritance

```python
class Animal:
    def speak(self):
        pass

class Dog(Animal):
    def speak(self):
        return "woof"
```

## Exception Handling

```python
try:
    x = 1 / 0
except ZeroDivisionError:
    print("error")
finally:
    print("done")
```

## File I/O

```python
with open("file.txt", "r") as f:
    data = f.read()

with open("file.txt", "w") as f:
    f.write("hello")
```

## Modules

```python
import math
from collections import defaultdict
from heapq import heappush, heappop
```

## Useful Standard Libraries

```python
collections
heapq
bisect
itertools
functools
math
random
re
os
sys
```

## Regex

```python
import re

re.search(r"abc", s)
re.findall(r"\d+", s)
re.sub(r"a", "b", s)
```

## Itertools

```python
from itertools import permutations, combinations

permutations([1,2,3])
combinations([1,2,3], 2)
```

## Heap

```python
import heapq

heap = []
heapq.heappush(heap, 3)
heapq.heappop(heap)
```

## DefaultDict / Counter

```python
from collections import defaultdict, Counter

mp = defaultdict(int)
cnt = Counter(arr)
```

## Deque

```python
from collections import deque

dq = deque()
dq.append(1)
dq.appendleft(2)
dq.pop()
dq.popleft()
```

## Sorting

```python
arr.sort()
arr.sort(key=lambda x: x[1])
sorted(arr, reverse=True)
```

## Generators

```python
def gen():
    yield 1
    yield 2
```

## Decorators

```python
def decorator(func):
    def wrapper():
        return func()
    return wrapper
```

## Async

```python
import asyncio

async def main():
    await asyncio.sleep(1)

asyncio.run(main())
```

## Typing

```python
from typing import List, Dict

def func(nums: List[int]) -> int:
    return sum(nums)
```

## Useful Idioms

```python
# swap
a, b = b, a

# frequency map
freq = {}
for x in arr:
    freq[x] = freq.get(x, 0) + 1

# flatten list
flat = [x for row in matrix for x in row]
```
