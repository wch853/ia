package com.njfu.ia.sys.domain;

/**
 * 作物
 */
public class Crop {

    /**
     * 作物编号
     */
    private Integer Id;

    /**
     * 作物名称
     */
    private String cropName;

    public Crop() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }
}
