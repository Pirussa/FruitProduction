package Utils;

import Controllers.App;
import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;

import java.util.*;

public class CountriesWithMinimumQuantitySum {
    private final DataStore dataStore = App.getInstance().getStore();

    public int getMinimumSumCountries(Quantity quantity) {
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = dataStore.getFruitHarvest();
        ArrayList<Quantity> producaoTotal = new ArrayList<>();
        for (Fruit f : fruitHarvest.keySet()) {
            Set<Country> countries = fruitHarvest.get(f).keySet();
            for (Country c : countries) {
                Set<Year> years = fruitHarvest.get(f).get(c).keySet();
                int soma = 0;
                for (Year y : years) {
                    soma += fruitHarvest.get(f).get(c).get(y).getQuantity();
                }
                producaoTotal.add(new Quantity(soma));
            }
        }
        producaoTotal.sort(new Comparator<>() {
            @Override
            public int compare(Quantity o1, Quantity o2) {
                return -(o1.getQuantity() - o2.getQuantity());
            }
        });
        return minNumberOfCountries(producaoTotal,quantity);
    }

    public int minNumberOfCountries(ArrayList<Quantity> producaoTotal, Quantity target) {
        int totalsSum = 0;
        int totalCountries = 0;
        long check = 0;
        for (int i = 0; i < producaoTotal.size(); i++) {
            check+= producaoTotal.get(i).getQuantity();
        }
        if(target.getQuantity() > check){
            return -1;
        }
        while (totalsSum <= target.getQuantity()){
            totalsSum = totalsSum + producaoTotal.get(totalCountries).getQuantity();
            totalCountries++;
        }
        return totalCountries;
    }
}
