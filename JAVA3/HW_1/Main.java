package HW_1;

public class Main {

    public static void main(String[] args) {
        Apple apple = new Apple(1f);
        Orange orange = new Orange(1.5f);
        Box<Apple> appleBox = new Box<>();
        Box<Apple> boxapl = new Box<>();
        Box<Orange> boxorn = new Box<>();


        boxapl.add(apple);
        boxapl.add(apple);
        boxapl.add(apple);
        //boxapl.add(orange);  на такое добавление Java ругается
        boxorn.add(orange);
        boxorn.add(orange);
        boxorn.add(orange);

        System.out.println(boxapl.getWeight()); // 3.0
        System.out.println(boxorn.getWeight()); // 4.5
        System.out.println(boxapl.compare(boxorn)); //false
        System.out.println(boxapl.compare(boxapl)); // true
        appleBox.boxToBox(boxapl); // пересыпаем
        System.out.println(boxapl.getWeight()); // должна стать пустой
        System.out.println(appleBox.getWeight()); // пересыпали сюда
    }
}
