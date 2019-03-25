package com.jiazhe.youxiang.server.domain.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditRecordPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AuditRecordPOExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPointIdsIsNull() {
            addCriterion("point_ids is null");
            return (Criteria) this;
        }

        public Criteria andPointIdsIsNotNull() {
            addCriterion("point_ids is not null");
            return (Criteria) this;
        }

        public Criteria andPointIdsEqualTo(String value) {
            addCriterion("point_ids =", value, "pointIds");
            return (Criteria) this;
        }

        public Criteria andPointIdsNotEqualTo(String value) {
            addCriterion("point_ids <>", value, "pointIds");
            return (Criteria) this;
        }

        public Criteria andPointIdsGreaterThan(String value) {
            addCriterion("point_ids >", value, "pointIds");
            return (Criteria) this;
        }

        public Criteria andPointIdsGreaterThanOrEqualTo(String value) {
            addCriterion("point_ids >=", value, "pointIds");
            return (Criteria) this;
        }

        public Criteria andPointIdsLessThan(String value) {
            addCriterion("point_ids <", value, "pointIds");
            return (Criteria) this;
        }

        public Criteria andPointIdsLessThanOrEqualTo(String value) {
            addCriterion("point_ids <=", value, "pointIds");
            return (Criteria) this;
        }

        public Criteria andPointIdsLike(String value) {
            addCriterion("point_ids like", value, "pointIds");
            return (Criteria) this;
        }

        public Criteria andPointIdsNotLike(String value) {
            addCriterion("point_ids not like", value, "pointIds");
            return (Criteria) this;
        }

        public Criteria andPointIdsIn(List<String> values) {
            addCriterion("point_ids in", values, "pointIds");
            return (Criteria) this;
        }

        public Criteria andPointIdsNotIn(List<String> values) {
            addCriterion("point_ids not in", values, "pointIds");
            return (Criteria) this;
        }

        public Criteria andPointIdsBetween(String value1, String value2) {
            addCriterion("point_ids between", value1, value2, "pointIds");
            return (Criteria) this;
        }

        public Criteria andPointIdsNotBetween(String value1, String value2) {
            addCriterion("point_ids not between", value1, value2, "pointIds");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileIsNull() {
            addCriterion("customer_mobile is null");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileIsNotNull() {
            addCriterion("customer_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileEqualTo(String value) {
            addCriterion("customer_mobile =", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileNotEqualTo(String value) {
            addCriterion("customer_mobile <>", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileGreaterThan(String value) {
            addCriterion("customer_mobile >", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileGreaterThanOrEqualTo(String value) {
            addCriterion("customer_mobile >=", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileLessThan(String value) {
            addCriterion("customer_mobile <", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileLessThanOrEqualTo(String value) {
            addCriterion("customer_mobile <=", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileLike(String value) {
            addCriterion("customer_mobile like", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileNotLike(String value) {
            addCriterion("customer_mobile not like", value, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileIn(List<String> values) {
            addCriterion("customer_mobile in", values, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileNotIn(List<String> values) {
            addCriterion("customer_mobile not in", values, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileBetween(String value1, String value2) {
            addCriterion("customer_mobile between", value1, value2, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andCustomerMobileNotBetween(String value1, String value2) {
            addCriterion("customer_mobile not between", value1, value2, "customerMobile");
            return (Criteria) this;
        }

        public Criteria andExchangePointIsNull() {
            addCriterion("exchange_point is null");
            return (Criteria) this;
        }

        public Criteria andExchangePointIsNotNull() {
            addCriterion("exchange_point is not null");
            return (Criteria) this;
        }

        public Criteria andExchangePointEqualTo(BigDecimal value) {
            addCriterion("exchange_point =", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointNotEqualTo(BigDecimal value) {
            addCriterion("exchange_point <>", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointGreaterThan(BigDecimal value) {
            addCriterion("exchange_point >", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("exchange_point >=", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointLessThan(BigDecimal value) {
            addCriterion("exchange_point <", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointLessThanOrEqualTo(BigDecimal value) {
            addCriterion("exchange_point <=", value, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointIn(List<BigDecimal> values) {
            addCriterion("exchange_point in", values, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointNotIn(List<BigDecimal> values) {
            addCriterion("exchange_point not in", values, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("exchange_point between", value1, value2, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andExchangePointNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("exchange_point not between", value1, value2, "exchangePoint");
            return (Criteria) this;
        }

        public Criteria andGivingPointIsNull() {
            addCriterion("giving_point is null");
            return (Criteria) this;
        }

        public Criteria andGivingPointIsNotNull() {
            addCriterion("giving_point is not null");
            return (Criteria) this;
        }

        public Criteria andGivingPointEqualTo(BigDecimal value) {
            addCriterion("giving_point =", value, "givingPoint");
            return (Criteria) this;
        }

        public Criteria andGivingPointNotEqualTo(BigDecimal value) {
            addCriterion("giving_point <>", value, "givingPoint");
            return (Criteria) this;
        }

        public Criteria andGivingPointGreaterThan(BigDecimal value) {
            addCriterion("giving_point >", value, "givingPoint");
            return (Criteria) this;
        }

        public Criteria andGivingPointGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("giving_point >=", value, "givingPoint");
            return (Criteria) this;
        }

        public Criteria andGivingPointLessThan(BigDecimal value) {
            addCriterion("giving_point <", value, "givingPoint");
            return (Criteria) this;
        }

        public Criteria andGivingPointLessThanOrEqualTo(BigDecimal value) {
            addCriterion("giving_point <=", value, "givingPoint");
            return (Criteria) this;
        }

        public Criteria andGivingPointIn(List<BigDecimal> values) {
            addCriterion("giving_point in", values, "givingPoint");
            return (Criteria) this;
        }

        public Criteria andGivingPointNotIn(List<BigDecimal> values) {
            addCriterion("giving_point not in", values, "givingPoint");
            return (Criteria) this;
        }

        public Criteria andGivingPointBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("giving_point between", value1, value2, "givingPoint");
            return (Criteria) this;
        }

        public Criteria andGivingPointNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("giving_point not between", value1, value2, "givingPoint");
            return (Criteria) this;
        }

        public Criteria andGivingTypeIsNull() {
            addCriterion("giving_type is null");
            return (Criteria) this;
        }

        public Criteria andGivingTypeIsNotNull() {
            addCriterion("giving_type is not null");
            return (Criteria) this;
        }

        public Criteria andGivingTypeEqualTo(String value) {
            addCriterion("giving_type =", value, "givingType");
            return (Criteria) this;
        }

        public Criteria andGivingTypeNotEqualTo(String value) {
            addCriterion("giving_type <>", value, "givingType");
            return (Criteria) this;
        }

        public Criteria andGivingTypeGreaterThan(String value) {
            addCriterion("giving_type >", value, "givingType");
            return (Criteria) this;
        }

        public Criteria andGivingTypeGreaterThanOrEqualTo(String value) {
            addCriterion("giving_type >=", value, "givingType");
            return (Criteria) this;
        }

        public Criteria andGivingTypeLessThan(String value) {
            addCriterion("giving_type <", value, "givingType");
            return (Criteria) this;
        }

        public Criteria andGivingTypeLessThanOrEqualTo(String value) {
            addCriterion("giving_type <=", value, "givingType");
            return (Criteria) this;
        }

        public Criteria andGivingTypeLike(String value) {
            addCriterion("giving_type like", value, "givingType");
            return (Criteria) this;
        }

        public Criteria andGivingTypeNotLike(String value) {
            addCriterion("giving_type not like", value, "givingType");
            return (Criteria) this;
        }

        public Criteria andGivingTypeIn(List<String> values) {
            addCriterion("giving_type in", values, "givingType");
            return (Criteria) this;
        }

        public Criteria andGivingTypeNotIn(List<String> values) {
            addCriterion("giving_type not in", values, "givingType");
            return (Criteria) this;
        }

        public Criteria andGivingTypeBetween(String value1, String value2) {
            addCriterion("giving_type between", value1, value2, "givingType");
            return (Criteria) this;
        }

        public Criteria andGivingTypeNotBetween(String value1, String value2) {
            addCriterion("giving_type not between", value1, value2, "givingType");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIsNull() {
            addCriterion("audit_reason is null");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIsNotNull() {
            addCriterion("audit_reason is not null");
            return (Criteria) this;
        }

        public Criteria andAuditReasonEqualTo(String value) {
            addCriterion("audit_reason =", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotEqualTo(String value) {
            addCriterion("audit_reason <>", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonGreaterThan(String value) {
            addCriterion("audit_reason >", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonGreaterThanOrEqualTo(String value) {
            addCriterion("audit_reason >=", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLessThan(String value) {
            addCriterion("audit_reason <", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLessThanOrEqualTo(String value) {
            addCriterion("audit_reason <=", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonLike(String value) {
            addCriterion("audit_reason like", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotLike(String value) {
            addCriterion("audit_reason not like", value, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonIn(List<String> values) {
            addCriterion("audit_reason in", values, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotIn(List<String> values) {
            addCriterion("audit_reason not in", values, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonBetween(String value1, String value2) {
            addCriterion("audit_reason between", value1, value2, "auditReason");
            return (Criteria) this;
        }

        public Criteria andAuditReasonNotBetween(String value1, String value2) {
            addCriterion("audit_reason not between", value1, value2, "auditReason");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andImgUrlsIsNull() {
            addCriterion("img_urls is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlsIsNotNull() {
            addCriterion("img_urls is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlsEqualTo(String value) {
            addCriterion("img_urls =", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsNotEqualTo(String value) {
            addCriterion("img_urls <>", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsGreaterThan(String value) {
            addCriterion("img_urls >", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsGreaterThanOrEqualTo(String value) {
            addCriterion("img_urls >=", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsLessThan(String value) {
            addCriterion("img_urls <", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsLessThanOrEqualTo(String value) {
            addCriterion("img_urls <=", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsLike(String value) {
            addCriterion("img_urls like", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsNotLike(String value) {
            addCriterion("img_urls not like", value, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsIn(List<String> values) {
            addCriterion("img_urls in", values, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsNotIn(List<String> values) {
            addCriterion("img_urls not in", values, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsBetween(String value1, String value2) {
            addCriterion("img_urls between", value1, value2, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andImgUrlsNotBetween(String value1, String value2) {
            addCriterion("img_urls not between", value1, value2, "imgUrls");
            return (Criteria) this;
        }

        public Criteria andPosCodeIsNull() {
            addCriterion("pos_code is null");
            return (Criteria) this;
        }

        public Criteria andPosCodeIsNotNull() {
            addCriterion("pos_code is not null");
            return (Criteria) this;
        }

        public Criteria andPosCodeEqualTo(String value) {
            addCriterion("pos_code =", value, "posCode");
            return (Criteria) this;
        }

        public Criteria andPosCodeNotEqualTo(String value) {
            addCriterion("pos_code <>", value, "posCode");
            return (Criteria) this;
        }

        public Criteria andPosCodeGreaterThan(String value) {
            addCriterion("pos_code >", value, "posCode");
            return (Criteria) this;
        }

        public Criteria andPosCodeGreaterThanOrEqualTo(String value) {
            addCriterion("pos_code >=", value, "posCode");
            return (Criteria) this;
        }

        public Criteria andPosCodeLessThan(String value) {
            addCriterion("pos_code <", value, "posCode");
            return (Criteria) this;
        }

        public Criteria andPosCodeLessThanOrEqualTo(String value) {
            addCriterion("pos_code <=", value, "posCode");
            return (Criteria) this;
        }

        public Criteria andPosCodeLike(String value) {
            addCriterion("pos_code like", value, "posCode");
            return (Criteria) this;
        }

        public Criteria andPosCodeNotLike(String value) {
            addCriterion("pos_code not like", value, "posCode");
            return (Criteria) this;
        }

        public Criteria andPosCodeIn(List<String> values) {
            addCriterion("pos_code in", values, "posCode");
            return (Criteria) this;
        }

        public Criteria andPosCodeNotIn(List<String> values) {
            addCriterion("pos_code not in", values, "posCode");
            return (Criteria) this;
        }

        public Criteria andPosCodeBetween(String value1, String value2) {
            addCriterion("pos_code between", value1, value2, "posCode");
            return (Criteria) this;
        }

        public Criteria andPosCodeNotBetween(String value1, String value2) {
            addCriterion("pos_code not between", value1, value2, "posCode");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNull() {
            addCriterion("trade_time is null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIsNotNull() {
            addCriterion("trade_time is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTimeEqualTo(Date value) {
            addCriterion("trade_time =", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotEqualTo(Date value) {
            addCriterion("trade_time <>", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThan(Date value) {
            addCriterion("trade_time >", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trade_time >=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThan(Date value) {
            addCriterion("trade_time <", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeLessThanOrEqualTo(Date value) {
            addCriterion("trade_time <=", value, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeIn(List<Date> values) {
            addCriterion("trade_time in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotIn(List<Date> values) {
            addCriterion("trade_time not in", values, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeBetween(Date value1, Date value2) {
            addCriterion("trade_time between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andTradeTimeNotBetween(Date value1, Date value2) {
            addCriterion("trade_time not between", value1, value2, "tradeTime");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNull() {
            addCriterion("card_no is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("card_no is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("card_no =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("card_no <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("card_no >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("card_no >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("card_no <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("card_no <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("card_no like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("card_no not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("card_no in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("card_no not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("card_no between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("card_no not between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameIsNull() {
            addCriterion("bank_outlets_name is null");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameIsNotNull() {
            addCriterion("bank_outlets_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameEqualTo(String value) {
            addCriterion("bank_outlets_name =", value, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameNotEqualTo(String value) {
            addCriterion("bank_outlets_name <>", value, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameGreaterThan(String value) {
            addCriterion("bank_outlets_name >", value, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_outlets_name >=", value, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameLessThan(String value) {
            addCriterion("bank_outlets_name <", value, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameLessThanOrEqualTo(String value) {
            addCriterion("bank_outlets_name <=", value, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameLike(String value) {
            addCriterion("bank_outlets_name like", value, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameNotLike(String value) {
            addCriterion("bank_outlets_name not like", value, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameIn(List<String> values) {
            addCriterion("bank_outlets_name in", values, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameNotIn(List<String> values) {
            addCriterion("bank_outlets_name not in", values, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameBetween(String value1, String value2) {
            addCriterion("bank_outlets_name between", value1, value2, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andBankOutletsNameNotBetween(String value1, String value2) {
            addCriterion("bank_outlets_name not between", value1, value2, "bankOutletsName");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdIsNull() {
            addCriterion("submitter_id is null");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdIsNotNull() {
            addCriterion("submitter_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdEqualTo(Integer value) {
            addCriterion("submitter_id =", value, "submitterId");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdNotEqualTo(Integer value) {
            addCriterion("submitter_id <>", value, "submitterId");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdGreaterThan(Integer value) {
            addCriterion("submitter_id >", value, "submitterId");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("submitter_id >=", value, "submitterId");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdLessThan(Integer value) {
            addCriterion("submitter_id <", value, "submitterId");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdLessThanOrEqualTo(Integer value) {
            addCriterion("submitter_id <=", value, "submitterId");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdIn(List<Integer> values) {
            addCriterion("submitter_id in", values, "submitterId");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdNotIn(List<Integer> values) {
            addCriterion("submitter_id not in", values, "submitterId");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdBetween(Integer value1, Integer value2) {
            addCriterion("submitter_id between", value1, value2, "submitterId");
            return (Criteria) this;
        }

        public Criteria andSubmitterIdNotBetween(Integer value1, Integer value2) {
            addCriterion("submitter_id not between", value1, value2, "submitterId");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameIsNull() {
            addCriterion("submitter_name is null");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameIsNotNull() {
            addCriterion("submitter_name is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameEqualTo(String value) {
            addCriterion("submitter_name =", value, "submitterName");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameNotEqualTo(String value) {
            addCriterion("submitter_name <>", value, "submitterName");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameGreaterThan(String value) {
            addCriterion("submitter_name >", value, "submitterName");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameGreaterThanOrEqualTo(String value) {
            addCriterion("submitter_name >=", value, "submitterName");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameLessThan(String value) {
            addCriterion("submitter_name <", value, "submitterName");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameLessThanOrEqualTo(String value) {
            addCriterion("submitter_name <=", value, "submitterName");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameLike(String value) {
            addCriterion("submitter_name like", value, "submitterName");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameNotLike(String value) {
            addCriterion("submitter_name not like", value, "submitterName");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameIn(List<String> values) {
            addCriterion("submitter_name in", values, "submitterName");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameNotIn(List<String> values) {
            addCriterion("submitter_name not in", values, "submitterName");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameBetween(String value1, String value2) {
            addCriterion("submitter_name between", value1, value2, "submitterName");
            return (Criteria) this;
        }

        public Criteria andSubmitterNameNotBetween(String value1, String value2) {
            addCriterion("submitter_name not between", value1, value2, "submitterName");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("audit_time is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(Date value) {
            addCriterion("audit_time =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(Date value) {
            addCriterion("audit_time <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(Date value) {
            addCriterion("audit_time >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_time >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(Date value) {
            addCriterion("audit_time <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("audit_time <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<Date> values) {
            addCriterion("audit_time in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<Date> values) {
            addCriterion("audit_time not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(Date value1, Date value2) {
            addCriterion("audit_time between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("audit_time not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIsNull() {
            addCriterion("auditor_id is null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIsNotNull() {
            addCriterion("auditor_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuditorIdEqualTo(Integer value) {
            addCriterion("auditor_id =", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotEqualTo(Integer value) {
            addCriterion("auditor_id <>", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThan(Integer value) {
            addCriterion("auditor_id >", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("auditor_id >=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThan(Integer value) {
            addCriterion("auditor_id <", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdLessThanOrEqualTo(Integer value) {
            addCriterion("auditor_id <=", value, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdIn(List<Integer> values) {
            addCriterion("auditor_id in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotIn(List<Integer> values) {
            addCriterion("auditor_id not in", values, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdBetween(Integer value1, Integer value2) {
            addCriterion("auditor_id between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("auditor_id not between", value1, value2, "auditorId");
            return (Criteria) this;
        }

        public Criteria andAuditorNameIsNull() {
            addCriterion("auditor_name is null");
            return (Criteria) this;
        }

        public Criteria andAuditorNameIsNotNull() {
            addCriterion("auditor_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuditorNameEqualTo(String value) {
            addCriterion("auditor_name =", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotEqualTo(String value) {
            addCriterion("auditor_name <>", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameGreaterThan(String value) {
            addCriterion("auditor_name >", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameGreaterThanOrEqualTo(String value) {
            addCriterion("auditor_name >=", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameLessThan(String value) {
            addCriterion("auditor_name <", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameLessThanOrEqualTo(String value) {
            addCriterion("auditor_name <=", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameLike(String value) {
            addCriterion("auditor_name like", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotLike(String value) {
            addCriterion("auditor_name not like", value, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameIn(List<String> values) {
            addCriterion("auditor_name in", values, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotIn(List<String> values) {
            addCriterion("auditor_name not in", values, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameBetween(String value1, String value2) {
            addCriterion("auditor_name between", value1, value2, "auditorName");
            return (Criteria) this;
        }

        public Criteria andAuditorNameNotBetween(String value1, String value2) {
            addCriterion("auditor_name not between", value1, value2, "auditorName");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andExtInfoIsNull() {
            addCriterion("ext_info is null");
            return (Criteria) this;
        }

        public Criteria andExtInfoIsNotNull() {
            addCriterion("ext_info is not null");
            return (Criteria) this;
        }

        public Criteria andExtInfoEqualTo(String value) {
            addCriterion("ext_info =", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoNotEqualTo(String value) {
            addCriterion("ext_info <>", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoGreaterThan(String value) {
            addCriterion("ext_info >", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoGreaterThanOrEqualTo(String value) {
            addCriterion("ext_info >=", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoLessThan(String value) {
            addCriterion("ext_info <", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoLessThanOrEqualTo(String value) {
            addCriterion("ext_info <=", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoLike(String value) {
            addCriterion("ext_info like", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoNotLike(String value) {
            addCriterion("ext_info not like", value, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoIn(List<String> values) {
            addCriterion("ext_info in", values, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoNotIn(List<String> values) {
            addCriterion("ext_info not in", values, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoBetween(String value1, String value2) {
            addCriterion("ext_info between", value1, value2, "extInfo");
            return (Criteria) this;
        }

        public Criteria andExtInfoNotBetween(String value1, String value2) {
            addCriterion("ext_info not between", value1, value2, "extInfo");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Byte value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Byte value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Byte value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Byte value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Byte> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Byte> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andModTimeIsNull() {
            addCriterion("mod_time is null");
            return (Criteria) this;
        }

        public Criteria andModTimeIsNotNull() {
            addCriterion("mod_time is not null");
            return (Criteria) this;
        }

        public Criteria andModTimeEqualTo(Date value) {
            addCriterion("mod_time =", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotEqualTo(Date value) {
            addCriterion("mod_time <>", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeGreaterThan(Date value) {
            addCriterion("mod_time >", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("mod_time >=", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeLessThan(Date value) {
            addCriterion("mod_time <", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeLessThanOrEqualTo(Date value) {
            addCriterion("mod_time <=", value, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeIn(List<Date> values) {
            addCriterion("mod_time in", values, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotIn(List<Date> values) {
            addCriterion("mod_time not in", values, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeBetween(Date value1, Date value2) {
            addCriterion("mod_time between", value1, value2, "modTime");
            return (Criteria) this;
        }

        public Criteria andModTimeNotBetween(Date value1, Date value2) {
            addCriterion("mod_time not between", value1, value2, "modTime");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusIsNull() {
            addCriterion("charge_receipt_status is null");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusIsNotNull() {
            addCriterion("charge_receipt_status is not null");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusEqualTo(Byte value) {
            addCriterion("charge_receipt_status =", value, "chargeReceiptStatus");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusNotEqualTo(Byte value) {
            addCriterion("charge_receipt_status <>", value, "chargeReceiptStatus");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusGreaterThan(Byte value) {
            addCriterion("charge_receipt_status >", value, "chargeReceiptStatus");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("charge_receipt_status >=", value, "chargeReceiptStatus");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusLessThan(Byte value) {
            addCriterion("charge_receipt_status <", value, "chargeReceiptStatus");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusLessThanOrEqualTo(Byte value) {
            addCriterion("charge_receipt_status <=", value, "chargeReceiptStatus");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusIn(List<Byte> values) {
            addCriterion("charge_receipt_status in", values, "chargeReceiptStatus");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusNotIn(List<Byte> values) {
            addCriterion("charge_receipt_status not in", values, "chargeReceiptStatus");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusBetween(Byte value1, Byte value2) {
            addCriterion("charge_receipt_status between", value1, value2, "chargeReceiptStatus");
            return (Criteria) this;
        }

        public Criteria andChargeReceiptStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("charge_receipt_status not between", value1, value2, "chargeReceiptStatus");
            return (Criteria) this;
        }

        public Criteria andPointCodesIsNull() {
            addCriterion("point_codes is null");
            return (Criteria) this;
        }

        public Criteria andPointCodesIsNotNull() {
            addCriterion("point_codes is not null");
            return (Criteria) this;
        }

        public Criteria andPointCodesEqualTo(String value) {
            addCriterion("point_codes =", value, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andPointCodesNotEqualTo(String value) {
            addCriterion("point_codes <>", value, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andPointCodesGreaterThan(String value) {
            addCriterion("point_codes >", value, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andPointCodesGreaterThanOrEqualTo(String value) {
            addCriterion("point_codes >=", value, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andPointCodesLessThan(String value) {
            addCriterion("point_codes <", value, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andPointCodesLessThanOrEqualTo(String value) {
            addCriterion("point_codes <=", value, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andPointCodesLike(String value) {
            addCriterion("point_codes like", value, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andPointCodesNotLike(String value) {
            addCriterion("point_codes not like", value, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andPointCodesIn(List<String> values) {
            addCriterion("point_codes in", values, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andPointCodesNotIn(List<String> values) {
            addCriterion("point_codes not in", values, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andPointCodesBetween(String value1, String value2) {
            addCriterion("point_codes between", value1, value2, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andPointCodesNotBetween(String value1, String value2) {
            addCriterion("point_codes not between", value1, value2, "pointCodes");
            return (Criteria) this;
        }

        public Criteria andProductValueIsNull() {
            addCriterion("product_value is null");
            return (Criteria) this;
        }

        public Criteria andProductValueIsNotNull() {
            addCriterion("product_value is not null");
            return (Criteria) this;
        }

        public Criteria andProductValueEqualTo(BigDecimal value) {
            addCriterion("product_value =", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueNotEqualTo(BigDecimal value) {
            addCriterion("product_value <>", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueGreaterThan(BigDecimal value) {
            addCriterion("product_value >", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_value >=", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueLessThan(BigDecimal value) {
            addCriterion("product_value <", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_value <=", value, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueIn(List<BigDecimal> values) {
            addCriterion("product_value in", values, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueNotIn(List<BigDecimal> values) {
            addCriterion("product_value not in", values, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_value between", value1, value2, "productValue");
            return (Criteria) this;
        }

        public Criteria andProductValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_value not between", value1, value2, "productValue");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeIsNull() {
            addCriterion("exchange_type is null");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeIsNotNull() {
            addCriterion("exchange_type is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeEqualTo(Byte value) {
            addCriterion("exchange_type =", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeNotEqualTo(Byte value) {
            addCriterion("exchange_type <>", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeGreaterThan(Byte value) {
            addCriterion("exchange_type >", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("exchange_type >=", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeLessThan(Byte value) {
            addCriterion("exchange_type <", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeLessThanOrEqualTo(Byte value) {
            addCriterion("exchange_type <=", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeIn(List<Byte> values) {
            addCriterion("exchange_type in", values, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeNotIn(List<Byte> values) {
            addCriterion("exchange_type not in", values, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeBetween(Byte value1, Byte value2) {
            addCriterion("exchange_type between", value1, value2, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("exchange_type not between", value1, value2, "exchangeType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}