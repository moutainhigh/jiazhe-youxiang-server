package com.jiazhe.youxiang.base.util;

import com.jiazhe.youxiang.server.dto.point.pointexchangecode.PointExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.rechargecard.rcexchangecode.RCExchangeCodeDTO;
import com.jiazhe.youxiang.server.dto.voucher.exchangecode.VoucherExchangeCodeDTO;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author tu
 * @description：
 * @date 2018/11/1
 */
public class ExportExcelUtils {

    public static void exportRechargeCardCode(HttpServletResponse response ,List<RCExchangeCodeDTO> rcExchangeCodeDTOList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        String fileName = System.currentTimeMillis()+".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"批次名称","卡号", "秘钥", "面额", "启用/停用","使用状态"};
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
            row1.createCell(4).setCellValue(dto.getStatus().equals(Byte.valueOf("1"))?"启用":"停用");
            row1.createCell(5).setCellValue(dto.getUsed().equals(Byte.valueOf("1"))?"已使用":"未使用");
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    public static void exportVoucherCode(HttpServletResponse response ,List<VoucherExchangeCodeDTO> voucherExchangeCodeDTOList) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        String fileName = System.currentTimeMillis()+".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"批次名称","卡号", "秘钥", "兑换商品数量", "启用/停用","使用状态"};
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
            row1.createCell(4).setCellValue(dto.getStatus().equals(Byte.valueOf("1"))?"启用":"停用");
            row1.createCell(5).setCellValue(dto.getUsed().equals(Byte.valueOf("1"))?"已使用":"未使用");
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
        String fileName = System.currentTimeMillis()+".xlsx";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"批次名称","卡号", "秘钥", "面额", "启用/停用","使用状态"};
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
            row1.createCell(4).setCellValue(dto.getStatus().equals(Byte.valueOf("1"))?"启用":"停用");
            row1.createCell(5).setCellValue(dto.getUsed().equals(Byte.valueOf("1"))?"已使用":"未使用");
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }
}
