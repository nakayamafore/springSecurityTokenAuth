/*
 * This file is generated by jOOQ.
 */
package com.example.webapi.infrastructure.jooq.tables;


import com.example.webapi.infrastructure.jooq.Demo3Db;
import com.example.webapi.infrastructure.jooq.Indexes;
import com.example.webapi.infrastructure.jooq.Keys;
import com.example.webapi.infrastructure.jooq.tables.records.MemoRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Memo extends TableImpl<MemoRecord> {

    private static final long serialVersionUID = 1333330158;

    /**
     * The reference instance of <code>demo3_db.memo</code>
     */
    public static final Memo MEMO = new Memo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MemoRecord> getRecordType() {
        return MemoRecord.class;
    }

    /**
     * The column <code>demo3_db.memo.id</code>.
     */
    public final TableField<MemoRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>demo3_db.memo.title</code>.
     */
    public final TableField<MemoRecord, String> TITLE = createField(DSL.name("title"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>demo3_db.memo.description</code>.
     */
    public final TableField<MemoRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>demo3_db.memo.done</code>.
     */
    public final TableField<MemoRecord, Byte> DONE = createField(DSL.name("done"), org.jooq.impl.SQLDataType.TINYINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.TINYINT)), this, "");

    /**
     * The column <code>demo3_db.memo.updated</code>.
     */
    public final TableField<MemoRecord, Timestamp> UPDATED = createField(DSL.name("updated"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP(3)", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>demo3_db.memo</code> table reference
     */
    public Memo() {
        this(DSL.name("memo"), null);
    }

    /**
     * Create an aliased <code>demo3_db.memo</code> table reference
     */
    public Memo(String alias) {
        this(DSL.name(alias), MEMO);
    }

    /**
     * Create an aliased <code>demo3_db.memo</code> table reference
     */
    public Memo(Name alias) {
        this(alias, MEMO);
    }

    private Memo(Name alias, Table<MemoRecord> aliased) {
        this(alias, aliased, null);
    }

    private Memo(Name alias, Table<MemoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Memo(Table<O> child, ForeignKey<O, MemoRecord> key) {
        super(child, key, MEMO);
    }

    @Override
    public Schema getSchema() {
        return Demo3Db.DEMO3_DB;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.MEMO_PRIMARY);
    }

    @Override
    public Identity<MemoRecord, Long> getIdentity() {
        return Keys.IDENTITY_MEMO;
    }

    @Override
    public UniqueKey<MemoRecord> getPrimaryKey() {
        return Keys.KEY_MEMO_PRIMARY;
    }

    @Override
    public List<UniqueKey<MemoRecord>> getKeys() {
        return Arrays.<UniqueKey<MemoRecord>>asList(Keys.KEY_MEMO_PRIMARY);
    }

    @Override
    public Memo as(String alias) {
        return new Memo(DSL.name(alias), this);
    }

    @Override
    public Memo as(Name alias) {
        return new Memo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Memo rename(String name) {
        return new Memo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Memo rename(Name name) {
        return new Memo(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, String, Byte, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}