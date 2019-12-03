package com.jiazhe.youxiang.base.util;

import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
import com.jiazhe.youxiang.server.dto.order.orderinfo.OrderInfoDTO;
import com.jiazhe.youxiang.server.dto.partnerorder.PartnerOrderInfoDTO;
import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/1
 */
public class ExportExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExportExcelUtils.class);

    private static final int maxColumnWidth = 30;

    public static void exportRechargeCardCode(HttpServletResponse response, List<RCExchangeCodeDTO> rcExchangeCodeDTOList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        String fileName = System.currentTimeMillis() + ".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"批次名称", "卡号", "秘钥", "面额", "启用/停用", "使用状态"};
        //headers表示excel表中第一行的表头
        XSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (RCExchangeCodeDTO dto : rcExchangeCodeDTOList) {
            XSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(dto.getBatchName());
            row1.createCell(1).setCellValue(dto.getCode());
            row1.createCell(2).setCellValue(dto.getKeyt());
            XSSFCell cell_3 = row1.createCell(3);
            cell_3.setCellType(CellType.NUMERIC);
            cell_3.setCellValue(dto.getFaceValue().doubleValue());
            row1.createCell(4).setCellValue(dto.getStatus().equals(Byte.valueOf("1")) ? "启用" : "停用");
            row1.createCell(5).setCellValue(dto.getUsed().equals(Byte.valueOf("1")) ? "已使用" : "未使用");
            rowNum++;
        }
        resetColumnWidth(sheet, headers.length, true);
        export(response, fileName, workbook);
    }

    public static void exportVoucherCode(HttpServletResponse response, List<VoucherExchangeCodeDTO> voucherExchangeCodeDTOList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        String fileName = System.currentTimeMillis() + ".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"批次名称", "卡号", "秘钥", "兑换商品数量", "启用/停用", "使用状态"};
        //headers表示excel表中第一行的表头
        XSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (VoucherExchangeCodeDTO dto : voucherExchangeCodeDTOList) {
            XSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(dto.getBatchName());
            row1.createCell(1).setCellValue(dto.getCode());
            row1.createCell(2).setCellValue(dto.getKeyt());
            XSSFCell cell_3 = row1.createCell(3);
            cell_3.setCellType(CellType.NUMERIC);
            cell_3.setCellValue(dto.getCount());
            row1.createCell(4).setCellValue(dto.getStatus().equals(Byte.valueOf("1")) ? "启用" : "停用");
            row1.createCell(5).setCellValue(dto.getUsed().equals(Byte.valueOf("1")) ? "已使用" : "未使用");
            rowNum++;
        }
        resetColumnWidth(sheet, headers.length, true);
        export(response, fileName, workbook);
        ;
    }

    public static void exportPointCode(HttpServletResponse response, List<PointExchangeCodeDTO> pointExchangeCodeDTOList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        String fileName = System.currentTimeMillis() + ".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"批次名称", "卡号", "秘钥", "面额", "启用/停用", "使用状态"};
        //headers表示excel表中第一行的表头
        XSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (PointExchangeCodeDTO dto : pointExchangeCodeDTOList) {
            XSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(dto.getBatchName());
            row1.createCell(1).setCellValue(dto.getCode());
            row1.createCell(2).setCellValue(dto.getKeyt());
            XSSFCell cell_3 = row1.createCell(3);
            cell_3.setCellType(CellType.NUMERIC);
            cell_3.setCellValue(dto.getFaceValue().doubleValue());
            row1.createCell(4).setCellValue(dto.getStatus().equals(Byte.valueOf("1")) ? "启用" : "停用");
            row1.createCell(5).setCellValue(dto.getUsed().equals(Byte.valueOf("1")) ? "已使用" : "未使用");
            rowNum++;
        }
        resetColumnWidth(sheet, headers.length, true);
        export(response, fileName, workbook);
    }

    public static void exportPartnerOrder(HttpServletResponse response, List<PartnerOrderInfoDTO> partnerOrderInfoDTOList) {
        String[] orderStatus = {"","待派单","待服务","已完成","已取消"};
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        String fileName = System.currentTimeMillis() + ".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"客户姓名", "客户手机号", "城市", "地址", "兑换密钥", "兑换时间", "预约时间", "订单来源", "服务人员姓名", "服务人员电话", "服务项目", "服务商", "预付", "再支付","订单状态"};
        //headers表示excel表中第一行的表头
        XSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (PartnerOrderInfoDTO dto : partnerOrderInfoDTOList) {
            XSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(dto.getCustomerName());
            row1.createCell(1).setCellValue(dto.getCustomerMobile());
            row1.createCell(2).setCellValue(dto.getCustomerCityName());
            row1.createCell(3).setCellValue(dto.getCustomerAddress());
            row1.createCell(4).setCellValue(dto.getKeyt());
            row1.createCell(5).setCellValue(DateUtil.dateToStr(dto.getOrderTime()));
            row1.createCell(6).setCellValue(DateUtil.dateToStr(dto.getServiceTime()));
            row1.createCell(7).setCellValue(dto.getOrderSource());
            row1.createCell(8).setCellValue(dto.getWorkerName());
            row1.createCell(9).setCellValue(dto.getWorkerMobile());
            row1.createCell(10).setCellValue(dto.getServiceItemDTO().getName());
            row1.createCell(11).setCellValue(dto.getPartnerDTO().getName());
            XSSFCell cell_11 = row1.createCell(12);
            cell_11.setCellType(CellType.NUMERIC);
            cell_11.setCellValue(dto.getPrePay().doubleValue());
            XSSFCell cell_12 = row1.createCell(13);
            cell_12.setCellType(CellType.NUMERIC);
            cell_12.setCellValue(dto.getAppendPay().doubleValue());
            row1.createCell(14).setCellValue(orderStatus[dto.getStatus().intValue()]);
            rowNum++;
        }
        resetColumnWidth(sheet, headers.length, false);
        export(response, fileName, workbook);
    }

    public static void exportOrder(HttpServletResponse response, List<OrderInfoDTO> orderInfoDTOList) {
        String[] orderStatus = {"","代付款","待派单","待服务","已完成","取消待审核","取消未通过","已取消"};
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        String fileName = System.currentTimeMillis() + ".xlsx";
        int rowNum = 1;
        String[] headers = {"订单号","服务商品","扣分商品","下单价格","数量","下单时间", "服务时间", "订单成本", "城市", "地址","订单状态"};
        XSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (OrderInfoDTO dto : orderInfoDTOList) {
            XSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(dto.getOrderCode());
            row1.createCell(1).setCellValue(dto.getServiceProductDTO().getName());
            row1.createCell(2).setCellValue(dto.getProductDTO().getName());
            XSSFCell cell_3 = row1.createCell(3);
            cell_3.setCellType(CellType.NUMERIC);
            cell_3.setCellValue(dto.getProductPrice().doubleValue());
            XSSFCell cell_4 = row1.createCell(4);
            cell_4.setCellType(CellType.NUMERIC);
            cell_4.setCellValue(dto.getCount().intValue());
            row1.createCell(5).setCellValue(DateUtil.dateToStr(dto.getOrderTime()));
            row1.createCell(6).setCellValue(DateUtil.dateToStr(dto.getRealServiceTime()));
            XSSFCell cell_7 = row1.createCell(7);
            cell_7.setCellType(CellType.NUMERIC);
            cell_7.setCellValue(dto.getCost().doubleValue());
            row1.createCell(8).setCellValue(dto.getCustomerCityName());
            row1.createCell(9).setCellValue(dto.getCustomerAddress());
            row1.createCell(10).setCellValue(orderStatus[dto.getStatus().intValue()]);
            rowNum++;
        }
        resetColumnWidth(sheet, headers.length, false);
        export(response, fileName, workbook);
    }

    public static void exportChargeReceipt(HttpServletResponse response, List<ChargeReceiptDTO> dtoList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        String fileName = System.currentTimeMillis() + ".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"序号", "商户名称", "POS名称", "客户姓名", "交易日期", "卡号后4位", "分值", "金额", "是否已经兑换服务", "是否已经对账", "备注"};
        //headers表示excel表中第一行的表头
        XSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        for (ChargeReceiptDTO dto : dtoList) {
            XSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(rowNum);
            row1.createCell(1).setCellValue("北京悠享互联信息技术有限公司");
            row1.createCell(2).setCellValue(dto.getPosCode());
            row1.createCell(3).setCellValue(dto.getCustomerName());
            row1.createCell(4).setCellValue(DateUtil.yyyyMMDD(dto.getTradeTime()));
            row1.createCell(5).setCellValue(dto.getCardNo());
            XSSFCell cell_6 = row1.createCell(6);
            cell_6.setCellType(CellType.NUMERIC);
            cell_6.setCellValue(dto.getExchangePoint().doubleValue());
            row1.createCell(7).setCellValue("");
            row1.createCell(8).setCellValue("是");
            row1.createCell(9).setCellValue("是");
            row1.createCell(10).setCellValue("");
            rowNum++;
        }
        resetColumnWidth(sheet, headers.length, false);
        export(response, fileName, workbook);
    }

    /**
     * 重新调整单元格的列宽
     *
     * @param sheet      表格
     * @param maxColumn  最大列数
     * @param sameLength 除表头外，数据长度是否一致，一致只需要遍历2行，否则遍历所有
     * @throws UnsupportedEncodingException
     */
    public static void resetColumnWidth(XSSFSheet sheet, int maxColumn, boolean sameLength) {
        for (int i = 0; i <= maxColumn; i++) {
            sheet.autoSizeColumn(i);
        }
        int maxRow = sheet.getLastRowNum();
        if (sameLength) {
            maxRow = 1;
        }
        for (int columnNum = 0; columnNum <= maxColumn; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum <= maxRow; rowNum++) {
                XSSFRow currentRow;
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(columnNum) != null) {
                    Cell currentCell = currentRow.getCell(columnNum);
                    int length = 0;
                    try {
                        length = currentCell.toString().getBytes("GBK").length;
                    } catch (UnsupportedEncodingException e) {
                        logger.error("导出excel时获取列宽出现不支持的编码格式，错误信息：" + e.getMessage());
                    }
                    if (columnWidth < length + 1) {
                        columnWidth = length > maxColumnWidth ? maxColumnWidth : length + 1;
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }

    public static void export(HttpServletResponse response, String fileName, XSSFWorkbook workbook) {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            logger.error("导出excel时出现IO异常，错误信息：" + e.getMessage());
        }
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            logger.error("导出excel时出现IO异常，错误信息：" + e.getMessage());
        }
    }
}
