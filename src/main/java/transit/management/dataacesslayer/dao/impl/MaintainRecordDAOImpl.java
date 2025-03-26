package transit.management.dataacesslayer.dao.impl;

import transit.management.dataacesslayer.DataSourceManager;
import transit.management.dataacesslayer.dao.MaintainRecordDAO;
import transit.management.transferobjects.MaintainRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaintainRecordDAOImpl implements MaintainRecordDAO {
    private final DataSourceManager dataSource;

    public MaintainRecordDAOImpl() {
        this.dataSource = DataSourceManager.getInstance();
    }
    @Override
    public int insert(MaintainRecord entity) throws SQLException {
        String sql = "INSERT INTO maintain_record (vehicle_id, maintain_time) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, entity.getVehicleId());
            stmt.setTimestamp(2, new Timestamp(entity.getMaintainTime().getTime()));

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }

            return -1;
        }
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }

    @Override
    public int update(MaintainRecord entity) {
        return 0;
    }

    @Override
    public MaintainRecord selectById(int id) {
        return null;
    }

    @Override
    public List<MaintainRecord> selectAll() {
        return null;
    }

    @Override
    public List<MaintainRecord> listByVehicleId(Integer vehicleId) throws SQLException {
        List<MaintainRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM maintain_record WHERE vehicle_id = ? ORDER BY maintain_time DESC";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vehicleId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MaintainRecord record = new MaintainRecord.Builder()
                            .id(rs.getInt("id"))
                            .vehicleId(rs.getInt("vehicle_id"))
                            .maintainTime(rs.getTimestamp("maintain_time"))
                            .build();
                    records.add(record);
                }
            }
        }

        return records;
    }
}
