# Coffee Shop Simulation - Multithreaded Producer-Consumer Problem

This program simulates a coffee shop where **baristas (producers)** prepare coffee, **customers (consumers)** pick up coffee, and a **reviewer (observer)** samples coffee for quality checks. The interaction between these roles is synchronized using **multithreading** with `wait()` and `notify()`.

---

## Problem Overview

### Key Roles:
1. **Baristas (Producers):**
   - Prepare coffee and place it on the counter.
   - Wait if the counter is full (maximum 3 cups).

2. **Customers (Consumers):**
   - Pick up coffee from the counter.
   - Wait if the counter is empty.
   - Raise a custom exception (`CounterEmptyException`) if coffee is unavailable.

3. **Reviewer (Observer):**
   - Samples coffee from the counter for quality checks.
   - Waits if the counter is empty.

---

## Features

1. **Synchronization:** Ensures baristas, customers, and the reviewer interact without race conditions using `wait()` and `notify()`.
2. **Custom Exception Handling:** A `CounterEmptyException` is thrown if customers or the reviewer attempt to take coffee when the counter is empty.
3. **Threading:** Baristas, customers, and the reviewer are implemented as concurrent threads.
4. **Counter Management:** Tracks the number of coffee cups on the counter, with a maximum capacity of 3.

---

## Input and Output

### Input:
1. **Barista Tasks:**
   - Barista 1: Prepares 2 coffees.
   - Barista 2: Prepares 3 coffees.
2. **Customer Tasks:**
   - Customer 1: Picks up 1 coffee.
   - Customer 2: Picks up 2 coffees.
   - Customer 3: Picks up 1 coffee.
3. **Reviewer Task:**
   - Samples 1 coffee.

### Expected Output:

Barista 1 prepared coffee. Counter: 1 Barista 1 prepared coffee. Counter: 2 Barista 2 prepared coffee. Counter: 3 Barista 2 is waiting. Counter is full. Customer 1 picked up coffee. Counter: 2 Barista 2 prepared coffee. Counter: 3 Barista 2 is waiting. Counter is full. Customer 2 picked up coffee. Counter: 2 Customer 2 picked up coffee. Counter: 1 Barista 2 prepared coffee. Counter: 2 Customer 3 picked up coffee. Counter: 1 Coffee Reviewer sampled coffee. Counter: 0 Barista 1 is notified. Counter is empty. Barista 2 is notified. Counter is empty.