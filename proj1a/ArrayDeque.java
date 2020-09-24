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
        }
    }

    public void addFirst(T item) {
        checkSize();
        if (nextFirst < 0) {
            nextFirst = arr.length - 1;
        }
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
        if (size == 0) {
            return;
        }
        for (T item : arr) {
            System.out.print(item + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
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
        if (size == 0) {
            return null;
        }
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
}
