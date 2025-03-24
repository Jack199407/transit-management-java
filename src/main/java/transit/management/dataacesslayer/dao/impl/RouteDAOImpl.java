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
    public int insert(RouteDAO entity) {
        return 0;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public int update(RouteDAO entity) {
        return 0;
    }

    @Override
    public RouteDAO selectById(int id) {
        return null;
    }

    @Override
    public List<RouteDAO> selectAll() {
        return null;
    }

    @Override
    public List<Route> listAll() throws SQLException {
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
