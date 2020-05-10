package HW_5;

public class Req {
    public static void main(String[] args) {

        System.out.println(step(2,-3));
        System.out.println(step(-3,3));
        System.out.println(step(-3,4));
        System.out.println(step(-2,-3));
        System.out.println(step(-2.5,2));
    }
// только для целочисленных степеней
    public static double step(double n, int s){
        if (s==0) return 1; // базовый случай;
        if (n==0) return 0; // для ускорения процесса (0^100 = 1), а так потребуется 100 операций;
        if (n==1) return 1; // для такого же ускорения;
        if (s<0) return (1/n)*step(n, ++s); // рекурсивный случай положительных степеней;
        else { return n*step(n,--s);} // рекурсивный случай отрицательных степеней;
    }
}
