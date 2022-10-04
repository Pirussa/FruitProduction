package Utils;

import Controllers.App;
import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Stores.DataStore;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class CountriesWithMinimumQuantitySum {
    private final DataStore dataStore = App.getInstance().getStore();

    public boolean getMinimumSumCountries(Quantity quantity) {
        ArrayList<Country> listOfCountries = new ArrayList<>();
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = dataStore.getFruitHarvest();
        int qt = quantity.getQuantity();
        for (Fruit f : fruitHarvest.keySet()) {
            Set<Country> countries = fruitHarvest.get(f).keySet();
            for (Country c : countries) {
                Set<Year> years = fruitHarvest.get(f).get(c).keySet();
                for (Year y : years) {
                    /*if (fruitHarvest.get(f).get(c).get(y).getQuantity() > qt) {
                        listOfCountries.add(c);
                        return listOfCountries;
                    }*/

                }
            }
        }
        return true;
    }
    public void printCountries(ArrayList<Country> listOfCountries){
        for(Country c : listOfCountries){
            System.out.printf("%s",c);
        }
    }
}
