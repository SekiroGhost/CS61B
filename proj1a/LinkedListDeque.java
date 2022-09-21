import java.rmi.Remote;

public class LinkedListDeque<T>{
    private Node last;
    private Node first;
    private int size;

    private class Node{
        Node prev;
        T item;
        Node next;

        public Node(T i){
            prev = null;
            item = i;
            next = null;
        }

        public Node(T i,Node n){
            prev = null;
            item = i;
            next = n;
        }

        public Node(T i, Node n, Node p){
            prev = p;
            item = i;
            next = n;
        }
    }

    public LinkedListDeque(){
        last = new Node(null);
        first = new Node(null,last);
        last.prev = first;
        last.next = first;
        first.prev = last;
        size = 0;
    }
    /** 
    public LinkedListDeque(T it){
        Node p = new Node(it);
        last = new Node(null);
        first = new Node(null,p);
        first.prev = last;
        last.next = first;
        last.prev = p;
        p.prev = first;
        p.next = last;

        size = 1;
    }*/

    public void addFirst(T it){
        if (size == 0){
            Node p = new Node(it);
            last = new Node(null);
            first = new Node(null,p);
            first.prev = last;
            last.next = first;
            last.prev = p;
            p.prev = first;
            p.next = last;
    
            size = 1;
        }
        else{
        Node p = new Node(it);
        p.next = first.next;
        p.prev = first;
        first.next.prev = p;
        first.next = p;
        size += 1;}
    }

    public void addLast(T it){
        if(size == 0){
            Node p = new Node(it);
            last = new Node(null);
            first = new Node(null,p);
            first.prev = last;
            last.next = first;
            last.prev = p;
            p.prev = first;
            p.next = last;
    
            size = 1;
        }
        else{
        Node p = new Node(it);
        last.prev.next = p;
        p.prev = last.prev;
        p.next = last;
        last.prev = p;
        size += 1;}
    }

    public void printDeque(){
        Node p = first.next;
        for (int i = 0; i < size; i += 1){
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public T removeFirst(){
        if (this.isEmpty()){
            return null;
        }
        else{
            Node p = first.next;
            first.next = p.next;
            p.next.prev = first;
            size -= 1;
            return p.item;
        }
    }

    public T removeLast(){
        if(this.isEmpty()){
            return null;
        }
        else{
            Node p = last.prev;
            last.prev = last.prev.prev;
            last.prev.next = last;
            size -= 1;
            return p.item;
        }
    }

    public T get(int i){
        if (i > size){
            return null;
        }
        else{
            Node p = first.next;
            for (int j = 0; j < i; j +=1){
                p = p.next;
            }
            return p.item;
        }
    }

    public T getRecursive(int index){
        if (index > size){
            return null;
        }
        else{
            Node p = first.next;
            return getRecursivehelper(p,index);
        }
    }

    private T getRecursivehelper(Node p,int i){
        if(i == 0){
            return p.item;
        }
        else{
            return getRecursivehelper(p.next, i-1);
        }
    }


    public static void main(String[] args) {
        LinkedListDeque<Integer> s1 = new LinkedListDeque();
        s1.printDeque();
        s1.addFirst(0);
        s1.addFirst(1);
        s1.addFirst(2);
        s1.addFirst(3);
        System.out.println(s1.removeLast());
        s1.addFirst(5);
        s1.addFirst(6);
        s1.addFirst(7);
        s1.addFirst(8);
        System.out.println(s1.removeLast());
    }
}
