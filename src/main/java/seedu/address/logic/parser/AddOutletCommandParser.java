package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTAL_CODE;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddOutletCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.outlet.Outlet;
import seedu.address.model.outlet.OutletAddress;
import seedu.address.model.outlet.OutletName;
import seedu.address.model.outlet.OutletPostalCode;

/**
 * Parses input arguments and creates a new AddOutletCommand object.
 */
public class AddOutletCommandParser implements Parser<AddOutletCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddOutletCommand
     * and returns an AddOutletCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddOutletCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS,
                PREFIX_POSTAL_CODE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_POSTAL_CODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOutletCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_POSTAL_CODE);
        OutletName outletName = ParserUtil.parseOutletName(argMultimap.getValue(PREFIX_NAME).get());
        OutletAddress outletAddress = ParserUtil.parseOutletAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        OutletPostalCode outletPostalCode =
                ParserUtil.parseOutletPostalCode(argMultimap.getValue(PREFIX_POSTAL_CODE).get());

        Outlet outlet = new Outlet(outletName, outletAddress, outletPostalCode);
        return new AddOutletCommand(outlet);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
