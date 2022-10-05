package Controllers;

import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;
import Utils.QuickSort;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CoutryListController {

    private final DataStore fruitStore = App.getInstance().getStore();

    private final QuickSort quickSort = new QuickSort();

    private Map<Country, Map<Year, Quantity>> findCountryYearQuantity(Fruit F, Quantity Q) {
        Map<Country, Map<Year, Quantity>> harvestPerCountryMap = fruitStore.getFruitHarvest().get(F);

        Map<Country, Map<Year, Quantity>> harvestQuantitySuperiorMap = new LinkedHashMap<>();
        boolean flag = false;

        for (Map.Entry<Country, Map<Year, Quantity>> countryYearQuantityEntry : harvestPerCountryMap.entrySet()) {

            Map<Year, Quantity> yearQuantityMap = new LinkedHashMap<>();

            for (Map.Entry<Year, Quantity> yearQuantityEntry : countryYearQuantityEntry.getValue().entrySet()) {

                if (yearQuantityEntry.getValue().getQuantity() >= Q.getQuantity() && !flag) {
                    yearQuantityMap.put(yearQuantityEntry.getKey(), yearQuantityEntry.getValue());
                    harvestQuantitySuperiorMap.put(countryYearQuantityEntry.getKey(), yearQuantityMap);
                    flag = true;
                }
            }

            flag = false;
        }

        return harvestQuantitySuperiorMap;
    }

    public List<Country> sort(Fruit F, Quantity Q) {
        Map<Country, Map<Year, Quantity>> countryYearQuantity = findCountryYearQuantity(F, Q);
        List<Country> countries = new ArrayList<>();
        List<Year> years = new ArrayList<>();
        List<Quantity> quantities = new ArrayList<>();
        List<Country> equalYearCountries = new ArrayList<>();
        List<Year> equalYearYears = new ArrayList<>();
        List<Quantity> equalYearQuantities = new ArrayList<>();
        int numberSort = 0;

        quickSort.sort(countryYearQuantity, countries, years, quantities, numberSort, equalYearCountries, equalYearYears, equalYearQuantities);
        numberSort++;
        quickSort.sort(countryYearQuantity, countries, years, quantities, numberSort, equalYearCountries, equalYearYears, equalYearQuantities);

        return countries;
    }
}
