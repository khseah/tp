package seedu.mindmymoney.command;

/**
 * Represents an executable command. This class cannot be instantiated and serves as a Parent class for specific
 * Command classes to inherit from.
 */
public abstract class Command {

    /**
     * Executes the given command. This method is to be implemented by child classes.
     */
    public abstract void executeCommand();
}
