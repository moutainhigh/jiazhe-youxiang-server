/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo;

/**
 * 分页对象
 *
 * @author niexiao
 * @created 2018/10/16
 */
public class Paging {
    private Integer offset = 0;

    private Integer limit = 20;

    private Integer total = 0;

    private Boolean hasMore = false;

    public Paging() {
        // 默认构造函数也给出来
    }

    public Paging(Integer offset, Integer limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public Paging(Integer offset, Integer limit, Integer total, Boolean hasMore) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.hasMore = hasMore;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
        this.hasMore = this.getLimit() + this.getOffset() < total;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}