package HW_1;

import java.util.ArrayList;

class ArrayMethods <T>  {

    T [] array;

    public ArrayMethods(T[] array){
        this.array = (T[]) array;
    }
// задание 1
    public void changePos( int a, int b ){
        int max = Math.max(a,b);
        int min = Math.min(a,b);
        T temp;
        temp = array[min];
        array[min]=array[max];
        array[max]=temp;
    }
// не совсем понял, что имели ввиду в задании 2, сделал так:
    public ArrayList<T> arrayToList (T[] array){
        ArrayList <T> arList = new ArrayList<T>();
        for (T t : array) {
            arList.add(t);
        }
    return arList;}

    public static class  Test1_2 {
        public static void main(String[] args) {
            Integer [] array = new Integer[10];
            for (int i = 0; i < array.length; i++) {
                array[i] =i ;
            }
            ArrayMethods arr = new ArrayMethods<>(array);
            arr.changePos(0,9);
            for (Integer integer : array) {
                System.out.print(integer + " ");
            }

            ArrayList arrayList = arr.arrayToList(array);
            arrayList.add("List");
            arrayList.remove(5);
            System.out.println("\n"+arrayList);
        }
    }
}

