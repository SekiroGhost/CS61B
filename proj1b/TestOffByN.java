import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(5);

    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/

    @Test
    public void testEqualChars(){
        boolean except1 = offByN.equalChars('a', 'f');
        boolean except2 = offByN.equalChars('a', 'a');
        boolean except3 = offByN.equalChars('f', 'a');
        assertTrue(except1);
        assertFalse(except2);
        assertTrue(except3);
    }
}

