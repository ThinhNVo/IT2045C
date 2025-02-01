import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class InheritanceDemo {



    public static void main(String[] args) {
        ArrayList<Worker> workers = new ArrayList<>();
        Worker intern = new Worker("000001", "David", "Smith", "Mr.", 2002, 15);
        Worker studentWorker = new Worker("000002", "Jane", "Smith", "Mrs.", 2000, 12);
        Worker Cleaner = new Worker("000003", "James", "Pond", "Mr.", 1999, 15);
        SalaryWorker actor = new SalaryWorker("000004", "the", "Rock", "Mr.", 1980,3846.15, 200000);
        SalaryWorker Singer = new SalaryWorker("000005", "Jack", "Trinh", "Mr.", 1997, 50, 104000);
        SalaryWorker  hitman = new SalaryWorker("000006", "John", "Wick", "Mr.", 1964, 4807.69, 250000);

        workers.add(intern);
        workers.add(studentWorker);
        workers.add(Cleaner);
        workers.add(actor);
        workers.add(Singer);
        workers.add(hitman);

        for (int week = 1; week <= 3; week++) {
            System.out.println("\nWeek " + week + ":");
            int workHours = week == 2 ? 50 : 40;

            for (Worker person : workers) {
                System.out.printf("%-10s%-14s%-14.2f%-10.2f\n",
                        person.getID(),
                        person.getFullName(),
                        person.getHourlyPayRate(),
                        person.calculateWeeklyPay(workHours));
            }
        }
    }
}