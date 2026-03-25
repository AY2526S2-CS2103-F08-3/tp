package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.outlet.Outlet;
import seedu.address.model.person.Person;
import seedu.address.model.tag.TagCombo;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_OUTLET = "Outlets list contains duplicate outlet(s).";
    public static final String MESSAGE_DUPLICATE_TAG_COMBO = "Tag Combo list contains duplicate outlet(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedOutlet> outlets = new ArrayList<>();
    private final List<JsonAdaptedTagCombo> tagCombos = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
            @JsonProperty("outlets") List<JsonAdaptedOutlet> outlets,
            @JsonProperty("tagCombos") List<JsonAdaptedTagCombo> tagCombos) {
        if (persons != null) {
            this.persons.addAll(persons);
        }
        if (outlets != null) {
            this.outlets.addAll(outlets);
        }
        if (tagCombos != null) {
            this.tagCombos.addAll(tagCombos);
        }
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        outlets.addAll(source.getOutletList().stream().map(JsonAdaptedOutlet::new).collect(Collectors.toList()));
        tagCombos.addAll(source.getTagComboList().stream().map(JsonAdaptedTagCombo::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedOutlet jsonAdaptedOutlet : outlets) {
            Outlet outlet = jsonAdaptedOutlet.toModelType();
            if (addressBook.hasOutlet(outlet)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_OUTLET);
            }
            addressBook.addOutlet(outlet);
        }
        for (JsonAdaptedTagCombo jsonAdaptedTagCombo : tagCombos) {
            TagCombo tagCombo = jsonAdaptedTagCombo.toModelType();
            if (addressBook.hasTagCombo(tagCombo)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TAG_COMBO);
            }
            addressBook.addTagCombo(tagCombo);
        }
        return addressBook;
    }

}
