package com.njfu.ia.process.domain;

public class DataType {

    /**
     * 数据类型编号
     */
    private Integer id;

    /**
     * 数据类型名称
     */
    private String dataTypeName;

    /**
     * 数据类型缩写
     */
    private String dataShortName;

    /**
     * 使用状态 0，未使用 1，使用中
     */
    private Integer useStatus;

    public DataType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataTypeName() {
        return dataTypeName;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public String getDataShortName() {
        return dataShortName;
    }

    public void setDataShortName(String dataShortName) {
        this.dataShortName = dataShortName;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    @Override
    public String toString() {
        return "DataType{" +
                "id=" + id +
                ", dataTypeName='" + dataTypeName + '\'' +
                ", useStatus=" + useStatus +
                '}';
    }
}
