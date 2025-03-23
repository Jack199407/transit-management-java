package transit.management.dataacesslayer.dao.impl;

import transit.management.dataacesslayer.DataSourceManager;
import transit.management.dataacesslayer.dao.UserDAO;
import transit.management.transferobjects.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final DataSourceManager dataSource;

    public UserDAOImpl() {
        this.dataSource = DataSourceManager.getInstance();
    }

    @Override
    public int insert(User entity) throws SQLException {
        String sql = "INSERT INTO user (user_name, email, password, role_type) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getUserName());
            stmt.setString(2, entity.getEmail());
            stmt.setString(3, entity.getPassword());
            stmt.setInt(4, entity.getRoleType());

            return stmt.executeUpdate();
        }
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public int update(User entity) {
        return 0;
    }

    @Override
    public User selectById(int id) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public User selectByNameAndPassword(String userName, String password) throws SQLException {
        String sql = "SELECT * FROM user WHERE user_name = ? AND password = ?";
        User user = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userName);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User.Builder()
                            .id(rs.getInt("id"))
                            .userName(rs.getString("user_name"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .roleType(rs.getByte("role_type"))
                            .build();
                }
            }
        }
        return user;
    }

    @Override
    public User selectByName(String userName) throws SQLException {
        String sql = "SELECT * FROM user WHERE user_name = ?";
        User user = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userName);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User.Builder()
                            .id(rs.getInt("id"))
                            .userName(rs.getString("user_name"))
                            .email(rs.getString("email"))
                            .password(rs.getString("password"))
                            .roleType(rs.getByte("role_type"))
                            .build();
                }
            }
        }
        return user;
    }
}
