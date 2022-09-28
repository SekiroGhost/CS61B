import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/

    @Test
    public void testEqualChars(){
        boolean except1 = offByOne.equalChars('a', 'b');
        boolean except2 = offByOne.equalChars('a', 'a');

        assertTrue(except2);
        assertFalse(except1);
    }
}
