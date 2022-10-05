package Utils;

import Domain.Country;
import Domain.Quantity;
import Domain.Year;

import java.util.*;

public class QuickSort implements ISorter {
    @Override
    public void sort(Map<Country, Map<Year, Quantity>> mainList, List<Country> countries, List<Year> years, List<Quantity> quantities, int numberSort, List<Country> equalYearCountries, List<Year> equalYearYears, List<Quantity> equalYearQuantities) {
        if (numberSort == 0) {
            fillLists(mainList, countries, years, quantities);
            quickSortYear(countries, years, quantities, 0, countries.size() - 1);
        } else {
            int brokePosition = equalYearCountry(countries, years, quantities, equalYearCountries, equalYearYears, equalYearQuantities);
            quickSortQuantity(equalYearCountries, equalYearYears, equalYearQuantities, 0, equalYearCountries.size() - 1);
            rearrange(countries, years, quantities, equalYearCountries, equalYearYears, equalYearQuantities, brokePosition);
        }
    }

    static void fillLists(Map<Country, Map<Year, Quantity>> countryList, List<Country> countries, List<Year> years, List<Quantity> quantities) {
        int lenght = 0;
        countries.addAll(countryList.keySet().stream().toList());
        for (Map<Year, Quantity> yearQuantityMap : countryList.values().stream().toList()) {
            years.add(countryList.values().stream().toList().get(lenght).keySet().stream().toList().get(0));
            quantities.addAll(yearQuantityMap.values());
            lenght++;
        }
    }

    static int equalYearCountry(List<Country> countries, List<Year> years, List<Quantity> quantities, List<Country> equalYearCountries, List<Year> equalYearYears, List<Quantity> equalYearQuantities) {
        boolean flag = false;
        int positionBroke = -1;
        for (int position = 0; position < countries.size(); position++) {
            if ((position + 1) < countries.size() && years.get(position).getYear() == years.get(position + 1).getYear()) {
                equalYearCountries.add(countries.get(position));
                equalYearYears.add(years.get(position));
                equalYearQuantities.add(quantities.get(position));
                if (!flag) {
                    positionBroke = position;
                    flag = true;
                }
            }
            else if (position == countries.size() - 1) {
                equalYearCountries.add(countries.get(position));
                equalYearYears.add(years.get(position));
                equalYearQuantities.add(quantities.get(position));
            }
        }
        return positionBroke;
    }

    static void rearrange(List<Country> countries, List<Year> years, List<Quantity> quantities, List<Country> equalYearCountries, List<Year> equalYearYears, List<Quantity> equalYearQuantities, int brokePosition) {
        if (brokePosition != -1) {
            if (countries.size() > brokePosition) {
                countries.subList(brokePosition, countries.size()).clear();
                years.subList(brokePosition, years.size()).clear();
                quantities.subList(brokePosition, quantities.size()).clear();
            }
            for (int position = 0; position < equalYearCountries.size(); position++) {
                countries.add(equalYearCountries.get(position));
                years.add(equalYearYears.get(position));
                quantities.add(equalYearQuantities.get(position));
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