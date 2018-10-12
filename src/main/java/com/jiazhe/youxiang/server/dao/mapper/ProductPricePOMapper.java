package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.ProductPricePO;
import com.jiazhe.youxiang.server.domain.po.ProductPricePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductPricePOMapper {
    int countByExample(ProductPricePOExample example);

    int deleteByExample(ProductPricePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductPricePO record);

    int insertSelective(ProductPricePO record);

    List<ProductPricePO> selectByExample(ProductPricePOExample example);

    ProductPricePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductPricePO record, @Param("example") ProductPricePOExample example);

    int updateByExample(@Param("record") ProductPricePO record, @Param("example") ProductPricePOExample example);

    int updateByPrimaryKeySelective(ProductPricePO record);

    int updateByPrimaryKey(ProductPricePO record);
}