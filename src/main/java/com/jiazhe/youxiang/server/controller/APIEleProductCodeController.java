package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.base.util.PagingParamUtil;
import com.jiazhe.youxiang.base.util.UploadUtil;
import com.jiazhe.youxiang.server.adapter.EleProductCodeAdapter;
import com.jiazhe.youxiang.server.biz.EleProductCodeBiz;
import com.jiazhe.youxiang.server.common.enums.EleProductCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.EleProductCodeException;
import com.jiazhe.youxiang.server.dto.eleproductexcode.EleProductCodeDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.eleproductcode.EleProductCodePageReq;
import com.jiazhe.youxiang.server.vo.req.eleproductcode.ExpiryTimeEditReq;
import com.jiazhe.youxiang.server.vo.req.eleproductcode.ImportCodeReq;
import com.jiazhe.youxiang.server.vo.resp.eleproductcode.EleProductCodeResp;
import com.jiazhe.youxiang.server.vo.resp.eleproductcode.UploadExcelResp;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description 电子商品兑换码接口
 * @date 2018/10/24.
 */
@RestController
@RequestMapping("api/eleproductcode")
public class APIEleProductCodeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIEleProductCodeController.class);

    @Autowired
    private EleProductCodeBiz eleProductCodeBiz;

    @Value("${web.upload.ele-product-code-excel-path}")
    private String excelpath;

    @ApiOperation(value = "查询批次下所有电子码", httpMethod = "GET",response = EleProductCodeResp.class ,responseContainer = "List" ,notes = "查询批次下所有电子码")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute EleProductCodePageReq req) {
        //参数检查
        Paging paging = PagingParamUtil.pagingParamSwitch(req);
        List<EleProductCodeDTO> eleProductExCodeDTOList = eleProductCodeBiz.getList(req.getProductId(),req.getBatchName(),req.getStatus(),req.getCode(),req.getKeyt(),paging);
        List<EleProductCodeResp> eleProductExCodeRespList = eleProductExCodeDTOList.stream().map(EleProductCodeAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(eleProductExCodeRespList);
    }

    @ApiOperation(value = "获取所有批次", httpMethod = "GET",response = EleProductCodeResp.class ,responseContainer = "List" ,notes = "获取所有批次")
    @RequestMapping(value = "/getallbatch", method = RequestMethod.GET)
    public Object getAllBatch() {
        //参数检查
        List<EleProductCodeDTO> eleProductExCodeDTOList = eleProductCodeBiz.getAllBatch();
        List<EleProductCodeResp> eleProductExCodeRespList = eleProductExCodeDTOList.stream().map(EleProductCodeAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(eleProductExCodeRespList);
    }

    @RequestMapping(value = "uploadexcel", method = RequestMethod.POST, headers = ("content-type=multipart/*"), consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {
        //参数检查
        String url = UploadUtil.uploadImage(file, excelpath);
        UploadExcelResp result = new UploadExcelResp();
        result.setUrl(url);
        Sheet sheet = eleProductCodeBiz.excel2Sheet(excelpath+"/"+url);
        Integer count = 0;
        try{
            for(Row row:sheet){
                if(row.getCell(1).toString().equals("")){
                    throw new EleProductCodeException(EleProductCodeEnum.FILE_FORMAT_ERROR);
                }
                count ++ ;
            }
            result.setCount(count);
        }catch (Exception e){
            throw new EleProductCodeException(EleProductCodeEnum.FILE_FORMAT_ERROR);
        }
        return ResponseFactory.buildResponse(result);
    }

    @ApiOperation(value = "导入电子码", httpMethod = "POST",notes = "导入电子码")
    @RequestMapping(value = "/importcode", method = RequestMethod.POST)
    public Object importCode(@ModelAttribute ImportCodeReq req) throws IOException {
        //参数检查
        eleProductCodeBiz.importCode(excelpath+"/"+req.getExcelUrl(),req.getProductId(),req.getBatchName(),req.getExpiryTime());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "批量修改电子码有效期", httpMethod = "POST",notes = "批量修改电子码有效期")
    @RequestMapping(value = "/changeexpirytime", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute ExpiryTimeEditReq req) {
        //参数检查
        eleProductCodeBiz.changeExpiryTime(req.getBatchName(),req.getExpiryTime());
        return ResponseFactory.buildSuccess();
    }

}
