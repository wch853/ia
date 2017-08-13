package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Crop;
import com.njfu.wa.sys.mapper.CropMapper;
import com.njfu.wa.sys.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropServiceImpl implements CropService {

    @Autowired
    private CropMapper cropMapper;

    @Override
    public List<Crop> getCrops(Crop crop) {
        return cropMapper.selectCrops(crop);
    }
}
