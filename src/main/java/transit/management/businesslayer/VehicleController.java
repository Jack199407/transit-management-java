package transit.management.businesslayer;

import transit.management.businesslayer.command.AssignRouteCommand;
import transit.management.businesslayer.command.Command;
import transit.management.businesslayer.command.Dispatcher;
import transit.management.businesslayer.command.VehicleManager;
import transit.management.businesslayer.dto.VehiclesAndRoutesDto;
import transit.management.businesslayer.factory.VehicleFactory;
import transit.management.dataacesslayer.dao.DepartureScheduleDAO;
import transit.management.dataacesslayer.dao.RouteDAO;
import transit.management.dataacesslayer.dao.UserDAO;
import transit.management.dataacesslayer.dao.VehicleDAO;
import transit.management.dataacesslayer.dao.impl.DepartureScheduleDAPImpl;
import transit.management.dataacesslayer.dao.impl.RouteDAOImpl;
import transit.management.dataacesslayer.dao.impl.UserDAOImpl;
import transit.management.dataacesslayer.dao.impl.VehicleDAOImpl;
import transit.management.transferobjects.DepartureSchedule;
import transit.management.transferobjects.Route;
import transit.management.transferobjects.User;
import transit.management.transferobjects.Vehicle;
import utils.DateConvertUtil;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
            list = vehicleDAO.listAll();
            routes = routeDAO.listAll();
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
}
