package com.jiazhe.youxiang.base.util;

import com.jiazhe.youxiang.server.dto.chargereceipt.ChargeReceiptDTO;
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

    public static void exportRechargeCardCode(HttpServletResponse response, List<RCExchangeCodeDTO> rcExchangeCodeDTOList) throws IOException {
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
            row1.createCell(3).setCellValue(dto.getFaceValue().toString());
            row1.createCell(4).setCellValue(dto.getStatus().equals(Byte.valueOf("1")) ? "启用" : "停用");
            row1.createCell(5).setCellValue(dto.getUsed().equals(Byte.valueOf("1")) ? "已使用" : "未使用");
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    public static void exportVoucherCode(HttpServletResponse response, List<VoucherExchangeCodeDTO> voucherExchangeCodeDTOList) throws IOException {
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
            row1.createCell(3).setCellValue(dto.getCount().toString());
            row1.createCell(4).setCellValue(dto.getStatus().equals(Byte.valueOf("1")) ? "启用" : "停用");
            row1.createCell(5).setCellValue(dto.getUsed().equals(Byte.valueOf("1")) ? "已使用" : "未使用");
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    public static void exportPointCode(HttpServletResponse response, List<PointExchangeCodeDTO> pointExchangeCodeDTOList) throws IOException {
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
            row1.createCell(3).setCellValue(dto.getFaceValue().toString());
            row1.createCell(4).setCellValue(dto.getStatus().equals(Byte.valueOf("1")) ? "启用" : "停用");
            row1.createCell(5).setCellValue(dto.getUsed().equals(Byte.valueOf("1")) ? "已使用" : "未使用");
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    public static void exportPartnerOrder(HttpServletResponse response, List<PartnerOrderInfoDTO> partnerOrderInfoDTOList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        String fileName = System.currentTimeMillis() + ".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"客户姓名", "客户手机号", "城市", "兑换密钥", "兑换时间", "预约时间", "订单来源", "服务人员姓名", "服务人员电话", "服务项目", "服务商", "预付", "再支付"};
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
            row1.createCell(3).setCellValue(dto.getKeyt());
            row1.createCell(4).setCellValue(DateUtil.dateToStr(dto.getOrderTime()));
            row1.createCell(5).setCellValue(DateUtil.dateToStr(dto.getServiceTime()));
            row1.createCell(6).setCellValue(dto.getOrderSource());
            row1.createCell(7).setCellValue(dto.getWorkerName());
            row1.createCell(8).setCellValue(dto.getWorkerMobile());
            row1.createCell(9).setCellValue(dto.getServiceItemDTO().getName());
            row1.createCell(10).setCellValue(dto.getPartnerDTO().getName());
            row1.createCell(11).setCellValue(dto.getPrePay().toString());
            row1.createCell(12).setCellValue(dto.getAppendPay().toString());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


    public static void exportChargeReceipt(HttpServletResponse response, List<ChargeReceiptDTO> dtoList) throws IOException {
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
            XSSFCell cell6 = row1.createCell(6);
            cell6.setCellType(CellType.NUMERIC);
            cell6.setCellValue(dto.getExchangePoint().doubleValue());
            row1.createCell(7).setCellValue("");
            row1.createCell(8).setCellValue("是");
            row1.createCell(9).setCellValue("是");
            row1.createCell(10).setCellValue("");
            rowNum++;
        }
        resetColumnWidth(sheet,headers.length);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    /**
     *  重新调整单元格的列宽
     * @param sheet
     * @param maxColumn
     * @throws UnsupportedEncodingException
     */
    public static void resetColumnWidth(XSSFSheet sheet, int maxColumn) throws UnsupportedEncodingException {
        for (int i = 0; i <= maxColumn; i++) {
            sheet.autoSizeColumn(i);
        }
        for (int columnNum = 0; columnNum <= maxColumn; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
                XSSFRow currentRow;
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }

                if (currentRow.getCell(columnNum) != null) {
                    Cell currentCell = currentRow.getCell(columnNum);
                    int length = currentCell.toString().getBytes("UTF-8").length;
                    if (columnWidth < length) {
                        columnWidth = length;
                    }
                }
            }
            sheet.setColumnWidth(columnNum, columnWidth * 256);
        }
    }
}
