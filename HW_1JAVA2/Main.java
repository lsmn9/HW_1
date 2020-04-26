package HW_1;

class Human implements Movies {
    boolean isItPossible  = true;
    protected String name;
    double run, jump;
    Human (String name, double run, double jump){
        this.name = name;
        this.run = run;
        this.jump = jump;
    }

    @Override
    public Boolean getValid() {
        return isItPossible;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run(double r) {
        if (isItPossible)
        if (r > run) {
            System.out.println(name +" дальше не идет\n");
            isItPossible = false;
        }
    }

    @Override
    public void jump(double j) {
        if(isItPossible)
        if (j > jump) {
            System.out.println(name +" дальше не идет\n" );
            isItPossible = false;
        }
    }
}

class Cat implements Movies {
    boolean isItPossible  = true;
    protected String name;
    double run, jump;
    Cat (String name, double run, double jump){
        this.name = name;
        this.run = run;
        this.jump = jump;
    }
    @Override
    public Boolean getValid() {
        return isItPossible;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run(double r) {
        if (isItPossible)
            if (r > run) {
                System.out.println(name +" дальше не идет\n");
                isItPossible = false;
            }
    }

    @Override
    public void jump(double j) {
        if(isItPossible)
            if (j > jump) {
                System.out.println(name +" дальше не идет\n" );
                isItPossible = false;
            }
    }

}

class Robot implements Movies {
    boolean isItPossible  = true;
    protected String name;
    double run, jump;
    Robot (String name, double run, double jump){
        this.name = name;
        this.run = run;
        this.jump = jump;
    }
    @Override
    public Boolean getValid() {
        return isItPossible;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run(double r) {
        if (isItPossible)
            if (r > run) {
                System.out.println(name +" дальше не идет\n");
                isItPossible = false;
            }
    }
    @Override
    public void jump(double j) {
        if(isItPossible)
            if (j > jump) {
                System.out.println(name +" дальше не идет\n" );
                isItPossible = false;
            }
    }
}

class Obstacle {
    public double obstacle;
    public String name;
    Obstacle(String name, double obstacle){
        this.name =name;
        this.obstacle = obstacle;
    }
}
class Road extends Obstacle{
    public Road(String name, double obstacle) {
        super(name, obstacle);
    }
}
class Wall extends Obstacle{
    public Wall(String name, double obstacle) {
        super(name, obstacle);
    }
}

public  class Main {
    public static void main(String[] args) {


        Wall w1 = new Wall("Wall#1", 2);
        Wall w2 =  new Wall ("Wall#2", 3);
        Road r1 = new Road("Road#1", 600);
        Road r2 = new Road ("Road#2", 1400);

        Movies h1 = new Human("human1",1200,1);
        Movies h2 = new Human("human2",1500, 1.2);
        Movies rb1 = new Robot("robo1", 3000, 5);
        Movies c1 = new Cat("cat1", 500, 3.1);
        Movies c2 = new Cat("cat2", 1000, 1.5);

        Movies [] sports = {h1, h2, rb1, c1, c2};
        Obstacle [] obs = {r1, r2, w2, w1};


        for (Movies sport : sports) {
            for (Obstacle ob : obs) {
                if (ob instanceof Wall) {
                    sport.jump(ob.obstacle);
                    if (sport.getValid()) {
                        System.out.println(sport.getName() + " перепрыгнул стену " + ob.name);
                        if (ob == obs[(obs.length - 1)]) {
                            System.out.println(sport.getName() + " прошел всю полосу препятствий!\n");
                        }
                    }
                } else {
                    sport.run(ob.obstacle);
                    if (sport.getValid()) {
                        System.out.println(sport.getName() + " пробежал дорогу " + ob.name);
                        if (ob == obs[(obs.length - 1)]) {
                            System.out.println(sport.getName() + " прошел всю полосу препятствий! \n");
                        }
                    }
                }
            }
        }
    }
}


