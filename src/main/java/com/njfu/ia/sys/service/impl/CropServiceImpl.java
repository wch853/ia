package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.Crop;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.CropMapper;
import com.njfu.ia.sys.service.CropService;
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
     * @param crop
     * @return
     */
    @Override
    public List<Crop> queryCrops(Crop crop) {
        return cropMapper.selectCrops(crop);
    }

    /**
     * 新增作物信息
     *
     * @param crop
     */
    @Override
    public void addCrop(Crop crop) {
        int res = cropMapper.insertCrop(crop);
        if (res <= 0) {
            throw new BusinessException("新增作物信息失败");
        }
    }

    /**
     * 修改作物信息
     *
     * @param crop
     */
    @Override
    public void modifyCrop(Crop crop) {
        int res = cropMapper.updateCrop(crop);
        if (res <= 0) {
            throw new BusinessException("修改作物信息失败!");
        }
    }

    /**
     * 删除作物信息
     *
     * @param crop
     */
    @Override
    public void removeCrop(Crop crop) {
        int res = cropMapper.deleteCrop(crop);
        if (res <= 0) {
            throw new BusinessException("删除作物信息失败!");
        }
    }
}
