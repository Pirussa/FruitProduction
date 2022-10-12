package Controllers;

import Domain.*;
import Domain.Stores.DataStore;
import Miscellaneous.*;

import java.util.Map;

public class SortByConsecutiveYearController {

    DataStore fruitStore = App.getInstance().getStore();
    SortByConsecutiveYears sort = new SortByConsecutiveYears();

    public Map<Country, Integer> sortByConsecutiveYears(Fruit f) {
        Map<Country, Map<Year, Quantity>> fruitInfoMap = fruitStore.getFruitHarvest().get(f);
        return sort.sortByConsecutiveYears(fruitInfoMap);
    }


    public Map<Fruit, Map<Country, Map<Year, Quantity>>> getFruitHarvest() {
        return fruitStore.getFruitHarvest();
    }
}