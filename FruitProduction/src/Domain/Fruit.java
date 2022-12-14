package Domain;


import java.util.Objects;

public class Fruit {

    private final String fruitName;

    public Fruit(String fruitName) {
        this.fruitName = fruitName;
    }

    public String getFruitName() {
        return fruitName;
    }

    public String toString() {
        return fruitName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return fruitName.equals(fruit.fruitName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fruitName);
    }


}
