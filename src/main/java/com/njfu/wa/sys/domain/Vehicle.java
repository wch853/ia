package com.njfu.wa.sys.domain;

/**
 * 车辆
 */
public class Vehicle {

    private String vehId;

    private String vehType;

    private Block block;

    private String useStatus;

    private String vehPs;

    public Vehicle() {
    }

    public String getVehId() {
        return vehId;
    }

    public void setVehId(String vehId) {
        this.vehId = vehId;
    }

    public String getVehType() {
        return vehType;
    }

    public void setVehType(String vehType) {
        this.vehType = vehType;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getVehPs() {
        return vehPs;
    }

    public void setVehPs(String vehPs) {
        this.vehPs = vehPs;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehId='" + vehId + '\'' +
                ", vehType='" + vehType + '\'' +
                ", block=" + block +
                ", useStatus='" + useStatus + '\'' +
                ", vehPs='" + vehPs + '\'' +
                '}';
    }
}
