
import java.util.Scanner;

class OBJInputTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SafeInputOBJ sio = new SafeInputOBJ(scanner);
        sio.getNonZeroLenString("Enter text");
        sio.getInt("Enter an integer");
        sio.getRangedInt("Enter an integer", 1, 10);
        sio.getDouble("Enter a double");
        sio.getRangedDouble("Enter a double", 0, 1000);
        sio.getRegExString("Enter a string", "[a-zA-Z]+");
        sio.getYNConfirm("Enter a Y/N");
        scanner.close();
    }
}