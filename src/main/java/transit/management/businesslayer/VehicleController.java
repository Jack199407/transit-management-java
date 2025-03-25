package transit.management.businesslayer;

import transit.management.businesslayer.command.AssignRouteCommand;
import transit.management.businesslayer.command.Command;
import transit.management.businesslayer.command.Dispatcher;
import transit.management.businesslayer.command.VehicleManager;
import transit.management.businesslayer.dto.ScheduleTracksDto;
import transit.management.businesslayer.dto.VehiclesAndRoutesDto;
import transit.management.businesslayer.factory.VehicleFactory;
import transit.management.dataacesslayer.dao.*;
import transit.management.dataacesslayer.dao.impl.*;
import transit.management.transferobjects.*;
import transit.management.viewlayer.dto.AddOrUpdateDto;
import utils.DateConvertUtil;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class VehicleController {

    private static volatile VehicleController instance;

    private VehicleController() {}

    public static VehicleController getInstance() {
        if (instance == null) {
            synchronized (VehicleController.class) {
                if (instance == null) {
                    instance = new VehicleController();
                }
            }
        }
        return instance;
    }

    private final UserDAO userDAO = new UserDAOImpl();
    private final VehicleDAO vehicleDAO = new VehicleDAOImpl();
    private final DepartureScheduleDAO departureScheduleDAO = new DepartureScheduleDAPImpl();
    private final RouteDAO routeDAO = new RouteDAOImpl();
    private final RouteStationDAO routeStationDAO = new RouteStationDAOImpl();
    private final StationDAO stationDAO = new StationDAOImpl();
    private final GpsTrackDAO gpsTrackDAO = new GpsTrackDAOImpl();

    public boolean validUserInfo(String userName, String password) {
        User user;
        try {
            user = userDAO.selectByNameAndPassword(userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user != null;
    }

    public String signUp(String name, String email, String password, byte role) {
        User user;
        try {
            user = userDAO.selectByName(name);
            if (Objects.nonNull(user)) {
                return "Username exists!";
            }
            int insert = userDAO.insert(new User.Builder()
                    .userName(name)
                    .email(email)
                    .password(password)
                    .roleType(role).build());
            if (insert < 1) {
                return "insert error";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

    public VehiclesAndRoutesDto listVehicles() {
        VehiclesAndRoutesDto dto = new VehiclesAndRoutesDto();
        List<Vehicle> list;
        List<Route> routes;
        try {
            list = vehicleDAO.selectAll();
            routes = routeDAO.selectAll();
            Map<Integer, Route> routeMap = routes.stream()
                    .collect(Collectors.toMap(Route::getId, Function.identity(), (v1, v2) -> v1));
            for (Vehicle vehicle : list) {
                vehicle.setCurrentRouteName(routeMap.get(vehicle.getCurrentAssignedRouteId()).getRouteName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dto.setVehicles(list);
        dto.setRoutes(routes);
        return dto;
    }

    public void AssignRoute(Integer vehicleId, Integer routeId) {
        VehicleManager manager = new VehicleManager();
        Command assignRoute = new AssignRouteCommand(manager, vehicleId, routeId);

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.addCommand(assignRoute);
        try {
            dispatcher.executeCommands();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void addVehicle(String type) {
        Vehicle vehicle = VehicleFactory.createVehicle(type);
        try {
            vehicleDAO.insert(vehicle);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DepartureSchedule> listSchedulesByVehicleId(Integer vehicleId) {
        List<DepartureSchedule> list;

        try {
            list = departureScheduleDAO.listAllByVehicleId(vehicleId);
            list.sort(Comparator.comparing(DepartureSchedule::getExpectedDepartTime));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public boolean updateStatus(Integer scheduleId, String newStatus) {
        boolean success;
        try {
            success = departureScheduleDAO.updateStatus(scheduleId, newStatus);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    public boolean addSchedule(Integer vehicleId, Integer routeId, String expectedDepartTime, String expectedArrivalTime) {
        DepartureSchedule scheduled = new DepartureSchedule.Builder()
                .vehicleId(vehicleId)
                .routeId(routeId)
                .expectedDepartTime(DateConvertUtil.StringToDate(expectedDepartTime))
                .expectedArrivalTime(DateConvertUtil.StringToDate(expectedArrivalTime))
                .status("scheduled").build();
        int insert = 0;
        try {
            insert = departureScheduleDAO.insert(scheduled);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return insert > 0;
    }

    public List<ScheduleTracksDto> listTracksByVehicleIdAndScheduleId(DepartureSchedule schedule) {
        List<ScheduleTracksDto> res;
        Integer vehicleId = schedule.getVehicleId();
        Integer routeId = schedule.getRouteId();
        try {
            // list all stations on this route
            Route route = routeDAO.selectById(routeId);
            List<RouteStation> rs = routeStationDAO.selectByRouteId(routeId);
            List<Integer> stationIds = rs.stream().map(RouteStation::getStationId).collect(Collectors.toList());
            List<Station> stations = stationDAO.listByIds(stationIds);
            Map<Integer, String> stationMap = stations.stream()
                    .collect(Collectors.toMap(
                            Station::getId,
                            Station::getStationName,
                            (v1, v2) -> v1
                    ));
            // list all stations have arrived
            List<GpsTrack> gps = gpsTrackDAO.listByScheduleId(schedule.getId());
            Map<Integer, GpsTrack> gpsMap = gps.stream().collect(Collectors.toMap(
                            GpsTrack::getStationId,
                            Function.identity(),
                            (v1, v2) -> v1
                    ));
            // combine data together
            res = rs.stream().map(s -> {
                        ScheduleTracksDto dto = new ScheduleTracksDto();
                        dto.setScheduleId(schedule.getId());
                        dto.setVehicleId(vehicleId);
                        dto.setRouteId(routeId);
                        dto.setRouteName(route.getRouteName());
                        dto.setStationId(s.getStationId());
                        dto.setStationName(stationMap.get(s.getStationId()));
                        dto.setStationOrder(s.getStationOrder());
                        // check if this station has arrived
                        GpsTrack track = gpsMap.getOrDefault(s.getStationId(), null);
                        boolean arrived = Objects.nonNull(track);
                        dto.setDepartTime(arrived ? track.getDepartTime() : null);
                        dto.setArrivalTime(arrived ? track.getArrivalTime() : null);
                        dto.setRealMiles(arrived ? track.getRealMiles() : new BigDecimal(0));
                        dto.setRealConsumption(arrived ? track.getRealConsumption() : new BigDecimal(0));
                        dto.setDone(arrived);
                        return dto;
                    })
                    .collect(Collectors.toList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    public boolean addOrUpdateTrack(AddOrUpdateDto dto) {
        // query by scheduleId and stationId
        boolean finish =false;
        BigDecimal diffMiles = new BigDecimal(0);
        BigDecimal diffConsumption = new BigDecimal(0);
        try {
            GpsTrack exist = gpsTrackDAO.queryByScheduleIdAndStationId(dto.getScheduleId(), dto.getStationId());
            if (Objects.nonNull(exist)) {
                // update
                boolean arrivalEqual = Objects.equals(exist.getArrivalTime(), dto.getArrivalTime());
                boolean departEqual = Objects.equals(exist.getDepartTime(), dto.getDepartTime());
                boolean milesEqual = Objects.equals(exist.getRealMiles(), dto.getRealMiles());
                boolean consumptionEqual = Objects.equals(exist.getRealConsumption(), dto.getRealConsumption());
                if (arrivalEqual && departEqual && milesEqual && consumptionEqual) {
                    // nothing changed, return true
                    return true;
                }
                 finish = gpsTrackDAO.updateByScheduleIdAndStationId(dto.getScheduleId(), dto.getStationId(),
                        dto.getDepartTime(), dto.getArrivalTime(), dto.getRealMiles(), dto.getRealConsumption());
                diffMiles = dto.getRealMiles().subtract(exist.getRealMiles());
                diffConsumption = dto.getRealConsumption().subtract(exist.getRealConsumption());
            } else {
                GpsTrack entity = new GpsTrack.Builder()
                        .departureScheduleId(dto.getScheduleId())
                        .stationId(dto.getStationId())
                        .departTime(dto.getDepartTime())
                        .arrivalTime(dto.getArrivalTime())
                        .realMiles(dto.getRealMiles())
                        .realConsumption(dto.getRealConsumption()).build();
                finish = gpsTrackDAO.insert(entity) > 0;
                diffMiles = dto.getRealMiles();
                diffConsumption = dto.getRealConsumption();
            }
            if (finish) {
                int updateVehicle = vehicleDAO
                        .updateMilesAndConsumptionByVehicleId(dto.getVehicleId(), diffMiles, diffConsumption);
                return updateVehicle >0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
