package transit.management.transferobjects;

public class Station {
    private Integer id;
    private String stationName;

    private Station(Builder builder) {
        this.id = builder.id;
        this.stationName = builder.stationName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public static class Builder {
        private Integer id;
        private String stationName;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder stationName(String stationName) {
            this.stationName = stationName == null ? null : stationName.trim();
            return this;
        }

        public Station build() {
            return new Station(this);
        }
    }
}
