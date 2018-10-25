package com.jiazhe.youxiang.server.vo.resp.eleproductexcode;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author TU
 * @description
 * @date 2018/10/25.
 */
public class ExcelLegalityResp extends BaseVO {

    private Integer count;

    private Byte isLegal;

    private String excelUrl;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Byte getIsLegal() {
        return isLegal;
    }

    public void setIsLegal(Byte isLegal) {
        this.isLegal = isLegal;
    }

    public String getExcelUrl() {
        return excelUrl;
    }

    public void setExcelUrl(String excelUrl) {
        this.excelUrl = excelUrl;
    }
}
