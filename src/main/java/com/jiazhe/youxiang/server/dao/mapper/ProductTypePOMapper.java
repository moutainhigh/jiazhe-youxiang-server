package com.jiazhe.youxiang.server.dao.mapper;

import com.jiazhe.youxiang.server.domain.po.ProductTypePO;
import com.jiazhe.youxiang.server.domain.po.ProductTypePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductTypePOMapper {
    int countByExample(ProductTypePOExample example);

    int deleteByExample(ProductTypePOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductTypePO record);

    int insertSelective(ProductTypePO record);

    List<ProductTypePO> selectByExample(ProductTypePOExample example);

    ProductTypePO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductTypePO record, @Param("example") ProductTypePOExample example);

    int updateByExample(@Param("record") ProductTypePO record, @Param("example") ProductTypePOExample example);

    int updateByPrimaryKeySelective(ProductTypePO record);

    int updateByPrimaryKey(ProductTypePO record);
}