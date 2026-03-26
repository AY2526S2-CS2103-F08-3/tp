package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class TagComboTest {

    private final Set<Tag> firstTagSet = Set.of(new Tag("python"), new Tag("java"));
    private final Set<Tag> secondTagSet = Set.of(new Tag("python"), new Tag("java"));
    private final Set<Tag> thirdTagSet = Set.of(new Tag("python"), new Tag("java"), new Tag("C"));

    private final TagCombo firstTagCombo = new TagCombo(new TagComboName("one"), firstTagSet);
    private final TagCombo secondTagCombo = new TagCombo(new TagComboName("two"), secondTagSet);
    private final TagCombo thirdTagCombo = new TagCombo(new TagComboName("three"), thirdTagSet);
    private final TagCombo fourthTagCombo = new TagCombo(new TagComboName("three"), thirdTagSet);

    @Test
    public void equals() {
        assertFalse(firstTagCombo.equals(secondTagCombo));
        assertFalse(firstTagCombo.equals(thirdTagCombo));
        assertTrue(firstTagCombo.equals(firstTagCombo));
        assertFalse(firstTagCombo.equals((TagCombo) null));
    }

    @Test
    public void isSameTagCombo_sameTagSetDifferentName_returnsTrue() {
        assertTrue(firstTagCombo.isSameTagCombo(secondTagCombo));
    }

    @Test
    public void isSameTagCombo_differentTagSetDifferentName_returnFalse() {
        assertFalse(firstTagCombo.isSameTagCombo(thirdTagCombo));
    }

    @Test
    public void isSameTagCombo_sameNameDifferentTagSet_returnTrue() {
        assertTrue(thirdTagCombo.isSameTagCombo(fourthTagCombo));
    }

    @Test
    public void hashcode() {
        assertEquals(firstTagCombo.hashCode(), firstTagCombo.hashCode());
        assertNotEquals(firstTagCombo.hashCode(), thirdTagCombo.hashCode());
        assertNotEquals(firstTagCombo.hashCode(), secondTagCombo.hashCode());
    }
}
