package com.jiazhe.youxiang.server.dao.mapper.manual.voucher;

import com.jiazhe.youxiang.server.domain.po.RechargeCardPO;
import com.jiazhe.youxiang.server.domain.po.VoucherPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author tu
 * @description：
 * @date 2018/11/03
 */
public interface VoucherPOManualMapper {

    /**
     * 根据cardIds查找代金券
     * @param cardIds
     * @return
     */
    List<VoucherPO> findByIds(List<Integer> cardIds);

    /**
     * 批量更新代金券信息
     * @param rcPOList
     */
    void batchUpdate(List<VoucherPO> rcPOList);

    /**
     * 根据代金券ids 批量启用、停用充值卡
     * @param map
     */
    void batchChangeStatus(Map<String, Object> map);
}
