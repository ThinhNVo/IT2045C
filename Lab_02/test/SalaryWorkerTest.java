import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaryWorkerTest {
    Person p1;
    Worker w1;
    SalaryWorker s1;

    @BeforeEach
    void setUp() {
        p1 = new Person("123456789", "John", "Doe", "Mr.", 1990);
        w1 = new Worker(p1, 10);
        s1 = new SalaryWorker(w1, 20800);
    }

    @Test
    void setAnnualSalary() {
        s1.setAnnualSalary(100000);
        assertEquals(100000, s1.getAnnualSalary());
        s1.setAnnualSalary(20800);
    }

    @Test
    void calculateWeeklyPay() {
        assertEquals(400, s1.calculateWeeklyPay(40));
    }

    @Test
    void toCSV() {
        assertEquals("123456789, John, Doe, Mr., 1990, 10.00, 20800.00", s1.toCSV());
    }

    @Test
    void toXML() {
        assertEquals("<SalaryWorker><Worker><Person><ID>123456789</ID><firstName>John</firstName><lastName>Doe</lastName><title>Mr.</title><YearOfBirth>1990</YearOfBirth></Person><hourlyPayRate>10.00</hourlyPayRate></Worker><annualSalary>20800.00</annualSalary></SalaryWorker>", s1.toXML());
    }

    @Test
    void toJSON() {
        assertEquals("{{{\"ID\":\"123456789\", \"firstName\":\"John\", \"lastName\":\"Doe\", \"title\":\"Mr.\", \"YearOfBirth\":1990}, \"hourlyPayRate\":10.00}, \"annualSalary\":20800.00}", s1.toJSON());
    }
}