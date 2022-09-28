public interface Deque<Item> {
    public void addFirst(Item it);
    public void addLast(Item it);
    public Item removeFirst();
    public Item removeLast();
    public void printDeque();
    public Item get(int index);
    public int size();
}
