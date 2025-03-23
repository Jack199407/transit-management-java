package transit.management.businesslayer.observer;

import transit.management.transferobjects.Vehicle;

public interface Observer {
    void update(Vehicle vehicle);
}
