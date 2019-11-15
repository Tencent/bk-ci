/*
 * This file is generated by jOOQ.
*/
package com.tencent.devops.model.dispatch.tables;


import com.tencent.devops.model.dispatch.DevopsDispatch;
import com.tencent.devops.model.dispatch.Keys;
import com.tencent.devops.model.dispatch.tables.records.TDispatchVmTypeRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TDispatchVmType extends TableImpl<TDispatchVmTypeRecord> {

    private static final long serialVersionUID = -2068203550;

    /**
     * The reference instance of <code>devops_dispatch.T_DISPATCH_VM_TYPE</code>
     */
    public static final TDispatchVmType T_DISPATCH_VM_TYPE = new TDispatchVmType();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TDispatchVmTypeRecord> getRecordType() {
        return TDispatchVmTypeRecord.class;
    }

    /**
     * The column <code>devops_dispatch.T_DISPATCH_VM_TYPE.TYPE_ID</code>.
     */
    public final TableField<TDispatchVmTypeRecord, Integer> TYPE_ID = createField("TYPE_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>devops_dispatch.T_DISPATCH_VM_TYPE.TYPE_NAME</code>.
     */
    public final TableField<TDispatchVmTypeRecord, String> TYPE_NAME = createField("TYPE_NAME", org.jooq.impl.SQLDataType.VARCHAR.length(64).nullable(false), this, "");

    /**
     * The column <code>devops_dispatch.T_DISPATCH_VM_TYPE.TYPE_CREATED_TIME</code>.
     */
    public final TableField<TDispatchVmTypeRecord, LocalDateTime> TYPE_CREATED_TIME = createField("TYPE_CREATED_TIME", org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * The column <code>devops_dispatch.T_DISPATCH_VM_TYPE.TYPE_UPDATED_TIME</code>.
     */
    public final TableField<TDispatchVmTypeRecord, LocalDateTime> TYPE_UPDATED_TIME = createField("TYPE_UPDATED_TIME", org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * Create a <code>devops_dispatch.T_DISPATCH_VM_TYPE</code> table reference
     */
    public TDispatchVmType() {
        this("T_DISPATCH_VM_TYPE", null);
    }

    /**
     * Create an aliased <code>devops_dispatch.T_DISPATCH_VM_TYPE</code> table reference
     */
    public TDispatchVmType(String alias) {
        this(alias, T_DISPATCH_VM_TYPE);
    }

    private TDispatchVmType(String alias, Table<TDispatchVmTypeRecord> aliased) {
        this(alias, aliased, null);
    }

    private TDispatchVmType(String alias, Table<TDispatchVmTypeRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DevopsDispatch.DEVOPS_DISPATCH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<TDispatchVmTypeRecord, Integer> getIdentity() {
        return Keys.IDENTITY_T_DISPATCH_VM_TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<TDispatchVmTypeRecord> getPrimaryKey() {
        return Keys.KEY_T_DISPATCH_VM_TYPE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<TDispatchVmTypeRecord>> getKeys() {
        return Arrays.<UniqueKey<TDispatchVmTypeRecord>>asList(Keys.KEY_T_DISPATCH_VM_TYPE_PRIMARY, Keys.KEY_T_DISPATCH_VM_TYPE_TYPE_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TDispatchVmType as(String alias) {
        return new TDispatchVmType(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public TDispatchVmType rename(String name) {
        return new TDispatchVmType(name, null);
    }
}
