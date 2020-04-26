package HW_2;

public class ArraySizeException extends Exception {
    @Override
    public String getMessage() {
        return "Массив НЕВЕРНОГО размера";
    }
}

