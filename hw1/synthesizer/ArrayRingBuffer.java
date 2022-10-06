// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T>   extends AbstractBoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (this.capacity == 0){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last += 1;
        this.fillCount += 1;
        capacity -= 1;

        if (last >= rb.length){
            last -= rb.length;
        }

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
        if (fillCount == 0){
            throw new RuntimeException("Ring buffer underflow");
        }

        T res = rb[first];
        rb[first] = null;
        capacity += 1;
        this.fillCount -= 1;
        first += 1;
        if (first >= rb.length){
            first -= rb.length;
        }

        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (fillCount == 0){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator(){
        return new Myiterator();
    }
    private class Myiterator implements Iterator<T>{
        private int ptr;

        public Myiterator(){
            ptr = 0;
        }

        public boolean hasNext(){
            return (ptr - fillCount) < 0;
        }

        public T next(){
            int i = ptr + first;
            if (i > rb.length) { i -= rb.length;}
            T returnItem = rb[i];
            ptr += 1;
            return returnItem;

        }
    }


    public static void main(String[] args) {
        BoundedQueue<Integer> a = new ArrayRingBuffer<Integer>(10);
        for (int i = 0; i < 10; i ++){
            a.enqueue(i);
        }

        for (int i = 0; i < 10; i++){
            a.dequeue();
            a.enqueue(i);
        }
        for (int i: a){
            System.out.println(i);
        }
        
    }
}
