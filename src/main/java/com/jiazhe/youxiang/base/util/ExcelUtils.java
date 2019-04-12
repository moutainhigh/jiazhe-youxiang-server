package com.jiazhe.youxiang.base.util;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author TU
 * @description  excel处理工具
 * @date 2019-04-12.
 */
public class ExcelUtils {

    private static Logger logger = LoggerFactory.getLogger(ExportExcelUtils.class);

    /**
     * 将excel转为sheet
     * @param url
     * @return
     */
    public static Sheet excel2Sheet(String url) {
        File excelFile = new File(url);
        FileInputStream in = null;
        try {
            in = new FileInputStream(excelFile);
        } catch (FileNotFoundException e) {
            throw new CommonException(CommonCodeEnum.FILE_NOT_EXIST);
        }
        Workbook workbook = null;
        if (excelFile.getName().endsWith("xlsx")) {
            try {
                workbook = new XSSFWorkbook(in);
            } catch (IOException e) {
                logger.error("IO异常," + e.getMessage());
            }
        }
        Sheet sheet = workbook.getSheetAt(0);
        return sheet;
    }
}
