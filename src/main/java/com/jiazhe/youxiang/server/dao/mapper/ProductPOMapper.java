package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.ProductPO;
import com.jiazhe.youxiang.server.domain.po.ProductPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductPOMapper {
    int countByExample(ProductPOExample example);

    int deleteByExample(ProductPOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductPO record);

    int insertSelective(ProductPO record);

    List<ProductPO> selectByExample(ProductPOExample example);

    ProductPO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductPO record, @Param("example") ProductPOExample example);

    int updateByExample(@Param("record") ProductPO record, @Param("example") ProductPOExample example);

    int updateByPrimaryKeySelective(ProductPO record);

    int updateByPrimaryKey(ProductPO record);
}