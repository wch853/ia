package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.utils.Result;
import com.njfu.wa.sys.mapper.CropMapper;
import com.njfu.wa.sys.service.CropService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CropServiceImpl implements CropService {

    @Resource
    private CropMapper cropMapper;

    /**
     * 获取作物列表
     *
     * @param crop cropId cropName
     * @return data
     */
    @Override
    public List<Crop> getCrops(Crop crop) {
        return cropMapper.selectCrops(crop);
    }

    /**
     * 新增作物信息
     *
     * @param crop cropId cropName cropPs
     */
    @Override
    public void addCrop(Crop crop) {
        if ("".equals(crop.getCropPs())) {
            crop.setCropPs(null);
        }
        int res = cropMapper.insertCrop(crop);
        if (res <= 0) {
            throw new BusinessException("新增作物信息失败");
        }
    }

    /**
     * 修改作物信息
     *
     * @param crop cropId cropName cropPs
     */
    @Override
    public void modifyCrop(Crop crop) {
        if ("".equals(crop.getCropPs())) {
            crop.setCropPs(null);
        }
        int res = cropMapper.updateCrop(crop);
        if (res <= 0) {
            throw new BusinessException("修改作物信息失败!");
        }
    }

    /**
     * 删除作物信息
     *
     * @param crop cropId
     */
    @Override
    public void removeCrop(Crop crop) {
        int res = cropMapper.deleteCrop(crop);
        if (res <= 0) {
            throw new BusinessException("删除作物信息失败!");
        }
    }
}
