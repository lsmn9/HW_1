package HW_6;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MethodsTest {

    private static int[] arr1 = {1,2,2,5,7,4,5,7};
    private static int[] arr1c = {5,7};
    private static int[] arr2 = {4,2,5,7,4,5,7,4};
    private static int[] arr2c = {};
    private static int[] arr3 = {1,4,2,5,0,5,7};
    private static int[] arr3c = {2,5,0,5,7};
    private static int[] arr4 = {1,2,2,5,7,5,7};

    @Test
    void arrayCuttertest() {
        Assertions.assertArrayEquals(arr1c, Methods.arrayCutter(arr1));
        Assertions.assertArrayEquals(arr2c, Methods.arrayCutter(arr2));
        Assertions.assertArrayEquals(arr3c, Methods.arrayCutter(arr3));
        Assertions.assertThrows(RuntimeException.class, ()-> Methods.arrayCutter(arr4));
    }

    @Test
    void oneFourFoundertest() {

        Assertions.assertTrue(Methods.oneFourFounder(arr1));
        Assertions.assertFalse(Methods.oneFourFounder(arr2));
        Assertions.assertTrue(Methods.oneFourFounder(arr3));
        Assertions.assertFalse(Methods.oneFourFounder(arr4));
    }
}