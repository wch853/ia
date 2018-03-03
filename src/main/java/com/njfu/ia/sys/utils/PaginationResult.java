package com.njfu.ia.sys.utils;

import java.util.List;

/**
 * 用于返回分页结果
 */
public class PaginationResult<T> {

    /**
     * 数据总数
     */
    private long total;

    /**
     * 返回数据
     */
    private List<T> rows;

    public PaginationResult() {
    }

    public PaginationResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PaginationResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
