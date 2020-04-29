package HW_5;

// Я не стал деллать скрин, закомитил результаты возле методов
// Но ради интереса написал метод для тройного разделения
public class myThread {
    static final int SIZE = 9999999; // уменьшил на 1, чтобы было кратно 3
    static final int HALF = SIZE / 2;
    static final int THIRD1 = SIZE / 3;//  переменные для метода
    static final int THIRD2 = (2* SIZE / 3);// с 3мя разрывами

    static void metod1(float[]arr){
        for (int i = 0; i <SIZE ; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

    static  void method2(float[]arr, float[] clone1, float [] clone2, int h){
        System.arraycopy(arr, 0, clone1, 0, h);
        System.arraycopy(arr, h, clone2, 0, h);
        System.arraycopy(clone1, 0, arr, 0, h);
        System.arraycopy(clone2, 0, arr, h, h);
    }
// Метод с 3мя разрывами
    static  void method3(float[]arr, float[] clone1, float [] clone2, float [] clone3,int h1, int h2){
        System.arraycopy(arr, 0, clone1, 0, h1);
        System.arraycopy(arr, h1, clone2, 0, h1);
        System.arraycopy(arr, h2, clone3, 0, h1);
        System.arraycopy(clone1, 0, arr, 0, h1);
        System.arraycopy(clone2, 0, arr, h1, h1);
        System.arraycopy(clone3, 0, arr, h2, h1);
    }


    public static void main(String[] args) {

        float[] arr = new float[SIZE];
        float[] clone1 = new float[HALF];
        float[] clone2 = new float[HALF];
        float[] clone3 = new float[HALF];

        //заполним массив единицами
        for (int i = 0; i < SIZE; i++) { arr[i] = 1;}

        long  a = System.currentTimeMillis();
        metod1(arr);
        System.out.println("Method1 time = " + (System.currentTimeMillis()-a)+" mls");
        // Время около 4 секунд для SIZE 10^7

        a = System.currentTimeMillis();
        method2(arr, clone1, clone2, HALF);
        System.out.println("Method2 time = " + (System.currentTimeMillis()-a)+" mls");
        // Время около 20 милисекунд для SIZE 10^7

        a = System.currentTimeMillis();
        method3(arr, clone1, clone2,clone3, THIRD1, THIRD2);
        System.out.println("Method3 time = " + (System.currentTimeMillis()-a)+" mls");
        // Время около 20 милисекунд для SIZE 10^7

        // Общий вывод:
        // Я пробывал разное количество элементов. Метод2 и Метод3 примерно справлялисьза одно и то же время.
        // Аналогия Метода3 вроде соблюдается верно.
        // Видимо для данной задачи разделение больше, чем на 2 не имеет смысла.
        // Хотя я пытался уйти в бОльшие числа, но после SIZE = 66699999 выскакивает OutOfMemoryError;
        // Поэтому изначальный вопрос о выборе оптимального количества потоков становится интересным :)
    }
}

