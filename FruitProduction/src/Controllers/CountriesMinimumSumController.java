package Controllers;

import Domain.Quantity;
import Miscellaneous.CountriesWithMinimumQuantitySum;
public class CountriesMinimumSumController {
    public int getMinimumSumCountries(Quantity quantity){
        CountriesWithMinimumQuantitySum calc = new CountriesWithMinimumQuantitySum();
        return calc.getMinimumSumCountries(quantity);
    }
}
