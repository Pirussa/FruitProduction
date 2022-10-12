package UI;

import Controllers.BiggestAbsoluteDifferenceOfProductionController;
import Domain.Country;
import Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BiggestAbsoluteDifferenceOfProductionUI implements Runnable {

    private final BiggestAbsoluteDifferenceOfProductionController controller = new BiggestAbsoluteDifferenceOfProductionController();


    public void run() {
        System.out.printf("-------------------------------%n   Biggest Absolute Difference          %n--------------------------------%n%n");
        List<Country> countries = new ArrayList<>();
        int index = Utils.showAndSelectIndex(controller.getCountriesList(countries), "Available Countries:");
        checkIfEmpty(index, countries);
}

    public void checkIfEmpty(int index, List<Country> countries) {
        if (!controller.getBiggestAbsoluteDifference(countries.get(index)).isEmpty()) System.out.printf("%nThe Biggest Absolute Difference of Production in %s is --> %s%n%n", countries.get(index).toString(), controller.getBiggestAbsoluteDifference(countries.get(index)).toString());
        else System.out.printf("%nNot enough data registered in the system, each country must have at least two years of information%n%n");
    }
}
