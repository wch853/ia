package com.njfu.wa.sys.domain;

/**
 * 机械
 */
public class Machine {

    private String machineId;

    private String machineType;

    private Block block;

    private String useStatus;

    private String machinePs;

    public Machine() {
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
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

    public String getMachinePs() {
        return machinePs;
    }

    public void setMachinePs(String machinePs) {
        this.machinePs = machinePs;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "machineId='" + machineId + '\'' +
                ", machineType='" + machineType + '\'' +
                ", block=" + block +
                ", useStatus='" + useStatus + '\'' +
                ", machinePs='" + machinePs + '\'' +
                '}';
    }
}
