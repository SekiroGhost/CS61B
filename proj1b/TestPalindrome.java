import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    } //Uncomment this class once you've created your Palindrome class. 

    @Test
    public void testisPalindrome(){
        boolean exception1 = palindrome.isPalindrome("racecar");
        boolean exception2 = palindrome.isPalindrome("noon");
        boolean exception3 = palindrome.isPalindrome("abcdba");
        boolean exception4 = palindrome.isPalindrome("a");
        assertTrue(exception1);
        assertTrue(exception2);
        assertFalse(exception3);
        assertTrue(exception4);
    }
    @Test
    public void testisPalindrome2(){
        CharacterComparator cc = new OffByOne();
        boolean exception1 = palindrome.isPalindrome("racecar",cc);
        boolean exception2 = palindrome.isPalindrome("noon",cc);
        boolean exception3 = palindrome.isPalindrome("abcdba",cc);
        boolean exception4 = palindrome.isPalindrome("a",cc);
        assertTrue(exception1);
        assertTrue(exception2);
        assertFalse(exception3);
        assertTrue(exception4);
    }
}
