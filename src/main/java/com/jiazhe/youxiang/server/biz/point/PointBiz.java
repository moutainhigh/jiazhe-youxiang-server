package com.jiazhe.youxiang.server.biz.point;

import com.jiazhe.youxiang.base.util.RSAUtil;
import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.common.enums.CodeStatusEnum;
import com.jiazhe.youxiang.server.common.enums.PointCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.PointException;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.point.PurchaseOrderDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointDTO;
import com.jiazhe.youxiang.server.dto.point.point.PointEditDTO;
import com.jiazhe.youxiang.server.service.point.PointExchangeCodeBatchService;
import com.jiazhe.youxiang.server.service.point.PointExchangeRecordService;
import com.jiazhe.youxiang.server.service.point.PointService;
import com.jiazhe.youxiang.server.vo.Paging;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tu
 * @description：
 * @date 2018/12/13
 */
@Service("pointBiz")
public class PointBiz {

    /**
     * 二维码兑换需要延迟的秒数
     */
    private final int QRCDOE_CHARGE_DELAY_SECONDS = 1000 * 3600 * 24;

    @Autowired
    private PointService pointService;
    @Autowired
    private PointExchangeCodeBatchService pointExchangeCodeBatchService;
    @Autowired
    private PointExchangeRecordService pointExchangeRecordService;

    @Autowired
    private CustomerBiz customerBiz;

    public List<PointDTO> getList(String mobile, Integer exchangeType, Byte status, Byte expiry, Paging paging) {
        return pointService.getList(mobile, exchangeType, status, expiry, paging);
    }

    public List<PointDTO> getListByCustomerId(Integer customerId, Byte status, Paging paging) {
        CustomerDTO customerDTO = customerBiz.getById(customerId);
        if (status.equals(Byte.valueOf("0"))) {
            List<PointDTO> pointdtoListAll = pointService.getList(customerDTO.getMobile(), null, null, null, paging);
            return pointdtoListAll;
        }
        if (status.equals(Byte.valueOf("1"))) {
            List<PointDTO> pointdtoListAll = pointService.getList(customerDTO.getMobile(), null, null, null, paging);
            List<PointDTO> pointdtoListUnusable = pointdtoListAll.stream()
                    .filter(bean ->
                            bean.getStatus().equals(Byte.valueOf("0"))
                                    || bean.getExpiryTime().compareTo(new Date()) == -1
                                    || bean.getBalance().compareTo(new BigDecimal(0)) == 0
                    ).collect(Collectors.toList());
            paging.setTotal(pointdtoListUnusable.size());
            return pointdtoListUnusable;
        }
        if (status.equals(Byte.valueOf("2"))) {
            List<PointDTO> temp = pointService.getList(customerDTO.getMobile(), null, Byte.valueOf("1"), Byte.valueOf("0"), paging);
            List<PointDTO> pointdtoListUsable = temp.stream()
                    .filter(bean -> bean.getBalance().compareTo(new BigDecimal(0)) == 1)
                    .collect(Collectors.toList());
            paging.setTotal(pointdtoListUsable.size());
            return pointdtoListUsable;
        }
        return null;
    }

    public List<PointDTO> getListByGoodsAttr(Integer customerId, Integer productId, String cityCode, Paging paging) {
        CustomerDTO customerDTO = customerBiz.getById(customerId);
        List<PointDTO> temp = pointService.getList(customerDTO.getMobile(), null, Byte.valueOf("1"), Byte.valueOf("0"), paging);
        List<PointDTO> pointDtoListUsable = temp.stream()
                .filter(bean ->
                        bean.getBalance().compareTo(new BigDecimal(0)) == 1
                                && bean.getCityCodes().contains(cityCode))
                .filter(bean -> productsHasProduct(bean.getProductIds(), productId))
                .collect(Collectors.toList());
        paging.setTotal(pointDtoListUsable.size());
        return pointDtoListUsable;
    }

    public boolean productsHasProduct(String productIds, Integer productId) {
        List<String> result = Arrays.asList(productIds.split(","));
        return result.contains(productId.toString());
    }

