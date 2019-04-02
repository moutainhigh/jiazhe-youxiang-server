package com.jiazhe.youxiang.server.biz.voucher;

import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.common.constant.CommonConstant;
import com.jiazhe.youxiang.server.common.enums.VoucherCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.VoucherException;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherDTO;
import com.jiazhe.youxiang.server.dto.voucher.voucher.VoucherEditDTO;
import com.jiazhe.youxiang.server.service.voucher.VoucherService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
@Service("voucherBiz")
public class VoucherBiz {

    @Autowired
    private VoucherService voucherService;
    @Autowired
    private CustomerBiz customerBiz;

    public Integer totalValidVoucher(Integer customerId) {
        return voucherService.totalValidVoucher(customerId);
    }

    public void startUsing(Integer id) {
        voucherService.changeStatus(id, CommonConstant.CODE_START_USING);
    }

    public void stopUsing(Integer id) {
        voucherService.changeStatus(id, CommonConstant.CODE_STOP_USING);
    }

    public List<VoucherDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging) {
        return voucherService.getList(mobile, exchangeType, status, expiry, paging);
    }

    public VoucherDTO getById(Integer id) {
        return voucherService.getById(id);
    }

    public void editSave(VoucherEditDTO dto) {
        voucherService.editSave(dto);
    }

    /**
     * app端根据客户id，和是否可用查询充值卡列表
     *
     * @param customerId
     * @param status     0为所有，1为不可用【包括过期、停用和已使用】，2为可用【包括未生效】
     * @param paging
     * @return
     */
    public List<VoucherDTO> getListByCustomerId(Integer customerId, Byte status, Paging paging) {
        CustomerDTO customerDTO = customerBiz.getById(customerId);
        if(null == customerDTO){
            throw new VoucherException(VoucherCodeEnum.CUSTOMER_NOT_EXIST);
        }
        if (status.equals(Byte.valueOf("0"))) {
            List<VoucherDTO> voucherDTOListAll = voucherService.getList(customerDTO.getMobile(), null, null, null, paging);
            return voucherDTOListAll;
        }
        if (status.equals(Byte.valueOf("1"))) {
            List<VoucherDTO> voucherDTOListAll = voucherService.getList(customerDTO.getMobile(), null, null, null, paging);
            List<VoucherDTO> voucherDTOListUsable = voucherDTOListAll.stream()
                    .filter(bean ->
                            bean.getStatus().equals(Byte.valueOf("0"))
                                    || bean.getExpiryTime().getTime() < System.currentTimeMillis()
                                    || bean.getUsed().equals(Byte.valueOf("1"))
                    ).collect(Collectors.toList());
            paging.setTotal(voucherDTOListUsable.size());
            return voucherDTOListUsable;
        }
        if (status.equals(Byte.valueOf("2"))) {
            List<VoucherDTO> temp = voucherService.getList(customerDTO.getMobile(), null, Byte.valueOf("1"), Byte.valueOf("0"), paging);
            List<VoucherDTO> voucherDTOListUsable = temp.stream()
                    .filter(bean -> bean.getUsed().equals(Byte.valueOf("0")))
                    .collect(Collectors.toList());
            paging.setTotal(voucherDTOListUsable.size());
            return voucherDTOListUsable;
        }
        return null;
    }

    public List<VoucherDTO> getListByGoodsAttr(Integer customerId, Integer productId, String cityCode, Paging paging) {
        CustomerDTO customerDTO = customerBiz.getById(customerId);
        if(null == customerDTO){
            throw new VoucherException(VoucherCodeEnum.CUSTOMER_NOT_EXIST);
        }
        List<VoucherDTO> temp = voucherService.getList(customerDTO.getMobile(), null, Byte.valueOf("1"), Byte.valueOf("0"), paging);
        List<VoucherDTO> voucherDTOListUsable = temp.stream()
                .filter(bean ->
                        bean.getUsed().equals(Byte.valueOf("0"))
                                && bean.getCityCodes().contains(cityCode))
                .filter(bean -> productsHasProduct(bean.getProductIds(), productId))
                .filter(bean -> bean.getEffectiveTime().getTime() < System.currentTimeMillis())
                .collect(Collectors.toList());
        paging.setTotal(voucherDTOListUsable.size());
        return voucherDTOListUsable;
    }

    public boolean productsHasProduct(String productIds, Integer productId) {
        List<String> result = Arrays.asList(productIds.split(","));
        return result.contains(productId.toString());
    }
}
