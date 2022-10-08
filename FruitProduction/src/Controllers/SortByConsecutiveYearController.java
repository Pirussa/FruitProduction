package Controllers;

import Domain.*;
import Stores.DataStore;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortByConsecutiveYearController {

    DataStore fruitStore = App.getInstance().getStore();

    public Map<Country, Integer> sortByConsecutiveYears(Fruit f) {
        Map<Country, Map<Year, Quantity>> map = fruitStore.getFruitHarvest().get(f);
        Map<Country, Integer> countryMap = new LinkedHashMap<>();

        for (Map.Entry<Country, Map<Year, Quantity>> entry : map.entrySet()) {
            Map<Year, Quantity> yearQuantityMap = entry.getValue();
            Country country = entry.getKey();
            countryMap.put(country, getConsecutiveYears(yearQuantityMap));
        }

        return countryMap;
    }


    private int getConsecutiveYears(Map<Year, Quantity> yearQuantityMap) {
        Quantity quantity = yearQuantityMap.entrySet().stream().findFirst().get().getValue();
        int consecutiveYears = 0, max = 0;

        Iterator<Map.Entry<Year, Quantity>> itr = yearQuantityMap.entrySet().iterator();
        Map.Entry<Year, Quantity> entry = itr.next();

        int size = yearQuantityMap.size();
        while(itr.hasNext())
        {
            entry = itr.next();
            if (quantity.getQuantity() < entry.getValue().getQuantity())
                consecutiveYears++;
            else {
                consecutiveYears = 0;

                if (max > size)
                    return max;
            }

            if (max < consecutiveYears)
                max = consecutiveYears;

            size--;
            quantity = entry.getValue();
        }
        return max;
    }

    public Map<Fruit, Map<Country, Map<Year, Quantity>>> getFruitHarvest() {
        return fruitStore.getFruitHarvest();
    }
}