package Domain;

public class Quantity {

    private final int quantity;

    public Quantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.valueOf(quantity);
    }

}
