package transit.management.dataacesslayer.dao;

import transit.management.transferobjects.GpsTrack;

import java.sql.SQLException;
import java.util.List;

public interface GpsTrackDAO extends BaseDAO<GpsTrack>{
    List<GpsTrack> listByScheduleId(Integer id) throws SQLException;
}
