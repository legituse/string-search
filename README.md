# String Search Project

## Overview
This project shows two approaches to counting occurrences of a substring within a larger string in Java: the Brute Force approach and the Rabin-Karp algorithm. 

### Technologies Used
- Java
- Maven
- JUnit

## Prerequisites
- Java Development Kit (JDK) version 17
- Maven

## Instructions
1. Clone the repository.
2. Navigate to the project directory.
3. Run tests using Maven: `mvn test`.

## Implementation Details

### Brute Force Approach
This method loops through the larger string and compares each substring of the same length as the search term to count occurrences.
It has a time complexity of O(n * m), where `n` is the length of the large string and `m` is the length of the search term.

### Rabin-Karp Algorithm
This method uses hashing to compare substrings and a rolling hash to efficiently update the hash value as the window slides through the larger string.
This algorithm typically performs in O(n) time on average but can be O(n * m) in the worst case.

## Test Scenarios
- **Count Occurrences with Brute Force Approach**: Verifies the number of occurrences of a substring within a larger string using the Brute Force method.
- **Count Occurrences with Rabin-Karp Algorithm**: Verifies the number of occurrences of a substring within a larger string using the Rabin-Karp method.
- **Search Term Not in Text**: Checks that both approaches return zero when the search term is not present in the larger string.
- **Performance Comparison**: Compares the execution time of both the Brute Force approach and the Rabin-Karp algorithm to ensure the Rabin-Karp algorithm is faster or at least as fast as the Brute Force approach.
<br></br>
![image](https://github.com/legituse/string-search/assets/35747061/82d74bce-4d25-4b28-853c-b2b684813506)


