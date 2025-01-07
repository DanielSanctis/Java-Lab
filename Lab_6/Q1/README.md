# Coin Change Problem - Multithreaded Solution

This program calculates the number of ways to make a target sum using given coin denominations, utilizing **dynamic programming (DP)** and **multithreading** for efficiency.

## Problem
Given:
- `coins[]`: Array of coin denominations (infinite supply).
- `sum`: Target sum.

Find the total number of ways to achieve the target sum using combinations of coins.

### Examples
**Input:**  
`N = 3, sum = 4, coins = {1, 2, 3}`  
**Output:**  
`4`  
**Combinations:** {1,1,1,1}, {1,1,2}, {2,2}, {1,3}

**Input:**  
`N = 4, sum = 10, coins = {2, 5, 3, 6}`  
**Output:**  
`5`

## Approach
1. **Dynamic Programming:**  
   A `dp[]` array stores ways to make each sum, updated as `dp[i] += dp[i - coin]`.

2. **Multithreading:**  
   - Tasks split across threads using `ExecutorService`.  
   - Each thread processes subsets of coins or sums for faster computation.  
   - Results are combined for the final output.

## How to Run
1. **Compile:**  
   ```bash
   javac CoinChangeMultithreaded.java
