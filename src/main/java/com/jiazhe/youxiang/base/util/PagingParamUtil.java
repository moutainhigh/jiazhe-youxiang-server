package com.jiazhe.youxiang.base.util;

import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.req.OffsetLimitReq;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;

/**
 * @author TU
 * @description
 * @date 2018/11/1.
 */
public class PagingParamUtil {

    public static Paging pagingParamSwitch(PageSizeNumReq req){
        Paging paging = new Paging();
        paging.setOffset((req.getPageNum()-1)*req.getPageSize());
        paging.setLimit(req.getPageSize());
        return paging;
    }

    public static Paging pagingParamSwitch(OffsetLimitReq req){
        Paging paging = new Paging();
        paging.setOffset(req.getOffset());
        paging.setLimit(req.getLimit());
        return paging;
    }
}
