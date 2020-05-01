package HW_3;

public class Main {
    public static void main(String[] args) {
        QueueImpl qi = new QueueImpl(5);
        qi.insert(1); //добавляем 1 раз
        qi.insert("2"); //добавляем 2 раз
        qi.insert(3); //добавляем 3 раз
        qi.insert("4"); //добавляем 4 раз
        qi.insert(5);//добавляем 5 раз
        System.out.println("Первое удаление:"+ qi.remove());
        qi.insert(58); //добавляем 6 раз
        System.out.println("Второе " + qi.remove());
        qi.insert(159); //добавляем 7 раз
        System.out.println("Третье " + qi.remove());
        System.out.println("Четвертое " + qi.remove());
        System.out.println("Пятое " + qi.remove());
        System.out.println("Шестое " + qi.remove());
        System.out.println("Размер очереди равен = " + qi.size);

        for (int i = 0; i <qi.data.length ; i++) {
            System.out.print(qi.data[i] +" ");
        }
    }
}
