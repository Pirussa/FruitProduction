package Controllers;

import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;
import Utils.QuickSort;

import java.util.*;

public class CoutryListController {

    private final int MAX_YEAR = 3000;
    private final DataStore fruitStore = App.getInstance().getStore();

    private final QuickSort quickSort = new QuickSort();

    private List<Map<Country, Map<Year, Quantity>>> findCountryYearQuantity(Fruit F, Quantity Q) {

        Map<Country, Map<Year, Quantity>> harvestPerCountryMap = fruitStore.getFruitHarvest().get(F);

        List<Map<Country, Map<Year, Quantity>>> harvestQuantitySuperiorListMap = new ArrayList<>();

        boolean flag = false;

        for (Map.Entry<Country, Map<Year, Quantity>> countryYearQuantityEntry : harvestPerCountryMap.entrySet()) {

            Map<Year, Quantity> yearQuantityMap = new LinkedHashMap<>();

            Map<Country, Map<Year, Quantity>> harvestQuantitySuperiorMap = new LinkedHashMap<>();

            Year year = new Year(MAX_YEAR);

            for (Map.Entry<Year, Quantity> yearQuantityEntry : countryYearQuantityEntry.getValue().entrySet()) {

                if (yearQuantityEntry.getValue().getQuantity() >= Q.getQuantity() &&  yearQuantityEntry.getKey().getYear() < year.getYear()/*&& !flag*/) {
                    year = yearQuantityEntry.getKey();
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

    public List<Map.Entry<Country, Map<Year, Quantity>>> sort(Fruit F, Quantity Q) {
        List<Map<Country, Map<Year, Quantity>>> countryYearQuantity = findCountryYearQuantity(F, Q);

        List<Map.Entry<Country, Map<Year, Quantity>>> mainList = new ArrayList<>();
        fillListWithMap(countryYearQuantity, mainList);

        //List<Country> countries = new ArrayList<>();
        quickSort.sort(mainList);
        return mainList;
    }

    private void fillListWithMap(List<Map<Country, Map<Year, Quantity>>> countryYearQuantity, List<Map.Entry<Country, Map<Year, Quantity>>> mainList) {
        for (Map<Country, Map<Year, Quantity>> countryMapMap : countryYearQuantity) {
            mainList.addAll(countryMapMap.entrySet());
        }
    }

    public List<Country> addCountriesToList(List<Map.Entry<Country, Map<Year, Quantity>>> mainList) {
        List<Country> countries = new ArrayList<>();
        for (Map.Entry<Country, Map<Year, Quantity>> countryMapEntry : mainList) {
            countries.add(countryMapEntry.getKey());
        }
        return countries;
    }

    public Map<Fruit, Map<Country, Map<Year, Quantity>>> getHarvestMap() {
        return fruitStore.getFruitHarvest();
    }

    public Quantity newQuantity(int quantity) {
        return new Quantity(quantity);
    }
}