
import org.example.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testCountOccurrencesBruteForceApproach() {
        String bigString = "diwahuidfhawabcdjkwahfjkawnhknawjkdwabfaabcaabcaabaaadwandajwklldawkjijfgwabkfjsenbiogbrdhnflksdnfiusenhfaa";
        String smallString = "aa";

        int expectedCount = 6;
        int actualCount = Main.countOccurrencesBruteForceApproach(smallString, bigString);

        assertEquals(expectedCount, actualCount, "Bruteforce Approach: Count should be " + expectedCount);
    }

    @Test
    public void testCountOccurrencesRabinKarp() {
        String bigString = "diwahuidfhawabcdjkwahfjkawnhknawjkdwabfaabcaabcaabaaadwandajwklldawkjijfgwabkfjsenbiogbrdhnflksdnfiusenhfaa";
        String smallString = "aa";

        int expectedCount = 6;
        int actualCount = Main.countOccurrencesRabinKarp(smallString, bigString);

        assertEquals(expectedCount, actualCount, "Rabin-Karp: Count should be " + expectedCount);
    }

    @Test
    public void testSearchTermNotInText() {
        String bigString = "hello world";
        String smallString = "test";
        assertEquals(0, Main.countOccurrencesBruteForceApproach(smallString, bigString));
        assertEquals(0, Main.countOccurrencesRabinKarp(smallString, bigString));
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    public void testPerformanceComparison() {
        String bigString = "diwahuidfhawabcdjkwahfjkawnhknawjkdwabfaabcaabcaabaaadwandajwklldawkjijfgwabkfjsenbiogbrdhnflksdnfiusenhfaa";
        String smallString = "aa";

        // Measure time for Bruteforce Approach
        long startTime = System.nanoTime();
        Main.countOccurrencesBruteForceApproach(smallString, bigString);
        long endTime = System.nanoTime();
        long bruteforceApproachDuration = endTime - startTime;

        // Measure time for Rabin-Karp Approach
        startTime = System.nanoTime();
        Main.countOccurrencesRabinKarp(smallString, bigString);
        endTime = System.nanoTime();
        long rabinKarpDuration = endTime - startTime;

        System.out.println("Bruteforce Approach Duration (ns): " + bruteforceApproachDuration);
        System.out.println("Rabin-Karp Duration (ns): " + rabinKarpDuration);

        // Compare performance
        assert (rabinKarpDuration <= bruteforceApproachDuration) : "Rabin-Karp should be faster or equal in time to the Bruteforce Approach";
    }

}
