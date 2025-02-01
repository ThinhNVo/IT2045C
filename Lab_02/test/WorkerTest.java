import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {
    Person p1;
    Worker e1;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        p1 = new Person("123456789", "John", "Doe", "Mr.", 1990);
        e1 = new Worker(p1, 10);
    }

    @Test
    void setHourlyPayRate() {
        e1.setHourlyPayRate(15);
        assertEquals(15, e1.getHourlyPayRate());
        e1.setHourlyPayRate(10);
    }

    @Test
    void calculateWeeklyPay() {
        assertEquals(400, e1.calculateWeeklyPay(40));
        assertEquals(475, e1.calculateWeeklyPay(45));
    }

    @Test
    void toCSV() {
        assertEquals("123456789, John, Doe, Mr., 1990, 10.00", e1.toCSV());
    }

    @Test
    void toXML() {
        assertEquals("<Worker><Person><ID>123456789</ID><firstName>John</firstName><lastName>Doe</lastName><title>Mr.</title><YearOfBirth>1990</YearOfBirth></Person><hourlyPayRate>10.00</hourlyPayRate></Worker>", e1.toXML());
    }

    @Test
    void toJSON() {
        assertEquals("{{\"ID\":\"123456789\", \"firstName\":\"John\", \"lastName\":\"Doe\", \"title\":\"Mr.\", \"YearOfBirth\":1990}, \"hourlyPayRate\":10.00}", e1.toJSON());
    }
}