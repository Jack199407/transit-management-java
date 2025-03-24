package transit.management.businesslayer.dto;

import transit.management.transferobjects.Route;
import transit.management.transferobjects.Vehicle;

import java.util.List;

public class VehiclesAndRoutesDto {
    List<Vehicle> vehicles;
    List<Route> routes;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
