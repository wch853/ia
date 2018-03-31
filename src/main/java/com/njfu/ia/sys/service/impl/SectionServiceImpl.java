package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.Section;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.SectionMapper;
import com.njfu.ia.sys.service.SectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

    @Resource
    private SectionMapper sectionMapper;

    /**
     * 查询区块信息
     *
     * @param section
     * @return
     */
    @Override
    public List<Section> getSections(Section section) {
        return sectionMapper.selectSections(section);
    }

    /**
     * 新增区块信息
     *
     * @param section
     */
    @Override
    public void addSection(Section section) {
        int count = sectionMapper.insertSection(section);
        if (count <= 0) {
            throw new BusinessException("新增区块信息失败！");
        }
    }

    /**
     * 修改区块信息
     *
     * @param section
     */
    @Override
    public void modifySection(Section section) {
        int count = sectionMapper.updateSection(section);
        if (count <= 0) {
            throw new BusinessException("修改区块信息失败！");
        }
    }

    /**
     * 删除区块信息
     *
     * @param id
     */
    @Override
    public void removeSection(Integer id) {
        int count = sectionMapper.deleteSection(id);
        if (count <= 0) {
            throw new BusinessException("删除区块信息失败！");
        }
    }

}
