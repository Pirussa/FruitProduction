package Controllers;

import Domain.Stores.DataStore;


public class App {

    private DataStore store;


    private App() {
        this.store = new DataStore();
    }

    public DataStore getStore() {
        return this.store;
    }

    private static App singleton = null;

    public static App getInstance() {
        if (singleton == null) {
            synchronized (App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }
}