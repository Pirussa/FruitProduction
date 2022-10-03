package Stores;
import Domain.Country;
import Domain.Fruit;
import java.time.Year;
import java.util.LinkedHashMap;


public class FruitStore {

    private LinkedHashMap<Fruit, LinkedHashMap<Country, LinkedHashMap<Year, Integer>>> fruitHarvest = new LinkedHashMap<>();

    public LinkedHashMap<Fruit, LinkedHashMap<Country, LinkedHashMap<Year, Integer>>> getFruitHarvest() {
        return fruitHarvest;
    }

    public void setFruitHarvest(LinkedHashMap<Fruit, LinkedHashMap<Country, LinkedHashMap<Year, Integer>>> fruitHarvest) {
        this.fruitHarvest = fruitHarvest;
    }

}
