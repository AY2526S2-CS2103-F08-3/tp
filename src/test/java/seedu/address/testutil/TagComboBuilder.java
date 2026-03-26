package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COMBO_NAME;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagCombo;
import seedu.address.model.tag.TagComboName;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building {@code TagCombo} objects.
 */
public class TagComboBuilder {
    public static final String DEFAULT_NAME = VALID_TAG_COMBO_NAME;

    private TagComboName name;
    private Set<Tag> tagSet;

    /**
     * Constructs a default {@code TagCombo} object.
     */
    public TagComboBuilder() {
        name = new TagComboName(VALID_TAG_COMBO_NAME);
        tagSet = new HashSet<Tag>();
    }

    /**
     * Initializes the TagComboBuilder with the data of {@code tagCombo}.
     */
    public TagComboBuilder(TagCombo tagCombo) {
        name = tagCombo.getName();
        tagSet = tagCombo.getTagSet();
    }

    /**
     * Sets the {@code TagComboName} of the {@code TagCombo} that we are building.
     */
    public TagComboBuilder withName(String name) {
        this.name = new TagComboName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public TagComboBuilder withTags(String ... tags) {
        this.tagSet = SampleDataUtil.getTagSet(tags);
        return this;
    }


    public TagCombo build() {
        return new TagCombo(name, tagSet);
    }
}
