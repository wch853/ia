package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Crop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CropMapper {

    /**
     * 获取作物列表
     * @param crop crop
     * @return data
     */
    List<Crop> selectCrops(Crop crop);

    /**
     * 新增作物信息
     * @param crop crop
     * @return row count
     */
    int insertCrop(Crop crop);

    /**
     * 修改作物信息
     * @param crop crop
     * @return row count
     */
    int updateCrop(Crop crop);

    /**
     * 删除作物信息
     * @param crop crop
     * @return row count
     */
    int deleteCrop(Crop crop);
}
