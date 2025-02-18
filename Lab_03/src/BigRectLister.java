import java.util.ArrayList;

public class BigRectLister {
    public static void main(String[] args) {
        BigRectangleFilter filter = new BigRectangleFilter();
        ArrayList<Double> regtangle = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            regtangle.add((double) (i * 4));
        }

        for (Double rec : regtangle) {
            System.out.println(rec + " is the perimeter of a regtangle and it is over 10: " + filter.accept(rec));
        }

    }
}
