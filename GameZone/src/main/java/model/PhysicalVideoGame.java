package model;

public class PhysicalVideoGame extends VideoGame {

    private boolean used;

    public PhysicalVideoGame(String title,
                             String platform,
                             double price,
                             int stock,
                             boolean used) {

        super(title, platform, price, stock);

        this.used = used;
    }

    @Override
    public double calculateFinalPrice() {

        if (used) {
            return price - (price * 0.25);
        }

        return price;
    }
}