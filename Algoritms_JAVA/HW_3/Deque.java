package HW_3;

public interface Deque <E> {

    boolean insertRight(E value);

    boolean insertLeft(E value);

    E removeRight();

    E removeLeft();


    E peekFront();

    E peekBack();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    boolean isFull();

}
