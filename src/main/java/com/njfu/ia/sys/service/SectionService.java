package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Section;

import java.util.List;

public interface SectionService {

    /**
     * 查询区块信息
     *
     * @param section
     * @return
     */
    List<Section> querySections(Section section);

    /**
     * 新增区块信息
     *
     * @param section
     */
    void addSection(Section section);

    /**
     * 修改区块信息
     *
     * @param section
     */
    void modifySection(Section section);

    /**
     * 删除区块信息
     *
     * @param id
     */
    void removeSection(Integer id);
}
