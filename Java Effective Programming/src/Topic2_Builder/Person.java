package Topic2_Builder;

public class Person {
    private String name;
    private int age;
    private double weight;
    private double height;

    public static class Builder {
        private String name;
        private int age;

        private double weight = 0;
        private double height = 0;

        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder weight(double val) {
            weight = val;
            return this;
        }

        public Builder height(double val) {
            height = val;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    private Person(Builder builder) {
        name = builder.name;
        age = builder.age;
        weight = builder.weight;
        height = builder.height;
    }
}
