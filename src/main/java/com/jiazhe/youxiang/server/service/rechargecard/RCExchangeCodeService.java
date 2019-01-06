package com.jiazhe.youxiang.server.service.rechargecard;

import com.jiazhe.youxiang.server.domain.po.RechargeCardExchangeCodePO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeEditDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecodebatch.RCExchangeCodeBatchSaveDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.text.ParseException;
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
    void batchInsert(List<RechargeCardExchangeCodePO> rechargeCardExchangeCodePOList);

    /**
     * 修改兑换码启用停用状态
     * @param id
     * @param status
     * @return
     */
    void changeCodeStatus(Integer id, Byte status);

    /**
     * 修改充值卡兑换码过期时间
     * @param id
     * @param expiryTime
     * @return
     */
    void changeExpiryTime(Integer id, Date expiryTime);


    /**
     * 通过充值卡兑换码密钥查找
     * @param keyt
     * @return
     */
    RechargeCardExchangeCodePO findByKeyt(String keyt);

    /**
     * 用兑换码进行充值【type=0表示后台兑换码充值的，1表示客户自行兑换码充值的】
     * @param id
     * @param keyt
     * @return
     */
    void codeCharge(Integer type, Integer id, String keyt) ;

    /**
     * 根据批次id，获取批次下码的数量
     * @param id
     * @return
     */
    List<RCExchangeCodeDTO> getByBatchId(Integer id);

    /**
     * 根据批次信息，修改兑换码信息
     * @param batchSaveDTO
     * @return
     */
    void updateWithBatch(RCExchangeCodeBatchSaveDTO batchSaveDTO);

    /**
     * 根据批次id，修改兑换码的启用、停用状态
     * @param batchId
     * @param status
     * @return
     */
    void batchChangeStatus(Integer batchId, Byte status);

    /**
     * 根据批次id，和相关条件查询兑换码
     * @param batchId
     * @param code
     * @param keyt
     * @param status
     * @param used
     * @param paging
     * @return
     */
    List<RCExchangeCodeDTO> getList(Integer batchId, String code, String keyt, Byte status, Byte used, Paging paging);

    /**
     * 根据id获取兑换码信息
     * @param id
     * @return
     */
    RCExchangeCodeDTO getById(Integer id);

    void editSave(RCExchangeCodeEditDTO dto);
}
