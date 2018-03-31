package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Section;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SectionMapper {

    /**
     * 查询区块信息
     *
     * @param section
     * @return
     */
    List<Section> selectSections(Section section);

    /**
     * 新增区块信息
     *
     * @param section
     * @return
     */
    int insertSection(Section section);

    /**
     * 修改区块信息
     *
     * @param section
     * @return
     */
    int updateSection(Section section);

    /**
     * 删除区块信息
     *
     * @param id
     * @return
     */
    int deleteSection(@Param("id") Integer id);
}
