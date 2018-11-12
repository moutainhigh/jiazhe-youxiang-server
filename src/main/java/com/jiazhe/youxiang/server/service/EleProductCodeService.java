package com.jiazhe.youxiang.server.service;

import com.jiazhe.youxiang.server.domain.po.ElectronicProductExchangeCodePO;
import com.jiazhe.youxiang.server.dto.eleproductexcode.EleProductCodeDTO;
import com.jiazhe.youxiang.server.vo.Paging;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/11/12.
 */
public interface EleProductCodeService {

    /**
     * 描述信息
     * @param productId
     * @param batchName
     * @param status
     * @param code
     * @param keyt
     * @param paging
     * @return
     */
    List<EleProductCodeDTO> getList(Integer productId, String batchName, Byte status, String code, String keyt, Paging paging);

    /**
     * 获取所有批次
     * @return
     */
    List<EleProductCodeDTO> getAllBatch();

    /**
     * 批量插入电子码
     * @param poList
     */
    void batchInsert(List<ElectronicProductExchangeCodePO> poList);
}
