package HW_5.BackPack;

import java.util.ArrayList;

public class Backpack {
    static int counter = 0;
    static double weight = 0;
    static double price = 0;
    static final double BACKPACK = 10;
    static final int THE_FIRST = 0;
    static double max = 0;
    static ArrayList items_array, optimal;

    public static void main(String[] args) {
        items_array = new ArrayList();

        System.out.println("Список всех вещей: ");
        for (Items item: Items.values()) {
            items_array.add(item);
            System.out.println( item.name()+ "  "+item.getPrice() +"$  "+ item.getWeight()+"kg");
        }
        System.out.println();
        showOptimalCase(items_array);
    }

    public static void showOptimalCase(ArrayList arr_list){
        for (int i = 0; i <arr_list.size() ; i++) {
            ArrayList arr_copy = new ArrayList();
            arr_copy.addAll(arr_list);
            Items temp = (Items) arr_list.get(THE_FIRST);
            arr_list.add(temp);
            arr_list.remove(THE_FIRST);
            tryToPut(arr_copy);
        }
        if (max == -1) {System.out.println("Любой из предметов слишком тяжелый!!!");}
        else {System.out.println("Оптимальный набор: " + optimal+ " = " + max + "$" );}
    }

    public static double tryToPut(ArrayList arr_list){
        Items item;
        if (arr_list.isEmpty()){
            return max = -1;
        }
        for (Object o : arr_list) {
            item = (Items) o;
            weight = weight + item.getWeight();
            price = price + item.getPrice();
        }
        if (BACKPACK >= weight){
            if (max<price){
                optimal = new ArrayList();
                max =price;
                optimal.addAll(arr_list);
                }
            price=0;
            return max;
        }
        else {weight=0; price=0; arr_list.remove((arr_list.size()-1));
            return tryToPut(arr_list);}
    }


}

