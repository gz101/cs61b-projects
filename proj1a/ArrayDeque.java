/**
 * TODO: Resizing array functionality
 * How does the nextFirst and nextLast get copied over?
 */
public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] arr;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private void checkSize() {
        if (size == arr.length / 2) {
            T[] newArr = (T[]) new Object[arr.length * 2];
            System.arraycopy(arr, 0, newArr, newArr.length / 4, arr.length);
            nextFirst += arr.length / 2;
            nextLast += arr.length / 2;
            arr = newArr;
        } else if (size == arr.length / 4 && arr.length > 8) {
            T[] newArr = (T[]) new Object[arr.length / 2];
            System.arraycopy(arr, arr.length / 4, newArr, 0, newArr.length);
            nextFirst -= arr.length / 4;
            nextLast -= arr.length / 4;
            arr = newArr;
        } else return;
    }

    public void addFirst(T item) {
        checkSize();
        if (nextFirst < 0) nextFirst = arr.length - 1;
        arr[nextFirst--] = item;
        size++;
    }

    public void addLast(T item) {
        checkSize();
        nextLast = nextLast % arr.length;
        arr[nextLast++] = item;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) return;
        for (T item : arr) System.out.print(item + " ");
    }

    public T removeFirst() {
        if (size == 0) return null;
        nextFirst = nextFirst % arr.length;
        int interest = (nextFirst + 1) % arr.length;
        T res = arr[interest];
        arr[interest] = null;
        nextFirst++;
        size--;
        checkSize();
        return res;
    }

    public T removeLast() {
        if (size == 0) return null;
        if (nextLast == 0) {
            nextLast = arr.length - 1;
            T res = arr[nextLast];
            arr[nextLast] = null;
            size--;
            checkSize();
            return res;
        } else {
            T res = arr[nextLast - 1];
            arr[--nextLast] = null;
            size--;
            checkSize();
            return res;
        }
    }

    public T get(int index) {
        return arr[index];
    }

    public static void main(String[] args) {

        System.out.println("----Test addFirst circular implementation----");
        ArrayDeque<String> a1 = new ArrayDeque<>();
        a1.addFirst("a");
        a1.addFirst("b");
        a1.addFirst("c");
        a1.addFirst("d");
        a1.addFirst("e");
        a1.addFirst("f");
        a1.addFirst("g");
        a1.addFirst("h");
        a1.addFirst("i");
        a1.addFirst("j");
        a1.printDeque();
        System.out.println();

        System.out.println();
        System.out.println("----Test removeFirst circular implementation----");
        System.out.println("Removed: " + a1.removeFirst());
        System.out.println("Removed: " + a1.removeFirst());
        System.out.println("Removed: " + a1.removeFirst());
        System.out.println("Removed: " + a1.removeFirst());
        System.out.println("Removed: " + a1.removeFirst());
        System.out.println("Removed: " + a1.removeFirst());
        a1.printDeque();
        System.out.println();

        System.out.println();
        System.out.println("----Test addLast circular implementation----");
        ArrayDeque<String> a2 = new ArrayDeque<>();
        a2.addLast("a");
        a2.addLast("b");
        a2.addLast("c");
        a2.addLast("d");
        a2.addLast("e");
        a2.addLast("f");
        a2.addLast("g");
        a2.addLast("h");
        a2.addLast("i");
        a2.addLast("j");
        a2.printDeque();
        System.out.println();

        System.out.println();
        System.out.println("----Test removeLast circular implementation----");
        System.out.println("Removed: " + a2.removeLast());
        System.out.println("Removed: " + a2.removeLast());
        System.out.println("Removed: " + a2.removeLast());
        System.out.println("Removed: " + a2.removeLast());
        System.out.println("Removed: " + a2.removeLast());
        System.out.println("Removed: " + a2.removeLast());
        a2.printDeque();
        System.out.println();
        a2.addLast("A");
        a2.printDeque();
        System.out.println();
    }
}
