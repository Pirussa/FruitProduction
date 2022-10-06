package Controllers;

import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;
import Utils.QuickSort;
import Utils.SortCountries;

import java.util.*;

public class CoutryListController {

    private final DataStore fruitStore = App.getInstance().getStore();

    private final QuickSort quickSort = new QuickSort();

    private final SortCountries sortCountries = new SortCountries();

    private List<Map<Country, Map<Year, Quantity>>> findCountryYearQuantity(Fruit F, Quantity Q) {

        Map<Country, Map<Year, Quantity>> harvestPerCountryMap = fruitStore.getFruitHarvest().get(F);

        List<Map<Country, Map<Year, Quantity>>> harvestQuantitySuperiorListMap = new ArrayList<>();

        boolean flag = false;

        for (Map.Entry<Country, Map<Year, Quantity>> countryYearQuantityEntry : harvestPerCountryMap.entrySet()) {

            Map<Year, Quantity> yearQuantityMap = new LinkedHashMap<>();

            Map<Country, Map<Year, Quantity>> harvestQuantitySuperiorMap = new LinkedHashMap<>();

            for (Map.Entry<Year, Quantity> yearQuantityEntry : countryYearQuantityEntry.getValue().entrySet()) {

                if (yearQuantityEntry.getValue().getQuantity() >= Q.getQuantity() && !flag) {
                    yearQuantityMap.put(yearQuantityEntry.getKey(), yearQuantityEntry.getValue());
                    harvestQuantitySuperiorMap.put(countryYearQuantityEntry.getKey(), yearQuantityMap);
                    harvestQuantitySuperiorListMap.add(harvestQuantitySuperiorMap);
                    flag = true;
                }
            }

            flag = false;
        }

        return harvestQuantitySuperiorListMap;
    }

    public List<Country> sort(Fruit F, Quantity Q) {
        /*Map<Country, Map<Year, Quantity>> countryYearQuantity = findCountryYearQuantity(F, Q);

        List<Year> years = new ArrayList<>();
        List<Quantity> quantities = new ArrayList<>();
        List<Country> equalYearCountries = new ArrayList<>();
        List<Year> equalYearYears = new ArrayList<>();
        List<Quantity> equalYearQuantities = new ArrayList<>();

        quickSort.sort(countryYearQuantity, countries, years, quantities, numberSort, equalYearCountries, equalYearYears, equalYearQuantities);
        numberSort++;
        quickSort.sort(countryYearQuantity, countries, years, quantities, numberSort, equalYearCountries, equalYearYears, equalYearQuantities);
        */

        List<Map<Country, Map<Year, Quantity>>> countryYearQuantity = findCountryYearQuantity(F, Q);

        List<Map.Entry<Country, Map<Year, Quantity>>> mainList = new ArrayList<>();

        List<Country> countries = new ArrayList<>();

        for (Map<Country, Map<Year, Quantity>> countryMapMap : countryYearQuantity) {
            mainList.addAll(countryMapMap.entrySet());
        }

        sortCountries.sortTest(mainList);
        int numberSort = 0;

        for (Map.Entry<Country, Map<Year, Quantity>> countryMapEntry : mainList) {
            countries.add(countryMapEntry.getKey());
        }



        return countries;
    }
}
