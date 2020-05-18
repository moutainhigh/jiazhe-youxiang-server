package com.jiazhe.youxiang.server.dao.mapper.manual.point;

import com.jiazhe.youxiang.base.util.boccc.BOCCCCouponEntity;
import com.jiazhe.youxiang.base.util.boccc.BOCCCCouponUsedEntity;
import com.jiazhe.youxiang.server.domain.po.PointExchangeCodePO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
public interface PointExchangeCodePOManualMapper {

    /**
     * 批量更新
     *
     * @param poList
     */
    void batchUpdate(List<PointExchangeCodePO> poList);

    /**
     * 批量插入积分卡兑换码，此时卡号和密码为空
     *
     * @param pointExchangeCodePOList
     */
    void batchInsert(List<PointExchangeCodePO> pointExchangeCodePOList);

    /**
     * 批量启用、停用
     *
     * @param batchId
     * @param status
     */
    void batchChangeStatus(@Param("batchId") Integer batchId, @Param("status") Byte status);

    /**
     * 分页列表总数量
     *
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
     *
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
     *
     * @param keyt
     * @return
     */
    PointExchangeCodePO findByKeyt(@Param("keyt") String keyt);

    /**
     * 获取已有积分卡的最大id
     *
     * @return
     */
    Integer getMaxId();

    /**
     * 批量更新代金券的code和keyt
     *
     * @param poList
     */
    void batchUpdateCodeAndKeyt(List<PointExchangeCodePO> poList);

    /**
     * 根据卡号codes查询所有兑换码
     *
     * @param codes
     * @return
     */
    List<PointExchangeCodePO> findByCodes(List<String> codes);

    /**
     * 批量激活兑换码，更新兑换码的过期时间
     *
     * @param pointExchangeCodeDtoList
     */
    void batchActive(List<PointExchangeCodeDTO> pointExchangeCodeDtoList);


    /**
     * 通过code查找积分兑换码
     *
     * @param code
     * @return
     */
    PointExchangeCodePO findByCode(@Param("code") String code);

    /**
     * 通过中行储蓄卡礼品编号查询一个兑换码
     *
     * @param giftNo
     * @param expiryDate
     * @return
     */
    PointExchangeCodePO queryStock(@Param("giftNo") String giftNo, @Param("expiryDate") Date expiryDate);

    /**
     * 中行信用卡专用：获取批次下兑换码信息
     * @param batchIds
     * @return
     */
    List<BOCCCCouponEntity> getBOCCCCoupon(List<Integer> batchIds);

    /**
     *中行信用卡专用：获取某个时间段使用的兑换码
     * @param beginTime
     * @param endTime
     * @return
     */
    List<BOCCCCouponUsedEntity> getBOCCCUsed(@Param("beginTime")Date beginTime, @Param("endTime")Date endTime);

    /**
     * 根据id查找记录，悲观锁
     * @param id
     */
    PointExchangeCodePO findByIdForUpdate(Integer id);
}
