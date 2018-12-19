package com.jiazhe.youxiang.server.domain.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartnerOrderInfoPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PartnerOrderInfoPOExample() {
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

        public Criteria andCustomerAddressIsNull() {
            addCriterion("customer_address is null");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIsNotNull() {
            addCriterion("customer_address is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressEqualTo(String value) {
            addCriterion("customer_address =", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotEqualTo(String value) {
            addCriterion("customer_address <>", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressGreaterThan(String value) {
            addCriterion("customer_address >", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressGreaterThanOrEqualTo(String value) {
            addCriterion("customer_address >=", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLessThan(String value) {
            addCriterion("customer_address <", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLessThanOrEqualTo(String value) {
            addCriterion("customer_address <=", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressLike(String value) {
            addCriterion("customer_address like", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotLike(String value) {
            addCriterion("customer_address not like", value, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressIn(List<String> values) {
            addCriterion("customer_address in", values, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotIn(List<String> values) {
            addCriterion("customer_address not in", values, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressBetween(String value1, String value2) {
            addCriterion("customer_address between", value1, value2, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerAddressNotBetween(String value1, String value2) {
            addCriterion("customer_address not between", value1, value2, "customerAddress");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeIsNull() {
            addCriterion("customer_city_code is null");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeIsNotNull() {
            addCriterion("customer_city_code is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeEqualTo(String value) {
            addCriterion("customer_city_code =", value, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeNotEqualTo(String value) {
            addCriterion("customer_city_code <>", value, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeGreaterThan(String value) {
            addCriterion("customer_city_code >", value, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("customer_city_code >=", value, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeLessThan(String value) {
            addCriterion("customer_city_code <", value, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeLessThanOrEqualTo(String value) {
            addCriterion("customer_city_code <=", value, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeLike(String value) {
            addCriterion("customer_city_code like", value, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeNotLike(String value) {
            addCriterion("customer_city_code not like", value, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeIn(List<String> values) {
            addCriterion("customer_city_code in", values, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeNotIn(List<String> values) {
            addCriterion("customer_city_code not in", values, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeBetween(String value1, String value2) {
            addCriterion("customer_city_code between", value1, value2, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityCodeNotBetween(String value1, String value2) {
            addCriterion("customer_city_code not between", value1, value2, "customerCityCode");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameIsNull() {
            addCriterion("customer_city_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameIsNotNull() {
            addCriterion("customer_city_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameEqualTo(String value) {
            addCriterion("customer_city_name =", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameNotEqualTo(String value) {
            addCriterion("customer_city_name <>", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameGreaterThan(String value) {
            addCriterion("customer_city_name >", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_city_name >=", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameLessThan(String value) {
            addCriterion("customer_city_name <", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameLessThanOrEqualTo(String value) {
            addCriterion("customer_city_name <=", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameLike(String value) {
            addCriterion("customer_city_name like", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameNotLike(String value) {
            addCriterion("customer_city_name not like", value, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameIn(List<String> values) {
            addCriterion("customer_city_name in", values, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameNotIn(List<String> values) {
            addCriterion("customer_city_name not in", values, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameBetween(String value1, String value2) {
            addCriterion("customer_city_name between", value1, value2, "customerCityName");
            return (Criteria) this;
        }

        public Criteria andCustomerCityNameNotBetween(String value1, String value2) {
            addCriterion("customer_city_name not between", value1, value2, "customerCityName");
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

        public Criteria andOrderTimeIsNull() {
            addCriterion("order_time is null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIsNotNull() {
            addCriterion("order_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTimeEqualTo(Date value) {
            addCriterion("order_time =", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotEqualTo(Date value) {
            addCriterion("order_time <>", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThan(Date value) {
            addCriterion("order_time >", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("order_time >=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThan(Date value) {
            addCriterion("order_time <", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeLessThanOrEqualTo(Date value) {
            addCriterion("order_time <=", value, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeIn(List<Date> values) {
            addCriterion("order_time in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotIn(List<Date> values) {
            addCriterion("order_time not in", values, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeBetween(Date value1, Date value2) {
            addCriterion("order_time between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andOrderTimeNotBetween(Date value1, Date value2) {
            addCriterion("order_time not between", value1, value2, "orderTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeIsNull() {
            addCriterion("service_time is null");
            return (Criteria) this;
        }

        public Criteria andServiceTimeIsNotNull() {
            addCriterion("service_time is not null");
            return (Criteria) this;
        }

        public Criteria andServiceTimeEqualTo(Date value) {
            addCriterion("service_time =", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeNotEqualTo(Date value) {
            addCriterion("service_time <>", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeGreaterThan(Date value) {
            addCriterion("service_time >", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("service_time >=", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeLessThan(Date value) {
            addCriterion("service_time <", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeLessThanOrEqualTo(Date value) {
            addCriterion("service_time <=", value, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeIn(List<Date> values) {
            addCriterion("service_time in", values, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeNotIn(List<Date> values) {
            addCriterion("service_time not in", values, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeBetween(Date value1, Date value2) {
            addCriterion("service_time between", value1, value2, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andServiceTimeNotBetween(Date value1, Date value2) {
            addCriterion("service_time not between", value1, value2, "serviceTime");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("product_type is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("product_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(String value) {
            addCriterion("product_type =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(String value) {
            addCriterion("product_type <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(String value) {
            addCriterion("product_type >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(String value) {
            addCriterion("product_type >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(String value) {
            addCriterion("product_type <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(String value) {
            addCriterion("product_type <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLike(String value) {
            addCriterion("product_type like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotLike(String value) {
            addCriterion("product_type not like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<String> values) {
            addCriterion("product_type in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<String> values) {
            addCriterion("product_type not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(String value1, String value2) {
            addCriterion("product_type between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(String value1, String value2) {
            addCriterion("product_type not between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andWorkerNameIsNull() {
            addCriterion("worker_name is null");
            return (Criteria) this;
        }

        public Criteria andWorkerNameIsNotNull() {
            addCriterion("worker_name is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerNameEqualTo(String value) {
            addCriterion("worker_name =", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameNotEqualTo(String value) {
            addCriterion("worker_name <>", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameGreaterThan(String value) {
            addCriterion("worker_name >", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameGreaterThanOrEqualTo(String value) {
            addCriterion("worker_name >=", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameLessThan(String value) {
            addCriterion("worker_name <", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameLessThanOrEqualTo(String value) {
            addCriterion("worker_name <=", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameLike(String value) {
            addCriterion("worker_name like", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameNotLike(String value) {
            addCriterion("worker_name not like", value, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameIn(List<String> values) {
            addCriterion("worker_name in", values, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameNotIn(List<String> values) {
            addCriterion("worker_name not in", values, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameBetween(String value1, String value2) {
            addCriterion("worker_name between", value1, value2, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerNameNotBetween(String value1, String value2) {
            addCriterion("worker_name not between", value1, value2, "workerName");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileIsNull() {
            addCriterion("worker_mobile is null");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileIsNotNull() {
            addCriterion("worker_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileEqualTo(String value) {
            addCriterion("worker_mobile =", value, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileNotEqualTo(String value) {
            addCriterion("worker_mobile <>", value, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileGreaterThan(String value) {
            addCriterion("worker_mobile >", value, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileGreaterThanOrEqualTo(String value) {
            addCriterion("worker_mobile >=", value, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileLessThan(String value) {
            addCriterion("worker_mobile <", value, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileLessThanOrEqualTo(String value) {
            addCriterion("worker_mobile <=", value, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileLike(String value) {
            addCriterion("worker_mobile like", value, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileNotLike(String value) {
            addCriterion("worker_mobile not like", value, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileIn(List<String> values) {
            addCriterion("worker_mobile in", values, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileNotIn(List<String> values) {
            addCriterion("worker_mobile not in", values, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileBetween(String value1, String value2) {
            addCriterion("worker_mobile between", value1, value2, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andWorkerMobileNotBetween(String value1, String value2) {
            addCriterion("worker_mobile not between", value1, value2, "workerMobile");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdIsNull() {
            addCriterion("service_item_id is null");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdIsNotNull() {
            addCriterion("service_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdEqualTo(Integer value) {
            addCriterion("service_item_id =", value, "serviceItemId");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdNotEqualTo(Integer value) {
            addCriterion("service_item_id <>", value, "serviceItemId");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdGreaterThan(Integer value) {
            addCriterion("service_item_id >", value, "serviceItemId");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("service_item_id >=", value, "serviceItemId");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdLessThan(Integer value) {
            addCriterion("service_item_id <", value, "serviceItemId");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdLessThanOrEqualTo(Integer value) {
            addCriterion("service_item_id <=", value, "serviceItemId");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdIn(List<Integer> values) {
            addCriterion("service_item_id in", values, "serviceItemId");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdNotIn(List<Integer> values) {
            addCriterion("service_item_id not in", values, "serviceItemId");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdBetween(Integer value1, Integer value2) {
            addCriterion("service_item_id between", value1, value2, "serviceItemId");
            return (Criteria) this;
        }

        public Criteria andServiceItemIdNotBetween(Integer value1, Integer value2) {
            addCriterion("service_item_id not between", value1, value2, "serviceItemId");
            return (Criteria) this;
        }

        public Criteria andPrePayIsNull() {
            addCriterion("pre_pay is null");
            return (Criteria) this;
        }

        public Criteria andPrePayIsNotNull() {
            addCriterion("pre_pay is not null");
            return (Criteria) this;
        }

        public Criteria andPrePayEqualTo(BigDecimal value) {
            addCriterion("pre_pay =", value, "prePay");
            return (Criteria) this;
        }

        public Criteria andPrePayNotEqualTo(BigDecimal value) {
            addCriterion("pre_pay <>", value, "prePay");
            return (Criteria) this;
        }

        public Criteria andPrePayGreaterThan(BigDecimal value) {
            addCriterion("pre_pay >", value, "prePay");
            return (Criteria) this;
        }

        public Criteria andPrePayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pre_pay >=", value, "prePay");
            return (Criteria) this;
        }

        public Criteria andPrePayLessThan(BigDecimal value) {
            addCriterion("pre_pay <", value, "prePay");
            return (Criteria) this;
        }

        public Criteria andPrePayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pre_pay <=", value, "prePay");
            return (Criteria) this;
        }

        public Criteria andPrePayIn(List<BigDecimal> values) {
            addCriterion("pre_pay in", values, "prePay");
            return (Criteria) this;
        }

        public Criteria andPrePayNotIn(List<BigDecimal> values) {
            addCriterion("pre_pay not in", values, "prePay");
            return (Criteria) this;
        }

        public Criteria andPrePayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pre_pay between", value1, value2, "prePay");
            return (Criteria) this;
        }

        public Criteria andPrePayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pre_pay not between", value1, value2, "prePay");
            return (Criteria) this;
        }

        public Criteria andAppendPayIsNull() {
            addCriterion("append_pay is null");
            return (Criteria) this;
        }

        public Criteria andAppendPayIsNotNull() {
            addCriterion("append_pay is not null");
            return (Criteria) this;
        }

        public Criteria andAppendPayEqualTo(BigDecimal value) {
            addCriterion("append_pay =", value, "appendPay");
            return (Criteria) this;
        }

        public Criteria andAppendPayNotEqualTo(BigDecimal value) {
            addCriterion("append_pay <>", value, "appendPay");
            return (Criteria) this;
        }

        public Criteria andAppendPayGreaterThan(BigDecimal value) {
            addCriterion("append_pay >", value, "appendPay");
            return (Criteria) this;
        }

        public Criteria andAppendPayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("append_pay >=", value, "appendPay");
            return (Criteria) this;
        }

        public Criteria andAppendPayLessThan(BigDecimal value) {
            addCriterion("append_pay <", value, "appendPay");
            return (Criteria) this;
        }

        public Criteria andAppendPayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("append_pay <=", value, "appendPay");
            return (Criteria) this;
        }

        public Criteria andAppendPayIn(List<BigDecimal> values) {
            addCriterion("append_pay in", values, "appendPay");
            return (Criteria) this;
        }

        public Criteria andAppendPayNotIn(List<BigDecimal> values) {
            addCriterion("append_pay not in", values, "appendPay");
            return (Criteria) this;
        }

        public Criteria andAppendPayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("append_pay between", value1, value2, "appendPay");
            return (Criteria) this;
        }

        public Criteria andAppendPayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("append_pay not between", value1, value2, "appendPay");
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

        public Criteria andPartnerIdIsNull() {
            addCriterion("partner_id is null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIsNotNull() {
            addCriterion("partner_id is not null");
            return (Criteria) this;
        }

        public Criteria andPartnerIdEqualTo(Integer value) {
            addCriterion("partner_id =", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotEqualTo(Integer value) {
            addCriterion("partner_id <>", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdGreaterThan(Integer value) {
            addCriterion("partner_id >", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("partner_id >=", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLessThan(Integer value) {
            addCriterion("partner_id <", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdLessThanOrEqualTo(Integer value) {
            addCriterion("partner_id <=", value, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdIn(List<Integer> values) {
            addCriterion("partner_id in", values, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotIn(List<Integer> values) {
            addCriterion("partner_id not in", values, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdBetween(Integer value1, Integer value2) {
            addCriterion("partner_id between", value1, value2, "partnerId");
            return (Criteria) this;
        }

        public Criteria andPartnerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("partner_id not between", value1, value2, "partnerId");
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