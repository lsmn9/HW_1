package HW_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MainClass {

    public static Lock lock = new ReentrantLock(); // понадобится в классе Cars
    public static boolean isFirst = true; // для определения первого финишера
    public static final int CARS_COUNT = 4;
    public static  CountDownLatch readyCDL = new CountDownLatch(CARS_COUNT); // счетчик для готовых к поездке
    public static  CountDownLatch finishedCDL = new CountDownLatch(CARS_COUNT); // счетчик для финишировавших

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (Car car : cars) {
            new Thread(car).start();
        }
        readyCDL.await(); // ждем готовящихся
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        finishedCDL.await();//ждем пока все финишируют
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}
class Car implements Runnable {
    // барьер для ожидания неподготовленных участников
    private static CyclicBarrier cbr = new CyclicBarrier(MainClass.CARS_COUNT);
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cbr.await(); // ставим барьер
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainClass.readyCDL.countDown(); // отсчитываем готовых к гонке
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        MainClass.finishedCDL.countDown(); //отсчитываем финишировавших
        MainClass.lock.lock(); // ставим замок для "фотофиниша"
        if (MainClass.isFirst){ // если первого еще небыло, то есть isFirst =true;
                System.out.println(this.name + " is winner"); // то этот участник победил
                MainClass.isFirst = false; // теперь первый уже есть
        }
        MainClass.lock.unlock(); // размыкаем замок
    }
}

abstract class Stage {
    protected int length;
    protected String description;
    public String getDescription() {
        return description;
    } // из методички, не пригодилось
    public abstract void go(Car c);
}

class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {
    Semaphore smp = new Semaphore(2); // ограничиваем въезжающих в тоннель
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire(); // записываем участника в список заехавших в тоннель
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smp.release(); // убираем участника из числа находящихся в тоннеле
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Race {
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
