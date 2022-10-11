package Controllers;

import Domain.*;
import Stores.DataStore;
import Utils.*;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortByConsecutiveYearController {

    DataStore fruitStore = App.getInstance().getStore();
    SortByConsecutiveYears sort = new SortByConsecutiveYears();

    public Map<Country, Integer> sortByConsecutiveYears(Fruit f) {
        Map<Country, Map<Year, Quantity>> fruitInfoMap = fruitStore.getFruitHarvest().get(f);
        return sort.sortByConsecutiveYears(f, fruitInfoMap);
    }


    public Map<Fruit, Map<Country, Map<Year, Quantity>>> getFruitHarvest() {
        return fruitStore.getFruitHarvest();
    }
}