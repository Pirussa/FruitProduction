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

    public String getYears() {
        return years;
    }

    public String getFruit() {
        return fruit;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "The Biggest Absolute Difference of Production is ---> [" + years + "," + fruit + "," + quantity + "]";
    }
}
