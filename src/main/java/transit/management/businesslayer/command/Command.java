package transit.management.businesslayer.command;

import java.sql.SQLException;

public interface Command {
    void execute() throws SQLException;
}
