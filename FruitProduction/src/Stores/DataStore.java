package Stores;
import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import java.util.LinkedHashMap;
import java.util.Map;


public class DataStore {

    private Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = new LinkedHashMap<>();

    public Map<Fruit, Map<Country, Map<Year, Quantity>>> getFruitHarvest() {
        return fruitHarvest;
    }

    public void setFruitHarvest(Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest) {
        this.fruitHarvest = fruitHarvest;
    }

    public boolean isEmpty() {
        return fruitHarvest.isEmpty();
    }
}
