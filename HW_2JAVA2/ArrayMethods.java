package HW_2;

public class ArrayMethods {
    private final static int LIMIT = 4;

    public static void checkArr(String[][] array) throws ArraySizeException{
        if (array.length != LIMIT ) throw new ArraySizeException(); // формируем исключения размера
        else System.out.println("Массив нужного размера"); // вывод для проверки
    }

    public static void arrSum (String[][] array) throws ArrayDataException {
        int sum = 0, numb;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                numb = Integer.parseInt(array[i][j]);            // строку приводит к целому числу
                sum = sum + numb;                               // считаем сумму массива
                } catch (NumberFormatException ex) {           // представление числа не отвечает требованию
                System.out.println("Data-mistake massive is caught!!!"); // вывод ошибки преобразования и ячейки
                System.out.println("Ошибка в строке " + (i+1) + " столбце " + (j+1) +  " элемент - " +array[i][j]);
                throw new ArrayDataException();             //  в таком случае формируем исключения преобразования
                }
            }
        }  System.out.println("Сумма массива = " +sum); // если исключения нет - выводим сумму
    }
}