    public void startUsing(Integer id) {
        pointService.changeStatus(id, CodeStatusEnum.START_USING.getId().byteValue());
    }

    public void stopUsing(Integer id) {
        pointService.changeStatus(id, CodeStatusEnum.STOP_USING.getId().byteValue());
    }

    public void directCharge(Integer id, Integer batchId, BigDecimal faceValue) {
        pointService.directCharge(id, batchId, faceValue);
    }

    public PointDTO getById(Integer id) {
        return pointService.getById(id);
    }

    public void editSave(PointEditDTO dto) {
        pointService.editSave(dto);
    }

    public BigDecimal totalValidBalance(Integer customerId) {
        return pointService.totalValidBalance(customerId);
    }

    /**
     * 通过二维码给当前登录用户充积分
     *
     * @param qrCode
     */
    public void chargeByQRCode(String qrCode) {
        //将二维码利用公钥解密
        String purchaseOrderStr = "";
        try {
            purchaseOrderStr = RSAUtil.publicDecrypt(qrCode);
        } catch (Exception e) {
            throw new PointException(PointCodeEnum.QRCODE_DECRYPT_ERROR);
        }
        //检查是否充过值
        if (pointExchangeRecordService.hasCharged(purchaseOrderStr)) {
            throw new PointException(PointCodeEnum.QRCODE_HAS_CHARGED);
        }
        //解析签购单信息
        PurchaseOrderDTO purchaseOrderDTO = createPurchaseOrderDTO(purchaseOrderStr);
        //检查时间是否满足自交易日期后24个小时
        checkDateTime(purchaseOrderDTO);
        if (SecurityUtils.getSubject().getPrincipal() == null || !(SecurityUtils.getSubject().getPrincipal() instanceof CustomerDTO)) {
            throw new PointException(PointCodeEnum.CUSTOMER_IS_NOT_LOGIN);
        }
        CustomerDTO customerDTO = (CustomerDTO) SecurityUtils.getSubject().getPrincipal();
        Integer batchId = pointExchangeCodeBatchService.getBatchIdByMerchantNo(purchaseOrderDTO.getMerchantNo());
        BigDecimal faceValue = pointExchangeCodeBatchService.getFaceValue(batchId, purchaseOrderDTO.getBonus());
        //获得当前用户，给当前用户充值
        pointService.chargeByQRCode(purchaseOrderStr,customerDTO, batchId, faceValue);
    }

    /**
     * 根据字符串创建签购单对象
     *
     * @param purchaseOrderStr
     * @return
     */
    private PurchaseOrderDTO createPurchaseOrderDTO(String purchaseOrderStr) {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO();
        String[] stringArray = purchaseOrderStr.split(",");
        purchaseOrderDTO.setMerchantNo(stringArray[0]);
        purchaseOrderDTO.setPosNo(stringArray[1]);
        purchaseOrderDTO.setReferNo(stringArray[2]);
        purchaseOrderDTO.setCardNo(stringArray[3]);
        purchaseOrderDTO.setBonus(stringArray[4]);
        purchaseOrderDTO.setDate(stringArray[5]);
        purchaseOrderDTO.setTime(stringArray[6]);
        return purchaseOrderDTO;
    }

    /**
     * 检查兑换时间是否符合规则
     *
     * @param purchaseOrderDTO
     */
    private void checkDateTime(PurchaseOrderDTO purchaseOrderDTO) {
        String dateTimeStr = purchaseOrderDTO.getDate() + purchaseOrderDTO.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            Long chargeTime = sdf.parse(dateTimeStr).getTime();
            //判断此时是否是交易时间24小时后
            if (System.currentTimeMillis() - chargeTime.longValue() <= QRCDOE_CHARGE_DELAY_SECONDS) {
                throw new PointException(PointCodeEnum.QRCODE_CHARGE_TIME_NOT_YET);
            }
        } catch (ParseException e) {
            throw new PointException(PointCodeEnum.QRCODE_CHARGE_TIME_ERROR);
        }
    }
}
