package HW_3;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    // 1 пункт
      public static void main(String[] args) {
          System.out.println("Задание 1");
        QueueImpl qi = new QueueImpl(5);
        qi.insert(1); 
        qi.insert("2"); 
        qi.insert(3f);
        System.out.println(qi.remove());
        System.out.println(qi.remove());
        System.out.println(qi.peekFront());
        System.out.println("Размер очереди равен = " + qi.size);
// 2 пункт
          System.out.println("\nЗадание 2");
          System.out.println("Введите строчку");
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        StackImpl si = new StackImpl(string.length());
        int n = 0;

        while (n!=(string.length())){
            si.push(string.charAt(n));
            n++; 
        }
        System.out.print("Строка наоборот: ");
        while (!si.isEmpty()){ 
            System.out.print(si.pop()); 
        }
          System.out.println();
// 3 пункт
          System.out.println("\nЗадание 3");
          DequeImpl di = new DequeImpl(5);
          di.insertLeft("1l");
          di.insertRight("1r");
          di.insertLeft("2l");
          di.insertRight("2r");
          di.insertRight("3r");
          di.insertLeft("3l"); // лишний элемент
          System.out.println("Удален слева " +di.removeLeft());
          System.out.println("Крайний слева " + di.peekFront());
          System.out.println("Удален справа " +di.removeRight());
          System.out.println("Удален справа " +di.removeRight());
          System.out.println("Крайний справа " +di.peekBack());
          System.out.println("Удален слева " +di.removeLeft());
          System.out.println("Удален слева " +di.removeLeft());
          System.out.println("Удален слева " +di.removeLeft()); // удаление при пустом массиве
          System.out.println("Размер массива " + di.size);
    }
}
