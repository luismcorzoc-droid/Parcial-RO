package model;

public abstract class VideoGame {

    protected String title;
    protected String platform;
    protected double price;
    protected int stock;

    public VideoGame(String title, String platform, double price, int stock) {
        this.title = title;
        this.platform = platform;
        this.price = price;
        this.stock = stock;
    }

    public abstract double calculateFinalPrice();

    public String getTitle() {
        return title;
    }

    public String getPlatform() {
        return platform;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return title + " - " + platform;
    }
}