package synthesizer;

public interface BoundedQueue<T> extends Iterable<T>{
    int capacity();
    int fillCount();
    void enqueue(T x);
    T dequeue();
    T peek();
    default boolean hasNext(){
        return fillCount() > 0;
    }
    default T next(){
        return dequeue();
    }

    default boolean isEmpty(){
        return fillCount() == 0;
    }

    default boolean isFull(){
        return (capacity() - fillCount()) <= 0;
    }

}