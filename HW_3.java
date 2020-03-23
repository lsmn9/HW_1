import java.util.Scanner;

public class HW_3
{
    public static void main(String[] args)
    {
        System.out.println("Привет! Попробуйте отгадать придуманный продукт питания.");

        String [] arr = new String [] {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut",
                "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        int random = (int) (Math.random() * (arr.length -1));

        String prod = arr [random], answer;

        do {

            System.out.println("Введите предполагаемый продукт");
            Scanner sc = new Scanner(System.in);
            answer = sc.nextLine();

            // для удобства проверки
            System.out.println (prod + "(выводится для удобства проверки задания)");

          if (answer.equals(prod)) { System.out.println("Вы угадали. Поздравляем!!!");
              break;}

          else {

              for (int i = 0; i < 16; i++)
              {

                  if (answer.length() >= prod.length() & prod.length() > i)
                  {
                         for (; i < prod.length(); i++) {
                                char x = prod.charAt(i);
                                char y = answer.charAt(i);
                                    if (x == y) {x = y;}
                                    else x = '#';
                                    System.out.print(x);
                                                         }

                  }
                  else if (prod.length() > answer.length() & answer.length() > i)
                  {

                     for (; i < answer.length(); i++) {
                         char x = prod.charAt(i);
                         char y = answer.charAt(i);
                             if (x == y) {x = y;}
                             else x = '#';
                            System.out.print(x);

                                                        }

                }
                  else  System.out.print('#');
              }

            System.out.println("\nВы не угадали, попробуйте снова!");

          }

        } while (!answer.equals(prod));

    }
}

