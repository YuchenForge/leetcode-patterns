# C++ Cheat Sheet

## Includes

```cpp
#include <iostream>
#include <vector>
#include <string>
#include <map>
#include <unordered_map>
#include <set>
#include <unordered_set>
#include <queue>
#include <stack>
#include <deque>
#include <algorithm>
#include <numeric>
#include <cmath>
using namespace std;
```

## Variables & Types

```cpp
int x = 10;
double y = 3.14;
char c = 'a';
bool flag = true;
string s = "hello";
```

## Input / Output

```cpp
cin >> x;
cout << x << endl;
```

## Fast IO

```cpp
ios::sync_with_stdio(false);
cin.tie(nullptr);
```

## Conditionals

```cpp
if (x > 0) {

} else if (x == 0) {

} else {

}
```

## Loops

```cpp
for (int i = 0; i < 10; i++) {}

for (auto x : arr) {}

while (x > 0) {}
```

## Functions

```cpp
int add(int a, int b) {
    return a + b;
}
```

## References

```cpp
int x = 10;
int &ref = x;
```

## Pointers

```cpp
int *p = &x;
cout << *p;
```

## Arrays

```cpp
int arr[5] = {1,2,3};
```

## Vector

```cpp
vector<int> v = {1,2,3};

v.push_back(4);
v.pop_back();
v.size();
v.clear();
```

## Pair

```cpp
pair<int, int> p = {1, 2};
```

## Tuple

```cpp
tuple<int, int, int> t = {1,2,3};
```

## String

```cpp
s.length();
s.substr(1, 3);
s.find("ab");
reverse(s.begin(), s.end());
```

## Map / Unordered Map

```cpp
map<string, int> mp;
unordered_map<int, int> ump;

mp["a"] = 1;
```

## Set / Unordered Set

```cpp
set<int> st;
unordered_set<int> ust;
```

## Stack / Queue / Deque

```cpp
stack<int> st;
queue<int> q;
deque<int> dq;
```

## Priority Queue

```cpp
priority_queue<int> maxHeap;

priority_queue<int, vector<int>, greater<int>> minHeap;
```

## Sorting

```cpp
sort(v.begin(), v.end());

sort(v.begin(), v.end(), greater<int>());
```

## Lambda

```cpp
auto add = [](int a, int b) {
    return a + b;
};
```

## Struct

```cpp
struct Person {
    string name;
    int age;
};
```

## Class

```cpp
class Person {
public:
    string name;

    Person(string n) {
        name = n;
    }
};
```

## Inheritance

```cpp
class Animal {
public:
    virtual void speak() {}
};

class Dog : public Animal {
public:
    void speak() override {}
};
```

## Templates

```cpp
template <typename T>
T add(T a, T b) {
    return a + b;
}
```

## Smart Pointers

```cpp
unique_ptr<int> p = make_unique<int>(10);
shared_ptr<int> q = make_shared<int>(20);
```

## File I/O

```cpp
#include <fstream>

ifstream fin("input.txt");
ofstream fout("output.txt");
```

## STL Algorithms

```cpp
sort()
reverse()
binary_search()
lower_bound()
upper_bound()
max_element()
min_element()
accumulate()
```

## Iterators

```cpp
for (auto it = v.begin(); it != v.end(); it++) {
    cout << *it;
}
```

## Range-Based Loops

```cpp
for (const auto &x : v) {}
```

## Dynamic Memory

```cpp
int *p = new int(5);
delete p;
```

## Exception Handling

```cpp
try {
    throw runtime_error("error");
} catch (exception &e) {
    cout << e.what();
}
```

## Namespaces

```cpp
namespace math {
    int add(int a, int b) {
        return a + b;
    }
}
```

## Competitive Programming Tricks

```cpp
#define all(x) x.begin(), x.end()
#define pb push_back
#define ll long long
```

## Bit Operations

```cpp
x & y
x | y
x ^ y
~x
x << 1
x >> 1
```

## Common STL Containers

```cpp
vector
map
unordered_map
set
unordered_set
queue
priority_queue
deque
stack
```
