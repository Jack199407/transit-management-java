package transit.management.dataacesslayer.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
    int insert(T entity) throws SQLException;

    int deleteById(int id);

    int update(T entity);

    T selectById(int id) throws SQLException;

    List<T> selectAll() throws SQLException;
}
