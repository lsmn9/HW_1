import java.util.Random;
import java.util.Scanner;

public class HW_4 {


    public static class MainClass {
        public static int SIZE = 5;
        public static int DOTS_TO_WIN = 4;
        public static final char DOT_EMPTY = '•';
        public static final char DOT_X = 'X';
        public static final char DOT_O = 'O';
        public static char[][] map;
        public static Scanner sc = new Scanner(System.in);
        public static Random rand = new Random();
        public static void main(String[] args) {
            initMap();
            printMap();
            while (true) {
                humanTurn();
                printMap();
                if (checkWin(DOT_X)) {
                    System.out.println("Победил человек");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }
                aiTurn();
                printMap();
                if (checkWin(DOT_O)) {
                    System.out.println("Победил Искуственный Интеллект");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }
            }
            System.out.println("Игра закончена");
        }


        // здесь происходит проверка.
        // Знаю, что написано плохо, нужно просто метод написать по-хорошему для всех этих If-ов)
        // времени нет доработать, а для 5 на 5 работает) "8" - просто рандомный символ
        public static boolean checkWin(char symb) {

            String X_Winner ="" , O_Winner = "";
            for (int i = 0; i < DOTS_TO_WIN ; i++) {
                X_Winner = X_Winner + "X";
                O_Winner = O_Winner + "O";
            }
            
            for (int i = 0; i < SIZE; i++) {
                String a="", b = "", c = "", d ="", e="", f="", g="", h="";

                for (int j = 0; j <SIZE; j++) {
// проверяем строки
                    if (map[i][j]==symb)
                    { a = a + symb ;}
                    else { a = a + 8;}
                    if (a.regionMatches(0, X_Winner, 0, 4) || a.regionMatches(1, X_Winner, 0, 4)) return true;
                    if (a.regionMatches(0, O_Winner, 0, 4) || a.regionMatches(1, O_Winner, 0, 4) ) return true;
// проверяем столбцы
                  if (map[j][i]==symb )
                    { b = b + symb ;}
                  else {b = b +8;}
                    if (b.regionMatches(0, X_Winner, 0, 4) || b.regionMatches(1, X_Winner, 0, 4)) return true;
                    if (b.regionMatches(0, O_Winner, 0, 4) || b.regionMatches(1, O_Winner, 0, 4) ) return true;
// проверяем 2 главные диагонали
                    if ( map[j][j] == symb )
                    { c = c + symb ;}
                       else {c = c +8;}
                      if (c.regionMatches(0, X_Winner, 0, 4) || c.regionMatches(1, X_Winner, 0, 4)) return true;
                       if (c.regionMatches(0, O_Winner, 0, 4) || c.regionMatches(1, O_Winner, 0, 4) ) return true;

                    if (map[j][SIZE - 1- j] == symb)
                    { d = d + symb ;}
                    else {d = d +8;}
                     if (d.regionMatches(0, X_Winner, 0, 4) || d.regionMatches(1, X_Winner, 0, 4)) return true;
                    if (d.regionMatches(0, O_Winner, 0, 4) || d.regionMatches(1, O_Winner, 0, 4) ) return true;

                }
                for (int j = 0; j <DOTS_TO_WIN ; j++) {

// проверяем другие диагонали
                if (map[j][DOTS_TO_WIN-1-j]==symb)
                { e = e + symb ;}
                else { e = e + 8;}
                if (e.regionMatches(0, X_Winner, 0, 4) ) return true;
                if (e.regionMatches(0, O_Winner, 0, 4)  ) return true;

                if (map[SIZE-DOTS_TO_WIN+j][SIZE-1-j]==symb )
                { f = f + symb ;}
                else {f = f +8;}
                if (f.regionMatches(0, X_Winner, 0, 4) ) return true;
                if (f.regionMatches(0, O_Winner, 0, 4)  ) return true;

                if ( map[j][SIZE-DOTS_TO_WIN+j] == symb )
                { g = g + symb ;}
                else {g = g +8;}
                if (g.regionMatches(0, X_Winner, 0, 4) ) return true;
                if (g.regionMatches(0, O_Winner, 0, 4)  ) return true;

                if (map[SIZE-DOTS_TO_WIN+j][j] == symb)
                { h = h + symb ;}
                else {h = h +8;}
                if (h.regionMatches(0, X_Winner, 0, 4) ) return true;
                if (h.regionMatches(0, O_Winner, 0, 4) ) return true; }
            }
            return false;
        }


        public static boolean isMapFull() {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] == DOT_EMPTY) return false;
                }
            }
            return true;
        }
        public static void aiTurn() {
            int x, y;
            do {
                x = rand.nextInt(SIZE);
                y = rand.nextInt(SIZE);
            } while (!isCellValid(x, y));
            System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
            map[y][x] = DOT_O;
        }
        public static void humanTurn() {
            int x, y;
            do {
                System.out.println("Введите координаты в формате X Y");
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)
            map[y][x] = DOT_X;
        }
        public static boolean isCellValid(int x, int y) {
            if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
            if (map[y][x] == DOT_EMPTY) return true;
            return false;
        }
        public static void initMap() {
            map = new char[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    map[i][j] = DOT_EMPTY;
                }
            }
        }
        public static void printMap() {
            for (int i = 0; i <= SIZE; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < SIZE; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

        }
