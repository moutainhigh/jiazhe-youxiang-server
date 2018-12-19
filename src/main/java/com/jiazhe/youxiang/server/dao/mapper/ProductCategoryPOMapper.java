package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.ProductCategoryPO;
import com.jiazhe.youxiang.server.domain.po.ProductCategoryPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductCategoryPOMapper {
    int countByExample(ProductCategoryPOExample example);

    int deleteByExample(ProductCategoryPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductCategoryPO record);

    int insertSelective(ProductCategoryPO record);

    List<ProductCategoryPO> selectByExample(ProductCategoryPOExample example);

    ProductCategoryPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductCategoryPO record, @Param("example") ProductCategoryPOExample example);

    int updateByExample(@Param("record") ProductCategoryPO record, @Param("example") ProductCategoryPOExample example);

    int updateByPrimaryKeySelective(ProductCategoryPO record);

    int updateByPrimaryKey(ProductCategoryPO record);
}