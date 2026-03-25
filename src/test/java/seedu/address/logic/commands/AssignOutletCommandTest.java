package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.outlet.Outlet;
import seedu.address.model.person.Person;
import seedu.address.testutil.OutletBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.ui.UiAction;
import seedu.address.ui.content.PersonContent;

/**
 * Contains integration tests for {@code AssignOutletCommand}.
 */
public class AssignOutletCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addOutlet(new OutletBuilder().build());
        model.addOutlet(new OutletBuilder().withName("FinServ").withAddress("Marina Bay").withPostalCode("018956")
                .build());
    }

    @Test
    public void execute_validIndexes_success() {
        Person personToAssign = model.getFilteredPersonList().get(INDEX_FIRST_ENTRY.getZeroBased());
        Outlet outletToAssign = model.getFilteredOutletList().get(INDEX_SECOND_ENTRY.getZeroBased());
        Person assignedPerson = new PersonBuilder(personToAssign).withWorkingAddress(outletToAssign).build();

        AssignOutletCommand assignOutletCommand = new AssignOutletCommand(INDEX_FIRST_ENTRY, INDEX_SECOND_ENTRY);
        String expectedMessage = String.format(AssignOutletCommand.MESSAGE_SUCCESS, personToAssign.getName(),
                outletToAssign.getOutletName());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(personToAssign, assignedPerson);

        assertCommandSuccess(assignOutletCommand, model, expectedMessage, expectedModel,
                UiAction.UPDATE_RIGHT_PANE,
                Optional.of(new PersonContent(assignedPerson, "Candidate #" + INDEX_FIRST_ENTRY.getOneBased())));
    }

    @Test
    public void execute_invalidPersonIndex_failure() {
        Index outOfBoundPersonIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        AssignOutletCommand assignOutletCommand = new AssignOutletCommand(outOfBoundPersonIndex, INDEX_FIRST_ENTRY);

        assertCommandFailure(assignOutletCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidOutletIndex_failure() {
        Index outOfBoundOutletIndex = Index.fromOneBased(model.getFilteredOutletList().size() + 1);
        AssignOutletCommand assignOutletCommand = new AssignOutletCommand(INDEX_FIRST_ENTRY, outOfBoundOutletIndex);

        assertCommandFailure(assignOutletCommand, model, Messages.MESSAGE_INVALID_OUTLET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AssignOutletCommand assignFirstCommand = new AssignOutletCommand(INDEX_FIRST_ENTRY, INDEX_FIRST_ENTRY);
        AssignOutletCommand assignSecondCommand = new AssignOutletCommand(INDEX_SECOND_ENTRY, INDEX_SECOND_ENTRY);

        assertTrue(assignFirstCommand.equals(assignFirstCommand));
        assertTrue(assignFirstCommand.equals(new AssignOutletCommand(INDEX_FIRST_ENTRY, INDEX_FIRST_ENTRY)));
        assertFalse(assignFirstCommand.equals(1));
        assertFalse(assignFirstCommand.equals(null));
        assertFalse(assignFirstCommand.equals(assignSecondCommand));
    }

    @Test
    public void toStringMethod() {
        AssignOutletCommand assignOutletCommand = new AssignOutletCommand(INDEX_FIRST_ENTRY, INDEX_SECOND_ENTRY);
        String expected = AssignOutletCommand.class.getCanonicalName() + "{candidateIndex="
                + INDEX_FIRST_ENTRY + ", outletIndex=" + INDEX_SECOND_ENTRY + "}";
        assertEquals(expected, assignOutletCommand.toString());
    }
}
