/*
 * This file is generated by jOOQ.
 */
package com.example.webapi.infrastructure.jooq.tables.records;


import com.example.webapi.infrastructure.jooq.tables.Memo;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


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
public class MemoRecord extends UpdatableRecordImpl<MemoRecord> implements Record5<Long, String, String, Byte, Timestamp> {

    private static final long serialVersionUID = 478066717;

    /**
     * Setter for <code>demo3_db.memo.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>demo3_db.memo.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>demo3_db.memo.title</code>.
     */
    public void setTitle(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>demo3_db.memo.title</code>.
     */
    public String getTitle() {
        return (String) get(1);
    }

    /**
     * Setter for <code>demo3_db.memo.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>demo3_db.memo.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>demo3_db.memo.done</code>.
     */
    public void setDone(Byte value) {
        set(3, value);
    }

    /**
     * Getter for <code>demo3_db.memo.done</code>.
     */
    public Byte getDone() {
        return (Byte) get(3);
    }

    /**
     * Setter for <code>demo3_db.memo.updated</code>.
     */
    public void setUpdated(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>demo3_db.memo.updated</code>.
     */
    public Timestamp getUpdated() {
        return (Timestamp) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, String, Byte, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, String, String, Byte, Timestamp> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Memo.MEMO.ID;
    }

    @Override
    public Field<String> field2() {
        return Memo.MEMO.TITLE;
    }

    @Override
    public Field<String> field3() {
        return Memo.MEMO.DESCRIPTION;
    }

    @Override
    public Field<Byte> field4() {
        return Memo.MEMO.DONE;
    }

    @Override
    public Field<Timestamp> field5() {
        return Memo.MEMO.UPDATED;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getTitle();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public Byte component4() {
        return getDone();
    }

    @Override
    public Timestamp component5() {
        return getUpdated();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getTitle();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public Byte value4() {
        return getDone();
    }

    @Override
    public Timestamp value5() {
        return getUpdated();
    }

    @Override
    public MemoRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public MemoRecord value2(String value) {
        setTitle(value);
        return this;
    }

    @Override
    public MemoRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public MemoRecord value4(Byte value) {
        setDone(value);
        return this;
    }

    @Override
    public MemoRecord value5(Timestamp value) {
        setUpdated(value);
        return this;
    }

    @Override
    public MemoRecord values(Long value1, String value2, String value3, Byte value4, Timestamp value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MemoRecord
     */
    public MemoRecord() {
        super(Memo.MEMO);
    }

    /**
     * Create a detached, initialised MemoRecord
     */
    public MemoRecord(Long id, String title, String description, Byte done, Timestamp updated) {
        super(Memo.MEMO);

        set(0, id);
        set(1, title);
        set(2, description);
        set(3, done);
        set(4, updated);
    }
}