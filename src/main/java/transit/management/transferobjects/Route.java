package transit.management.transferobjects;

import java.math.BigDecimal;

public class Route {
    private Integer id;
    private String routeName;
    private BigDecimal expectedTotalMiles;

    private Route(Builder builder) {
        this.id = builder.id;
        this.routeName = builder.routeName;
        this.expectedTotalMiles = builder.expectedTotalMiles;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public void setExpectedTotalMiles(BigDecimal expectedTotalMiles) {
        this.expectedTotalMiles = expectedTotalMiles;
    }

    public Integer getId() {
        return id;
    }

    public String getRouteName() {
        return routeName;
    }

    public BigDecimal getExpectedTotalMiles() {
        return expectedTotalMiles;
    }

    public static class Builder {
        private Integer id;
        private String routeName;
        private BigDecimal expectedTotalMiles;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder routeName(String routeName) {
            this.routeName = routeName == null ? null : routeName.trim();
            return this;
        }

        public Builder expectedTotalMiles(BigDecimal expectedTotalMiles) {
            this.expectedTotalMiles = expectedTotalMiles;
            return this;
        }

        public Route build() {
            return new Route(this);
        }
    }
}
