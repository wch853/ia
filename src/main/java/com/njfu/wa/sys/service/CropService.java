package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.util.Message;

import java.util.List;

public interface CropService {

    /**
     * 获取作物列表
     *
     * @param crop crop
     * @return data
     */
    List<Crop> getCrops(Crop crop);

    /**
     * 新增作物信息
     *
     * @param crop
     * @return
     */
    Message addCrop(Crop crop);

    /**
     * 修改作物信息
     *
     * @param crop crop
     * @return message
     */
    Message modifyCrop(Crop crop);

    /**
     * 删除作物信息
     *
     * @param crop crop
     * @return message
     */
    Message removeCrop(Crop crop);
}
