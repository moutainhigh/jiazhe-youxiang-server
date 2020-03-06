package com.jiazhe.youxiang.server.domain.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeOffPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChargeOffPOExample() {
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

        public Criteria andCityCodeIsNull() {
            addCriterion("city_code is null");
            return (Criteria) this;
        }

        public Criteria andCityCodeIsNotNull() {
            addCriterion("city_code is not null");
            return (Criteria) this;
        }

        public Criteria andCityCodeEqualTo(String value) {
            addCriterion("city_code =", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotEqualTo(String value) {
            addCriterion("city_code <>", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThan(String value) {
            addCriterion("city_code >", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("city_code >=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThan(String value) {
            addCriterion("city_code <", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLessThanOrEqualTo(String value) {
            addCriterion("city_code <=", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeLike(String value) {
            addCriterion("city_code like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotLike(String value) {
            addCriterion("city_code not like", value, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeIn(List<String> values) {
            addCriterion("city_code in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotIn(List<String> values) {
            addCriterion("city_code not in", values, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeBetween(String value1, String value2) {
            addCriterion("city_code between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityCodeNotBetween(String value1, String value2) {
            addCriterion("city_code not between", value1, value2, "cityCode");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNull() {
            addCriterion("city_name is null");
            return (Criteria) this;
        }

        public Criteria andCityNameIsNotNull() {
            addCriterion("city_name is not null");
            return (Criteria) this;
        }

        public Criteria andCityNameEqualTo(String value) {
            addCriterion("city_name =", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotEqualTo(String value) {
            addCriterion("city_name <>", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThan(String value) {
            addCriterion("city_name >", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("city_name >=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThan(String value) {
            addCriterion("city_name <", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLessThanOrEqualTo(String value) {
            addCriterion("city_name <=", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameLike(String value) {
            addCriterion("city_name like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotLike(String value) {
            addCriterion("city_name not like", value, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameIn(List<String> values) {
            addCriterion("city_name in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotIn(List<String> values) {
            addCriterion("city_name not in", values, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameBetween(String value1, String value2) {
            addCriterion("city_name between", value1, value2, "cityName");
            return (Criteria) this;
        }

        public Criteria andCityNameNotBetween(String value1, String value2) {
            addCriterion("city_name not between", value1, value2, "cityName");
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

        public Criteria andChargeOffTypeIsNull() {
            addCriterion("charge_off_type is null");
            return (Criteria) this;
        }

        public Criteria andChargeOffTypeIsNotNull() {
            addCriterion("charge_off_type is not null");
            return (Criteria) this;
        }

        public Criteria andChargeOffTypeEqualTo(Byte value) {
            addCriterion("charge_off_type =", value, "chargeOffType");
            return (Criteria) this;
        }

        public Criteria andChargeOffTypeNotEqualTo(Byte value) {
            addCriterion("charge_off_type <>", value, "chargeOffType");
            return (Criteria) this;
        }

        public Criteria andChargeOffTypeGreaterThan(Byte value) {
            addCriterion("charge_off_type >", value, "chargeOffType");
            return (Criteria) this;
        }

        public Criteria andChargeOffTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("charge_off_type >=", value, "chargeOffType");
            return (Criteria) this;
        }

        public Criteria andChargeOffTypeLessThan(Byte value) {
            addCriterion("charge_off_type <", value, "chargeOffType");
            return (Criteria) this;
        }

        public Criteria andChargeOffTypeLessThanOrEqualTo(Byte value) {
            addCriterion("charge_off_type <=", value, "chargeOffType");
            return (Criteria) this;
        }

        public Criteria andChargeOffTypeIn(List<Byte> values) {
            addCriterion("charge_off_type in", values, "chargeOffType");
            return (Criteria) this;
        }

        public Criteria andChargeOffTypeNotIn(List<Byte> values) {
            addCriterion("charge_off_type not in", values, "chargeOffType");
            return (Criteria) this;
        }

        public Criteria andChargeOffTypeBetween(Byte value1, Byte value2) {
            addCriterion("charge_off_type between", value1, value2, "chargeOffType");
            return (Criteria) this;
        }

        public Criteria andChargeOffTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("charge_off_type not between", value1, value2, "chargeOffType");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointIsNull() {
            addCriterion("charge_off_point is null");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointIsNotNull() {
            addCriterion("charge_off_point is not null");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointEqualTo(String value) {
            addCriterion("charge_off_point =", value, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointNotEqualTo(String value) {
            addCriterion("charge_off_point <>", value, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointGreaterThan(String value) {
            addCriterion("charge_off_point >", value, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointGreaterThanOrEqualTo(String value) {
            addCriterion("charge_off_point >=", value, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointLessThan(String value) {
            addCriterion("charge_off_point <", value, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointLessThanOrEqualTo(String value) {
            addCriterion("charge_off_point <=", value, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointLike(String value) {
            addCriterion("charge_off_point like", value, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointNotLike(String value) {
            addCriterion("charge_off_point not like", value, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointIn(List<String> values) {
            addCriterion("charge_off_point in", values, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointNotIn(List<String> values) {
            addCriterion("charge_off_point not in", values, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointBetween(String value1, String value2) {
            addCriterion("charge_off_point between", value1, value2, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andChargeOffPointNotBetween(String value1, String value2) {
            addCriterion("charge_off_point not between", value1, value2, "chargeOffPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointIsNull() {
            addCriterion("total_point is null");
            return (Criteria) this;
        }

        public Criteria andTotalPointIsNotNull() {
            addCriterion("total_point is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPointEqualTo(BigDecimal value) {
            addCriterion("total_point =", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointNotEqualTo(BigDecimal value) {
            addCriterion("total_point <>", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointGreaterThan(BigDecimal value) {
            addCriterion("total_point >", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_point >=", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointLessThan(BigDecimal value) {
            addCriterion("total_point <", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_point <=", value, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointIn(List<BigDecimal> values) {
            addCriterion("total_point in", values, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointNotIn(List<BigDecimal> values) {
            addCriterion("total_point not in", values, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_point between", value1, value2, "totalPoint");
            return (Criteria) this;
        }

        public Criteria andTotalPointNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_point not between", value1, value2, "totalPoint");
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

        public Criteria andSubmitterTimeIsNull() {
            addCriterion("submitter_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmitterTimeIsNotNull() {
            addCriterion("submitter_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitterTimeEqualTo(Date value) {
            addCriterion("submitter_time =", value, "submitterTime");
            return (Criteria) this;
        }

        public Criteria andSubmitterTimeNotEqualTo(Date value) {
            addCriterion("submitter_time <>", value, "submitterTime");
            return (Criteria) this;
        }

        public Criteria andSubmitterTimeGreaterThan(Date value) {
            addCriterion("submitter_time >", value, "submitterTime");
            return (Criteria) this;
        }

        public Criteria andSubmitterTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("submitter_time >=", value, "submitterTime");
            return (Criteria) this;
        }

        public Criteria andSubmitterTimeLessThan(Date value) {
            addCriterion("submitter_time <", value, "submitterTime");
            return (Criteria) this;
        }

        public Criteria andSubmitterTimeLessThanOrEqualTo(Date value) {
            addCriterion("submitter_time <=", value, "submitterTime");
            return (Criteria) this;
        }

        public Criteria andSubmitterTimeIn(List<Date> values) {
            addCriterion("submitter_time in", values, "submitterTime");
            return (Criteria) this;
        }

        public Criteria andSubmitterTimeNotIn(List<Date> values) {
            addCriterion("submitter_time not in", values, "submitterTime");
            return (Criteria) this;
        }

        public Criteria andSubmitterTimeBetween(Date value1, Date value2) {
            addCriterion("submitter_time between", value1, value2, "submitterTime");
            return (Criteria) this;
        }

        public Criteria andSubmitterTimeNotBetween(Date value1, Date value2) {
            addCriterion("submitter_time not between", value1, value2, "submitterTime");
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