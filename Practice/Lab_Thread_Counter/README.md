<div align="center">

# 🧵 Static vs Non-Static Counter in Java

**Comparison of Thread Counting Using Static and Non-Static Variables**

![Java](https://img.shields.io/badge/Java-Multithreading-orange?logo=openjdk&logoColor=white)
![Course](https://img.shields.io/badge/Course-SEPM-blue)
![Problem](https://img.shields.io/badge/Problem%20ID-SEPM--001-green)

</div>

---

## 📋 Table of Contents

1. [Lab Information](#1-lab-information)
2. [Objective](#2-objective)
3. [Program Description](#3-program-description)
4. [How to Compile and Run](#4-how-to-compile-and-run)
5. [Test Cases](#5-test-cases)
6. [Result Analysis](#6-result-analysis)
7. [Analysis Questions and Answers](#7-analysis-questions-and-answers)
8. [Observation](#8-observation)
9. [Conclusion](#9-conclusion)
10. [Project Structure and Supporting Evidence](#10-project-structure-and-supporting-evidence)

---

## 1. Lab Information

| Item | Details |
|---|---|
| **Author** | Khalid |
| **Department** | Information and Communication Technology, Mawlana Bhashani Science and Technology University (MBSTU), Tangail |
| **Course** | Software Engineering Project Management |
| **Problem ID** | SEPM-001 |
| **Experiment Title** | Comparison of Thread Counting Using Static and Non-Static Variables |
| **Java Source File** | `Khalid_Thread.java` |

## 2. Objective

- Understand the difference between static and non-static variables in Java.
- Implement multithreading using the `Thread` class.
- Compare a thread-safe static counter with an unsynchronized static counter.
- Calculate the absolute difference and the percentage difference between the static and non-static counter totals.
- Investigate how increasing the number of threads affects counter accuracy.

## 3. Program Description

The program compares two kinds of static counters:

| Counter | Implementation | Behaviour |
|---|---|---|
| **Thread-safe** | `AtomicLong` | Every increment is atomic, so no update is lost |
| **Unsynchronized** | regular static `long` | Race conditions can cause lost updates |

Each thread also owns a separate **non-static counter**. After all threads finish, the program adds up the individual non-static counters and compares that total with the relevant static counter. `join()` is used on every thread so the final results are calculated only after all work is complete.

## 4. How to Compile and Run

**Compile**

```bash
javac Khalid_Thread.java
```

**Run**

```bash
java Khalid_Thread <threads> <increments> <true|false>
```

| Argument | Meaning |
|---|---|
| `threads` | Number of threads |
| `increments` | Number of increments performed by each thread |
| `true` | Thread-safe experiment using `AtomicLong` |
| `false` | Unsynchronized experiment using a regular `long` |

**Examples**

```bash
java Khalid_Thread 1 1000 true
java Khalid_Thread 1 1000 false
java Khalid_Thread 10 50000 true
java Khalid_Thread 10 50000 false
```

**Sample output** (`java Khalid_Thread 2 10000 false`)

```text
Expected: 20000
Static: 19235
Non-static total: 20000
Difference: 765
Difference (%): 3.83%
```

## 5. Test Cases

| Test Case | Threads | Increments per Thread | Expected Count |
|---|---:|---:|---:|
| TC1 | 1 | 1,000 | 1,000 |
| TC2 | 2 | 10,000 | 20,000 |
| TC3 | 5 | 10,000 | 50,000 |
| TC4 | 10 | 50,000 | 500,000 |
| TC5 | 20 | 50,000 | 1,000,000 |
| TC6 | 50 | 50,000 | 2,500,000 |
| TC7 | 100 | 50,000 | 5,000,000 |

Both modes were executed for every test case. The `true` mode was run once per test case, and the unsynchronized (`false`) mode was repeated **five times** for each thread count.

## 6. Result Analysis

### 6.1 Thread-Safe Experiment (`true` mode)

| Threads | Expected Count | Static Count | Non-Static Total | Absolute Difference | Difference (%) |
|---:|---:|---:|---:|---:|---:|
| 1 | 1,000 | 1,000 | 1,000 | 0 | 0.00% |
| 2 | 20,000 | 20,000 | 20,000 | 0 | 0.00% |
| 5 | 50,000 | 50,000 | 50,000 | 0 | 0.00% |
| 10 | 500,000 | 500,000 | 500,000 | 0 | 0.00% |
| 20 | 1,000,000 | 1,000,000 | 1,000,000 | 0 | 0.00% |
| 50 | 2,500,000 | 2,500,000 | 2,500,000 | 0 | 0.00% |
| 100 | 5,000,000 | 5,000,000 | 5,000,000 | 0 | 0.00% |

### 6.2 Unsynchronized Experiment (`false` mode, 5 runs per thread count)

**Summary table**

| Threads | Expected Count | Static Count: Runs 1–5 | Non-Static Total | Absolute Differences: Runs 1–5 | Average Difference (%) |
|---:|---:|---|---:|---|---:|
| 1 | 1,000 | 1000, 1000, 1000, 1000, 1000 | 1,000 | 0, 0, 0, 0, 0 | 0.00% |
| 2 | 20,000 | 19235, 19348, 20000, 18684, 16126 | 20,000 | 765, 652, 0, 1316, 3874 | 6.61% |
| 5 | 50,000 | 27445, 43077, 27750, 18824, 14849 | 50,000 | 22555, 6923, 22250, 31176, 35151 | 47.22% |
| 10 | 500,000 | 72838, 110378, 84618, 109856, 66866 | 500,000 | 427162, 389622, 415382, 390144, 433134 | 82.22% |
| 20 | 1,000,000 | 106016, 128581, 115528, 103175, 132433 | 1,000,000 | 893984, 871419, 884472, 896825, 867567 | 88.29% |
| 50 | 2,500,000 | 598482, 390300, 302343, 837577, 566001 | 2,500,000 | 1901518, 2109700, 2197657, 1662423, 1933999 | 78.44% |
| 100 | 5,000,000 | 2503028, 2615263, 2749369, 2862869, 2891905 | 5,000,000 | 2496972, 2384737, 2250631, 2137131, 2108095 | 45.51% |

**Percentage difference for each run**

| Threads | Run 1 | Run 2 | Run 3 | Run 4 | Run 5 | Average |
|---:|---:|---:|---:|---:|---:|---:|
| 1 | 0.00% | 0.00% | 0.00% | 0.00% | 0.00% | **0.00%** |
| 2 | 3.83% | 3.26% | 0.00% | 6.58% | 19.37% | **6.61%** |
| 5 | 45.11% | 13.85% | 44.50% | 62.35% | 70.30% | **47.22%** |
| 10 | 85.43% | 77.92% | 83.08% | 78.03% | 86.63% | **82.22%** |
| 20 | 89.40% | 87.14% | 88.45% | 89.68% | 86.76% | **88.29%** |
| 50 | 76.06% | 84.39% | 87.91% | 66.50% | 77.36% | **78.44%** |
| 100 | 49.94% | 47.69% | 45.01% | 42.74% | 42.16% | **45.51%** |

### 6.3 Calculation Formulas

```text
Expected Count        = Number of Threads × Increments per Thread
Absolute Difference   = |Static Count − Non-Static Total|
Percentage Difference = (Absolute Difference ÷ Non-Static Total) × 100
```

- If the non-static total is zero and both counts are zero, the percentage difference is defined as 0%. If the denominator is zero but the counts are not both zero, it is undefined.
- For the unsynchronized experiment, the percentage difference is calculated for each run, and then the average of the five percentages is taken.

**Worked example (2 threads, Run 1):**
Absolute Difference = |19,235 − 20,000| = 765
Percentage Difference = (765 ÷ 20,000) × 100 = **3.83%**

## 7. Analysis Questions and Answers

<details>
<summary><b>1. What is the difference between a static variable and a non-static variable in Java?</b></summary>

A static variable belongs to the class itself and is shared by all of its instances. A non-static variable belongs to an individual object, so every object has its own separate copy.
</details>

<details>
<summary><b>2. Why do all threads share the same static counter?</b></summary>

All threads access the same class-level variable, so any change made to that static variable by one thread is visible to every other thread.
</details>

<details>
<summary><b>3. Why does each thread have its own non-static counter in this experiment?</b></summary>

Each thread is represented by a separate `Counter` object. Its instance variable belongs to that particular object, so each thread increments only its own counter.
</details>

<details>
<summary><b>4. Why is <code>join()</code> required before calculating the final counts?</b></summary>

The `join()` method makes the main thread wait until a worker thread has finished. Without it, the program might calculate the totals before all increments are complete.
</details>

<details>
<summary><b>5. Why can the unsynchronized static count be lower than the expected count?</b></summary>

The `unsafe++` operation is not atomic: it reads the value, adds one, and writes it back. Several threads may read the same value and overwrite one another's updates, so some increments are lost. This situation is called a **race condition**.
</details>

<details>
<summary><b>6. Does increasing the number of threads always increase the percentage difference?</b></summary>

No. More threads can increase contention and the chance of lost updates, but the percentage difference is not guaranteed to rise every time. It depends on thread scheduling, hardware, and runtime conditions. In this experiment the average difference rose up to 20 threads (88.29%) and then fell at 50 threads (78.44%) and 100 threads (45.51%).
</details>

<details>
<summary><b>7. Why might two runs with the same number of threads produce different results?</b></summary>

Thread scheduling and execution timing differ from run to run. Because the unsynchronized counter has a race condition, the number of lost increments also varies. For example, with 2 threads the difference ranged from 0% to 19.37% across five runs.
</details>

<details>
<summary><b>8. What changes when <code>AtomicLong</code> is used instead of a regular <code>long</code>?</b></summary>

`AtomicLong` provides atomic operations such as `incrementAndGet()`. Concurrent increments are performed safely, so no updates are lost to the non-atomic increment operation.
</details>

<details>
<summary><b>9. How would you modify the program so that all threads share one instance counter as well as the static counter?</b></summary>

Create one shared counter object and pass a reference to it to every thread, so that all threads increment the same instance variable. To prevent lost updates, protect the increment with synchronization or use an atomic variable.
</details>

## 8. Observation

**Thread-safe experiment (`true` mode).** For all seven test cases, the static counter and the non-static total both matched the expected count exactly. The absolute difference was 0 and the percentage difference was 0.00% every time, so `AtomicLong` counted every increment correctly.

**Unsynchronized experiment (`false` mode).**

- With **1 thread** there is no concurrency, so all five runs were exactly correct (0.00%).
- With **2 threads** the results were inconsistent. One run was exactly correct, while another lost 19.37% of the increments. The average difference was 6.61%.
- With **5 threads** the average difference rose sharply to 47.22%, and individual runs ranged from 13.85% to 70.30%.
- With **10 and 20 threads** most of the increments were lost. The average differences were 82.22% and 88.29%, and the runs were fairly close to one another.
- With **50 and 100 threads** the average differences were lower, at 78.44% and 45.51%. The static count was still far below the expected value in every run.
- The **non-static total** was always equal to the expected count, because each thread increments its own private counter and nothing is shared.

**Effect of thread count.** The error was 0% at one thread and grew quickly as threads were added, reaching its highest average at 20 threads. It did not keep increasing at 50 and 100 threads. One possible explanation is that when threads are started one after another and each has a long workload, early threads can do a large part of their work before later threads begin, so the overlap between threads becomes smaller. This was not measured in the experiment, so it should be treated as a hypothesis.

**Variation between repeated runs.** For the same thread count, the unsynchronized static count changed from run to run. This variation is the main sign of a race condition: the outcome depends on timing, so it cannot be predicted or reproduced.

## 9. Conclusion

This experiment shows that static and non-static variables differ in **ownership and sharing**, not inherently in thread safety. A static variable is shared at the class level, while each object has its own instance variables.

The thread-safe experiment using `AtomicLong` served as a control case, and it counted every increment correctly. The unsynchronized experiment showed how race conditions cause lost updates. However, the percentage difference does not necessarily increase as the number of threads increases.

**Conclusion based on my results:** The `AtomicLong` counter produced 0.00% difference for all thread counts from 1 to 100. The unsynchronized static counter was accurate only with a single thread. With two or more threads it lost updates, with an average error of 6.61% at 2 threads, up to 88.29% at 20 threads, and 45.51% at 100 threads. The results also varied between runs of the same test case. Therefore, any counter shared between threads must be protected with an atomic variable or synchronization.

## 10. Project Structure and Supporting Evidence

```text
.
├── Khalid_Thread.java      # Source code
├── inputs/                 # Test commands used in the experiment
├── outputs/                # Screenshots of actual program executions for each test case
├── handwritten_code/       # Photographs or scans of the handwritten Java code
└── README.md
```

The submitted report includes the handwritten code, the test inputs, the actual program outputs, the result analysis, and the conclusion.
