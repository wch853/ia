package com.njfu.ia.sys.domain;

/**
 * 大棚
 */
public class Field {

    /**
     * 大棚编号
     */
    private Integer id;

    /**
     * 大棚名称
     */
    private String fieldName;

    /**
     * 所属区块编号
     */
    private Integer sectionId;

    /**
     * 种植作物编号
     */
    private Integer cropId;

    /**
     * 种植作物名称
     */
    private String cropName;

    public Field() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getCropId() {
        return cropId;
    }

    public void setCropId(Integer cropId) {
        this.cropId = cropId;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }
}
