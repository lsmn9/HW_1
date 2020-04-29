package HW_1;

public class Massives {
    // пункт 2 просмотрел код еще раз - всё понятно
    // сделал методы сортировок для целочисленных массивов

    static final  int S =10000; // размер массива
    // метод замены
    static private void swap(int index1, int index2, int[] data) {
        int temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    // пузырьковая, я сделал без флага
    static  public void sortBubble( int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) { swap(j, j + 1, data); }
            }
        }
    }
    // выбором
   static public void sortSelect( int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIndex]) { minIndex = j;}
            }
            if (minIndex != i) { swap(minIndex, i, data);}
        }
    }
    // вставкой
    static public void sortInsert(int[] data ) {
        for (int i = 1; i < data.length; i++) {
            int temp = data[i];
            int in = i;
            while (in > 0 && (data[in - 1] > temp) ) {
                data[in] = data[in - 1];
                in--;
            } data[in] = temp;
        }
    }

    public static void main(String[] args) {
        int [] arr = new int[S];
        int[] arrCopy = new int[S];// массив-копия

        // заполянем массив arr числами
        for (int i = 0; i < S ; i++) {
            int rnd = (int) (Math.random() * 10);
            arr[i] = rnd;
        }
// копируем массив arr в arrCopy для объективности результатов
        System.arraycopy(arr, 0, arrCopy, 0, S);

        long  t = System.currentTimeMillis();//засекаем время для sortBubble
        sortBubble(arrCopy);
        System.out.println("sortBubble time = " + (System.currentTimeMillis()-t)+" mls");
        // как правило около 250 млс для S = 10^4
        // около 26c для S=10^5
        // разница более чем в 100 раз

        t = System.currentTimeMillis(); //засекаем время для sortSelect
        sortSelect(arrCopy);
        System.out.println("sortSelect time = " + (System.currentTimeMillis()-t)+" mls");
        // как правило около 50 млс для S = 10^4
        // около 2с для S=10^5
        // разница примерно в 40 раз

        t = System.currentTimeMillis();//засекаем время для sortInsert
        sortInsert(arrCopy);
        System.out.println("sortInsert time = " + (System.currentTimeMillis()-t)+" mls");
        // 0-1 для S = 10^4
        // 4-5 млс для S=10^5
        // разница в 5 раз
    }
}





