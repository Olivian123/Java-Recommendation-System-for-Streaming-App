import java.util.ArrayList;
import java.util.List;

/* manages the commands */
public class CommandCaller {

    /* stores the commands until ready to execute them */
    private final List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void executeCommands() {
        for (Command command : commands) {
            command.doCommand();
        }
    }
}
