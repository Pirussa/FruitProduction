package Utils;

import Domain.*;

import java.util.*;

public class SortByConsecutiveYears {



    public Map<Country, Integer> sortByConsecutiveYears(Fruit f, Map<Country, Map<Year, Quantity>> fruitInfoMap) {
        Map<Country, Integer> countryMap = new LinkedHashMap<>();

        for (Map.Entry<Country, Map<Year, Quantity>> entry : fruitInfoMap.entrySet()) {
            Map<Year, Quantity> yearQuantityMap = entry.getValue();
            Country country = entry.getKey();
            countryMap.put(country, getConsecutiveYears(yearQuantityMap));
        }

        return countryMap;
    }


    private int getConsecutiveYears(Map<Year, Quantity> yearQuantityMap) {

        if (yearQuantityMap.isEmpty()){
            return 0;
        }
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
}
