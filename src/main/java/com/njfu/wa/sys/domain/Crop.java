package com.njfu.wa.sys.domain;

/**
 * 作物
 */
public class Crop {

    private String cropId;

    private String cropName;

    private String cropPs;

    public Crop() {}

    public Crop(String cropId) {
        this.cropId = cropId;
    }

    public String getCropId() {
        return cropId;
    }

    public void setCropId(String cropId) {
        this.cropId = cropId;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getCropPs() {
        return cropPs;
    }

    public void setCropPs(String cropPs) {
        this.cropPs = cropPs;
    }

    @Override
    public String toString() {
        return "Crop{" +
                "cropId='" + cropId + '\'' +
                ", cropName='" + cropName + '\'' +
                ", cropPs='" + cropPs + '\'' +
                '}';
    }
}
