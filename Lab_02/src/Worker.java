import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Worker extends Person{
    private double hourlyPayRate;

    public Worker(String ID, String firstName, String lastName, String title, int yearOfBirth, double hourlyPayRate) {
        super(ID, firstName, lastName, title, yearOfBirth);
        this.hourlyPayRate = hourlyPayRate;
    }

    public Worker(Person person, double hourlyPayRate) {
        super(person.getID(), person.getFirstName(), person.getLastName(), person.getTitle(), person.getYearOfBirth());
        this.hourlyPayRate = hourlyPayRate;
    }

    public double getHourlyPayRate() {
        return hourlyPayRate;
    }

    public void setHourlyPayRate(double hourlyPayRate) {
        this.hourlyPayRate = hourlyPayRate;
    }

    public double calculateWeeklyPay(double hoursWorked) {
        if (hoursWorked > 40) {
            return (hourlyPayRate * 40) + (hoursWorked - 40) * 1.5 * hourlyPayRate;
        }
        return hourlyPayRate * hoursWorked;
    }

    public void displayWeeklyPay(double hoursWorked) {
        System.out.printf("Work Hours: " + hoursWorked + " hours\n");
        if (hoursWorked > 40) {
            System.out.println("Overtime Work Hours: " + (hoursWorked - 40) + " hours");
            System.out.println("Overtime Pay: $" + String.format("%.2f", calculateWeeklyPay(hoursWorked) - (hourlyPayRate * 40)));
        }
        System.out.println("Weekly pay is $" + String.format("%.2f", calculateWeeklyPay(hoursWorked)));
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ", " + String.format("%.2f", hourlyPayRate);
    }

    @Override
    public String toXML() {
        return "<Worker>" +
                super.toXML() +
                "<hourlyPayRate>" + String.format("%.2f", hourlyPayRate) + "</hourlyPayRate>" +
                "</Worker>";
    }

    @Override
    public String toJSON() {
        return "{" + super.toJSON() +
                ", \"hourlyPayRate\":" + String.format("%.2f", hourlyPayRate) +
                "}";
    }
}
