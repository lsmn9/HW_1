package HW_2;


public class Main {
    public static void main(String[] args)  {
        // Корректный массив
        String [][]arrNumbers = new String [][] {
                {"1","2","3","4"},
                {"1","5","3","4"},
                {"7","2","-1","4"},
                {"1","2","3","-9"} };
        // Массив с буквами
        String [][]arrLetters = new String [][] {
                {"1","2","3","4"},
                {"1","Z","3","4"},
                {"Z","2","Z","4"},
                {"1","Z","3","Z"} };
        // Массив другой размерности
        String [][] arrAnotherSize = new String[3][4];



        try {
            ArrayMethods.checkArr(arrNumbers); // без ошибки
            ArrayMethods.arrSum(arrNumbers); // сумма без ошибки
            ArrayMethods.checkArr(arrAnotherSize); // ArraySizeException
            ArrayMethods.arrSum(arrLetters); // ArrayDataException
        } catch (ArraySizeException  ex){ System.out.println("Size-mistake massive is caught");
        } catch (ArrayDataException ex) {// вывод сделан в методе, для логичности вывода в консоль
        }
    }
}




