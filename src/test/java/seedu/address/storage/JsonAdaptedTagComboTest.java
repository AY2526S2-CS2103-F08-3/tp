package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagCombo;
import seedu.address.model.tag.TagComboName;

public class JsonAdaptedTagComboTest {
    private static final String INVALID_NAME = "ml_dev";
    private static final String INVALID_TAG = "#python";

    private static final TagComboName VALID_NAME = new TagComboName("python dev");
    private static final Set<Tag> VALID_TAGS = Set.of(new Tag("python"), new Tag("java"));
    private static final List<JsonAdaptedTag> VALID_TAGS_JSON = Set.of(new Tag("python"), new Tag("java"))
            .stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    private static final TagCombo VALID_TAG_COMBO = new TagCombo(VALID_NAME, VALID_TAGS);

    @Test
    public void toModelType_validTagComboDetails_returnsTagCombo() throws Exception {
        JsonAdaptedTagCombo tagCombo = new JsonAdaptedTagCombo(VALID_TAG_COMBO);
        assertEquals(VALID_TAG_COMBO, tagCombo.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTagCombo tagCombo = new JsonAdaptedTagCombo(INVALID_NAME, VALID_TAGS_JSON);
        String expectedMessage = TagComboName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tagCombo::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>();
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTagCombo tagCombo = new JsonAdaptedTagCombo(VALID_NAME.name, invalidTags);
        assertThrows(IllegalValueException.class, tagCombo::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTagCombo tagCombo = new JsonAdaptedTagCombo(null, VALID_TAGS_JSON);
        String expectedMessage = String.format(JsonAdaptedTagCombo.MISSING_FIELD_MESSAGE_FORMAT,
                TagComboName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tagCombo::toModelType);
    }
}
