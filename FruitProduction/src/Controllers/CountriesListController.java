package Controllers;

import Domain.Quantity;
import Utils.CountriesWithMinimumQuantitySum;

public class CountriesListController {
    public boolean getMinimumSumCountries(Quantity quantity){
        CountriesWithMinimumQuantitySum calc = new CountriesWithMinimumQuantitySum();
        return calc.getMinimumSumCountries(quantity);
    }
}
