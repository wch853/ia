package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Crop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CropMapper {

    /**
     * 获取作物列表
     *
     * @param crop cropId cropName
     * @return data
     */
    List<Crop> selectCrops(Crop crop);

    /**
     * 新增作物信息
     *
     * @param crop cropId cropName cropPs
     * @return row count
     */
    int insertCrop(Crop crop);

    /**
     * 修改作物信息
     *
     * @param crop cropId cropName cropPs
     * @return row count
     */
    int updateCrop(Crop crop);

    /**
     * 删除作物信息
     *
     * @param crop cropId
     * @return row count
     */
    int deleteCrop(Crop crop);
}
