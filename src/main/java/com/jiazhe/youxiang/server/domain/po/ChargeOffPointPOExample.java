package com.jiazhe.youxiang.server.domain.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeOffPointPOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ChargeOffPointPOExample() {
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

        public Criteria andChargeOffIdIsNull() {
            addCriterion("charge_off_id is null");
            return (Criteria) this;
        }

        public Criteria andChargeOffIdIsNotNull() {
            addCriterion("charge_off_id is not null");
            return (Criteria) this;
        }

        public Criteria andChargeOffIdEqualTo(Integer value) {
            addCriterion("charge_off_id =", value, "chargeOffId");
            return (Criteria) this;
        }

        public Criteria andChargeOffIdNotEqualTo(Integer value) {
            addCriterion("charge_off_id <>", value, "chargeOffId");
            return (Criteria) this;
        }

        public Criteria andChargeOffIdGreaterThan(Integer value) {
            addCriterion("charge_off_id >", value, "chargeOffId");
            return (Criteria) this;
        }

        public Criteria andChargeOffIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("charge_off_id >=", value, "chargeOffId");
            return (Criteria) this;
        }

        public Criteria andChargeOffIdLessThan(Integer value) {
            addCriterion("charge_off_id <", value, "chargeOffId");
            return (Criteria) this;
        }

        public Criteria andChargeOffIdLessThanOrEqualTo(Integer value) {
            addCriterion("charge_off_id <=", value, "chargeOffId");
            return (Criteria) this;
        }

        public Criteria andChargeOffIdIn(List<Integer> values) {
            addCriterion("charge_off_id in", values, "chargeOffId");
            return (Criteria) this;
        }

        public Criteria andChargeOffIdNotIn(List<Integer> values) {
            addCriterion("charge_off_id not in", values, "chargeOffId");
            return (Criteria) this;
        }

        public Criteria andChargeOffIdBetween(Integer value1, Integer value2) {
            addCriterion("charge_off_id between", value1, value2, "chargeOffId");
            return (Criteria) this;
        }

        public Criteria andChargeOffIdNotBetween(Integer value1, Integer value2) {
            addCriterion("charge_off_id not between", value1, value2, "chargeOffId");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdIsNull() {
            addCriterion("point_exchange_code_id is null");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdIsNotNull() {
            addCriterion("point_exchange_code_id is not null");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdEqualTo(Integer value) {
            addCriterion("point_exchange_code_id =", value, "pointExchangeCodeId");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdNotEqualTo(Integer value) {
            addCriterion("point_exchange_code_id <>", value, "pointExchangeCodeId");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdGreaterThan(Integer value) {
            addCriterion("point_exchange_code_id >", value, "pointExchangeCodeId");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("point_exchange_code_id >=", value, "pointExchangeCodeId");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdLessThan(Integer value) {
            addCriterion("point_exchange_code_id <", value, "pointExchangeCodeId");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdLessThanOrEqualTo(Integer value) {
            addCriterion("point_exchange_code_id <=", value, "pointExchangeCodeId");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdIn(List<Integer> values) {
            addCriterion("point_exchange_code_id in", values, "pointExchangeCodeId");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdNotIn(List<Integer> values) {
            addCriterion("point_exchange_code_id not in", values, "pointExchangeCodeId");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdBetween(Integer value1, Integer value2) {
            addCriterion("point_exchange_code_id between", value1, value2, "pointExchangeCodeId");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("point_exchange_code_id not between", value1, value2, "pointExchangeCodeId");
            return (Criteria) this;
        }

        public Criteria andPointNameIsNull() {
            addCriterion("point_name is null");
            return (Criteria) this;
        }

        public Criteria andPointNameIsNotNull() {
            addCriterion("point_name is not null");
            return (Criteria) this;
        }

        public Criteria andPointNameEqualTo(String value) {
            addCriterion("point_name =", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotEqualTo(String value) {
            addCriterion("point_name <>", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameGreaterThan(String value) {
            addCriterion("point_name >", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameGreaterThanOrEqualTo(String value) {
            addCriterion("point_name >=", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameLessThan(String value) {
            addCriterion("point_name <", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameLessThanOrEqualTo(String value) {
            addCriterion("point_name <=", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameLike(String value) {
            addCriterion("point_name like", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotLike(String value) {
            addCriterion("point_name not like", value, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameIn(List<String> values) {
            addCriterion("point_name in", values, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotIn(List<String> values) {
            addCriterion("point_name not in", values, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameBetween(String value1, String value2) {
            addCriterion("point_name between", value1, value2, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointNameNotBetween(String value1, String value2) {
            addCriterion("point_name not between", value1, value2, "pointName");
            return (Criteria) this;
        }

        public Criteria andPointValueIsNull() {
            addCriterion("point_value is null");
            return (Criteria) this;
        }

        public Criteria andPointValueIsNotNull() {
            addCriterion("point_value is not null");
            return (Criteria) this;
        }

        public Criteria andPointValueEqualTo(BigDecimal value) {
            addCriterion("point_value =", value, "pointValue");
            return (Criteria) this;
        }

        public Criteria andPointValueNotEqualTo(BigDecimal value) {
            addCriterion("point_value <>", value, "pointValue");
            return (Criteria) this;
        }

        public Criteria andPointValueGreaterThan(BigDecimal value) {
            addCriterion("point_value >", value, "pointValue");
            return (Criteria) this;
        }

        public Criteria andPointValueGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("point_value >=", value, "pointValue");
            return (Criteria) this;
        }

        public Criteria andPointValueLessThan(BigDecimal value) {
            addCriterion("point_value <", value, "pointValue");
            return (Criteria) this;
        }

        public Criteria andPointValueLessThanOrEqualTo(BigDecimal value) {
            addCriterion("point_value <=", value, "pointValue");
            return (Criteria) this;
        }

        public Criteria andPointValueIn(List<BigDecimal> values) {
            addCriterion("point_value in", values, "pointValue");
            return (Criteria) this;
        }

        public Criteria andPointValueNotIn(List<BigDecimal> values) {
            addCriterion("point_value not in", values, "pointValue");
            return (Criteria) this;
        }

        public Criteria andPointValueBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("point_value between", value1, value2, "pointValue");
            return (Criteria) this;
        }

        public Criteria andPointValueNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("point_value not between", value1, value2, "pointValue");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeIsNull() {
            addCriterion("point_exchange_code_code is null");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeIsNotNull() {
            addCriterion("point_exchange_code_code is not null");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeEqualTo(String value) {
            addCriterion("point_exchange_code_code =", value, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeNotEqualTo(String value) {
            addCriterion("point_exchange_code_code <>", value, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeGreaterThan(String value) {
            addCriterion("point_exchange_code_code >", value, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("point_exchange_code_code >=", value, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeLessThan(String value) {
            addCriterion("point_exchange_code_code <", value, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeLessThanOrEqualTo(String value) {
            addCriterion("point_exchange_code_code <=", value, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeLike(String value) {
            addCriterion("point_exchange_code_code like", value, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeNotLike(String value) {
            addCriterion("point_exchange_code_code not like", value, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeIn(List<String> values) {
            addCriterion("point_exchange_code_code in", values, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeNotIn(List<String> values) {
            addCriterion("point_exchange_code_code not in", values, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeBetween(String value1, String value2) {
            addCriterion("point_exchange_code_code between", value1, value2, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeCodeNotBetween(String value1, String value2) {
            addCriterion("point_exchange_code_code not between", value1, value2, "pointExchangeCodeCode");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytIsNull() {
            addCriterion("point_exchange_code_keyt is null");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytIsNotNull() {
            addCriterion("point_exchange_code_keyt is not null");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytEqualTo(String value) {
            addCriterion("point_exchange_code_keyt =", value, "pointExchangeCodeKeyt");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytNotEqualTo(String value) {
            addCriterion("point_exchange_code_keyt <>", value, "pointExchangeCodeKeyt");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytGreaterThan(String value) {
            addCriterion("point_exchange_code_keyt >", value, "pointExchangeCodeKeyt");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytGreaterThanOrEqualTo(String value) {
            addCriterion("point_exchange_code_keyt >=", value, "pointExchangeCodeKeyt");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytLessThan(String value) {
            addCriterion("point_exchange_code_keyt <", value, "pointExchangeCodeKeyt");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytLessThanOrEqualTo(String value) {
            addCriterion("point_exchange_code_keyt <=", value, "pointExchangeCodeKeyt");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytLike(String value) {
            addCriterion("point_exchange_code_keyt like", value, "pointExchangeCodeKeyt");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytNotLike(String value) {
            addCriterion("point_exchange_code_keyt not like", value, "pointExchangeCodeKeyt");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytIn(List<String> values) {
            addCriterion("point_exchange_code_keyt in", values, "pointExchangeCodeKeyt");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytNotIn(List<String> values) {
            addCriterion("point_exchange_code_keyt not in", values, "pointExchangeCodeKeyt");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytBetween(String value1, String value2) {
            addCriterion("point_exchange_code_keyt between", value1, value2, "pointExchangeCodeKeyt");
            return (Criteria) this;
        }

        public Criteria andPointExchangeCodeKeytNotBetween(String value1, String value2) {
            addCriterion("point_exchange_code_keyt not between", value1, value2, "pointExchangeCodeKeyt");
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