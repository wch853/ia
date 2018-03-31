package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Crop;

import java.util.List;

public interface CropService {

    /**
     * 获取作物列表
     *
     * @param crop
     * @return
     */
    List<Crop> getCrops(Crop crop);

    /**
     * 新增作物信息
     *
     * @param crop
     */
    void addCrop(Crop crop);

    /**
     * 修改作物信息
     *
     * @param crop
     */
    void modifyCrop(Crop crop);

    /**
     * 删除作物信息
     *
     * @param crop
     */
    void removeCrop(Crop crop);
}
