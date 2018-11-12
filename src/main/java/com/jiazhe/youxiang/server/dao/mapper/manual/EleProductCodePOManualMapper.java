package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.ElectronicProductExchangeCodePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/11/12.
 */
public interface EleProductCodePOManualMapper {


    Integer count(@Param("productId") Integer productId, @Param("batchName") String batchName, @Param("status") Byte status, @Param("code") String code, @Param("keyt") String keyt);

    List<ElectronicProductExchangeCodePO> query(@Param("productId") Integer productId, @Param("batchName") String batchName, @Param("status") Byte status, @Param("code") String code, @Param("keyt") String keyt,@Param("offset") Integer offset, @Param("limit") Integer limit);

    List<ElectronicProductExchangeCodePO> getAllBatch();

    /**
     * 批量插入电子商品码
     * @param poList
     */
    void batchInsert(List<ElectronicProductExchangeCodePO> poList);
}
