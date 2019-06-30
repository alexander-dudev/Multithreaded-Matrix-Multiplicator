import org.junit.Test;
import static org.junit.Assert.*;

public class StringWithNumbersParserTest {

    @Test
    public void manipulateWithIntegers() {
        StringWithNumbersParser parser = new StringWithNumbersParser("3    1  2");
        assertFalse("The string must not be empty!", parser.isStringWithNumbersEmpty());

        int firstInteger = parser.extractFirstInteger();
        assertEquals("The first integer must be equal to 3", firstInteger, 3);
        assertFalse("The string must not be empty!", parser.isStringWithNumbersEmpty());

        int secondInteger = parser.extractFirstInteger();
        assertEquals("The second integer must be equal to 1", secondInteger, 1);
        assertFalse("The string must not be empty!", parser.isStringWithNumbersEmpty());

        int thirdInteger = parser.extractFirstInteger();
        assertEquals("The third integer must be equal to 2", thirdInteger, 2);
        assertTrue("The string must be empty!", parser.isStringWithNumbersEmpty());
    }

    @Test
    public void manipulateWithDoubles() {
        StringWithNumbersParser parser = new StringWithNumbersParser("3.1415926      1.61         2.71");
        assertFalse("The string must not be empty!", parser.isStringWithNumbersEmpty());

        double firstDouble = parser.extractFirstDouble();
        assertEquals("The first double must be equal to 3.1415926", firstDouble, 3.1415926, 0.01);
        assertFalse("The string must not be empty!", parser.isStringWithNumbersEmpty());

        double secondDouble = parser.extractFirstDouble();
        assertEquals("The second double must be equal to 1.61", secondDouble, 1.61, 0.01);
        assertFalse("The string must not be empty!", parser.isStringWithNumbersEmpty());

        double thirdDouble = parser.extractFirstDouble();
        assertEquals("The third double must be equal to 2.71", thirdDouble, 2.71, 0.01);
        assertTrue("The string must be empty!", parser.isStringWithNumbersEmpty());
    }
}
