package Miscellaneous;

import Domain.Country;
import Domain.Quantity;
import Domain.Year;

import java.util.List;
import java.util.Map;

public interface ISorter {

    void sort(List<Map.Entry<Country, Map<Year, Quantity>>> entries);
}
