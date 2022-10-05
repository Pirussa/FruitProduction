package Domain;

public class Quantity {

    private final int quantity;

    public Quantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.valueOf(quantity);
    }
}
