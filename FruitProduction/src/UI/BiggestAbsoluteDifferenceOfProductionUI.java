package UI;

import Controllers.BiggestAbsoluteDifferenceOfProductionController;
import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;
import Domain.Year;
import Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BiggestAbsoluteDifferenceOfProductionUI {

    private final BiggestAbsoluteDifferenceOfProductionController controller = new BiggestAbsoluteDifferenceOfProductionController();


    public void run(){
        Scanner read = new Scanner(System.in);

        System.out.print("Choose a country: ");
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = controller.getFruitHarvest();
        List<Country> countries = new ArrayList<>(fruitHarvest.get(fruitHarvest).keySet());
        int index = Utils.showAndSelectIndex(countries, "Avaliable Fruits");


        for (int i = 0; i < controller.getBiggestAbsoluteDifference(countries.get(index)).size() ; i++) {
            System.out.println(controller.getBiggestAbsoluteDifference(countries.get(index)).get(i));
        }




    }

}
