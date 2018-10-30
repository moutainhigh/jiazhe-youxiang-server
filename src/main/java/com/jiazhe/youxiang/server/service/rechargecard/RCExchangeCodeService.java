package com.jiazhe.youxiang.server.service.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/22.
 */
public interface RCExchangeCodeService {
    /**
     * 批量保存兑换码信息
     * @param rechargeCardExchangeCodePOList
     * @return
     */
    int batchInsert(List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList);

    /**
     * 修改兑换码启用停用状态
     * @param id
     * @param status
     * @return
     */
    int changeCodeStatus(Integer id, Byte status);

    /**
     * 修改充值卡兑换码过期时间
     * @param id
     * @param expiryTime
     * @return
     */
    int changeExpiryTime(Integer id, Date expiryTime);


    /**
     * 通过充值卡兑换码密钥查找
     * @param keyt
     * @return
     */
    RechargeCardExchangeCodePO findByKeyt(String keyt);

    /**
     * 用兑换码进行充值【type=0表示后台兑换码充值的，1表示客户自行兑换码充值的】
     * @param mobile
     * @param keyt
     * @return
     */
    int codeCharge(Integer type, String mobile, String keyt);

    /**
     * 根据批次id，获取批次下码的数量
     * @param id
     * @return
     */
    List<RCExchangeCodeDTO> getByBatchId(Integer id);

}
