import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        System.out.println("Введите любой год");
        int year;
        Scanner sc = new Scanner(System.in);
        year = sc.nextInt();
        if (year % 400 == 0 ) {
            System.out.println("Високосный год");
        } else if (year % 100 == 0) {
            System.out.println("Невисокосный год");
        } else if (year%4 ==0) {
            System.out.println("Високосный"); // убрал слово "год", чтобы от 400ого года можно было отличить
        } else System.out.println("Невисокосный "); // аналогично

    }

}

