package com.njfu.wa.sys.domain;

/**
 * 地块
 */
public class Block {

    private String blockId;

    private String blockName;

    private String blockLoc;

    private String blockPs;

    public Block() {}

    public Block(String blockId) {
        this.blockId = blockId;
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
