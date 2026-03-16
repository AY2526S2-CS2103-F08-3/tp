package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PostalCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PostalCode(null));
    }

    @Test
    public void constructor_invalidPostalCode_throwsIllegalArgumentException() {
        String invalidPostalCode = "";
        assertThrows(IllegalArgumentException.class, () -> new PostalCode(invalidPostalCode));
    }

    @Test
    public void isValidPostalCode() {
        // null postal code
        assertThrows(NullPointerException.class, () -> PostalCode.isValidPostalCode(null));

        // invalid postal codes
        assertFalse(PostalCode.isValidPostalCode("")); // empty string
        assertFalse(PostalCode.isValidPostalCode(" ")); // spaces only
        assertFalse(PostalCode.isValidPostalCode("12")); // too short
        assertFalse(PostalCode.isValidPostalCode("12345")); // less than 6 numbers
        assertFalse(PostalCode.isValidPostalCode("1234567")); // more than 6 numbers
        assertFalse(PostalCode.isValidPostalCode("12A456")); // alphabets within digits
        assertFalse(PostalCode.isValidPostalCode("123 56")); // spaces within digits

        // valid postal codes
        assertTrue(PostalCode.isValidPostalCode("000000"));
        assertTrue(PostalCode.isValidPostalCode("120311"));
    }

    @Test
    public void equals() {
        PostalCode postalCode = new PostalCode("120311");

        // same values -> returns true
        assertTrue(postalCode.equals(new PostalCode("120311")));

        // same object -> returns true
        assertTrue(postalCode.equals(postalCode));

        // null -> returns false
        assertFalse(postalCode.equals(null));

        // different types -> returns false
        assertFalse(postalCode.equals(5.0f));

        // different values -> returns false
        assertFalse(postalCode.equals(new PostalCode("530111")));
    }
}
