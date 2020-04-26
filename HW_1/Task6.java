import javax.naming.NameNotFoundException;
import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        System.out.println("Введите число");
        Scanner sc = new Scanner(System.in);
        double a;
        a = sc.nextDouble();
        int a1 = (int) a;
        if (a1 < 0) {System.out.println(true);
        }
        else {System.out.println("Внимание! Число положительное");
        }
    }

}
