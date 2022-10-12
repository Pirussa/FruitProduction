package Controllers;

import Miscellaneous.ReaderFromCsvFile;

public class ReadDataFromCsvFileController {

    public ReadDataFromCsvFileController() {
    }

    public boolean getDataFromFile(String filePath) {
        ReaderFromCsvFile reader = new ReaderFromCsvFile(filePath);
        return reader.readDataFromFile();
    }
}
