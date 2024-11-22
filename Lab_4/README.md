# Robber Simulation Program

## Overview
This Java program calculates the maximum money a robber can steal from various house types while avoiding security alarms. It uses abstraction, inheritance, and dynamic programming.

## Classes and Methods
- **Abstract Class `Robber`:**  
  - `RobbingClass`: Prints "MScAI&ML".  
  - `MachineLearning`: Prints "I love MachineLearning".  
  - Abstract methods: `RowHouses`, `RoundHouses`, `SquareHouse`, `MultiHouseBuilding`.  

- **Class `JAVAProfessionalRobber`:** Implements abstract methods to maximize robbery profits under constraints.

## Test Cases
- `RowHouses([1,2,3,0]) → 4`
- `RoundHouses([1,2,3,4]) → 6`
- `SquareHouse([5,10,2,7]) → 17`
- `MultiHouseBuilding([[5,3,8,2],[10,12,7,6],[4,9,11,5],[8,6,3,7]]) → 59`

## How to Run
1. Compile: `javac RobberMain.java`  
2. Run: `java RobberMain`  

Explore and test house arrangements to get results.
