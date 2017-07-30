package com.njfu.wa.sys.domain;

/**
 * 大棚
 */
public class Field {

    private String fieldId;

    private String fieldName;

    private Block block;

    private Crop crop;

    private String useStatus;

    private String fieldPs;

    public Field() {
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Crop getCrop() {
        return crop;
    }

    public void setCrop(Crop crop) {
        this.crop = crop;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getFieldPs() {
        return fieldPs;
    }

    public void setFieldPs(String fieldPs) {
        this.fieldPs = fieldPs;
    }

    @Override
    public String toString() {
        return "Field{" +
                "fieldId='" + fieldId + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", block=" + block +
                ", crop=" + crop +
                ", useStatus='" + useStatus + '\'' +
                ", fieldPs='" + fieldPs + '\'' +
                '}';
    }
}
