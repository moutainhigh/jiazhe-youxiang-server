package com.jiazhe.youxiang.server.controller;

import com.jiazhe.youxiang.base.controller.BaseController;
import com.jiazhe.youxiang.server.adapter.EleProductExCodeAdapter;
import com.jiazhe.youxiang.server.biz.EleProductExCodeBiz;
import com.jiazhe.youxiang.server.dto.eleproductexcode.BatchNameDTO;
import com.jiazhe.youxiang.server.dto.eleproductexcode.EleProductExCodeDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.ResponseFactory;
import com.jiazhe.youxiang.server.vo.req.IdReq;
import com.jiazhe.youxiang.server.vo.req.eleproductexcode.BatchNamePageReq;
import com.jiazhe.youxiang.server.vo.req.eleproductexcode.ExpiryTimeEditReq;
import com.jiazhe.youxiang.server.vo.req.eleproductexcode.ImportCodeReq;
import com.jiazhe.youxiang.server.vo.resp.eleproductexcode.BatchNameResp;
import com.jiazhe.youxiang.server.vo.resp.eleproductexcode.EleProductExCodeResp;
import com.jiazhe.youxiang.server.vo.resp.eleproductexcode.ExcelLegalityResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author TU
 * @description 电子商品兑换码接口
 * @date 2018/10/24.
 */
@RestController
@RequestMapping("api/eleproductexcode")
public class APIEleProductExCodeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(APIEleProductExCodeController.class);

    @Autowired
    private EleProductExCodeBiz eleProductExCodeBiz;

    @ApiOperation(value = "查询所有批次信息", httpMethod = "GET",response = BatchNameResp.class,responseContainer = "List" ,notes = "查询所有批次信息")
    @RequestMapping(value = "/listbatchall", method = RequestMethod.GET)
    public Object listBatchAll() {
        List<BatchNameDTO> batchNameDTOList = eleProductExCodeBiz.getBatchList();
        List<BatchNameResp> batchNameRespList = batchNameDTOList.stream().map(EleProductExCodeAdapter::batchNameDTO2BatchNameResp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(batchNameRespList);
    }

    @ApiOperation(value = "查询批次下所有电子码", httpMethod = "GET",response = EleProductExCodeResp.class ,responseContainer = "List" ,notes = "查询批次下所有电子码")
    @RequestMapping(value = "/listpage", method = RequestMethod.GET)
    public Object listPage(@ModelAttribute BatchNamePageReq req) {
        //参数检查
        Paging paging = new Paging();
        paging.setOffset((req.getPageNum()-1)*req.getPageSize());
        paging.setLimit(req.getPageSize());
        List<EleProductExCodeDTO> eleProductExCodeDTOList = eleProductExCodeBiz.getList(req.getName(),paging);
        List<EleProductExCodeResp> eleProductExCodeRespList = eleProductExCodeDTOList.stream().map(EleProductExCodeAdapter::DTO2Resp).collect(Collectors.toList());
        return ResponseFactory.buildResponse(eleProductExCodeRespList);
    }

    @ApiOperation(value = "启用电子商品码", httpMethod = "POST",notes = "启用电子商品码")
    @RequestMapping(value = "/startusing", method = RequestMethod.POST)
    public Object startUsing(@ModelAttribute IdReq req) {
        //参数检查
        eleProductExCodeBiz.startUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "停用电子商品码", httpMethod = "POST",notes = "启用电子商品吗")
    @RequestMapping(value = "/stopusing", method = RequestMethod.POST)
    public Object stopUsing(@ModelAttribute IdReq req) {
        //参数检查
        eleProductExCodeBiz.stopUsing(req.getId());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "批量修改电子码有效期", httpMethod = "POST",notes = "批量修改电子码有效期")
    @RequestMapping(value = "/changeexpirytime", method = RequestMethod.POST)
    public Object changeExpiryTime(@ModelAttribute ExpiryTimeEditReq req) {
        //参数检查
        eleProductExCodeBiz.changeExpiryTime(req.getBatchName(),req.getExpiryTime());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "导入电子码", httpMethod = "POST",notes = "导入电子码")
    @RequestMapping(value = "/importcode", method = RequestMethod.POST)
    public Object importCode(@ModelAttribute ImportCodeReq req) {
        //参数检查
        eleProductExCodeBiz.importCode(req.getExcelUrl(),req.getIsNew(),req.getBatchName(),req.getExpiryTime());
        return ResponseFactory.buildSuccess();
    }

    @ApiOperation(value = "上传excel，检查合法性并返回文件名", httpMethod = "POST",response = ExcelLegalityResp.class,notes = "上传excel，检查合法性并返回文件名")
    @RequestMapping(value = "/uploadexcel", method = RequestMethod.POST)
    public Object uploadExcel() {
        //参数检查
        ExcelLegalityResp excelLegalityResp = eleProductExCodeBiz.uploadExcel();
        return ResponseFactory.buildSuccess();
    }

}
