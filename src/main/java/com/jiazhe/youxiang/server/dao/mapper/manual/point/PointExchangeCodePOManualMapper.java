package com.jiazhe.youxiang.server.dao.mapper.manual.point;

import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public interface PointExchangeCodePOManualMapper {

    /**
     * 批量更新
     * @param poList
     */
    void batchUpdate(List<PointExchangeCodePO> poList);

    /**
     * 批量插入
     * @param pointExchangeCodePOList
     */
    void batchInsert(List<PointExchangeCodePO> pointExchangeCodePOList);

    /**
     * 批量启用、停用
     * @param batchId
     * @param status
     */
    void batchChangeStatus(@Param("batchId") Integer batchId, @Param("status") Byte status);

    /**
     * 分页列表总数量
     * @param batchId
     * @param code
     * @param keyt
     * @param status
     * @param used
     * @return
     */
    Integer count(@Param("batchId") Integer batchId, @Param("code") String code, @Param("keyt") String keyt, @Param("status") Byte status, @Param("used") Byte used);

    /**
     * 分页列表
     * @param batchId
     * @param code
     * @param keyt
     * @param status
     * @param used
     * @param offset
     * @param limit
     * @return
     */
    List<PointExchangeCodePO> query(@Param("batchId") Integer batchId, @Param("code") String code, @Param("keyt") String keyt, @Param("status") Byte status, @Param("used") Byte used, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 通过keyt查找积分兑换码
     * @param keyt
     * @return
     */
    PointExchangeCodePO findByKeyt(@Param("keyt") String keyt);

    /**
     * 获取已有积分卡的最大id
     * @return
     */
    Integer getMaxId();

    /**
     * 批量更新代金券的code和keyt
     * @param poList
     */
    void batchUpdateCodeAndKeyt(List<PointExchangeCodePO> poList);

    /**
     * 根据卡号codes查询所有兑换码
     * @param codes
     * @return
     */
    List<PointExchangeCodePO> findByCodes(List<String> codes);

    /**
     * 批量激活兑换码，更新兑换码的过期时间
     * @param pointExchangeCodeDtoList
     */
    void batchActive(List<PointExchangeCodeDTO> pointExchangeCodeDtoList);
}
