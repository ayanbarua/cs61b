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
        if (isEmpty()) {
            sentinel.next = new TNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new TNode(item, sentinel, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
        }
        size++;
    }

    public void addLast(T item) {
        if (isEmpty()) {
            sentinel.next = new TNode(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else{
            sentinel.prev = new TNode(item, sentinel.prev, sentinel);
            sentinel.prev.prev.next = sentinel.prev;
        }
        size++;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty()) {
            System.out.println("null");
            return;
        } else {
            TNode curr = sentinel;
            while(curr.next.item != null) {
                System.out.print(curr.next.item + " ");
                curr = curr.next;
            }
            return;
        }
    }
//
//    public T removeFirst() {
//
//    }
//
//    public T removeLast() {
//
//    }
//
//    public T get(T item) {
//
//    }

    public static void main(String[] args) {
        LinkedListDeque L = new LinkedListDeque();
        L.addFirst(2);
        L.addFirst(1);
        L.addLast(3);
        L.printDeque();
    }
}