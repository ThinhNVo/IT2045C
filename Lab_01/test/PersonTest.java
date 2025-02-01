import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class PersonTest {

    Person p1, p2, p3, p4, p5, p6;

    @BeforeEach
    void setUp()
    {
        p1 = new Person("00000A", "Bob", "Tester1", "Mr.",1955);
        p2 = new Person("00000B", "Sally", "Tester2", "Miss.",1975);
        /*p3 = new Person("Bob", "Tester3", 1960);
        p4 = new Person( "Sally", "Tester4", 1965);
        p5 = new Person( "Fred", "Tester5", 1970);
        p6 = new Person( "Cindy", "Tester6", 1975);
    */

    }

    @Test
    void setFirstName() {
        p1.setFirstName("Sally");
        assertEquals("Sally", p1.getFirstName());
    }

    @Test
    void setLastName() {
        p1.setLastName("Tester2");
        assertEquals("Tester2", p1.getLastName());
    }

    @Test
    void setTitle() {
        p1.setTitle("Dr.");
        assertEquals("Dr.", p1.getTitle());
    }

    @Test
    void setYearOfBirth() {
        p1.setYearOfBirth(1975);
        assertEquals(1975, p1.getYearOfBirth());
    }

    @Test
    void getFullName() {
        assertEquals("Sally Tester2", p2.getFullName());
    }

    @Test
    void getFormalName() {
        assertEquals("Miss. Sally Tester2", p2.getFormalName());
    }

    @Test
    void toCSV() {
        assertEquals("00000B, Sally, Tester2, Miss., 1975", p2.toCSV());
    }

    @Test
    void testToString() {
        assertEquals("Person{ID='00000B', firstName='Sally', lastName='Tester2', title='Miss.', YearOfBirth=1975}", p2.toString());
    }

    @Test
    void testEquals() {
        assertNotEquals(p1, p2);
        assertEquals(p1, p1);
    }

    @Test
    void toJSON() {
        assertEquals("{\"ID\":'\"00000A\", \"firstName\":\"Bob\", \"lastName\":\"Tester1\", \"title\":\"Mr.\", \"YearOfBirth\":\"1955\"}", p1.toJSON());
    }

    @Test
    void toXML() {
        assertEquals("<Person><ID>00000B</ID><firstName>Sally</firstName><lastName>Tester2</lastName><title>Miss.</title><YearOfBirth>1975</YearOfBirth></Person>", p2.toXML());
    }

    @Test
    void getAge() {
        assertEquals("70", p1.getAge());
    }

    @Test
    void testGetAge() {
        assertEquals("10", p2.getAge(1985));
    }
}