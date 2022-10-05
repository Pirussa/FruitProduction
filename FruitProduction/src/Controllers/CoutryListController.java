package Controllers;

import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CoutryListController {

    private final DataStore fruitStore = App.getInstance().getStore();

    public CoutryListController() {
    }

    private Map<Country, Map<Year, Quantity>> findCountryYearQuantity(Fruit F, Quantity Q) {
        Map<Country, Map<Year, Quantity>> harvestPerCountryMap = fruitStore.getFruitHarvest().get(F);
        Map<Year, Quantity> yearQuantityMap = new LinkedHashMap<>();
        Map<Country, Map<Year, Quantity>> harvestQuantitySuperiorMap = new LinkedHashMap<>();
        boolean flag = false;

        for (Map.Entry<Country, Map<Year, Quantity>> countryYearQuantityEntry : harvestPerCountryMap.entrySet()) {

            yearQuantityMap.clear();

            for (Map.Entry<Year, Quantity> yearQuantityEntry : countryYearQuantityEntry.getValue().entrySet()) {

                if (yearQuantityEntry.getValue().getQuantity() > Q.getQuantity() && !flag) {
                    yearQuantityMap.put(yearQuantityEntry.getKey(), yearQuantityEntry.getValue());
                    harvestQuantitySuperiorMap.put(countryYearQuantityEntry.getKey(), yearQuantityMap);
                    flag = true;
                }
            }

            flag = false;
        }

        return harvestQuantitySuperiorMap;
    }

   /* public List<Country> fillCountryList(Fruit F, Quantity Q) {
        Map<Country, Map<Year, Quantity>> harvestQuantitySuperiorMap = findCountryYearQuantity(F, Q);

    }*/
}
