# Lab Practice: Static vs Non-Static Counter in Java

## 1. Lab Information

- **Course:** Software Engineering Project Management
- **Problem ID:** SEPM-001
- **Experiment Title:** Comparison of Thread Counting Using Static and Non-Static Variables
- **Java Source File:** `Khalid_Thread.java`

## 2. Objective

The objectives of this experiment are:

- To understand the difference between static and non-static variables in Java.
- To implement multithreading using the `Thread` class.
- To compare thread-safe and unsynchronized static counters.
- To calculate the absolute difference and percentage difference between static and non-static counter totals.
- To investigate the effect of increasing the number of threads on counter accuracy.

## 3. Program Description

The Java program compares two types of static counters:

1. **Thread-safe counter:** Uses `AtomicLong` to perform atomic increment operations.
2. **Unsynchronized counter:** Uses a regular static `long` variable without synchronization, which may cause race conditions and lost updates.

Each thread has its own non-static counter. After all threads finish execution, the program adds the individual non-static counter values and compares the total with the relevant static counter.

The program uses `join()` to ensure that all threads finish before the final results are calculated.

## 4. How to Compile and Run

Compile the program:

```bash
javac Khalid_Thread.java
```

The general command format is:

```bash
java Khalid_Thread <threads> <increments> <true|false>
```

- `threads`: Number of threads.
- `increments`: Number of increments performed by each thread.
- `true`: Thread-safe experiment using `AtomicLong`.
- `false`: Unsynchronized experiment using a regular `long`.

Example commands:

```bash
java Khalid_Thread 1 1000 true
java Khalid_Thread 1 1000 false
java Khalid_Thread 10 50000 true
java Khalid_Thread 10 50000 false
```

## 5. Test Cases

The following test cases are used in the experiment.

| Test Case | Threads | Increments per Thread | Expected Count |
|---|---:|---:|---:|
| TC1 | 1 | 1,000 | 1,000 |
| TC2 | 2 | 10,000 | 20,000 |
| TC3 | 5 | 10,000 | 50,000 |
| TC4 | 10 | 50,000 | 500,000 |
| TC5 | 20 | 50,000 | 1,000,000 |
| TC6 | 50 | 50,000 | 2,500,000 |
| TC7 | 100 | 50,000 | 5,000,000 |

Both modes are executed for every test case. The unsynchronized experiment is repeated at least five times for each thread count.

## 6. Result Analysis

### 6.1 Thread-Safe Experiment

Complete this table using the actual output from the `true` mode.

| Threads | Expected Count | Static Count | Non-Static Total | Absolute Difference | Difference (%) |
|---:|---:|---:|---:|---:|---:|
| 1 | 1,000 | [Actual] | [Actual] | [Actual] | [Actual] |
| 2 | 20,000 | [Actual] | [Actual] | [Actual] | [Actual] |
| 5 | 50,000 | [Actual] | [Actual] | [Actual] | [Actual] |
| 10 | 500,000 | [Actual] | [Actual] | [Actual] | [Actual] |
| 20 | 1,000,000 | [Actual] | [Actual] | [Actual] | [Actual] |
| 50 | 2,500,000 | [Actual] | [Actual] | [Actual] | [Actual] |
| 100 | 5,000,000 | [Actual] | [Actual] | [Actual] | [Actual] |

### 6.2 Unsynchronized Experiment

Record all five runs for each thread count. The following table is a summary template; enter the actual values from your runs.

| Threads | Expected Count | Static Count: Runs 1–5 | Non-Static Total | Absolute Differences: Runs 1–5 | Average Difference (%) |
|---:|---:|---|---:|---|---:|
| 1 | 1,000 | [R1, R2, R3, R4, R5] | [Actual] | [D1, D2, D3, D4, D5] | [Actual] |
| 2 | 20,000 | [R1, R2, R3, R4, R5] | [Actual] | [D1, D2, D3, D4, D5] | [Actual] |
| 5 | 50,000 | [R1, R2, R3, R4, R5] | [Actual] | [D1, D2, D3, D4, D5] | [Actual] |
| 10 | 500,000 | [R1, R2, R3, R4, R5] | [Actual] | [D1, D2, D3, D4, D5] | [Actual] |
| 20 | 1,000,000 | [R1, R2, R3, R4, R5] | [Actual] | [D1, D2, D3, D4, D5] | [Actual] |
| 50 | 2,500,000 | [R1, R2, R3, R4, R5] | [Actual] | [D1, D2, D3, D4, D5] | [Actual] |
| 100 | 5,000,000 | [R1, R2, R3, R4, R5] | [Actual] | [D1, D2, D3, D4, D5] | [Actual] |

