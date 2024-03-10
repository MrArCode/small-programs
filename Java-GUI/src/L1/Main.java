package L1;

public class Main {

    public static void main(String[] args) {

        Prostokąt pr[] = {
                new Prostokąt(2, 3),
                new Prostokąt(0, 3),
                new ZProstokąt(4, 'a', 'e'),
                new ZProstokąt(5, 3, '*', '+'),
                new ZProstokąt(1, 2, 'a', 'a'),
                new ZProstokąt(3, 3, '+', 'x'),
                new ZProstokąt(1, 2, 'x', 'y'),
                new ZProstokąt(3, 4, '^', '$')
        };

        for (Prostokąt p : pr)
            try {
                p.rysuj();
            } catch (ProstokatException e) {
                System.out.println(e.getMessage());
            }

    }
}
