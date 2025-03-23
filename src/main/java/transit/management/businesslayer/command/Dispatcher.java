package transit.management.businesslayer.command;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dispatcher {
    private List<Command> commandQueue = new ArrayList<>();

    public void addCommand(Command command) {
        commandQueue.add(command);
    }

    public void executeCommands() throws SQLException {
        for (Command command : commandQueue) {
            command.execute();
        }
        commandQueue.clear();
    }
}
