/*
 * This file is generated by jOOQ.
*/
package com.tencent.devops.model.notify.tables;


import com.tencent.devops.model.notify.DevopsNotify;
import com.tencent.devops.model.notify.Keys;
import com.tencent.devops.model.notify.tables.records.TNotifyRtxRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TNotifyRtx extends TableImpl<TNotifyRtxRecord> {

    private static final long serialVersionUID = -2123103637;

    /**
     * The reference instance of <code>devops_notify.T_NOTIFY_RTX</code>
     */
    public static final TNotifyRtx T_NOTIFY_RTX = new TNotifyRtx();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TNotifyRtxRecord> getRecordType() {
        return TNotifyRtxRecord.class;
    }

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.ID</code>. RTX通知ID
     */
    public final TableField<TNotifyRtxRecord, String> ID = createField("ID", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "RTX通知ID");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.BATCH_ID</code>. RTX通知批次ID
     */
    public final TableField<TNotifyRtxRecord, String> BATCH_ID = createField("BATCH_ID", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "RTX通知批次ID");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.SUCCESS</code>. 是否成功
     */
    public final TableField<TNotifyRtxRecord, Boolean> SUCCESS = createField("SUCCESS", org.jooq.impl.SQLDataType.BIT.nullable(false), this, "是否成功");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.SOURCE</code>. 通知来源
     */
    public final TableField<TNotifyRtxRecord, String> SOURCE = createField("SOURCE", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "通知来源");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.SENDER</code>. 通知发送者
     */
    public final TableField<TNotifyRtxRecord, String> SENDER = createField("SENDER", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "通知发送者");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.RECEIVERS</code>. 通知接收者
     */
    public final TableField<TNotifyRtxRecord, String> RECEIVERS = createField("RECEIVERS", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "通知接收者");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.TITLE</code>. RTX通知标题
     */
    public final TableField<TNotifyRtxRecord, String> TITLE = createField("TITLE", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "RTX通知标题");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.BODY</code>. RTX通知内容
     */
    public final TableField<TNotifyRtxRecord, String> BODY = createField("BODY", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "RTX通知内容");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.PRIORITY</code>. 优先级
     */
    public final TableField<TNotifyRtxRecord, Integer> PRIORITY = createField("PRIORITY", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "优先级");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.RETRY_COUNT</code>. 重试次数
     */
    public final TableField<TNotifyRtxRecord, Integer> RETRY_COUNT = createField("RETRY_COUNT", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "重试次数");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.LAST_ERROR</code>. 最后错误内容
     */
    public final TableField<TNotifyRtxRecord, String> LAST_ERROR = createField("LAST_ERROR", org.jooq.impl.SQLDataType.CLOB, this, "最后错误内容");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.CREATED_TIME</code>. 记录创建时间
     */
    public final TableField<TNotifyRtxRecord, LocalDateTime> CREATED_TIME = createField("CREATED_TIME", org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "记录创建时间");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.UPDATED_TIME</code>. 记录最后更新时间
     */
    public final TableField<TNotifyRtxRecord, LocalDateTime> UPDATED_TIME = createField("UPDATED_TIME", org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "记录最后更新时间");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.CONTENT_MD5</code>. 内容md5值，由title和body计算得，频率限制时使用
     */
    public final TableField<TNotifyRtxRecord, String> CONTENT_MD5 = createField("CONTENT_MD5", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "内容md5值，由title和body计算得，频率限制时使用");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.FREQUENCY_LIMIT</code>. 频率限制时长，单位分钟，即n分钟内不重发成功的消息
     */
    public final TableField<TNotifyRtxRecord, Integer> FREQUENCY_LIMIT = createField("FREQUENCY_LIMIT", org.jooq.impl.SQLDataType.INTEGER, this, "频率限制时长，单位分钟，即n分钟内不重发成功的消息");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.TOF_SYS_id</code>.
     */
    public final TableField<TNotifyRtxRecord, String> TOF_SYS_ID = createField("TOF_SYS_id", org.jooq.impl.SQLDataType.VARCHAR.length(20), this, "");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.FROM_SYS_ID</code>.
     */
    public final TableField<TNotifyRtxRecord, String> FROM_SYS_ID = createField("FROM_SYS_ID", org.jooq.impl.SQLDataType.VARCHAR.length(20), this, "");

    /**
     * The column <code>devops_notify.T_NOTIFY_RTX.DelaySeconds</code>. 延迟发送的时间，秒
     */
    public final TableField<TNotifyRtxRecord, Integer> DELAYSECONDS = createField("DelaySeconds", org.jooq.impl.SQLDataType.INTEGER, this, "延迟发送的时间，秒");

    /**
     * Create a <code>devops_notify.T_NOTIFY_RTX</code> table reference
     */
    public TNotifyRtx() {
        this("T_NOTIFY_RTX", null);
    }

    /**
     * Create an aliased <code>devops_notify.T_NOTIFY_RTX</code> table reference
     */
    public TNotifyRtx(String alias) {
        this(alias, T_NOTIFY_RTX);
    }

    private TNotifyRtx(String alias, Table<TNotifyRtxRecord> aliased) {
        this(alias, aliased, null);
    }

    private TNotifyRtx(String alias, Table<TNotifyRtxRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DevopsNotify.DEVOPS_NOTIFY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TNotifyRtxRecord> getPrimaryKey() {
        return Keys.KEY_T_NOTIFY_RTX_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TNotifyRtxRecord>> getKeys() {
        return Arrays.<UniqueKey<TNotifyRtxRecord>>asList(Keys.KEY_T_NOTIFY_RTX_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TNotifyRtx as(String alias) {
        return new TNotifyRtx(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TNotifyRtx rename(String name) {
        return new TNotifyRtx(name, null);
    }
}
