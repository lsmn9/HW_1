package HW_3;

import java.util.ArrayList;
import java.util.HashMap;


public class NumbersList {
    public static void main(String[] args) {
        ArrayList<String> person = new ArrayList<>();
        ArrayList<String> number = new ArrayList<>();
        person.add("Ivanov");   number.add("1");
        person.add("Petrov");   number.add("2");
        person.add("Ivanov");   number.add("3");
        person.add("Sidorov");  number.add("4");
        person.add("Sidorov");  number.add("5");
        person.add("Ivanov");   number.add("6");
        String foundName = "Sidorov";
        HashMap<Object, Object> hm = new HashMap<>();
        for (int i = 0; i < person.size(); i++) {
            hm.put (person.get(i),number.get(i));
            if (foundName.equals(person.get(i))){
                System.out.println(person.get(i) +" "+hm.get(person.get(i)));
            }
        }

    }
}

