# Java Lab Practice: Stack and Heap Memory

**Student Name:** Md. Khalid Hasan  
**Student ID:** IT24013  
**Topic:** Stack and Heap Memory in Java

This repository contains two Java lab practice programs demonstrating instance variables, local variables, object creation, identity hash codes, method stack frames, and a local inner class.

## Contents

- [Problem 1: Student class](#1-problem-1--student-class-1-point)
- [Problem 2: Employee and Report classes](#2-problem-2--employee-and-report-classes-3-points)
- [Key memory concepts](#key-memory-concepts)
- [How to run](#how-to-run)

---

## 1. Problem 1 — Student Class (1 Point)

### Problem statement

Create a `Student` class with an instance variable `age` and a `display()` method. Inside `display()`, declare a local variable `marks`. In `main()`, create a `Student` object and display the instance variable, local variable, and object identity using `System.identityHashCode()`.

### Source code

File: `src/Student.java`

```java
import java.util.Scanner;

public class Student {
    int age; // Instance variable: stored as part of the Student object

    Student(int age) {
        this.age = age;
    }

    void display(Scanner sc) {
        System.out.print("Marks: ");
        int marks = sc.nextInt(); // Local variable in display()'s stack frame

        System.out.println("Instance Variable (age): " + age);
        System.out.println("Local Variable (marks): " + marks);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Age: ");
        int age = sc.nextInt(); // Local variable in main()

        Student s = new Student(age); // Reference s is local; object is on the heap
        System.out.println("Student Object Created");
        System.out.println("Object Identity Hash Code: " + System.identityHashCode(s));

        s.display(sc);
        sc.close();
    }
}
```

### Sample input

```text
Age: 20
Marks: 85
```

### Sample output

The identity hash code varies between executions, so your output may show a different number.

```text
Age: 20
Student Object Created
Object Identity Hash Code: 1927950199
Marks: 85
Instance Variable (age): 20
Local Variable (marks): 85
```

### IntelliJ IDEA output screenshot

![Student Program Output](Output/Output%20Student.png)

---

## 2. Problem 2 — Employee and Report Classes (3 Points)

### Problem statement

Write an `Employee` class with an instance variable `salary` and an instance method `display()`. Inside `display()`, declare a local variable `bonus` and create a local inner class named `Report` with a `show()` method.

The program must:
1. Create an `Employee` object and display its identity hash code.
2. Display the instance variable `salary` and local variable `bonus`.
3. Create a `Report` object, display its identity hash code, and print a message from `show()`.

### Source code

File: `src/Employee.java`

```java
import java.util.Scanner;

public class Employee {
    int salary; // Instance field: part of the Employee object

    Employee(int salary) {
        this.salary = salary;
    }

    void display(Scanner sc) {
        System.out.print("Enter bonus: ");
        final int bonus = sc.nextInt(); // Local variable in display()

        System.out.println("\nInside display() Method:");
        System.out.println("Salary (Instance Variable): " + salary);
        System.out.println("Bonus (Local Variable): " + bonus);

        // Local inner class declared inside display()
        class Report {
            void show() {
                System.out.println("Report: Employee salary is " + salary);
            }
        }

        Report r = new Report();
        System.out.println("\nReport Object Created");
        System.out.println("Report Identity Hash Code: " + System.identityHashCode(r));
        r.show();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter salary: ");
        int salary = sc.nextInt();

        Employee e = new Employee(salary);
        System.out.println("Employee Object Created");
        System.out.println("Employee Identity Hash Code: " + System.identityHashCode(e));

        e.display(sc);

        System.out.println("\nMemory Concept:");
        System.out.println("Employee object -> Heap");
        System.out.println("Report object -> Heap");
        System.out.println("salary -> Instance field");
        System.out.println("bonus -> Local variable in display()");
        System.out.println("show() -> Executes in its own method frame");

        sc.close();
    }
}
```

### Sample input

```text
Enter salary: 30000
Enter bonus: 5000
```

### Sample output

Identity hash codes are illustrative; they can differ on each run and are not actual memory addresses.

```text
Enter salary: 30000
Employee Object Created
Employee Identity Hash Code: 868693306
Enter bonus: 5000

Inside display() Method:
Salary (Instance Variable): 30000
Bonus (Local Variable): 5000

Report Object Created
Report Identity Hash Code: 989110044
Report: Employee salary is 30000

Memory Concept:
Employee object -> Heap
Report object -> Heap
salary -> Instance field
bonus -> Local variable in display()
show() -> Executes in its own method frame
```

### IntelliJ IDEA output screenshot

![Employee Program Output](Output/Output%20Employee.png)

---

## Key Memory Concepts

| Concept | Explanation |
|---|---|
| Heap | Objects created with `new`, such as `Student`, `Employee`, and `Report`, are allocated on the heap. |
| Stack frame | Each active method call has its own frame for local variables and execution state. |
| Instance variable | `age` and `salary` belong to their respective objects. |
| Local variable | `age` in `main()`, `marks` in `Student.display()`, and `bonus` in `Employee.display()` are local variables. |
| Object reference | Variables such as `s`, `e`, and `r` refer to objects. Local reference variables are associated with their method's execution frame. |
| Identity hash code | `System.identityHashCode(object)` returns the object's identity hash code; it is not a guaranteed memory address. |
| Local inner class | `Report` is declared inside `Employee.display()` and can access the enclosing instance's `salary`. |

> **Note:** The simplified “stack vs. heap” model is useful for learning. JVM implementation details may vary, and local variables are not all necessarily stored as ordinary memory slots in every implementation.

## How to Run

Make sure a JDK is installed and available in your terminal.

From the repository root, compile and run the Student program:

```bash
javac src/Student.java
java -cp src Student
```

Compile and run the Employee program:

```bash
javac src/Employee.java
java -cp src Employee
```

Run each program separately. Both classes use console input.

---

## Mark Distribution

### Problem 1 — Student (1 Point in the title; 3-point rubric supplied)

- Correctly define the `Student` class and instance variable.
- Declare and display the local variable inside `display()`.
- Create the object and display its identity hash code.

### Problem 2 — Employee (3 Points)

- Correctly implement the `Employee` class, instance variable, and `display()` method.
- Implement the local `Report` class and its `show()` method.
- Display identity hash codes and identify the instance field, local variable, and method execution frame.

---

**Submitted by:** Md. Khalid Hasan  
**Student ID:** IT24013


