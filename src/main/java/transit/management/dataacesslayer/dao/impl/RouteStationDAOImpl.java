package transit.management.dataacesslayer.dao.impl;

import transit.management.dataacesslayer.DataSourceManager;
import transit.management.dataacesslayer.dao.RouteStationDAO;
import transit.management.transferobjects.RouteStation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteStationDAOImpl implements RouteStationDAO {
    private final DataSourceManager dataSource;

    public RouteStationDAOImpl() {
        this.dataSource = DataSourceManager.getInstance();
    }
    @Override
    public int insert(RouteStation entity) {
        return 0;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public int update(RouteStation entity) {
        return 0;
    }

    @Override
    public RouteStation selectById(int id) {
        return null;
    }

    @Override
    public List<RouteStation> selectAll() {
        return null;
    }

    @Override
    public List<RouteStation> selectByRouteId(Integer routeId) throws SQLException {
        List<RouteStation> result = new ArrayList<>();
        String sql = "SELECT * FROM route_station WHERE route_id = ? ORDER BY station_order ASC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, routeId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RouteStation routeStation = new RouteStation.Builder()
                            .id(rs.getInt("id"))
                            .routeId(rs.getInt("route_id"))
                            .stationId(rs.getInt("station_id"))
                            .stationOrder(rs.getInt("station_order"))
                            .build();
                    result.add(routeStation);
                }
            }
        }
        return result;
    }
}
