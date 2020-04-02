public class HW_6 {
    public static void main(String[] args) {
        Cat cat = new Cat (" Барсик ", 0, 200, 2);
        Dog dog1 = new Dog(" Рекс ", 10, 500, 0.5);
        Dog dog2 = new Dog (" Дружок ", 10, 700, 1 );
        cat.info();
        cat.swim(3);
        cat.run(30);
        cat.jump(1);
        dog1.info();
        dog1.run(600);
        dog2.info();
        dog2.run(600);
    }
}


class Animal {
    double water, ground, air;
    double s, r, j;
    boolean possible = true, impossible = false;
    String name;
        public Animal (String name, double water, double ground, double air){
            this.water = water;
            this.ground = ground;
            this.air = air;
            this.name = name;
        }

         void swim (double s) {
            this.s = s;
            System.out.print( "Может ли" + name + "проплыть " + s + " м.? - "  );
            if ( s <= water) System.out.println(possible);
                else System.out.println(impossible);
            }
           void run (double r) {
            this.r = r;
              System.out.print( "Может ли" + name + "пробежать " + r + " м.? - "  );
            if (r <= ground) System.out.println(possible);
            else System.out.println(impossible);
    }
          void jump (double j) {
             System.out.print( "Может ли" + name + " прыгнуть на " + j + " м.? - "  );
            this.j = j;
            if (j <= air) System.out.println(possible);
            else System.out.println(impossible);
    }

}

class Cat extends Animal {

    public Cat(String name, int water, int ground, int air) {
        super(name, water, ground, air);
  }
    void swim (double s){
        System.out.print( "Может ли" + name + "проплыть " + s + " м.? - "  );
        System.out.println("Он же кот! Он не умеет плавать!");
  }
    void info(){
        System.out.println("\nЭто кот" + name);}
}

class Dog extends Animal {
    public Dog(String name, int water, int ground, double air) {
        super(name, water, ground, air);
    }

    void info(){
        System.out.println("\nЭто пёс" + name);}
}