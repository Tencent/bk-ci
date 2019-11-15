/*
 * This file is generated by jOOQ.
*/
package com.tencent.devops.model.notify.tables.records;


import com.tencent.devops.model.notify.tables.TBaseNotifyMessageTemplate;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * 通知模板表
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TBaseNotifyMessageTemplateRecord extends UpdatableRecordImpl<TBaseNotifyMessageTemplateRecord> implements Record12<String, String, String, String, String, String, Byte, Byte, String, String, LocalDateTime, LocalDateTime> {

    private static final long serialVersionUID = 360965683;

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.ID</code>. 主键
     */
    public TBaseNotifyMessageTemplateRecord setId(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.ID</code>. 主键
     */
    public String getId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.TEMPLATE_CODE</code>. 模板代码
     */
    public TBaseNotifyMessageTemplateRecord setTemplateCode(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.TEMPLATE_CODE</code>. 模板代码
     */
    public String getTemplateCode() {
        return (String) get(1);
    }

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.TEMPLATE_NAME</code>. 模板名称
     */
    public TBaseNotifyMessageTemplateRecord setTemplateName(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.TEMPLATE_NAME</code>. 模板名称
     */
    public String getTemplateName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.NOTIFY_TYPE_SCOPE</code>. 适用的通知类型（EMAIL:邮件 RTX:企业微信 WECHAT:微信 SMS:短信）
     */
    public TBaseNotifyMessageTemplateRecord setNotifyTypeScope(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.NOTIFY_TYPE_SCOPE</code>. 适用的通知类型（EMAIL:邮件 RTX:企业微信 WECHAT:微信 SMS:短信）
     */
    public String getNotifyTypeScope() {
        return (String) get(3);
    }

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.TITLE</code>. 标题
     */
    public TBaseNotifyMessageTemplateRecord setTitle(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.TITLE</code>. 标题
     */
    public String getTitle() {
        return (String) get(4);
    }

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.BODY</code>. 内容
     */
    public TBaseNotifyMessageTemplateRecord setBody(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.BODY</code>. 内容
     */
    public String getBody() {
        return (String) get(5);
    }

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.PRIORITY</code>. 优先级别（-1:低 0:普通 1:高）
     */
    public TBaseNotifyMessageTemplateRecord setPriority(Byte value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.PRIORITY</code>. 优先级别（-1:低 0:普通 1:高）
     */
    public Byte getPriority() {
        return (Byte) get(6);
    }

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.SOURCE</code>. 通知来源（0:本地业务 1:操作）
     */
    public TBaseNotifyMessageTemplateRecord setSource(Byte value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.SOURCE</code>. 通知来源（0:本地业务 1:操作）
     */
    public Byte getSource() {
        return (Byte) get(7);
    }

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.CREATOR</code>. 创建人
     */
    public TBaseNotifyMessageTemplateRecord setCreator(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.CREATOR</code>. 创建人
     */
    public String getCreator() {
        return (String) get(8);
    }

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.MODIFIER</code>. 最近修改人
     */
    public TBaseNotifyMessageTemplateRecord setModifier(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.MODIFIER</code>. 最近修改人
     */
    public String getModifier() {
        return (String) get(9);
    }

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.CREATE_TIME</code>. 创建时间
     */
    public TBaseNotifyMessageTemplateRecord setCreateTime(LocalDateTime value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.CREATE_TIME</code>. 创建时间
     */
    public LocalDateTime getCreateTime() {
        return (LocalDateTime) get(10);
    }

    /**
     * Setter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.UPDATE_TIME</code>. 更新时间
     */
    public TBaseNotifyMessageTemplateRecord setUpdateTime(LocalDateTime value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>devops_notify.T_BASE_NOTIFY_MESSAGE_TEMPLATE.UPDATE_TIME</code>. 更新时间
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
    public Row12<String, String, String, String, String, String, Byte, Byte, String, String, LocalDateTime, LocalDateTime> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row12<String, String, String, String, String, String, Byte, Byte, String, String, LocalDateTime, LocalDateTime> valuesRow() {
        return (Row12) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.TEMPLATE_CODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.TEMPLATE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.NOTIFY_TYPE_SCOPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.BODY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Byte> field7() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.PRIORITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Byte> field8() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.SOURCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field9() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.CREATOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.MODIFIER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field11() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.CREATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field12() {
        return TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE.UPDATE_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getTemplateCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getTemplateName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getNotifyTypeScope();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getBody();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte value7() {
        return getPriority();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Byte value8() {
        return getSource();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value9() {
        return getCreator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getModifier();
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
    public TBaseNotifyMessageTemplateRecord value1(String value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord value2(String value) {
        setTemplateCode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord value3(String value) {
        setTemplateName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord value4(String value) {
        setNotifyTypeScope(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord value5(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord value6(String value) {
        setBody(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord value7(Byte value) {
        setPriority(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord value8(Byte value) {
        setSource(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord value9(String value) {
        setCreator(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord value10(String value) {
        setModifier(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord value11(LocalDateTime value) {
        setCreateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord value12(LocalDateTime value) {
        setUpdateTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TBaseNotifyMessageTemplateRecord values(String value1, String value2, String value3, String value4, String value5, String value6, Byte value7, Byte value8, String value9, String value10, LocalDateTime value11, LocalDateTime value12) {
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
     * Create a detached TBaseNotifyMessageTemplateRecord
     */
    public TBaseNotifyMessageTemplateRecord() {
        super(TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE);
    }

    /**
     * Create a detached, initialised TBaseNotifyMessageTemplateRecord
     */
    public TBaseNotifyMessageTemplateRecord(String id, String templateCode, String templateName, String notifyTypeScope, String title, String body, Byte priority, Byte source, String creator, String modifier, LocalDateTime createTime, LocalDateTime updateTime) {
        super(TBaseNotifyMessageTemplate.T_BASE_NOTIFY_MESSAGE_TEMPLATE);

        set(0, id);
        set(1, templateCode);
        set(2, templateName);
        set(3, notifyTypeScope);
        set(4, title);
        set(5, body);
        set(6, priority);
        set(7, source);
        set(8, creator);
        set(9, modifier);
        set(10, createTime);
        set(11, updateTime);
    }
}
