package com.jiazhe.youxiang.server.vo.resp.message;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author TU
 * @description
 * @date 2019-04-08.
 */
public class MessageTemplateResp extends BaseVO {

    private Integer id;

    private String name;

    private Integer paramCount;

    private String aliTemplateCode;

    private String aliTemplateContent;

    private Integer tencentTemplateId;

    private String tencentTemplateContent;

    private Byte status;

    private String excelTemplate;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParamCount() {
        return paramCount;
    }

    public void setParamCount(Integer paramCount) {
        this.paramCount = paramCount;
    }

    public String getAliTemplateCode() {
        return aliTemplateCode;
    }

    public void setAliTemplateCode(String aliTemplateCode) {
        this.aliTemplateCode = aliTemplateCode;
    }

    public String getAliTemplateContent() {
        return aliTemplateContent;
    }

    public void setAliTemplateContent(String aliTemplateContent) {
        this.aliTemplateContent = aliTemplateContent;
    }

    public Integer getTencentTemplateId() {
        return tencentTemplateId;
    }

    public void setTencentTemplateId(Integer tencentTemplateId) {
        this.tencentTemplateId = tencentTemplateId;
    }

    public String getTencentTemplateContent() {
        return tencentTemplateContent;
    }

    public void setTencentTemplateContent(String tencentTemplateContent) {
        this.tencentTemplateContent = tencentTemplateContent;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getExcelTemplate() {
        return excelTemplate;
    }

    public void setExcelTemplate(String excelTemplate) {
        this.excelTemplate = excelTemplate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
