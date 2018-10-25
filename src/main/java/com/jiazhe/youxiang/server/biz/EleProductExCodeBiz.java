package com.jiazhe.youxiang.server.biz;

import com.jiazhe.youxiang.server.dto.eleproductexcode.BatchNameDTO;
import com.jiazhe.youxiang.server.dto.eleproductexcode.EleProductExCodeDTO;
import com.jiazhe.youxiang.server.vo.Paging;
import com.jiazhe.youxiang.server.vo.resp.eleproductexcode.ExcelLegalityResp;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author TU
 * @description  商品电子兑换码
 * @date 2018/10/24.
 */
@Service("eleProductExCodeBiz")
public class EleProductExCodeBiz {
    public int startUsing(Integer id) {
        return 0;
    }

    public int stopUsing(Integer id) {
        return 0;
    }

    public List<BatchNameDTO> getBatchList() {
        return null;
    }

    public List<EleProductExCodeDTO> getList(String name, Paging paging) {
        return null;
    }

    public int changeExpiryTime(String batchName, Date expiryTime) {
        return 0;
    }

    public int importCode(String excelUrl, Byte isNew , String batchName, Date expiryTime) {
        return 0;
    }

    public ExcelLegalityResp uploadExcel() {
        return null;
    }
}
