package Controllers;

import Domain.*;
import Stores.DataStore;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortByConsecutiveYearController {

    DataStore fruitStore = App.getInstance().getStore();

    public Map<Country, Integer> sortByConsecutiveYears(Fruit f) {
        Map<Country, Map<Year, Quantity>> map = fruitStore.getFruitHarvest().get(f);
        Map<Country, Integer> countryMap = new LinkedHashMap<>();

        for (Map.Entry<Country, Map<Year, Quantity>> entry : map.entrySet()) {
            Country country = entry.getKey();
            countryMap.put(country, getConsecutiveYears(entry));
        }

        return countryMap;
    }


    private int getConsecutiveYears(Map.Entry<Country, Map<Year, Quantity>> entry) {
        Quantity quantity = new Quantity(0);
        int consecutiveYears = 0, max = 0;

        int size = entry.getValue().size();
        for (Map.Entry<Year, Quantity> yearEntry : entry.getValue().entrySet()) {

            if (quantity.getQuantity() < yearEntry.getValue().getQuantity())
                consecutiveYears++;
            else {
                consecutiveYears = 0;

                if (max > size)
                    return max;
            }

            if (max < consecutiveYears)
                max = consecutiveYears;

            size--;
            quantity = yearEntry.getValue();
        }
        return max;
    }

    public Map<Fruit, Map<Country, Map<Year, Quantity>>> getFruitHarvest() {
        return fruitStore.getFruitHarvest();
    }
}