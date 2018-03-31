package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Crop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CropMapper {

    /**
     * 获取作物列表
     *
     * @param crop
     * @return
     */
    List<Crop> selectCrops(Crop crop);

    /**
     * 新增作物信息
     *
     * @param crop
     * @return
     */
    int insertCrop(Crop crop);

    /**
     * 修改作物信息
     *
     * @param crop
     * @return
     */
    int updateCrop(Crop crop);

    /**
     * 删除作物信息
     *
     * @param crop
     * @return
     */
    int deleteCrop(Crop crop);
}
