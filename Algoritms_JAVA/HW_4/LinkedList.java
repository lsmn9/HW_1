package HW_4;

import java.util.Iterator;

public interface LinkedList<E> extends Iterable<E> {
    //O(1)
    void insertFirst(E value);

    //O(1)
    E removeFirst();

    //O(n)
    boolean remove(E value);

    //O(n)
    boolean contains(E value);

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    void display();

    E getFirst();

    Entry<E> getFirstElement();

// методы с заглушками;
    class Entry<E> implements Iterator<E> {
        public E value;
        public Entry<E> next;

        public Entry(E value) {
            this.value = value;
        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }
    }

}
