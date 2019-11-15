/*
 * This file is generated by jOOQ.
*/
package com.tencent.devops.model.plugin.tables;


import com.tencent.devops.model.plugin.DevopsPlugin;
import com.tencent.devops.model.plugin.Keys;
import com.tencent.devops.model.plugin.tables.records.TPluginWetestInstResultRecord;

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
public class TPluginWetestInstResult extends TableImpl<TPluginWetestInstResultRecord> {

    private static final long serialVersionUID = -922271491;

    /**
     * The reference instance of <code>devops_plugin.T_PLUGIN_WETEST_INST_RESULT</code>
     */
    public static final TPluginWetestInstResult T_PLUGIN_WETEST_INST_RESULT = new TPluginWetestInstResult();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TPluginWetestInstResultRecord> getRecordType() {
        return TPluginWetestInstResultRecord.class;
    }

    /**
     * The column <code>devops_plugin.T_PLUGIN_WETEST_INST_RESULT.ID</code>.
     */
    public final TableField<TPluginWetestInstResultRecord, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>devops_plugin.T_PLUGIN_WETEST_INST_RESULT.TEST_ID</code>.
     */
    public final TableField<TPluginWetestInstResultRecord, String> TEST_ID = createField("TEST_ID", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

    /**
     * The column <code>devops_plugin.T_PLUGIN_WETEST_INST_RESULT.RESULT</code>.
     */
    public final TableField<TPluginWetestInstResultRecord, String> RESULT = createField("RESULT", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>devops_plugin.T_PLUGIN_WETEST_INST_RESULT.FINISH_TIME</code>.
     */
    public final TableField<TPluginWetestInstResultRecord, LocalDateTime> FINISH_TIME = createField("FINISH_TIME", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * Create a <code>devops_plugin.T_PLUGIN_WETEST_INST_RESULT</code> table reference
     */
    public TPluginWetestInstResult() {
        this("T_PLUGIN_WETEST_INST_RESULT", null);
    }

    /**
     * Create an aliased <code>devops_plugin.T_PLUGIN_WETEST_INST_RESULT</code> table reference
     */
    public TPluginWetestInstResult(String alias) {
        this(alias, T_PLUGIN_WETEST_INST_RESULT);
    }

    private TPluginWetestInstResult(String alias, Table<TPluginWetestInstResultRecord> aliased) {
        this(alias, aliased, null);
    }

    private TPluginWetestInstResult(String alias, Table<TPluginWetestInstResultRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DevopsPlugin.DEVOPS_PLUGIN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TPluginWetestInstResultRecord> getPrimaryKey() {
        return Keys.KEY_T_PLUGIN_WETEST_INST_RESULT_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TPluginWetestInstResultRecord>> getKeys() {
        return Arrays.<UniqueKey<TPluginWetestInstResultRecord>>asList(Keys.KEY_T_PLUGIN_WETEST_INST_RESULT_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TPluginWetestInstResult as(String alias) {
        return new TPluginWetestInstResult(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TPluginWetestInstResult rename(String name) {
        return new TPluginWetestInstResult(name, null);
    }
}
