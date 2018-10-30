package com.jiazhe.youxiang.server.domain.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VoucherExchangeCodePOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public VoucherExchangeCodePOExample() {
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

        public Criteria andBatchIdIsNull() {
            addCriterion("batch_id is null");
            return (Criteria) this;
        }

        public Criteria andBatchIdIsNotNull() {
            addCriterion("batch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBatchIdEqualTo(Integer value) {
            addCriterion("batch_id =", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotEqualTo(Integer value) {
            addCriterion("batch_id <>", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThan(Integer value) {
            addCriterion("batch_id >", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("batch_id >=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThan(Integer value) {
            addCriterion("batch_id <", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdLessThanOrEqualTo(Integer value) {
            addCriterion("batch_id <=", value, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdIn(List<Integer> values) {
            addCriterion("batch_id in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotIn(List<Integer> values) {
            addCriterion("batch_id not in", values, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdBetween(Integer value1, Integer value2) {
            addCriterion("batch_id between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchIdNotBetween(Integer value1, Integer value2) {
            addCriterion("batch_id not between", value1, value2, "batchId");
            return (Criteria) this;
        }

        public Criteria andBatchNameIsNull() {
            addCriterion("batch_name is null");
            return (Criteria) this;
        }

        public Criteria andBatchNameIsNotNull() {
            addCriterion("batch_name is not null");
            return (Criteria) this;
        }

        public Criteria andBatchNameEqualTo(String value) {
            addCriterion("batch_name =", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotEqualTo(String value) {
            addCriterion("batch_name <>", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameGreaterThan(String value) {
            addCriterion("batch_name >", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameGreaterThanOrEqualTo(String value) {
            addCriterion("batch_name >=", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameLessThan(String value) {
            addCriterion("batch_name <", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameLessThanOrEqualTo(String value) {
            addCriterion("batch_name <=", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameLike(String value) {
            addCriterion("batch_name like", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotLike(String value) {
            addCriterion("batch_name not like", value, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameIn(List<String> values) {
            addCriterion("batch_name in", values, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotIn(List<String> values) {
            addCriterion("batch_name not in", values, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameBetween(String value1, String value2) {
            addCriterion("batch_name between", value1, value2, "batchName");
            return (Criteria) this;
        }

        public Criteria andBatchNameNotBetween(String value1, String value2) {
            addCriterion("batch_name not between", value1, value2, "batchName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameIsNull() {
            addCriterion("voucher_name is null");
            return (Criteria) this;
        }

        public Criteria andVoucherNameIsNotNull() {
            addCriterion("voucher_name is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherNameEqualTo(String value) {
            addCriterion("voucher_name =", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotEqualTo(String value) {
            addCriterion("voucher_name <>", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameGreaterThan(String value) {
            addCriterion("voucher_name >", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameGreaterThanOrEqualTo(String value) {
            addCriterion("voucher_name >=", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameLessThan(String value) {
            addCriterion("voucher_name <", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameLessThanOrEqualTo(String value) {
            addCriterion("voucher_name <=", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameLike(String value) {
            addCriterion("voucher_name like", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotLike(String value) {
            addCriterion("voucher_name not like", value, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameIn(List<String> values) {
            addCriterion("voucher_name in", values, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotIn(List<String> values) {
            addCriterion("voucher_name not in", values, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameBetween(String value1, String value2) {
            addCriterion("voucher_name between", value1, value2, "voucherName");
            return (Criteria) this;
        }

        public Criteria andVoucherNameNotBetween(String value1, String value2) {
            addCriterion("voucher_name not between", value1, value2, "voucherName");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionIsNull() {
            addCriterion("batch_description is null");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionIsNotNull() {
            addCriterion("batch_description is not null");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionEqualTo(String value) {
            addCriterion("batch_description =", value, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionNotEqualTo(String value) {
            addCriterion("batch_description <>", value, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionGreaterThan(String value) {
            addCriterion("batch_description >", value, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("batch_description >=", value, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionLessThan(String value) {
            addCriterion("batch_description <", value, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionLessThanOrEqualTo(String value) {
            addCriterion("batch_description <=", value, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionLike(String value) {
            addCriterion("batch_description like", value, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionNotLike(String value) {
            addCriterion("batch_description not like", value, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionIn(List<String> values) {
            addCriterion("batch_description in", values, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionNotIn(List<String> values) {
            addCriterion("batch_description not in", values, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionBetween(String value1, String value2) {
            addCriterion("batch_description between", value1, value2, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andBatchDescriptionNotBetween(String value1, String value2) {
            addCriterion("batch_description not between", value1, value2, "batchDescription");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(Integer value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(Integer value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(Integer value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(Integer value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<Integer> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<Integer> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(Integer value1, Integer value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andCityCodesIsNull() {
            addCriterion("city_codes is null");
            return (Criteria) this;
        }

        public Criteria andCityCodesIsNotNull() {
            addCriterion("city_codes is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodesEqualTo(String value) {
            addCriterion("city_codes =", value, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCityCodesNotEqualTo(String value) {
            addCriterion("city_codes <>", value, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCityCodesGreaterThan(String value) {
            addCriterion("city_codes >", value, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCityCodesGreaterThanOrEqualTo(String value) {
            addCriterion("city_codes >=", value, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCityCodesLessThan(String value) {
            addCriterion("city_codes <", value, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCityCodesLessThanOrEqualTo(String value) {
            addCriterion("city_codes <=", value, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCityCodesLike(String value) {
            addCriterion("city_codes like", value, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCityCodesNotLike(String value) {
            addCriterion("city_codes not like", value, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCityCodesIn(List<String> values) {
            addCriterion("city_codes in", values, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCityCodesNotIn(List<String> values) {
            addCriterion("city_codes not in", values, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCityCodesBetween(String value1, String value2) {
            addCriterion("city_codes between", value1, value2, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCityCodesNotBetween(String value1, String value2) {
            addCriterion("city_codes not between", value1, value2, "cityCodes");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andKeytIsNull() {
            addCriterion("keyt is null");
            return (Criteria) this;
        }

        public Criteria andKeytIsNotNull() {
            addCriterion("keyt is not null");
            return (Criteria) this;
        }

        public Criteria andKeytEqualTo(String value) {
            addCriterion("keyt =", value, "keyt");
            return (Criteria) this;
        }

        public Criteria andKeytNotEqualTo(String value) {
            addCriterion("keyt <>", value, "keyt");
            return (Criteria) this;
        }

        public Criteria andKeytGreaterThan(String value) {
            addCriterion("keyt >", value, "keyt");
            return (Criteria) this;
        }

        public Criteria andKeytGreaterThanOrEqualTo(String value) {
            addCriterion("keyt >=", value, "keyt");
            return (Criteria) this;
        }

        public Criteria andKeytLessThan(String value) {
            addCriterion("keyt <", value, "keyt");
            return (Criteria) this;
        }

        public Criteria andKeytLessThanOrEqualTo(String value) {
            addCriterion("keyt <=", value, "keyt");
            return (Criteria) this;
        }

        public Criteria andKeytLike(String value) {
            addCriterion("keyt like", value, "keyt");
            return (Criteria) this;
        }

        public Criteria andKeytNotLike(String value) {
            addCriterion("keyt not like", value, "keyt");
            return (Criteria) this;
        }

        public Criteria andKeytIn(List<String> values) {
            addCriterion("keyt in", values, "keyt");
            return (Criteria) this;
        }

        public Criteria andKeytNotIn(List<String> values) {
            addCriterion("keyt not in", values, "keyt");
            return (Criteria) this;
        }

        public Criteria andKeytBetween(String value1, String value2) {
            addCriterion("keyt between", value1, value2, "keyt");
            return (Criteria) this;
        }

        public Criteria andKeytNotBetween(String value1, String value2) {
            addCriterion("keyt not between", value1, value2, "keyt");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeIsNull() {
            addCriterion("expiry_time is null");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeIsNotNull() {
            addCriterion("expiry_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeEqualTo(Date value) {
            addCriterion("expiry_time =", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeNotEqualTo(Date value) {
            addCriterion("expiry_time <>", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeGreaterThan(Date value) {
            addCriterion("expiry_time >", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expiry_time >=", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeLessThan(Date value) {
            addCriterion("expiry_time <", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeLessThanOrEqualTo(Date value) {
            addCriterion("expiry_time <=", value, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeIn(List<Date> values) {
            addCriterion("expiry_time in", values, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeNotIn(List<Date> values) {
            addCriterion("expiry_time not in", values, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeBetween(Date value1, Date value2) {
            addCriterion("expiry_time between", value1, value2, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andExpiryTimeNotBetween(Date value1, Date value2) {
            addCriterion("expiry_time not between", value1, value2, "expiryTime");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeIsNull() {
            addCriterion("voucher_expiry_time is null");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeIsNotNull() {
            addCriterion("voucher_expiry_time is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeEqualTo(Date value) {
            addCriterion("voucher_expiry_time =", value, "voucherExpiryTime");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeNotEqualTo(Date value) {
            addCriterion("voucher_expiry_time <>", value, "voucherExpiryTime");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeGreaterThan(Date value) {
            addCriterion("voucher_expiry_time >", value, "voucherExpiryTime");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("voucher_expiry_time >=", value, "voucherExpiryTime");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeLessThan(Date value) {
            addCriterion("voucher_expiry_time <", value, "voucherExpiryTime");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeLessThanOrEqualTo(Date value) {
            addCriterion("voucher_expiry_time <=", value, "voucherExpiryTime");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeIn(List<Date> values) {
            addCriterion("voucher_expiry_time in", values, "voucherExpiryTime");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeNotIn(List<Date> values) {
            addCriterion("voucher_expiry_time not in", values, "voucherExpiryTime");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeBetween(Date value1, Date value2) {
            addCriterion("voucher_expiry_time between", value1, value2, "voucherExpiryTime");
            return (Criteria) this;
        }

        public Criteria andVoucherExpiryTimeNotBetween(Date value1, Date value2) {
            addCriterion("voucher_expiry_time not between", value1, value2, "voucherExpiryTime");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodIsNull() {
            addCriterion("validity_period is null");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodIsNotNull() {
            addCriterion("validity_period is not null");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodEqualTo(Integer value) {
            addCriterion("validity_period =", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodNotEqualTo(Integer value) {
            addCriterion("validity_period <>", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodGreaterThan(Integer value) {
            addCriterion("validity_period >", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("validity_period >=", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodLessThan(Integer value) {
            addCriterion("validity_period <", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("validity_period <=", value, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodIn(List<Integer> values) {
            addCriterion("validity_period in", values, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodNotIn(List<Integer> values) {
            addCriterion("validity_period not in", values, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodBetween(Integer value1, Integer value2) {
            addCriterion("validity_period between", value1, value2, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andValidityPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("validity_period not between", value1, value2, "validityPeriod");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeIsNull() {
            addCriterion("expiry_type is null");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeIsNotNull() {
            addCriterion("expiry_type is not null");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeEqualTo(Byte value) {
            addCriterion("expiry_type =", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeNotEqualTo(Byte value) {
            addCriterion("expiry_type <>", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeGreaterThan(Byte value) {
            addCriterion("expiry_type >", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("expiry_type >=", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeLessThan(Byte value) {
            addCriterion("expiry_type <", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeLessThanOrEqualTo(Byte value) {
            addCriterion("expiry_type <=", value, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeIn(List<Byte> values) {
            addCriterion("expiry_type in", values, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeNotIn(List<Byte> values) {
            addCriterion("expiry_type not in", values, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeBetween(Byte value1, Byte value2) {
            addCriterion("expiry_type between", value1, value2, "expiryType");
            return (Criteria) this;
        }

        public Criteria andExpiryTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("expiry_type not between", value1, value2, "expiryType");
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

        public Criteria andUsedIsNull() {
            addCriterion("used is null");
            return (Criteria) this;
        }

        public Criteria andUsedIsNotNull() {
            addCriterion("used is not null");
            return (Criteria) this;
        }

        public Criteria andUsedEqualTo(Byte value) {
            addCriterion("used =", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedNotEqualTo(Byte value) {
            addCriterion("used <>", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedGreaterThan(Byte value) {
            addCriterion("used >", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedGreaterThanOrEqualTo(Byte value) {
            addCriterion("used >=", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedLessThan(Byte value) {
            addCriterion("used <", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedLessThanOrEqualTo(Byte value) {
            addCriterion("used <=", value, "used");
            return (Criteria) this;
        }

        public Criteria andUsedIn(List<Byte> values) {
            addCriterion("used in", values, "used");
            return (Criteria) this;
        }

        public Criteria andUsedNotIn(List<Byte> values) {
            addCriterion("used not in", values, "used");
            return (Criteria) this;
        }

        public Criteria andUsedBetween(Byte value1, Byte value2) {
            addCriterion("used between", value1, value2, "used");
            return (Criteria) this;
        }

        public Criteria andUsedNotBetween(Byte value1, Byte value2) {
            addCriterion("used not between", value1, value2, "used");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("customer_id is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("customer_id is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Integer value) {
            addCriterion("customer_id =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Integer value) {
            addCriterion("customer_id <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Integer value) {
            addCriterion("customer_id >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("customer_id >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Integer value) {
            addCriterion("customer_id <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
            addCriterion("customer_id <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Integer> values) {
            addCriterion("customer_id in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Integer> values) {
            addCriterion("customer_id not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
            addCriterion("customer_id between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("customer_id not between", value1, value2, "customerId");
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