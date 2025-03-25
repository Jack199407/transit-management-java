package transit.management.dataacesslayer.dao.impl;

import transit.management.dataacesslayer.DataSourceManager;
import transit.management.dataacesslayer.dao.StationDAO;
import transit.management.transferobjects.Station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StationDAOImpl implements StationDAO {
    private final DataSourceManager dataSource;

    public StationDAOImpl() {
        this.dataSource = DataSourceManager.getInstance();
    }
    @Override
    public int insert(Station entity) {
        return 0;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public int update(Station entity) {
        return 0;
    }

    @Override
    public Station selectById(int id) {
        return null;
    }

    @Override
    public List<Station> selectAll() {
        return null;
    }

    @Override
    public List<Station> listByIds(List<Integer> stationIds) throws SQLException {
        List<Station> stations = new ArrayList<>();

        if (stationIds == null || stationIds.isEmpty()) {
            return stations; // 空列表直接返回
        }

        StringBuilder sql = new StringBuilder("SELECT * FROM station WHERE id IN (");
        String placeholders = String.join(",", Collections.nCopies(stationIds.size(), "?"));
        sql.append(placeholders).append(")");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < stationIds.size(); i++) {
                stmt.setInt(i + 1, stationIds.get(i)); // JDBC 参数从 1 开始
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Station station = new Station.Builder()
                            .id(rs.getInt("id"))
                            .stationName(rs.getString("station_name"))
                            .build();
                    stations.add(station);
                }
            }
        }

        return stations;
    }
}
