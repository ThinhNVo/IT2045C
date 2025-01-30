import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class ProductTest {

    Product p1, p2;

    @BeforeEach
    void setUp() {
        p1 = new Product("00000A", "1819 Inovation hub cup", "coffee mug", 15.99);
        p2 = new Product("00000B", "Lime Lacroix", "sparkling water", 2.99);
    }

    @Test
    void setName() {
        p1.setName("1819 Inovation hub mug");
        assertEquals("1819 Inovation hub mug", p1.getName());
    }

    @Test
    void setDescription() {
        p1.setDescription("coffee mug");
        assertEquals("coffee mug", p1.getDescription());
    }

    @Test
    void setCost() {
        p2.setCost(1.99);
        assertEquals(1.99, p2.getCost());
    }

    @Test
    void testEquals() {
        assertNotEquals(p1, p2);
        assertEquals(p1, p1);
    }

    @Test
    void testToString() {
        assertEquals("Product{ID='00000A', Name='1819 Inovation hub cup', Description='coffee mug', Cost=15.99}", p1.toString());
    }

    @Test
    void toJSON() {
        assertEquals("{\"ID\":\"00000A\", \"Name\":\"1819 Inovation hub cup\", \"Description\":\"coffee mug\", \"Cost\":15.99}", p1.toJSON());
    }

    @Test
    void toXML() {
        assertEquals("<Product><ID>00000A</ID><Name>1819 Inovation hub cup</Name><Description>coffee mug</Description><Cost>15.99</Cost></Product>", p1.toXML());
    }
}