/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.djbx;

/**
 * @author tu
 * @version 1.0
 * @description TODO
 * @created 2020-05-19 23:07
 */
public class PointsConsumeParam {

    public PointsConsumeParam(String agentCode,String transactionId,String transactionType,String settlementType,Integer changePoints,String verifiCode){
        this.agentCode = agentCode;
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.settlementType = settlementType;
        this.changePoints = changePoints;
        this.verifiCode = verifiCode;
    }

    private String agentCode;

    private String transactionId;

    private String transactionType;

    private String settlementType;

    private Integer changePoints;

    private String verifiCode;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public Integer getChangePoints() {
        return changePoints;
    }

    public void setChangePoints(Integer changePoints) {
        this.changePoints = changePoints;
    }

    public String getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(String verifiCode) {
        this.verifiCode = verifiCode;
    }
}
