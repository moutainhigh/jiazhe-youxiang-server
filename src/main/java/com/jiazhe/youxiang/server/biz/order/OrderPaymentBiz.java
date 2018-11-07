package com.jiazhe.youxiang.server.biz.order;

import com.jiazhe.youxiang.server.dto.order.orderpayment.OrderPaymentDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangerecord.RCExchangeRecordDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangerecord.VoucherExchangeRecordDTO;
import com.jiazhe.youxiang.server.service.order.OrderPaymentService;
import com.jiazhe.youxiang.server.service.rechargecard.RCExchangeRecordService;
import com.jiazhe.youxiang.server.service.voucher.VoucherExchangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@Service("orderPaymentBiz")
public class OrderPaymentBiz {

    @Autowired
    private OrderPaymentService orderPaymentService;
    @Autowired
    private RCExchangeRecordService rcExchangeRecordService;
    @Autowired
    private VoucherExchangeRecordService voucherExchangeRecordService;
    /**
     * 通过充值卡id获取支付记录
     * @param id
     * @return
     */
    public List<OrderPaymentDTO> getByRechargeCardId(Integer id) {
        return orderPaymentService.getByRechargeCardId(id);
    }

    /**
     * 根据代金券id获取支付记录
     * @param id
     * @return
     */
    public List<OrderPaymentDTO> getByVoucherId(Integer id) {
        return orderPaymentService.getByVoucherId(id);
    }

    /**
     * 根据充值卡兑换码id获取支付记录
     * @param id
     * @return
     */
    public List<OrderPaymentDTO> getByRechargeCardCodeId(Integer id) {
        RCExchangeRecordDTO rcExchangeRecordDTO = rcExchangeRecordService.findByCodeId(id);
        return orderPaymentService.getByRechargeCardId(rcExchangeRecordDTO.getRechargeCardId());
    }

    /**
     * 根据代金券兑换码id获取支付记录
     * @param id
     * @return
     */
    public List<OrderPaymentDTO> getByVoucherCodeId(Integer id) {
        VoucherExchangeRecordDTO voucherExchangeRecordDTO = voucherExchangeRecordService.findByCodeId(id);
        return orderPaymentService.getByVoucherId(voucherExchangeRecordDTO.getVoucherId());
    }
}
