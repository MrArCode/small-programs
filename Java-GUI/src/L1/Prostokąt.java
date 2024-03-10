package L1;

public class Prostokąt {
    private int wysokość;
    private int długość;

    public void rysuj() throws ProstokatException {
        if (this.wysokość == 0 || this.długość == 0) {
            System.out.println("Prostokąt " + this.wysokość + " X " + this.długość);
            throw new ProstokatException("Błędny prostokąt!");

        } else {

            System.out.println("Zwykły prostokąt " + this.wysokość + " X " + this.długość);
            System.out.println();
        }
    }

    public Prostokąt(int wysokość, int długość) {
        this.wysokość = wysokość;
        this.długość = długość;
    }

    public int getWysokość() {
        return wysokość;
    }

    public void setWysokość(int wysokość) {
        this.wysokość = wysokość;
    }

    public int getDługość() {
        return długość;
    }

    public void setDługość(int długość) {
        this.długość = długość;
    }

}
