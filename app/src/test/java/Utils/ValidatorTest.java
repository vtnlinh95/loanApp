package Utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 4/30/18.
 */

public class ValidatorTest {

    @Test
    public void testPhoneValidator() {
        String [] testValidPhones = new String[] {
                "01678816910", "01225516910", "01881111111",
                "0891111111", "0933944279"
        };
        for (String testPhone : testValidPhones) {
            assertTrue(Validator.phoneValidator(testPhone));
        }
        String [] testInvalidPhones = new String[] {
                "0167881691", "0125516910", "018811111",
                "08911111111", "093944279"
        };
        for (String testPhone : testInvalidPhones) {
            assertFalse(Validator.phoneValidator(testPhone));
        }
    }

    @Test
    public void testNationalIDValidator() {
        String nationalID = "013312345";
        assertTrue(Validator.nationalIDValidator(nationalID));
        String invalidNationalID = "A12345";
        assertFalse(Validator.nationalIDValidator(invalidNationalID));
    }
}
