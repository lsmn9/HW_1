package HW_8;

public class Main {
    public static void main(String[] args) {

        Chains hashTable = new Chains(4);//4 * 2 = 8

        hashTable.put( "Orange", 150);
        hashTable.put("Oil",200);
        hashTable.put("Banana", 100);
        hashTable.put("Carrot" ,228);
        hashTable.put("Lemon", 250);
        hashTable.put("Milk", 120);
        hashTable.put("Potato", 67);
        hashTable.put("Lime",1000);
        hashTable.put("Apricot", 59);
        hashTable.put("Nuts", 88);
        hashTable.put("Melon",105);
        hashTable.put("Mint",5);

        System.out.println("Size is " + hashTable.size()); // 12
        hashTable.display();

        System.out.println("Cost potato is " + hashTable.get("Potato"));
        System.out.println("Cost banana is " + hashTable.get("Melon"));

        hashTable.remove("Potato");
        hashTable.remove("Melon");
        hashTable.remove("Lemon");

        System.out.println("Cost potato is " + hashTable.get("Potato"));
        System.out.println("Cost banana is " + hashTable.get( "Melon"));

        hashTable.display();

        hashTable.put("Apple", 25);
        hashTable.put("Tea", 3);

        hashTable.display();
        System.out.println("Size is " + hashTable.size()); // 3 удалили, 2 встали = начальный размер + 1
    }
}
