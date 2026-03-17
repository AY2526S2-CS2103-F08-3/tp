package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ListTagsCommand.MESSAGE_PREFIX;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ListTagsCommandTest {
    private Model model;
    private Model expectedModel;
    private String expectedMessage = MESSAGE_PREFIX + "{[friends]=3, [owesMoney]=1}";

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_successful() {
        assertCommandSuccess(new ListTagsCommand(), model, expectedMessage, expectedModel);
    }
}
