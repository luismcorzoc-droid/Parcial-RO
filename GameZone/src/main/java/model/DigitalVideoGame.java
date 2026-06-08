package model;

public class DigitalVideoGame extends VideoGame
        implements Downloadable, Sellable {

    private double sizeGB;

    public DigitalVideoGame(String title,
                            String platform,
                            double price,
                            int stock,
                            double sizeGB) {

        super(title, platform, price, stock);
        this.sizeGB = sizeGB;
    }

    @Override
    public double calculateFinalPrice() {

        if (sizeGB > 50) {
            return price + 5000;
        }

        return price;
    }

    @Override
    public void download() {
        System.out.println("Descargando videojuego...");
    }

    public double getSizeGB() {
        return sizeGB;
    }

    public void setSizeGB(double sizeGB) {
        this.sizeGB = sizeGB;
    }

    @Override
    public String toString() {
        return super.toString() + " Tamaño: " + sizeGB + " GB";
    }
}