package HW_8;

import java.util.LinkedList;

public class Chains <K, V> extends HashTableImpl<K, V> {  // расширяем класс из Вебинара 8;

   static private LinkedList [] chain;
   private int size;

    public Chains(int maxSize) {
        super(maxSize);
        this.chain = new LinkedList [maxSize*2];
        for (int i = 0; i < chain.length; i++) {  // заполняем массив связанными списками;
            chain[i] = new LinkedList();
        }
    }

@Override
    public boolean put(K key, V value) {
        int count =-1; // локальный счетчик
        Item item = new Item (key, value);
        for (int i=0; i<chain.length; i++ ) {
            if (chain[i].size() == 0) {    // если элемет массива(связанный список) =0, то:
                count = i;                // счетчик запоминает позиции крайнего пустого связанного списка;
            } else {  // если же список не пустой,то смотрим первые буквы ключа данного элемента и первого из списка:
                if (item.getKey().toString().charAt(0) == chain[i].get(0).toString().charAt(0)){ //если совпадают:
                    chain[i].add(item);  // добавляем элемент в список со "своей" буквой;
                    break; // выходим из цикла;
                }
            }
            if (i == (chain.length-1)){ // на последнем шаге цикла:
                if (count ==-1){ // если счетчик не увеличился, значит не было пустых списков или подходящей буквы
                    System.out.println("Для "+item.getKey()+ " нет места для вставки"); // сообщаем пользователю
                    return false;
                }
                else chain[count].add(item); // если же счетчик увеличился помещаем элемент в крайнее значение счетчика
            }
        }
        size++;
        return true;
    }

    @Override
    public V get(K key) {
        for (int i=0; i<chain.length; i++ ) {
            if (chain[i].size() != 0) { // в методе взятия имеет смысл смотреть только НЕпустые списки, если такой есть:
                if (key.toString().charAt(0) == chain[i].get(0).toString().charAt(0)){ //ищем список по букве, если есть:
                    for (int j = 0; j < chain[i].size(); j++) { // пробегаем по списку:
                        if (((Item<K, V>) chain[i].get(j)).getKey().equals(key)) { // если ключ совпал:
                            return ((Item<K, V>) chain[i].get(j)).getValue(); // возвращаем значение;
                        }
                   }
                    break; // дальше смысла искать нет - выходим из цикла;
                }
            }
        } // если такого списка или элемента в списке нет:
        System.out.println("Элемент " +key+ " не найден"); // сообщаем пользователю об этом;
        return  null; //возвращаем нулл;
    }

    @Override
    public V remove(K key) {
        for (int i=0; i<chain.length; i++) {
            if (chain[i].size() != 0) { // также ищем только НЕпустой список
                if (key.toString().charAt(0) == chain[i].get(0).toString().charAt(0)){ //поиск списка по букве
                    for (int j = 0; j < chain[i].size(); j++) { // просматриваем список, если подошёл
                        if (((Item<K, V>) chain[i].get(j)).getKey().equals(key)) { // ключ совпал
                            chain[i].remove(j); // удаляем элемент связанного списка
                        }
                    }
                    size--;
                    break; // смысла смотреть больше нет
                }
            }
        }
        return  null; // если не нашли элемент
    }

    @Override
    public void display() {
        System.out.println("----------");
        for (int i = 0; i < chain.length; i++) {
            System.out.printf("%d = [%s]", i, chain[i]);
            System.out.println();
        }
        System.out.println("----------");
    }

    @Override
    public int size() {
        return size;
    }
}
