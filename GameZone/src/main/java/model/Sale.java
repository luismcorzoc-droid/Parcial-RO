package model;

public class Sale {

    private String title;
    private int quantity;
    private double total;

    public Sale(String title, int quantity, double total) {
        this.title = title;
        this.quantity = quantity;
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Juego: " + title +
                " | Cantidad: " + quantity +
                " | Total: $" + total;
    }
}