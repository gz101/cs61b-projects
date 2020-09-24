public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;
    private Node traveller;

    public class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(T item, Node prev, Node next) {
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

    public Boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        if (size == 0) return;
        Node trv = sentinel.next;
        while (trv != sentinel) {
            System.out.print(trv.item + " ");
            trv = trv.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) return null;
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
        if (size == 0) return null;
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
        if (index > size - 1 || index < 0) return null;
        Node trv = sentinel.next;
        while (index > 0) {
            trv = trv.next;
            index--;
        }
        return trv.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) return null;
        if (index == 0) {
            Node res = traveller.next;
            traveller = sentinel;
            return res.item;
        } else {
            traveller = traveller.next;
            return getRecursive(index - 1);
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<String> d = new LinkedListDeque<>();
        System.out.println("Is Deque empty? " + d.isEmpty());
        System.out.println();

        System.out.println("----Testing Add First----");
        System.out.println("--Adding first element, size is 1.");
        d.addFirst("crickets");
        d.printDeque();
        System.out.println("Current size: " + d.size());
        System.out.println("--Adding second element, size is 2.");
        d.addFirst("covered");
        d.printDeque();
        System.out.println("Current size: " + d.size());
        System.out.println("--Adding third element, size is 3.");
        d.addFirst("Chocolate");
        d.printDeque();
        System.out.println("Current size: " + d.size());
        System.out.println();

        System.out.println("----Testing Add Last----");
        System.out.println("--Adding first element, size is 4.");
        d.addLast("his");
        d.printDeque();
        System.out.println("Current size: " + d.size());
        System.out.println("--Adding second element, size is 5.");
        d.addLast("favorite");
        d.printDeque();
        System.out.println("Current size: " + d.size());
        System.out.println("--Adding third element, size is 6.");
        d.addLast("snack");
        d.printDeque();
        System.out.println("Current size: " + d.size());
        System.out.println();

//        System.out.println("----Testing Get Index----");
//        System.out.println("Item and index 0: " + d.get(0));
//        System.out.println("Item and index 1: " + d.get(1));
//        System.out.println("Item and index 2: " + d.get(2));
//        System.out.println("Item and index 3: " + d.get(3));
//        System.out.println("Item and index 4: " + d.get(4));
//        System.out.println("Item and index 5: " + d.get(5));
//        System.out.println("Item and index 6: " + d.get(6));
//        System.out.println();

        System.out.println("----Testing getRecursive Index----");
        System.out.println("Item and index 0: " + d.getRecursive(0));
        System.out.println("Item and index 1: " + d.getRecursive(1));
        System.out.println("Item and index 2: " + d.getRecursive(2));
        System.out.println("Item and index 3: " + d.getRecursive(3));
        System.out.println("Item and index 4: " + d.getRecursive(4));
        System.out.println("Item and index 5: " + d.getRecursive(5));
        System.out.println("Item and index 6: " + d.getRecursive(6));
        System.out.println();

//        System.out.println("Is Deque empty? " + d.isEmpty());
//        System.out.println();
//
//        System.out.println("----Testing Remove First----");
//        System.out.println("--Remove first element, size is 5.");
//        System.out.println("Value of removed item is: " + d.removeFirst());
//        d.printDeque();
//        System.out.println("Current size: " + d.size());
//        System.out.println("--Remove second element, size is 4.");
//        System.out.println("Value of removed item is: " + d.removeFirst());
//        d.printDeque();
//        System.out.println("Current size: " + d.size());
//        System.out.println("--Remove third element, size is 3.");
//        System.out.println("Value of removed item is: " + d.removeFirst());
//        d.printDeque();
//        System.out.println("Current size: " + d.size());
//        System.out.println();
//
//        System.out.println("----Testing Remove Last----");
//        System.out.println("--Remove last element, size is 2.");
//        System.out.println("Value of removed item is: " + d.removeLast());
//        d.printDeque();
//        System.out.println("Current size: " + d.size());
//        System.out.println("--Remove last element, size is 1.");
//        System.out.println("Value of removed item is: " + d.removeLast());
//        d.printDeque();
//        System.out.println("Current size: " + d.size());
//        System.out.println("--Remove last element, size is 0.");
//        System.out.println("Value of removed item is: " + d.removeLast());
//        d.printDeque();
//        System.out.println("Current size: " + d.size());
//        System.out.println();
//
//        System.out.println();
//        System.out.println("Is Deque empty? " + d.isEmpty());
    }
}
