package transit.management.viewlayer.dto;

import java.math.BigDecimal;

public class AnalyticDto {
    private BigDecimal realTotalMiles;
    private BigDecimal realTotalConsumption;
    private boolean normal;
    private boolean needMaintain;
    private Integer maintainCount;

    public BigDecimal getRealTotalMiles() {
        return realTotalMiles;
    }

    public void setRealTotalMiles(BigDecimal realTotalMiles) {
        this.realTotalMiles = realTotalMiles;
    }

    public BigDecimal getRealTotalConsumption() {
        return realTotalConsumption;
    }

    public void setRealTotalConsumption(BigDecimal realTotalConsumption) {
        this.realTotalConsumption = realTotalConsumption;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    public boolean isNeedMaintain() {
        return needMaintain;
    }

    public void setNeedMaintain(boolean needMaintain) {
        this.needMaintain = needMaintain;
    }

    public Integer getMaintainCount() {
        return maintainCount;
    }

    public void setMaintainCount(Integer maintainCount) {
        this.maintainCount = maintainCount;
    }
}
