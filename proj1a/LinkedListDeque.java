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
        } else {
            TNode curr = sentinel;
            while(curr.next.item != null) {
                System.out.print(curr.next.item + " ");
                curr = curr.next;
            }
        }
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return last;
    }

    public T get(int index) {
        if ((index > size - 1) || (size == 0)) {
            return null;
        }
        TNode curr = sentinel.next;
        int count = 0;
        while (count != index) {
            curr = curr.next;
            count++;
        }
        return curr.item;
    }

    public T helper(TNode node, int index){
        if (index == 0) {
            return node.item;
        }
        return helper(node.next, index - 1);
    }

    public T getRecursive(int index) {
        if ((index > size - 1) || (size == 0)) {
            return null;
        }
        return helper(sentinel.next, index);
    }
}