import java.util.Scanner;

public class Task3 {

   public static void main(String[] args) {


        System.out.println("Введите по порядку числа a,b,c,d. Расчет будет произведен по формуле: a*(b+c/d)");

        Scanner sc = new Scanner(System.in);
        double a, b, c, d, sum;
        a = sc.nextDouble();
        b = sc.nextDouble();
        c = sc.nextDouble();
        d = sc.nextDouble();
        sum = a* (b + c/d) ;
        System.out.println("Значение выражения = " + sum);
        sc.close();
    }
}