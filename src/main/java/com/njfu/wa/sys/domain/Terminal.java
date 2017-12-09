package com.njfu.wa.sys.domain;

/**
 * 终端
 */
public class Terminal {

    /**
     * 终端编号
     */
    private String terminalId;

    /**
     * 终端类型
     */
    private String terminalType;

    /**
     * 使用状态
     */
    private String useStatus;

    /**
     * 终端备注
     */
    private String terminalPs;

    public Terminal() {
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getTerminalPs() {
        return terminalPs;
    }

    public void setTerminalPs(String terminalPs) {
        this.terminalPs = terminalPs;
    }

    @Override
    public String toString() {
        return "Terminal{" +
                "terminalId='" + terminalId + '\'' +
                ", terminalType='" + terminalType + '\'' +
                ", useStatus='" + useStatus + '\'' +
                ", terminalPs='" + terminalPs + '\'' +
                '}';
    }
}
