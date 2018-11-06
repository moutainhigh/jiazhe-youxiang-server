package com.jiazhe.youxiang.server.dao.mapper.manual.product;

import com.jiazhe.youxiang.server.domain.po.ProductCategoryPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryPOManualMapper {

    List<ProductCategoryPO> query(@Param("name") String name, @Param("offset") Integer offset, @Param("limit") Integer limit);

    Integer count(@Param("name") String name);
}