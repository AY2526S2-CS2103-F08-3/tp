package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class PersonContainsTagsPredicateTest {

    @Test
    public void equals() {
        Set<Tag> firstPredicateTagSet = Set.of(new Tag("Java"), new Tag("Python"));
        Set<Tag> secondPredicateTagSet = Set.of(new Tag("Java"), new Tag("Python"), new Tag("C"));

        PersonContainsTagsPredicate firstPredicate = new PersonContainsTagsPredicate(firstPredicateTagSet);
        PersonContainsTagsPredicate secondPredicate = new PersonContainsTagsPredicate(secondPredicateTagSet);

        // same object -> return true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same tag set -> return true
        PersonContainsTagsPredicate firstPredicateCopy = new PersonContainsTagsPredicate(firstPredicateTagSet);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different type -> return false
        assertFalse(firstPredicate.equals(1));

        // null -> return false
        assertFalse(firstPredicate.equals(null));

        // different tag set -> return false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personContainsTags_returnsTrue() {
        Set<Tag> firstPredicateTagSet = Set.of(new Tag("Java"), new Tag("Python"));
        Set<Tag> secondPredicateTagSet = Set.of(new Tag("C"));
        PersonContainsTagsPredicate firstPredicate = new PersonContainsTagsPredicate(firstPredicateTagSet);
        PersonContainsTagsPredicate secondPredicate = new PersonContainsTagsPredicate(secondPredicateTagSet);

        assertTrue(firstPredicate.test(new PersonBuilder().withTags("Java", "Python", "C").build()));
        assertTrue(secondPredicate.test(new PersonBuilder().withTags("Java", "Python", "C").build()));
        assertTrue(firstPredicate.test(new PersonBuilder().withTags("Java", "Python").build()));
        assertTrue(secondPredicate.test(new PersonBuilder().withTags("C").build()));
    }

    @Test
    public void test_personDoesNotContainsTags_returnsFalse() {
        Set<Tag> firstPredicateTagSet = Set.of(new Tag("Java"), new Tag("Python"));
        Set<Tag> secondPredicateTagSet = Set.of(new Tag("C"));
        PersonContainsTagsPredicate firstPredicate = new PersonContainsTagsPredicate(firstPredicateTagSet);
        PersonContainsTagsPredicate secondPredicate = new PersonContainsTagsPredicate(secondPredicateTagSet);

        assertFalse(secondPredicate.test(new PersonBuilder().withTags("Java", "Python").build()));
        assertFalse(firstPredicate.test(new PersonBuilder().withTags("C").build()));
        assertFalse(firstPredicate.test(new PersonBuilder().withTags("Java").build()));
    }
    @Test
    public void toStringMethod() {
        Set<Tag> predicateTagSet = Set.of(new Tag("Java"), new Tag("Python"));
        PersonContainsTagsPredicate predicate = new PersonContainsTagsPredicate(predicateTagSet);

        assertTrue(predicate.toString().contains("java"));
        assertTrue(predicate.toString().contains("python"));
        assertFalse(predicate.toString().contains("Java"));
        assertFalse(predicate.toString().contains("Python"));
    }
}
