package com.jiazhe.youxiang.server.biz;

import com.google.common.collect.Lists;
import com.jiazhe.youxiang.server.adapter.EleProductCodeAdapter;
import com.jiazhe.youxiang.server.common.enums.EleProductCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.EleProductCodeException;
import com.jiazhe.youxiang.server.domain.po.ElectronicProductExchangeCodePO;
import com.jiazhe.youxiang.server.dto.eleproductexcode.BatchNameDTO;
import com.jiazhe.youxiang.server.dto.eleproductexcode.EleProductCodeDTO;
import com.jiazhe.youxiang.server.service.EleProductCodeService;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.eleproductcode.ExcelLegalityResp;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author TU
 * @description  商品电子兑换码
 * @date 2018/10/24.
 */
@Service("eleProductCodeBiz")
public class EleProductCodeBiz {

    @Autowired
    private EleProductCodeService eleProductCodeService;

    /**
     * 电子商品兑换码已经兑换
     */
    public static final Byte CODE_ELEPRODUCT_CODE_EXCHANGED = Byte.valueOf("0");

    /**
     * 电子商品兑换码未兑换
     */
    public static final Byte CODE_ELEPRODUCT_CODE_NOT_EXCHANGED = Byte.valueOf("1");

    public void batchChangeExpiryTime(String batchName, Date expiryTime) {
        eleProductCodeService.batchChangeExpiryTime(batchName,expiryTime);
    }

    public void importCode(String excelUrl, Integer productId , String batchName, Date expiryTime) throws IOException {
        List<EleProductCodeDTO> eleProductCodeDTOList = Lists.newArrayList();
        Sheet sheet = excel2Sheet(excelUrl);
        for(Row row:sheet){
            EleProductCodeDTO dto = new EleProductCodeDTO();
            dto.setProductId(productId);
            dto.setBatchName(batchName);
            dto.setExpiryTime(expiryTime);
            row.getCell(0).setCellType(CellType.STRING);
            dto.setCode(row.getCell(0).getStringCellValue().toString());
            row.getCell(1).setCellType(CellType.STRING);
            dto.setKeyt(row.getCell(1).getStringCellValue().toString());
            eleProductCodeDTOList.add(dto);
        }
        List<ElectronicProductExchangeCodePO> poList = eleProductCodeDTOList.stream().map(EleProductCodeAdapter::DTO2PO).collect(Collectors.toList());
        eleProductCodeService.batchInsert(poList);
    }

    public Sheet excel2Sheet(String url) throws IOException {
        File excelFile = new File(url);
        FileInputStream in = new FileInputStream(excelFile);
        if(!excelFile.exists()){
            throw new EleProductCodeException(EleProductCodeEnum.FILE_NOT_EXIST);
        }
        Workbook workbook = null;
        if(excelFile.getName().endsWith("xlsx")){
            workbook = new XSSFWorkbook(in);
        }
        /*if(excelFile.getName().endsWith("xls")){
            workbook = new HSSFWorkbook(in);
        }*/
        Sheet sheet = workbook.getSheetAt(0);
        return sheet;
    }


    public List<EleProductCodeDTO> getList(Integer productId, String batchName, Byte status, String code, String keyt, Paging paging) {
        return eleProductCodeService.getList(productId,batchName,status,code,keyt,paging);
    }

    public List<EleProductCodeDTO> getAllBatch() {
        return eleProductCodeService.getAllBatch();
    }
}
