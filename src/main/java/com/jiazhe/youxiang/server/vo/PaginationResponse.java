/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo;

import java.util.List;

/**
 * 分页结果的返回值
 *
 * @author niexiao
 * @created 2018/10/16
 */
public class PaginationResponse<T> extends BaseObject {

    private List<T> data;

    private Paging paging;

    public PaginationResponse() {
    }

    public PaginationResponse(List<T> data, Integer offset, Integer limit,
                              Integer total, Boolean hasMore) {
        this.data = data;
        paging = new Paging(offset, limit, total, hasMore);
    }

    public PaginationResponse(List<T> data, Paging paging) {
        this.data = data;
        this.paging = paging;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}