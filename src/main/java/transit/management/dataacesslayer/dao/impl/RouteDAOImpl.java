package transit.management.dataacesslayer.dao.impl;

import transit.management.dataacesslayer.DataSourceManager;
import transit.management.dataacesslayer.dao.RouteDAO;
import transit.management.transferobjects.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDAOImpl implements RouteDAO {
    private final DataSourceManager dataSource;

    public RouteDAOImpl() {
        this.dataSource = DataSourceManager.getInstance();
    }
    @Override
    public int insert(Route entity) {
        return 0;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public int update(Route entity) {
        return 0;
    }

    @Override
    public Route selectById(int id) throws SQLException {
        String sql = "SELECT * FROM route WHERE id = ?";
        Route route = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    route = new Route.Builder()
                            .id(rs.getInt("id"))
                            .routeName(rs.getString("route_name"))
                            .expectedTotalMiles(rs.getBigDecimal("expected_total_miles"))
                            .build();
                }
            }
        }
        return route;
    }

    @Override
    public List<Route> selectAll() throws SQLException {
        List<Route> routes = new ArrayList<>();
        String sql = "SELECT * FROM route";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Route route = new Route.Builder()
                        .id(rs.getInt("id"))
                        .routeName(rs.getString("route_name"))
                        .expectedTotalMiles(rs.getBigDecimal("expected_total_miles"))
                        .build();
                routes.add(route);
            }
        }
        return routes;
    }

}
