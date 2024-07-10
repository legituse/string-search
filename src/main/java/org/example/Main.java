package org.example;

public class Main {
    public static void main(String[] args) {
        String bigString = "diwahuidfhawabcdjkwahfjkawnhknawjkdwabfaabcaabcaabaaadwandajwklldawkjijfgwabkfjsenbiogbrdhnflksdnfiusenhfaa";
        String smallString = "aa";

        System.out.println(countOccurrencesBruteForceApproach(smallString, bigString));
        System.out.println(countOccurrencesRabinKarp(smallString, bigString));
    }


    // Bruteforce approach for searching for number of occurrences of searchTerm in the larger string
    // Simply loops through windows of the length of the searchTerm through the larger string and compares the string
    // in each iteration. This takes O(n*m) time.
    public static int countOccurrencesBruteForceApproach(String searchTerm, String largeString){
        int count = 0;

        int largeStringLength = largeString.length();
        int searchTermLength = searchTerm.length();

        int index = 0;
        while (index <= largeStringLength - searchTermLength) {
            boolean found = true;
            for (int j = 0; j < searchTermLength; j++) {
                if (largeString.charAt(index + j) != searchTerm.charAt(j)) {
                    found = false;
                    break;
                }
            }
            if (found) {
                count += 1;
            }

            index++;

        }

        return count;
    }


    /* This approach is way faster on average O(n) than the brute force approach O(n*m)
    * However, it's worst case is also O(n*m)
    * This approach works as follows:
    *
    * This approach is similar in that we slide a window of the length of searchTerm (length m) thru the bigger text (length n)
    * However, we first compare these values using hashes which at first seems the same as bruteforce approach
    * But we use "rolling hash" instead which makes hashing of the windows into a constant time O(1) operation.
    * We start off with first hashing the searchTerm and then hash the first window in the bigger text, which both take O(m) and O(n) time
    *
    * Then, we loop through the windows of length m of the bigger text comparing the hashes which takes O(1) time instead
    * of O(m) like in the bruteforce approach where we always compare strings directly.
    *
    * Then, if the hashes match, we double-check that the strings are actually the same at that position O(m) and increment the count.
    * If they don't match, the next hash for text is calculated using rolling hashes taking O(1) time.
    * This step saves the most time as in the bruteforce approach we would essentially recheck strings but in this case
    * calculating the next hash is constant time
    *
    * At the end after we have gone through all the windows in the bigger text we return the count
    *
    */
    public static int countOccurrencesRabinKarp(String searchTerm, String largeString){
        int count = 0;

        int largeStringLength = largeString.length();
        int searchTermLength = searchTerm.length();
        int primeModulo = 599;
        int numberOfCharacters = 52;

        int hashWindowBase = 1;
        for (int i = 0; i < searchTermLength - 1; i++) {
            hashWindowBase = (hashWindowBase * numberOfCharacters) % primeModulo;
        }

        int searchTermHash = hashString(searchTerm, primeModulo, numberOfCharacters);
        int textHash = hashString(largeString.substring(0, searchTermLength), primeModulo, numberOfCharacters);

        int index = 0;
        while (index <= largeStringLength - searchTermLength) {

            if (searchTermHash == textHash && largeString.substring(index, index + searchTermLength).equals(searchTerm)) {
                count++;
            }

            if (index < largeStringLength - searchTermLength) {
                textHash = getNextLetterHash(textHash, hashWindowBase, primeModulo, numberOfCharacters, largeString.charAt(index), largeString.charAt(index + searchTermLength));
            }

            index++;
        }

        return count;
    }

    // Used to hash strings depending on the number of characters specified. It has linear time complexity O(n)
    // Mostly will be used to hash strings containing only the alphabet, there will be (26*2) = 52 characters, upper and lower case.
    public static int hashString(String s, int primeModulo, int numberOfCharacters){
        int stringLen = s.length();
        int hash = 0;

        for (int i = 0; i < stringLen; i++) {
            hash = (hash*numberOfCharacters + s.charAt(i)) % primeModulo;
        }

        return hash;
    }

    // Calculate the next hash given the next letter
    // this recalculates the hash by getting rid of the first letter from the hash and adding on the next letter in the
    // String, this is a constant time operation O(1)
    public static int getNextLetterHash(int currentHash, int hashWindowBase, int primeModulo, int numberOfCharacters, char firstLetter, char nextLetter) {

        // Remove the first letter from the hash
        currentHash = (currentHash - firstLetter * hashWindowBase) % primeModulo;

        // make sure the hash is positive
        if (currentHash < 0) {
            currentHash += primeModulo;
        }

        // Add the next letter and recalculate the hash
        currentHash = (currentHash * numberOfCharacters + nextLetter) % primeModulo;

        return currentHash;
    }
}