package UI;

import Controllers.CoutryListController;
import Domain.Country;
import Domain.Fruit;
import Domain.Quantity;

import java.util.List;

public class CountryListUI implements Runnable{
    private final CoutryListController controller = new CoutryListController();
    @Override
    public void run() {
        List<Country> countries = controller.sort(new Fruit("Apples"), new Quantity(300000));
        System.out.println(countries);
    }
}
