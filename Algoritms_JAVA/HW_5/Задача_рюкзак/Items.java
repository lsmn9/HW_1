package HW_5.BackPack;

public enum Items {
    LAPTOP(8, 5), DIAMOND(4, 2),
    PHONE(3, 3), GOLD(10, 1);

    private double price;
    private double weight;

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    Items(double price, double weight) {
        this.price = price;
        this.weight = weight;
    }
}
