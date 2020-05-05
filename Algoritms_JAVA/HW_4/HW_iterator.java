package HW_4;

public class HW_iterator {
    public static void main(String[] args) {
        LinkedList<Object> linkedList = new SimpleLinkedListImpl<>();
        linkedList.insertFirst(4);
        linkedList.insertFirst('?');
        linkedList.insertFirst(1);
        linkedList.remove(4);
        linkedList.removeFirst();
        linkedList.insertFirst("is HW completed");

        linkedList.iterator();

        System.out.println("---------");

        for (Object o : linkedList) {
            System.out.println(o);
        }
    }
}
