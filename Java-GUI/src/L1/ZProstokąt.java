package L1;

public class ZProstokąt extends Prostokąt {
    private char znak1;
    private char znak2;

    public ZProstokąt(int wysokość, char znak1, char znak2) {
        this(wysokość, wysokość, znak1, znak2);
    }

    public ZProstokąt(int wysokość, int szerekość, char znak1, char znak2) {
        super(wysokość, szerekość);
        this.znak1 = znak1;
        this.znak2 = znak2;
    }
}