package HW_1;

public class Fruit {
    String name;
    float weight;

        public Fruit( float weight){
            this.weight=weight;
        }
    }

class Apple extends Fruit{
    public Apple( float weight) {
        super( weight);
    }
}

class Orange extends Fruit{
    public Orange( float weight) {
        super( weight);
    }
}

