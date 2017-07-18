/*  Brenda Tran
    cssc0145
*/

package data_structures;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedVector<E> implements UnorderedListADT<E> {
    private final int DEFAULT_INIT_SIZE = 1000;
    private E[] Array;
    private int MaxSize, currentSize;

    public UnorderedVector() {
        currentSize = 0;
        MaxSize = DEFAULT_INIT_SIZE;
        Array = (E[]) new Object[MaxSize];
    }

    public void addFirst(E obj) { add(obj, 1); }

    public void addLast(E obj) { add(obj, currentSize + 1); }

    public void add(E obj, int position) {
        position--;
        if (isFull()) {
            MaxSize <<= 1;
            E TempArray[] = Array;
            Array = (E[]) new Object[MaxSize];
            for (int i = 0; i < currentSize; i++)
                Array[i] = TempArray[i];
        }
        if (position > currentSize || position < 0) {
            throw new NoSuchElementException("Unable to add object. Position out of range.");
        }
        if (Array[position] == null) {
            Array[position] = obj;
            currentSize++;
        } else {
            for (int i = currentSize - 1; i >= position; i--) {
                Array[i + 1] = Array[i];
            }
            Array[position] = obj;
            currentSize++;
        }
    }

    public E remove(int position) {
        position--;
        if (position <= currentSize && position >= 1) {
            E temp = Array[position];
            for (int i = position; i <= currentSize; i++) {
                Array[position] = Array[position + 1];
            }
            currentSize--;
            return temp;
        } else {
            throw new NoSuchElementException("Unable to remove position. Out of range.");
        }
    }

    public E remove(E obj) {
        int SecondTemp = find(obj);
        if (find(obj) != -1) {
            E temp = obj;
            for (int i = SecondTemp; i <= currentSize; i++) {
                Array[i] = Array[i + 1];
            }
            currentSize--;
            return temp;
        }
        return null;
    }

    public E removeFirst() {
        E temp = Array[0];
        if (Array[0] == null)
            return null;
        for (int i = 0; i <= currentSize; i++) {
            Array[i] = Array[i + 1];
        }
        currentSize--;
        return temp;
    }

    public E removeLast() {
        E temp = Array[currentSize - 1];
        if (Array[currentSize - 1] == null)
            return null;
        currentSize--;
        return temp;
    }

    public E get(int position) {
        position--;
        if (position > currentSize - 1 || position < 0)
            throw new NoSuchElementException("Unable to get Position. Out of range.");
        return Array[position];
    }

    public E get(E obj) {
        int temp = find(obj);
        if (find(obj) != -1)
            return Array[temp];
        return null;

    }

    public int find(E obj) {
        for (int i = 0; i < currentSize; i++)
            if (((Comparable<E>) obj).compareTo(Array[i]) == 0)
                return i;
        return -1;
    }

    public boolean contains(E obj) { return find(obj) != -1; }

    public void clear() { currentSize = 0; }

    public boolean isEmpty() { return currentSize == 0; }

    public boolean isFull() { return currentSize == MaxSize; }

    public int size() { return currentSize;}

    public Iterator<E> iterator() {
        return new IteratorHelper();
    }

    class IteratorHelper implements Iterator<E> {
        private int index;

        public IteratorHelper() {
            index = 0;
        }

        public boolean hasNext() {
            return index < currentSize;
        }

        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            return Array[index++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}

