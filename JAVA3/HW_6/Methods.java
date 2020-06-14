package HW_6;

public class Methods {

    public static int[] arrayCutter(int[] array) throws RuntimeException{
        int temp=-1;
        for (int i = 0; i < array.length; i++) {
            if (array[i]==4) {temp = i;}
        }
        if (temp == -1) throw new RuntimeException();
        int[] no4arr = new int[array.length -(temp+1)];
        for (int i = 0, j=(temp+1); i < no4arr.length;j++, i++) {
            no4arr[i] = array[j];
        }
        return no4arr;
    }

    public static boolean oneFourFounder(int[] array){
        int cnt1 =0, cnt4=0;
        for (int value : array) {
            if (value ==1) cnt1++;
            if (value ==4) cnt4++;
        }
        return cnt1 > 0 & cnt4 > 0;
    }
}
