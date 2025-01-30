import java.util.Scanner;

public class PersonDemo {
    public static void main(String[] args) {
        Person boy = new Person("000001", "Bill", "Bailey", "Dr.", 1942);
        Person girl = new Person("000002", "Sally", "Smith", "Dr.", 1952);

        System.out.println(boy.toJSON());


        Scanner in = new Scanner(System.in);
        SafeInputOBJ sio = new SafeInputOBJ(in);
        String id = sio.getNonZeroLenString("Enter your ID: ");
        System.out.println(id);



    }
}
