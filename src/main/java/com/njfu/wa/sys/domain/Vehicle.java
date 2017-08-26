package com.njfu.wa.sys.domain;

/**
 * 车辆
 */
public class Vehicle {

    private String vehicleId;

    private String vehicleType;

    private Block block;

    private String useStatus;

    private String vehiclePs;

    public Vehicle() {
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
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

    public String getVehiclePs() {
        return vehiclePs;
    }

    public void setVehiclePs(String vehiclePs) {
        this.vehiclePs = vehiclePs;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", block=" + block +
                ", useStatus='" + useStatus + '\'' +
                ", vehiclePs='" + vehiclePs + '\'' +
                '}';
    }
}
