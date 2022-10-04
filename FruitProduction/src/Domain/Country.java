package Domain;

import java.util.Objects;

public class Country {

    private final String countryName;

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public String toString() {
        return countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(countryName, country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName);
    }
}
