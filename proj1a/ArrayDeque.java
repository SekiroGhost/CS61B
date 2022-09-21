public class ArrayDeque<T> {
    T[] item;
    int size;
    int first;
    int last;

    public ArrayDeque(){
        item = (T[]) new Object[8];
        size = 0;
        first = 0;
        last = 1;
    }

    public ArrayDeque(T it){
        item = (T[]) new Object[8];
        size = 1;
        first = 0;
        last = 1;
        item[first+1] = it;
    }

    private T[] resizing(){
        T[] newItem = (T[]) new Object[size*2];
        System.arraycopy(item, last, newItem, 0, size - last);
        System.arraycopy(item, 0, newItem, size - last, last);
        first = newItem.length-1;
        last = size;
        return newItem;
    }

    private T[] decreasSizing(){
        T[] newItem = (T[]) new Object[item.length/4];
        if (first < last){
            System.arraycopy(item, first+1, newItem, 1, last-first);
            last = last-first;
            first = 0;
        }
        else{
            System.arraycopy(item, (first+1)%item.length, newItem, 1, item.length-first);
            System.arraycopy(item, 1, newItem, item.length-first, last);
            first = 0;
            last = item.length-first + last;
        }
        return newItem;
    }

    public void addFirst(T it){
        if(size < item.length){
            item[first] = it;
            first -= 1;
            size += 1;
            if(first < 0){
                first += item.length;
            }
        }
        else{
            item = resizing();
            addFirst(it);
        }
    }

    public void addLast(T it){
        if(size < item.length){
            item[last] = it;
            last += 1;
            size += 1;
            if(last > item.length){
                last -= item.length;
            }
        }
        else{
            item = resizing();
            addLast(it);
        }
    }

    public T get(int index){
        if (index > item.length){
            return null;
        }
        else{
            return item[index];
        }
    }

    public int size(){
        return size;
    }

    public void removeFirst(){
        first += 1;
        if (first >= item.length){
            first -= item.length;
        }
        item[first] = null;
        size -= 1;
        if(size < item.length/4-1){
            item = decreasSizing();
        }
    }

    public void removeLast(){
        last -= 1;
        if (last < 0){
            last += item.length;
        }
        item[last] = null;
        size -= 1;
        if(size < item.length/4-1){
            item = decreasSizing();
        }
    }

    public void printDeque(){
        if (first > last || size == item.length){
            for (int i =first+1; i < last + item.length; i += 1){
                if (i >= item.length){
                    System.out.print(item[i-item.length] + " ");
                }
                else{
                    System.out.print(item[i] + " ");
                }
            }
        }
        else{
            for (int i = first+1; i < last; i +=1){
                System.out.print(item[i] + " ");
            }
        }

        System.out.println();
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> s1 = new ArrayDeque<>();
        
        s1.addFirst(10);
        s1.addFirst(20);
        s1.addFirst(30);
        s1.printDeque();

        s1.addLast(10);
        s1.addLast(20);
        s1.addLast(30);
        s1.printDeque();
        

        s1.addFirst(40);
        s1.addLast(40);
        s1.addFirst(50);

        s1.removeFirst();
        s1.removeLast();
        s1.removeFirst();;
        s1.removeLast();
        s1.removeFirst();
        s1.removeLast();
        s1.removeFirst();;
        s1.removeLast();

        s1.printDeque();
    }
}
