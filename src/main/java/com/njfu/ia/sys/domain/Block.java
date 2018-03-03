package com.njfu.ia.sys.domain;

import java.util.List;

/**
 * 地块
 */
public class Block {

    private String blockId;

    private String blockName;

    private String blockLoc;

    private String blockPs;

    /**
     * index页获取地块下各大棚
     */
    private List<Field> fields;

    public Block() {
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getBlockLoc() {
        return blockLoc;
    }

    public void setBlockLoc(String blockLoc) {
        this.blockLoc = blockLoc;
    }

    public String getBlockPs() {
        return blockPs;
    }

    public void setBlockPs(String blockPs) {
        this.blockPs = blockPs;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "Block{" +
                "blockId='" + blockId + '\'' +
                ", blockName='" + blockName + '\'' +
                ", blockLoc='" + blockLoc + '\'' +
                ", blockPs='" + blockPs + '\'' +
                '}';
    }
}
