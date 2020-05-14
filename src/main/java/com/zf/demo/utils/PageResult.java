package com.zf.demo.utils;

/**
 * Create by zengfei
 * Date 2020/4/22 11:26
 */
public class PageResult {
    private long totalPageCount=0L;
    private long totalRowCount=0L;

    public long getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public long getTotalRowCount() {
        return totalRowCount;
    }

    public void setTotalRowCount(long totalRowCount) {
        this.totalRowCount = totalRowCount;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "totalPageCount=" + totalPageCount +
                ", totalRowCount=" + totalRowCount +
                '}';
    }
}
