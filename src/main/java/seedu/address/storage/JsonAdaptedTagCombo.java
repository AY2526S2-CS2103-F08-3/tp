package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagCombo;
import seedu.address.model.tag.TagComboName;

/**
 * Jackson-friendly version of {@link TagCombo}.
 */
public class JsonAdaptedTagCombo {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tag Combo's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTagCombo} with the given tagCombo details.
     */
    @JsonCreator
    public JsonAdaptedTagCombo(@JsonProperty("name") String name, @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedTagCombo(TagCombo source) {
        name = source.getName().name;
        tags.addAll(source.getTagSet().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted tagCombo object into the model's {@code TagCombo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tagCombo.
     */
    public TagCombo toModelType() throws IllegalValueException {
        final List<Tag> tagComboTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            tagComboTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TagComboName.class.getSimpleName()));
        }
        if (!TagComboName.isValidName(name)) {
            throw new IllegalValueException(TagComboName.MESSAGE_CONSTRAINTS);
        }
        final TagComboName modelName = new TagComboName(name);
        final Set<Tag> modelTags = new HashSet<>(tagComboTags);
        return new TagCombo(modelName, modelTags);
    }
}
