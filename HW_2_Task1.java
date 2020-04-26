import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Scanner;

public class HW_2_Task1 {

//задание 1
    public static void main(String [] args) {
        int[] arr = new int[]{0, 1, 1, 1, 0, 1, 1};

        System.out.print("Начальный массив ");
        for (int i = 0; i < 7; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(" ");
        System.out.print("Конечный массив ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) arr[i] = 0;
            else arr[i] = 1;
            System.out.print(arr[i] + " ");
        }
    }
}

// задание 2
class HW_2_Task2 {
    public static void main(String[] args) {
        int[] arr = new int [8];
        for (int i = 0, x = 0; i < arr.length; i ++, x+=3) {
            arr[i] = x;
            System.out.print(arr[i] + " ");
        }
    }
}

// задание3
class HW_2_Task3{
    public static void main(String [] args) {
        int[] arr = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        System.out.print("Начальный массив ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println(" ");
        System.out.print("Конечный массив ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) arr[i] *= 2 ;
            System.out.print(arr[i] + " ");
        }
    }
}

// задание 4

class HW_2_Task4{

    public static void main(String [] args) {

        int[][] arr = new int[5][5];
        int l = arr.length -1;
        for (int i = 0; i < arr.length; ++i) {
            for(int j = 0; j < arr [i].length; ++j) {
                arr [i][0+i] = 1;
                arr [i][l-i] = 1;

                System.out.print( arr [i][j] + " "  );
            }
            System.out.println();
        }
    }
}

// задание 5

class HW_2_Task5{
    public static void main(String[] args) {
        int [] arr = new int [8];
        for (int i = 0; i < arr.length; i++) {
            int l = (int) (Math.random() *20);
            arr [i] = l;
            System.out.print(arr[i] + " ");
        }

        int max = arr[arr.length-1] ;
        int min = arr[0];

        for (int i=0; i < arr.length; i++){
            int x = arr[i];
            if (x > max) max = arr[i];
            if (x < min) min = arr[i];
        }
        System.out.println("\nМаксимальное число массива = " + max);
        System.out.println("Минимальное число массива = " + min);
    }
}

// задание 6. C разделением не успел сделать

class HW_2_Task6{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bведите длину массива ");
        int v = sc.nextInt();
        int[] arr = new int[v];
        System.out.print("Получился массив:");
        for (int i = 0; i < arr.length; i++) {
            int l = (int) (Math.random() *3); //небольшое число для интереса
            arr[i] = l;
            System.out.print(arr[i] + " ");}

            int sum =0;
        for (int i = 0; i < v; i++ ){
           sum  +=  arr[i] ;
           }


        int tempSum = 0;
         for (int i = 0; i < v; i++ ){
           tempSum += arr[i];


           int dif =  sum - tempSum;
           if (dif == tempSum) {
           System.out.println("\n" + true); break; }

           else if (dif == 0) { System.out.println("\n Такого места нет!"); break;}

         }

    }
}







// задание 7
// без доп.массива не знаю как сохранить переменные, на которые перезаписываются другие. отправил то, что получилось.
class HW_2_Task7 {
    public static void main(String[] args) {

        System.out.println("Bведите n");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("Bведите длину массива ");
        int v = sc.nextInt();
        int[] arr = new int[v];
        int x;

        if (n > arr.length){ x = n%= arr.length; }
        else if (n < arr.length * -1) {x = n%= arr.length;}
        else x = n;

        for (int i = 0; i < arr.length; i++) {
            int l = (int) (Math.random() * 20);
            arr[i] = l;
            System.out.print(arr[i] + " ");
        }

        System.out.print("\n");

        for (int i = 0;  i < arr.length; i++) {
            if ((i + x) > arr.length - 1) {
                arr[i] = arr[i + x - arr.length];
            } else if ((i + x) < 0) {
                arr[i] = arr[i + x + arr.length];
            } else {
                arr[i] = arr[i + x];

                System.out.print(arr[i]  + " ");
            }


        }


    }
}
