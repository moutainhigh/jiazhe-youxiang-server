/*
 * Copyright (c) 2017 maoyan.com
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

    private Integer currPage;

    private Integer totalPage;

    private Integer totalCount;

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
        this.currPage = (int) Math.ceil(paging.getOffset()/paging.getLimit()+ 1) ;
        this.totalPage = (int) Math.ceil(paging.getTotal()/paging.getLimit()) ;
        this.totalCount = paging.getTotal();
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

    public Integer getCurrPage() {
        return currPage;
    }

    public void setCurrPage(Integer currPage) {
        this.currPage = currPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}