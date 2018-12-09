package com.jiazhe.youxiang.server.vo.resp.serviceitem;

import com.jiazhe.youxiang.server.vo.BaseVO;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/12/9
 */
public class ServiceItemResp extends BaseVO{
    private Integer id;

    private String name;

    private Byte status;

    private String extInfo;

    private Byte isDeleted;

    private Long addTime;

    private Long modTime;

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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }


    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public void setModTime(Long modTime) {
        this.modTime = modTime;
    }
}
