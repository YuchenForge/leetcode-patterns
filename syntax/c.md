
# C Cheat Sheet

## Includes

```c
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
```

## Variables & Types

```c
int x = 10;
float y = 3.14f;
double z = 3.14;
char c = 'a';
char str[] = "hello";
bool flag = true;
```

## Input / Output

```c
scanf("%d", &x);
printf("%d\n", x);
```

## Arithmetic

```c
+ - * / %
```

## Conditionals

```c
if (x > 0) {

} else if (x == 0) {

} else {

}
```

## Loops

```c
for (int i = 0; i < 10; i++) {}

while (x > 0) {}

do {

} while (x > 0);
```

## Arrays

```c
int arr[5] = {1,2,3};

arr[0] = 10;
```

## Strings

```c
strlen(str);
strcpy(dest, src);
strcat(a, b);
strcmp(a, b);
```

## Functions

```c
int add(int a, int b) {
    return a + b;
}
```

## Pointers

```c
int x = 10;
int *p = &x;

printf("%d", *p);
```

## Pointer Arithmetic

```c
p++;
*(p + 1);
```

## Dynamic Memory

```c
int *arr = malloc(sizeof(int) * n);

free(arr);
```

## Structs

```c
struct Person {
    char name[50];
    int age;
};

struct Person p;
```

## Typedef

```c
typedef struct {
    int x;
} Point;
```

## Enums

```c
enum Color {
    RED,
    GREEN,
    BLUE
};
```

## File I/O

```c
FILE *fp = fopen("file.txt", "r");

fgets(buffer, 100, fp);
fprintf(fp, "%d", x);

fclose(fp);
```

## Function Pointers

```c
int add(int a, int b) {
    return a + b;
}

int (*func)(int, int) = add;
```

## Memory Functions

```c
memset(arr, 0, sizeof(arr));
memcpy(dest, src, n);
```

## Preprocessor

```c
#define PI 3.14159
#define SQUARE(x) ((x)*(x))
```

## Command Line Args

```c
int main(int argc, char *argv[]) {

}
```

## Multidimensional Arrays

```c
int matrix[3][3];
```

## Common Pitfalls

```c
// dangling pointer
// buffer overflow
// memory leak
// uninitialized variable
```

---
