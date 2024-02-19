package Topic1_StaticFactoryMethods;

public class Number {
    private int randomNumber;

    public Number() {
        this.randomNumber = (int) (Math.random() * 101);
        return;
    }

    public static Number createNumber() {
        return new Number();
    }

    public String toString() {
        return String.valueOf(randomNumber);
    }
}
