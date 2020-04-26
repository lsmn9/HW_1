package HW_3;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Collect {
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        String[] arrLetters = new String[]{"A", "B", "c", "d", "AB", "CD", "XY", "Z", "D", "CD", "O", "A", "1", "Z", "3", "Z"};
        Collections.addAll(set, arrLetters);

        System.out.println(set+"\nКоличество уникальных элементов = "+set.size());

    }


}
