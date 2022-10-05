package Utils;

import Domain.Country;
import Domain.Quantity;
import Domain.Year;

import java.util.List;
import java.util.Map;

public interface ISorter {

    void sort(Map<Country, Map<Year, Quantity>> mainList, List<Country> countries, List<Year> years, List<Quantity> quantities, int numberSort, List<Country> equalYearCountries, List<Year> equalYearYears, List<Quantity> equalYearQuantities);
}
