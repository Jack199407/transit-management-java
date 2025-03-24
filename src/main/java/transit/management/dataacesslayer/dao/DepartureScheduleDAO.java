package transit.management.dataacesslayer.dao;

import transit.management.transferobjects.DepartureSchedule;

import java.sql.SQLException;
import java.util.List;

public interface DepartureScheduleDAO extends BaseDAO<DepartureSchedule>{
    List<DepartureSchedule> listAllByVehicleId(Integer vehicleId) throws SQLException;

    boolean updateStatus(Integer scheduleId, String newStatus) throws SQLException;
}
