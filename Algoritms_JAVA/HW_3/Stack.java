package HW_3;

public interface Stack<E> {

    void push(E value);

    E pop();

    E peek();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    boolean isFull();

}

