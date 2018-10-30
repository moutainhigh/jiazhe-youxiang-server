package com.jiazhe.youxiang.server.dao.mapper.manual.product;

import com.jiazhe.youxiang.server.domain.po.ProductPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductPOManualMapper {


    Integer count(@Param("productCategoryId") Integer productCategoryId,
                  @Param("name") String name,
                  @Param("productType") Integer productType,
                  @Param("status") Integer status,
                  @Param("productIds") List<Integer> productIds);

    List<ProductPO> query(@Param("productCategoryId") Integer productCategoryId,
                          @Param("name") String name,
                          @Param("productType") Integer productType,
                          @Param("status") Integer status,
                          @Param("productIds") List<Integer> productIds,
                          @Param("offset") Integer offset,
                          @Param("limit") Integer limit);


}