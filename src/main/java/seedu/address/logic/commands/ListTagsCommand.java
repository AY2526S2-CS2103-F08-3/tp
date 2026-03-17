package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all tags present in the address book to the user.
 */
public class ListTagsCommand extends Command {
    public static final String COMMAND_WORD = "listTags";

    public static final String MESSAGE_PREFIX = "The present tags in decreasing order are: \n";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_PREFIX + model.getTagCounterDescription());
    }
}
