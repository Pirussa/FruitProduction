package UI;

import Controllers.ReadDataFromCsvFileController;

import java.util.Scanner;

public class LoadDataFromCsvFileUI implements Runnable{

    private final ReadDataFromCsvFileController controller = new ReadDataFromCsvFileController();


    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Choose the path to the file:%nPath: ");
        String filePath = sc.nextLine();
        if (controller.getDataFromFile(filePath)){
            System.out.printf("Your data was successfully added to the system.%n%n%n");
        }else{
            System.out.println("There was a problem updating your data to the system.%n%n%n");
        }


    }
}
