package transit.management.dataacesslayer.dao;

import transit.management.transferobjects.RouteStation;

import java.sql.SQLException;
import java.util.List;

public interface RouteStationDAO extends BaseDAO<RouteStation> {
    List<RouteStation> selectByRouteId(Integer routeId) throws SQLException;
}
