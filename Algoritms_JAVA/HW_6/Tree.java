package HW_6;

    public interface Tree<E extends Comparable<? super E>> {

        enum TraverseMode {
            IN_ORDER,
            PRE_ORDER,
            POST_ORDER,
        }

        boolean add(E value);

        boolean contains(E value);

        boolean remove(E value);

        boolean isEmpty();

        int size();

        void display();

        void traverse(TraverseMode mode);

        // добавил 3 метода:

        int levelCounter(E value); // отслеживает уровень глубины

        void balanceConter(E value);// отслеживает количество элементов в полудереве

        int balanceCounter();// считает количество сбалансированный деревьев
    }

