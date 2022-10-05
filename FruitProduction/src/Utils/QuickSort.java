package Utils;

import Domain.Country;
import Domain.Quantity;
import Domain.Year;

import java.util.*;

public class QuickSort implements ISorter {
    @Override
    public void sort(Map<Country, Map<Year, Quantity>> mainList, List<Country> countries, List<Year> years, List<Quantity> quantities, int numberSort) {
        if (numberSort == 0) {
            fillLists(mainList, countries, years, quantities);
            quickSortYear(countries, years, quantities, 0, countries.size() - 1);
        }
        else
            quickSortQuantity(countries, years, quantities, 0, countries.size() - 1);
    }

    static void fillLists(Map<Country, Map<Year, Quantity>> countryList, List<Country> countries, List<Year> years, List<Quantity> quantities) {
        Set<Country> countriesAux = countryList.keySet();
        countries = new ArrayList<>(countriesAux);
        boolean flag = false;

        for (Map<Year, Quantity> yearQuantityMap : countryList.values()) {
            if (!flag) {
                Set<Year> yearsAux = yearQuantityMap.keySet();
                years = new ArrayList<>(yearsAux);
                flag = true;
                quantities.addAll(yearQuantityMap.values());
            }
        }
    }

    static void swap(List<Country> countries, List<Year> years, List<Quantity> quantities, int firstPos, int secondPos) {
        Collections.swap(countries, firstPos, secondPos);
        Collections.swap(years, firstPos, secondPos);
        Collections.swap(quantities, firstPos, secondPos);
    }

    static int partitionYear(List<Country> countries, List<Year> years, List<Quantity> quantities, int low, int high) {

        Year pivot = years.get(high);

        int firstPos = (low - 1);

        for (int secondPos = low; secondPos <= high - 1; secondPos++) {

            if (years.get(secondPos).getYear() < pivot.getYear()) {

                firstPos++;
                swap(countries, years, quantities, firstPos, secondPos);
            }
        }
        swap(countries, years, quantities, firstPos + 1, high);
        return (firstPos + 1);
    }

    static void quickSortYear(List<Country> countries, List<Year> years, List<Quantity> quantities, int low, int high) {
        if (low < high) {

            int pi = partitionYear(countries, years, quantities, low, high);

            quickSortYear(countries, years, quantities, low, pi - 1);
            quickSortYear(countries, years, quantities, pi + 1, high);
        }
    }

    static int partitionQuantity(List<Country> countries, List<Year> years, List<Quantity> quantities, int low, int high) {

        Quantity pivot = quantities.get(high);
        Year auxPivot = years.get(high);

        int firstPos = (low - 1);

        for (int secondPos = low; secondPos <= high - 1; secondPos++) {

            if (years.get(secondPos).getYear() == auxPivot.getYear() && quantities.get(secondPos).getQuantity() > pivot.getQuantity()) {

                firstPos++;
                swap(countries, years, quantities, firstPos, secondPos);
            }
        }
        swap(countries, years, quantities, firstPos + 1, high);
        return (firstPos + 1);
    }

    static void quickSortQuantity(List<Country> countries, List<Year> years, List<Quantity> quantities, int low, int high) {
        if (low < high) {

            int pi = partitionQuantity(countries, years, quantities, low, high);

            quickSortQuantity(countries, years, quantities, low, pi - 1);
            quickSortQuantity(countries, years, quantities, pi + 1, high);
        }
    }
}