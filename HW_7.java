import java.util.Scanner;

class Plate {
    private int food;

    public Plate(int food) {
        this.setFood(food); }

        public void decreaseFood(int n) { // здесь формируем неотрицательную еду в миске(задание 2)
        if ((getFood() - n) >= 0) {       // задание 4 - это допущение, поэтому не пишем else
            setFood(getFood() - n);}
    }

    public void info() {
        System.out.println("plate: " + getFood()); }

    public void addFood() {  // метод для добавления еды, решил сделать ручной ввод (задание 6)
        System.out.println("Сколько еды добавить?");
        Scanner sc = new Scanner(System.in);
        int add = sc.nextInt();
        food += add; }

    public int getFood() {
        return food; }

    public void setFood(int food) {
        this.food = food; }
}

class Cat {
    private String name;
    public int appetite;
    boolean fullness;

    public Cat(String name, int appetite, boolean fullness) { // добавляем поле сытости (сыт / не сыт)(задание 3)
        this.name = name;
        this.appetite = appetite;
        this.fullness = fullness;
    }

    public void eat(Plate p) {
        if ((p.getFood() - appetite) >= 0) {fullness = true; }
        System.out.println("Кот "+name+ " наелся? - " + this.fullness); // выводим задание 3 в консоль
        p.decreaseFood(appetite);
    }
}

public class HW_7 {
    public static void main(String[] args) {
        Cat [] catArr = new Cat [3]; // массив с котами (задание 5)
    catArr [0] = new Cat("Barsik", 21,  false);
    catArr[1] = new Cat ("Vas'ka", 10, false);
    catArr[2] = new Cat ("Murzik", 7, false);
    Plate plate = new Plate(20);
    catArr[0].eat (plate); // ест первый кот (при данном потреблении не наестся)
    catArr[1].eat (plate); // ест второй кот
    catArr[2].eat (plate); // ест третий кот
    plate.info(); // остаток после 3х котов

    plate.addFood(); // добавляем еду вручную
    catArr[0].eat (plate); // второй шанс поесть для первого кота
    plate.info();

    }
}
