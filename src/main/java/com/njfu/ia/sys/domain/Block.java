package com.njfu.ia.sys.domain;

import java.util.List;

/**
 * 地块
 */
public class Block {

    /**
     * 地块编号
     */
    private Integer id;

    /**
     * 地块名称
     */
    private String blockName;

    /**
     * 地块位置
     */
    private String blockLocation;

    /**
     * 包含区块
     */
    private List<Section> sections;

    public Block() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockLocation() {
        return blockLocation;
    }

    public void setBlockLocation(String blockLocation) {
        this.blockLocation = blockLocation;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
