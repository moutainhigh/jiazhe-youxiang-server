/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.djbx;

import java.math.BigDecimal;

/**
 * @author tu
 * @version 1.0
 * @description TODO
 * @created 2020-05-19 23:07
 */
public class PointsConsumeParam {

    public PointsConsumeParam(String agentCode,String transactionID,String transactionType,String settlementType,BigDecimal changePoints,String verifiCode){
        this.agentCode = agentCode;
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.settlementType = settlementType;
        this.changePoints = changePoints;
        this.verifiCode = verifiCode;
    }

    private String agentCode;

    private String transactionID;

    private String transactionType;

    private String settlementType;

    private BigDecimal changePoints;

    private String verifiCode;

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
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

    public BigDecimal getChangePoints() {
        return changePoints;
    }

    public void setChangePoints(BigDecimal changePoints) {
        this.changePoints = changePoints;
    }

    public String getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(String verifiCode) {
        this.verifiCode = verifiCode;
    }
}
