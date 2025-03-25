package transit.management.dataacesslayer.dao;

import transit.management.transferobjects.Station;

import java.sql.SQLException;
import java.util.List;

public interface StationDAO extends BaseDAO<Station>{
    List<Station> listByIds(List<Integer> stationIds) throws SQLException;
}
