package transit.management.businesslayer;

import transit.management.businesslayer.command.AssignRouteCommand;
import transit.management.businesslayer.command.Command;
import transit.management.businesslayer.command.Dispatcher;
import transit.management.businesslayer.command.VehicleManager;
import transit.management.businesslayer.factory.VehicleFactory;
import transit.management.dataacesslayer.dao.UserDAO;
import transit.management.dataacesslayer.dao.VehicleDAO;
import transit.management.dataacesslayer.dao.impl.UserDAOImpl;
import transit.management.dataacesslayer.dao.impl.VehicleDAOImpl;
import transit.management.transferobjects.User;
import transit.management.transferobjects.Vehicle;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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

    public List<Vehicle> listVehicles() {
        List<Vehicle> list;
        try {
            list = vehicleDAO.listAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
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
}
