public class SalaryWorker extends Worker {
    private double annualSalary;

    public SalaryWorker(String ID, String firstName, String lastName, String title, int yearOfBirth, double hourlyPayRate, double annualSalary) {
        super(ID, firstName, lastName, title, yearOfBirth, hourlyPayRate);
        this.annualSalary = annualSalary;
    }

    public SalaryWorker(Worker worker, double annualSalary) {
        super(worker.getID(), worker.getFirstName(), worker.getLastName(), worker.getTitle(), worker.getYearOfBirth(), worker.getHourlyPayRate());
        this.annualSalary = annualSalary;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    @Override
    public double calculateWeeklyPay(double hoursWorked) {
          return annualSalary / 52;
    }
    @Override
    public void displayWeeklyPay(double hoursWorked) {
        System.out.println("Annual Salary: $" + String.format("%.2f", annualSalary) + "\n");
        System.out.println("Weekly pay is $" + String.format("%.2f", calculateWeeklyPay(hoursWorked)) + "(Weekly pay is 1/52 of annual salary)\n");

    }

    @Override
    public String toCSV() {
        return super.toCSV() + ", " + String.format("%.2f", annualSalary);
    }

    @Override
    public String toXML() {
        return "<SalaryWorker>" +
                super.toXML() +
                "<annualSalary>" + String.format("%.2f", annualSalary) + "</annualSalary>" +
                "</SalaryWorker>";
    }

    @Override
    public String toJSON() {
        return "{" + super.toJSON() +
                ", \"annualSalary\":" + String.format("%.2f", annualSalary) +
                "}";
    }
}
