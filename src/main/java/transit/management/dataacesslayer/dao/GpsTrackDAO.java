package transit.management.dataacesslayer.dao;

import transit.management.transferobjects.GpsTrack;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface GpsTrackDAO extends BaseDAO<GpsTrack>{
    List<GpsTrack> listByScheduleId(Integer id) throws SQLException;

    GpsTrack queryByScheduleIdAndStationId(Integer scheduleId, Integer stationId) throws SQLException;


    boolean updateByScheduleIdAndStationId
            (Integer scheduleId, Integer stationId, Date departTime,
             Date arrivalTime, BigDecimal realMiles, BigDecimal realConsumption) throws SQLException;
}
