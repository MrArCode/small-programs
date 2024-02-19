package Topic1_StaticFactoryMethods;

public class Main {
    public static void main(String[] args) throws Exception {
        Number num1 = new Number();
        System.out.println(num1);
        System.out.println(Number.createNumber());
        Number num3 = Number.createNumber();
        System.out.println(num3);

    }
}
