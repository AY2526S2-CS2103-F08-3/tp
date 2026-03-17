package seedu.address.ui.content;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

public class PersonContentTest {

    @Test
    public void equals() {
        PersonContent firstPersonContent = new PersonContent(ALICE);
        PersonContent secondPersonContent = new PersonContent(BENSON);

        assertTrue(firstPersonContent.equals(new PersonContent(ALICE)));
        assertFalse(firstPersonContent.equals(secondPersonContent));
        assertTrue(firstPersonContent.equals(firstPersonContent));
    }

    @Test
    public void hashcode() {
        PersonContent firstPersonContent = new PersonContent(ALICE);
        PersonContent secondPersonContent = new PersonContent(BENSON);

        assertEquals(firstPersonContent.hashCode(), new PersonContent(ALICE).hashCode());
        assertEquals(firstPersonContent.hashCode(), firstPersonContent.hashCode());
        assertNotEquals(firstPersonContent.hashCode(), secondPersonContent.hashCode());
    }
}
