import java.util.Scanner;

public class Task4 {

    public static void main(String[] args) {
        System.out.println("Введите 2 числа");
        Scanner sc = new Scanner(System.in);
        float a, b;
        a = sc.nextFloat();
        b = sc.nextFloat();
        int a1 = (int)a;
        int b1 = (int)b;
        if (10 < a1 + b1 && a1 + b1 < 20){
            System.out.println(true);
        } else {
            System.out.println(false);

        }
    }
}