### 6.3 Calculation Formulas

**Expected Count**

Expected Count = Number of Threads × Increments per Thread

**Absolute Difference**

Absolute Difference = |Static Count − Non-Static Total|

**Percentage Difference**

Percentage Difference = (Absolute Difference / Non-Static Total) × 100

If the non-static total is zero and both counts are zero, the percentage difference is defined as 0%. If the denominator is zero but the counts are not both zero, it is undefined.

For the unsynchronized experiment, calculate the percentage difference for each run and then calculate the average of the five percentages.

## 7. Analysis Questions and Answers

### 1. What is the difference between a static variable and a non-static variable in Java?

A static variable belongs to the class and is shared among its instances. A non-static variable belongs to an individual object, so each object has its own copy.

### 2. Why do all threads share the same static counter?

All threads access the same class-level variable. Therefore, changes made to that static variable affect the shared counter.

### 3. Why does each thread have its own non-static counter in this experiment?

Each thread is represented by a separate `Counter` object. Its instance variable belongs to that particular object, so each thread increments its own counter.

### 4. Why is `join()` required before calculating the final counts?

The `join()` method makes the main thread wait until the worker thread finishes. Without waiting for all threads, the program might calculate totals before all increments are complete.

### 5. Why can the unsynchronized static count be lower than the expected count?

The `unsafe++` operation is not atomic. Multiple threads may read the same value and overwrite one another's updates, causing lost increments. This is known as a race condition.

### 6. Does increasing the number of threads always increase the percentage difference?

No. More threads can increase contention and the possibility of lost updates, but the percentage difference is not guaranteed to increase every time. It depends on thread scheduling, hardware, and runtime conditions.

### 7. Why might two runs with the same number of threads produce different results?

Thread scheduling and execution timing can differ between runs. Since the unsynchronized counter has a race condition, the number of lost increments may also vary.

### 8. What changes when `AtomicLong` is used instead of a regular `long`?

`AtomicLong` provides atomic operations such as `incrementAndGet()`. Concurrent increments are performed safely without lost updates caused by the non-atomic increment operation.

### 9. How would you modify the program so that all threads share one instance counter as well as the static counter?

Create one shared counter object and pass a reference to that object to every thread. All threads would increment the same instance variable. To prevent lost updates, protect the increment operation using synchronization or an atomic variable.

## 8. Observation

**Complete this section after running the experiments.**

In the thread-safe experiment, the static counter and the sum of all non-static counters are expected to match the expected count. Therefore, the absolute difference should be zero.

In the unsynchronized experiment, the static counter may be lower than the non-static total because concurrent updates can be lost. The results may also differ between repeated runs.

**My observed results:** [Describe the actual results from the seven test cases, including any variation between repeated runs and the effect of thread count.]

## 9. Conclusion

This experiment demonstrates that static and non-static variables differ in ownership and sharing, not inherently in thread safety. Static variables are shared at the class level, while each object has its own instance variables.

The thread-safe experiment using `AtomicLong` provides a control case in which all increments should be counted correctly. The unsynchronized experiment demonstrates how race conditions can cause lost updates. However, the percentage difference is not guaranteed to increase monotonically as the number of threads increases.

**Conclusion based on my results:** [Add a brief summary of the actual measurements and observations.]

## 10. Supporting Evidence

The `outputs/` directory contains screenshots of actual program executions for each test case. The `handwritten_code/` directory contains photographs or scans of the handwritten Java code. The `inputs/` directory contains the test commands used in the experiment.

The submitted report should include the handwritten code, test inputs, actual program outputs, result analysis, and conclusion.

