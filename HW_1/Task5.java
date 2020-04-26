import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        System.out.println("Введите целое число");
        Scanner sc = new Scanner(System.in);
        int a;
        a = sc.nextInt();
        if (a < 0) {
            System.out.println("Число " + a + " - отрицательное");
        } else {
            System.out.println("Число " + a + " - положительное");}
    }
}