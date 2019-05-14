import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        String racecar = "racecar";
        String cat = "cat";
//        assertTrue(Palindrome.isPalindrome("racecar"));
//        assertFalse(Palindrome.isPalindrome("cat"));
        assertTrue(new Palindrome().isPalindrome(racecar));
        assertFalse(new Palindrome().isPalindrome(cat));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", offByOne));

    }
}
