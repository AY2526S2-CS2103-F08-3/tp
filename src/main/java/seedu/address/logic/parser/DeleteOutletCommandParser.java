package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteOutletCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteOutletCommand object.
 */
public class DeleteOutletCommandParser implements Parser<DeleteOutletCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteOutletCommand
     * and returns a DeleteOutletCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteOutletCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteOutletCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteOutletCommand.MESSAGE_USAGE), pe);
        }
    }
}
