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

    public T[] resizing(){
        T[] newItem = (T[]) new Object[size*2];
        System.arraycopy(item, last, newItem, 0, size - last);
        System.arraycopy(item, 0, newItem, size - last, last);
        first = newItem.length-1;
        last = size;
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

        s1.printDeque();
    }
}
