package com.njfu.ia.sys.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.njfu.ia.sys.utils.Constants;

import java.util.Date;

/**
 * 设备终端
 */
public class EndDevice {

    /**
     * 终端编号
     */
    private Integer id;

    /**
     * 终端型号
     */
    private String model;

    /**
     * 终端类型：0-终端，1-路由器，2-协调器
     */
    private Integer type;

    /**
     * 终端mac地址
     */
    private String mac;

    /**
     * 所属区块编号
     */
    private Integer sectionId;

    /**
     * 终端使用状态：0，未使用；1，使用中；2：故障中
     */
    private Integer useStatus;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = Constants.DATE_SECOND_FORMAT, timezone = Constants.DEFAULT_GMT)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = Constants.DATE_SECOND_FORMAT, timezone = Constants.DEFAULT_GMT)
    private Date updateTime;

    public EndDevice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
