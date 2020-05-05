package HW_3;

public class DequeImpl<E> implements Deque<E> {

    protected final E[] data;
    protected int size;

    private int tail;
    private int head;
    private int maxSize;
    private E removedValue;
    private int b;

    public DequeImpl(int maxSize) {
        this.maxSize = maxSize;
        this.data = (E[]) new Object[maxSize];
        this.head = 0;
        this.tail = (maxSize - 1);
        this.b =(maxSize - 1);
    }


    @Override
    public boolean insertRight(E value) {
        if (isFull()) {
            System.out.println("Массив уже заполнен. Элемент " + value + " не добавлен!");
            return false;
        }
        if (head==0){head=0;}
        data[tail--] = value;
        size++;
        return true;
    }

    @Override
    public boolean insertLeft(E value) {
        if (isFull()) {
            System.out.println("Массив уже заполнен. Элемент " + value + " не добавлен!");
            return false;
        }
        if (head==maxSize){head=0;}
        data[head++] = value;
        size++;
        return true;
    }

    @Override
    public E removeRight() {
        if (isEmpty()) {
            System.out.print("Очередь пуста: ");
            return null;
        }
        if (tail != maxSize - 1) {
            removedValue = data[++tail];

        } else {
            removedValue = data[tail-b--];
           if(b==-1) {b=(maxSize-1);}

        }
        size--;
        return removedValue;
    }

    @Override
    public E removeLeft() {
        if (isEmpty()) {
            System.out.print("Очередь пуста: ");
            return null;
        }
        if (head != 0) {
            removedValue = data[--head];
        } else {
            removedValue = data[head+b--];
        }
        size--;
        return removedValue;
    }

    @Override
    public E peekFront() {
        if (isEmpty()) {
            return null;
        }
        if (head != 0) return data[head - 1];
        else return data[tail + size];
    }

    @Override
    public E peekBack() {
        if (isEmpty()) {
            return null;
        }
        if (tail != maxSize - 1) return data[tail + 1];
        else return data[head - size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return data.length == size;
    }
}