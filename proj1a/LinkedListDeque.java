public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;
    private Node traveller;

    public class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        traveller = sentinel;
    }

    public void addFirst(T item) {
        if (size == 0) {
            sentinel.next = new Node(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new Node(item, sentinel, sentinel.next);
            sentinel.next.next.prev = sentinel.next;
        }
        size++;
    }

    public void addLast(T item) {
        if (size == 0) {
            sentinel.next = new Node(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.prev.next = new Node(item, sentinel.prev, sentinel);
            sentinel.prev = sentinel.prev.next;
        }
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        Node trv = sentinel.next;
        while (trv != sentinel) {
            System.out.print(trv.item + " ");
            trv = trv.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node trv = sentinel.next;
        if (size == 1) {
            sentinel.prev = sentinel;
            sentinel.next = sentinel;
        } else {
            trv.next.prev = sentinel;
            sentinel.next = trv.next;
            trv.next = null;
            trv.prev = null;
        }
        size--;
        return trv.item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node trv = sentinel.prev;
        if (size == 1) {
            sentinel.prev = sentinel;
            sentinel.next = sentinel;
        } else {
            sentinel.prev = trv.prev;
            trv.prev.next = sentinel;
            trv.next = null;
            trv.prev = null;
        }
        size--;
        return trv.item;
    }

    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node trv = sentinel.next;
        while (index > 0) {
            trv = trv.next;
            index--;
        }
        return trv.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        if (index == 0) {
            Node res = traveller.next;
            traveller = sentinel;
            return res.item;
        } else {
            traveller = traveller.next;
            return getRecursive(index - 1);
        }
    }
}
