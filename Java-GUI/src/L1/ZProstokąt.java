package L1;

public class ZProstokąt extends Prostokąt {
    private char znak1;
    private char znak2;

    private static int numerProstokąta = 1;
    private static int numerKwadratu = 1;
    private int numer;

    private String typFigury;

    public ZProstokąt(int wysokość, char znak1, char znak2) {
        this(wysokość, wysokość, znak1, znak2);
    }

    public ZProstokąt(int wysokość, int szerekość, char znak1, char znak2) {
        super(wysokość, szerekość);
        if (wysokość == szerekość) {
            this.typFigury = "Kwadrat";
            this.numer = numerKwadratu;
            numerKwadratu += 1;
        } else {
            this.typFigury = "Prostokąt";
            this.numer = numerProstokąta;
            numerProstokąta += 1;
        }
        this.znak1 = znak1;
        this.znak2 = znak2;
    }

    public void rysuj() throws ProstokatException {
        if (((getWysokość() == 1 || getWysokość() == 2) || (getDługość() == 1 || getDługość() == 2))
                && (znak1 != znak2) || (getWysokość() > 2 && getDługość() > 2 && znak1 == znak2)) {
            System.out
                    .println(typFigury + " (" + numer + ") romiar " + getWysokość() + " X " + getDługość());
            System.out.println("Błędny prostokąt");
            System.out.println();
        } else if (((getWysokość() == 1 || getWysokość() == 2) || (getDługość() == 1 || getDługość() == 2))
                && (znak1 == znak2)) {
            System.out
                    .println(typFigury + " (" + numer + ") romiar " + getWysokość() + " X " + getDługość());
            for (int i = 0; i < getDługość(); i++) {
                for (int j = 0; j < getWysokość(); j++) {
                    System.out.println(znak1);
                }
            }
            System.out.println();
        } else {
            System.out
                    .println(typFigury + " (" + numer + ") romiar " + getWysokość() + " X " + getDługość()
                            + ", " + znak2 + " = " + ((2 * getDługość()) + (2 * (getWysokość() - 2))) + ", " + znak1
                            + " = "
                            + (((getWysokość() - 2) * (getDługość() - 2))));
            for (int i = 0; i < getWysokość(); i++) {
                System.out.print(znak2 + " ");
            }
            System.out.println();
            for (int i = 0; i < getDługość() - 2; i++) {
                System.out.print(znak2 + " ");
                for (int j = 0; j < getWysokość() - 2; j++) {
                    System.out.print(znak1 + " ");
                }
                System.out.println(znak2 + " ");
            }
            for (int i = 0; i < getWysokość(); i++) {
                System.out.print(znak2 + " ");
            }
            System.out.println();
            System.out.println();
        }
    }
}
