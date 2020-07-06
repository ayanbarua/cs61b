public class LinkedListDeque<T>{
    private class TNode {
        public T item;
        public TNode prev;
        public TNode next;

        public TNode(T item, TNode prev, TNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    public int size;
    public TNode sentinel;

    public LinkedListDeque() {
        size = 0;
        sentinel = new TNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        size++;
        sentinel.next = new TNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;
    }

    public void addLast(T item) {
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}