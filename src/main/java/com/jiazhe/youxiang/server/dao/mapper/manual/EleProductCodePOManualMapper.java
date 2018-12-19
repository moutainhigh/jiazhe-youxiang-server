package com.jiazhe.youxiang.server.dao.mapper.manual;

import com.jiazhe.youxiang.server.domain.po.ElectronicProductExchangeCodePO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取n个可用电子码
     * @param productId
     * @param count
     * @return
     */
    List<ElectronicProductExchangeCodePO> selectTopN(@Param("productId") Integer productId, @Param("count")Integer count);

    /**
     *  批量发放商品电子码
     * @param map
     */
    void batchSendOut(Map<String, Object> map);

    /**
     * 根据批次名，批量修改电子码过期时间
     * @param batchName
     * @param expiryTime
     */
    void batchChangeExpiryTime(@Param("batchName") String batchName, @Param("expiryTime") Date expiryTime);
}
