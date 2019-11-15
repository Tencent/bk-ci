/*
 * This file is generated by jOOQ.
*/
package com.tencent.devops.model.project.tables.records;


import com.tencent.devops.model.project.tables.TUser;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TUserRecord extends UpdatableRecordImpl<TUserRecord> implements Record12<String, String, Integer, String, Integer, String, Integer, String, Integer, String, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = -87090901;

    /**
     * Setter for <code>devops_project.T_USER.USER_ID</code>.
     */
    public TUserRecord setUserId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.USER_ID</code>.
     */
    public String getUserId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>devops_project.T_USER.NAME</code>.
     */
    public TUserRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.NAME</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>devops_project.T_USER.BG_ID</code>.
     */
    public TUserRecord setBgId(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.BG_ID</code>.
     */
    public Integer getBgId() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>devops_project.T_USER.BG_NAME</code>.
     */
    public TUserRecord setBgName(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.BG_NAME</code>.
     */
    public String getBgName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>devops_project.T_USER.DEPT_ID</code>.
     */
    public TUserRecord setDeptId(Integer value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.DEPT_ID</code>.
     */
    public Integer getDeptId() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>devops_project.T_USER.DEPT_NAME</code>.
     */
    public TUserRecord setDeptName(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.DEPT_NAME</code>.
     */
    public String getDeptName() {
        return (String) get(5);
    }

    /**
     * Setter for <code>devops_project.T_USER.CENTER_ID</code>.
     */
    public TUserRecord setCenterId(Integer value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.CENTER_ID</code>.
     */
    public Integer getCenterId() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>devops_project.T_USER.CENTER_NAME</code>.
     */
    public TUserRecord setCenterName(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.CENTER_NAME</code>.
     */
    public String getCenterName() {
        return (String) get(7);
    }

    /**
     * Setter for <code>devops_project.T_USER.GROYP_ID</code>.
     */
    public TUserRecord setGroypId(Integer value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.GROYP_ID</code>.
     */
    public Integer getGroypId() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>devops_project.T_USER.GROUP_NAME</code>.
     */
    public TUserRecord setGroupName(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.GROUP_NAME</code>.
     */
    public String getGroupName() {
        return (String) get(9);
    }

    /**
     * Setter for <code>devops_project.T_USER.CREATE_TIME</code>.
     */
    public TUserRecord setCreateTime(LocalDateTime value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.CREATE_TIME</code>.
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(10);
    }

    /**
     * Setter for <code>devops_project.T_USER.UPDATE_TIME</code>.
     */
    public TUserRecord setUpdateTime(LocalDateTime value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>devops_project.T_USER.UPDATE_TIME</code>.
     */
    public LocalDateTime getUpdateTime() {
        return (LocalDateTime) get(11);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record12 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row12<String, String, Integer, String, Integer, String, Integer, String, Integer, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row12<String, String, Integer, String, Integer, String, Integer, String, Integer, String, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row12) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return TUser.T_USER.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TUser.T_USER.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return TUser.T_USER.BG_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TUser.T_USER.BG_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return TUser.T_USER.DEPT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return TUser.T_USER.DEPT_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return TUser.T_USER.CENTER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return TUser.T_USER.CENTER_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field9() {
        return TUser.T_USER.GROYP_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return TUser.T_USER.GROUP_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field11() {
        return TUser.T_USER.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field12() {
        return TUser.T_USER.UPDATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getBgId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getBgName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getDeptId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getDeptName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getCenterId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getCenterName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value9() {
        return getGroypId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getGroupName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value11() {
        return getCreateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value12() {
        return getUpdateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value1(String value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value3(Integer value) {
        setBgId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value4(String value) {
        setBgName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value5(Integer value) {
        setDeptId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value6(String value) {
        setDeptName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value7(Integer value) {
        setCenterId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value8(String value) {
        setCenterName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value9(Integer value) {
        setGroypId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value10(String value) {
        setGroupName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value11(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord value12(LocalDateTime value) {
        setUpdateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TUserRecord values(String value1, String value2, Integer value3, String value4, Integer value5, String value6, Integer value7, String value8, Integer value9, String value10, LocalDateTime value11, LocalDateTime value12) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TUserRecord
     */
    public TUserRecord() {
        super(TUser.T_USER);
    }

    /**
     * Create a detached, initialised TUserRecord
     */
    public TUserRecord(String userId, String name, Integer bgId, String bgName, Integer deptId, String deptName, Integer centerId, String centerName, Integer groypId, String groupName, LocalDateTime createTime, LocalDateTime updateTime) {
        super(TUser.T_USER);

        set(0, userId);
        set(1, name);
        set(2, bgId);
        set(3, bgName);
        set(4, deptId);
        set(5, deptName);
        set(6, centerId);
        set(7, centerName);
        set(8, groypId);
        set(9, groupName);
        set(10, createTime);
        set(11, updateTime);
    }
}
