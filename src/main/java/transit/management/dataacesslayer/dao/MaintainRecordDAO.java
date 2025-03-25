package transit.management.dataacesslayer.dao;

import transit.management.transferobjects.MaintainRecord;

import java.sql.SQLException;
import java.util.List;

public interface MaintainRecordDAO extends BaseDAO<MaintainRecord>{
    List<MaintainRecord> listByVehicleId(Integer vehicleId) throws SQLException;
}
