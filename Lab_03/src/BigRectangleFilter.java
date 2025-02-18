import java.awt.*;

public class BigRectangleFilter implements Filter {

    @Override
    public boolean accept(Object x) {
        try {
            if (x instanceof Double && Double.parseDouble(x.toString()) > 10) {
               return true;
            }

        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }
}
