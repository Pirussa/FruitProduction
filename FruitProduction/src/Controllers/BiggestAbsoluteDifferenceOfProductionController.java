package Controllers;

import Domain.*;
import Stores.DataStore;
import Utils.BiggestProduction;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.*;

public class BiggestAbsoluteDifferenceOfProductionController {
    private final DataStore harvest = App.getInstance().getStore();

    public Set<BiggestProduction> getBiggestAbsoluteDifference(Country country) {

        Set<BiggestProduction> biggestDifferenceSet = new LinkedHashSet<>();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = harvest.getFruitHarvest();


        for (Fruit fruit : fruitHarvest.keySet()) {
            if (fruitHarvest.get(fruit).get(country) != null && fruitHarvest.get(fruit).get(country).values().size() >= 2) {
                Map<Year, Quantity> restrictedMap = fruitHarvest.get(fruit).get(country);
                if (!restrictedMap.isEmpty()) {
                    Year year = restrictedMap.entrySet().stream().findFirst().get().getKey();
                    Quantity quantity = restrictedMap.entrySet().stream().findFirst().get().getValue();
                    Iterator<Map.Entry<Year, Quantity>> iterator = restrictedMap.entrySet().iterator();
                    int firstYear = 0, lastYear = 0, biggestDifference = 0;
                    while (iterator.hasNext()) {
                        Map.Entry<Year, Quantity> entry = iterator.next();
                        if (Math.abs(entry.getValue().getQuantity() - quantity.getQuantity()) > biggestDifference) {
                            biggestDifference = Math.abs(entry.getValue().getQuantity() - quantity.getQuantity());
                            firstYear = entry.getKey().getYear();
                            lastYear = year.getYear();
                        }
                        year = entry.getKey();
                        quantity = entry.getValue();
                    }
                    if (firstYear > lastYear) {
                        int aux = lastYear;
                        lastYear = firstYear;
                        firstYear = aux;
                    }
                    biggestDifferenceSet.add(new BiggestProduction(firstYear + "/" + lastYear, fruit.getFruitName(), biggestDifference));
                }
            }
        }
        return biggestDifferenceSet;
    }


    public List<Country> getCountriesList(List<Country> countriesList) {
        for (Map<Country, Map<Year, Quantity>> subMap : harvest.getFruitHarvest().values()) {
            for (Country country : subMap.keySet()) {
                if (!countriesList.contains(country)) {
                    countriesList.add(country);
                }
            }
        }
        return countriesList;
    }
}
