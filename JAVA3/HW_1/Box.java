package HW_1;

import java.util.ArrayList;

public class Box<T extends Fruit> {

    ArrayList<T> volume = new ArrayList<>(); // объем коробки, бесконечный

    public void add (T item){ // добавляем в коробку

        volume.add(item);
    }

    public float getWeight(){  // смотрим вес
        float weight = 0;
        for (T t : volume) {
            weight += t.weight;
        }
        return  weight;}

    public boolean compare(Box box){  // сравниваем 2 коробки
        if(this.getWeight() == box.getWeight()) return true;
        else return false;
    }

    public void boxToBox(Box <T> box){ // пересыпаем
        if (this.equals(box)){  // на случай пересыпанию саму в себя
            System.out.println("Нельзя пересыпать из себя в себя же!");
        }
        else {
            for (int i = 0; i < box.volume.size(); i++) {
                this.volume.add((T) box.volume.get(i));
            }
            box.clearTheBox(); // чистим коробку
        }
    }

    private void clearTheBox(){
        this.volume.clear();
    }
}
