package Utils;

import Domain.Country;
import Domain.Quantity;
import Domain.Year;

import java.util.*;

public class QuickSort implements ISorter {

    public void sort(List<Map.Entry<Country, Map<Year, Quantity>>> entries) {
        quickSortYear(entries, 0, entries.size() - 1);
    }

    static void swap(List<Map.Entry<Country, Map<Year, Quantity>>> entries, int firstPos, int secondPos) {
        Collections.swap(entries, firstPos, secondPos);
    }

    static int partitionYear(List<Map.Entry<Country, Map<Year, Quantity>>> entries, int low, int high) {

        Year pivotY = entries.get(high).getValue().keySet().stream().toList().get(0);
        Quantity pivotQ = entries.get(high).getValue().values().stream().toList().get(0);

        int firstPos = (low - 1);

        for (int secondPos = low; secondPos <= high; secondPos++) {

            if (entries.get(secondPos).getValue().keySet().stream().toList().get(0).getYear() < pivotY.getYear()) {

                firstPos++;
                swap(entries, firstPos, secondPos);
            } else if (entries.get(secondPos).getValue().keySet().stream().toList().get(0).getYear() == pivotY.getYear() && entries.get(secondPos).getValue().values().stream().toList().get(0).getQuantity() > pivotQ.getQuantity()) {

                firstPos++;
                swap(entries, firstPos, secondPos);
            }
        }
        swap(entries, firstPos + 1, high);
        return (firstPos + 1);
    }

    static void quickSortYear(List<Map.Entry<Country, Map<Year, Quantity>>> entries, int low, int high) {
        if (low < high) {

            int pi = partitionYear(entries, low, high);

            quickSortYear(entries, low, pi - 1);
            quickSortYear(entries, pi + 1, high);
        }
    }
}