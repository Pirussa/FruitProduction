package Utils;

public class BiggestProduction {

    private final String years;
    private final String fruit;
    private final int quantity;

    public BiggestProduction(String years, String fruit, int quantity){
     this.years=years;
     this.fruit=fruit;
     this.quantity = quantity;

    }
    @Override
    public String toString() {
        return "[" + years + "," + fruit + "," + quantity + "]";
    }
}
