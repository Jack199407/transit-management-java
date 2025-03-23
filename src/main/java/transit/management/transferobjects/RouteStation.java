package transit.management.transferobjects;

public class RouteStation {
    private Integer id;
    private Integer routeId;
    private Integer stationId;
    private Integer stationOrder;

    private RouteStation(Builder builder) {
        this.id = builder.id;
        this.routeId = builder.routeId;
        this.stationId = builder.stationId;
        this.stationOrder = builder.stationOrder;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public void setStationOrder(Integer stationOrder) {
        this.stationOrder = stationOrder;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public Integer getStationOrder() {
        return stationOrder;
    }

    public static class Builder {
        private Integer id;
        private Integer routeId;
        private Integer stationId;
        private Integer stationOrder;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder routeId(Integer routeId) {
            this.routeId = routeId;
            return this;
        }

        public Builder stationId(Integer stationId) {
            this.stationId = stationId;
            return this;
        }

        public Builder stationOrder(Integer stationOrder) {
            this.stationOrder = stationOrder;
            return this;
        }

        public RouteStation build() {
            return new RouteStation(this);
        }
    }
}
