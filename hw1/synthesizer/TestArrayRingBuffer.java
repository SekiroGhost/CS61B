package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Assert;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(5);
        arb.enqueue(10);
        arb.enqueue(11);

        assertEquals(arb.capacity, 7);
        assertEquals(arb.fillCount, 3);
        int exp;
        exp = arb.dequeue();
        assertEquals(exp, 5);
        exp = arb.dequeue();
        assertEquals(exp, 10);
        exp = arb.dequeue();
        assertEquals(exp, 11);

        assertEquals(arb.capacity, 10);
        assertEquals(arb.fillCount, 0);

        assertTrue(arb.isEmpty());

        assertFalse(arb.isFull());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
