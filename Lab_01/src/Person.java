import java.util.Objects;
import java.util.Calendar;

public class Person {
    /*
    private String ID = "";
    private String firstName = "";
    private String lastName = "";
    private String title = "";
    private String rec = "";
    int YearOfBirth = 0;
    */
    private String ID = "";
    private String firstName = "";
    private String lastName = "";
    private String title = "";
    private int YearOfBirth = 0;

    public Person(String ID, String firstName, String lastName, String title, int yearOfBirth) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        YearOfBirth = yearOfBirth;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public int getYearOfBirth() {
        return YearOfBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYearOfBirth(int yearOfBirth) {
        YearOfBirth = yearOfBirth;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFormalName() {
        return title + " " + getFullName();
    }

    public String toCSV() {
        return ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YearOfBirth;
    }


    @Override
    public String toString() {
        return "Person{" +
                "ID='" + ID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", YearOfBirth=" + YearOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return YearOfBirth == person.YearOfBirth && Objects.equals(ID, person.ID) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(title, person.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, firstName, lastName, title, YearOfBirth);
    }



    public String toJSON() {
        return "{\"ID\":'\"" + ID + "\"" +
                ", \"firstName\":\"" + firstName + "\"" +
                ", \"lastName\":\"" + lastName + "\"" +
                ", \"title\":\"" + title + "\"" +
                ", \"YearOfBirth\":\"" + YearOfBirth +
                "\"}";
    }

    public String toXML() {
        return "<Person>" +
                "<ID>" + ID + "</ID>" +
                "<firstName>" + firstName + "</firstName>" +
                "<lastName>" + lastName + "</lastName>" +
                "<title>" + title + "</title>" +
                "<YearOfBirth>" + YearOfBirth + "</YearOfBirth>" +
                "</Person>";
    }

    public String getAge() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(String.valueOf(this.YearOfBirth)));
    }

    public String getAge(int age) {
        return String.valueOf(age - Integer.parseInt(String.valueOf(this.YearOfBirth)));
    }
}
