package transit.management.dataacesslayer.dao;

import transit.management.transferobjects.User;

import java.sql.SQLException;

public interface UserDAO extends BaseDAO<User>{
    User selectByNameAndPassword(String userName, String password) throws SQLException;

    User selectByName(String userName) throws SQLException;
}
