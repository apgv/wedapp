/*
 * This file is generated by jOOQ.
*/
package codes.foobar.wedapp.jooq.tables;


import codes.foobar.wedapp.jooq.DefaultSchema;
import codes.foobar.wedapp.jooq.Indexes;
import codes.foobar.wedapp.jooq.Keys;
import codes.foobar.wedapp.jooq.tables.records.DirectionRecord;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
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
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Direction extends TableImpl<DirectionRecord> {

    private static final long serialVersionUID = -522985980;

    /**
     * The reference instance of <code>direction</code>
     */
    public static final Direction DIRECTION = new Direction();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DirectionRecord> getRecordType() {
        return DirectionRecord.class;
    }

    /**
     * The column <code>direction.id</code>.
     */
    public final TableField<DirectionRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('direction_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>direction.title</code>.
     */
    public final TableField<DirectionRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR(40).nullable(false), this, "");

    /**
     * The column <code>direction.content</code>.
     */
    public final TableField<DirectionRecord, String> CONTENT = createField("content", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>direction.last_updated</code>.
     */
    public final TableField<DirectionRecord, OffsetDateTime> LAST_UPDATED = createField("last_updated", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE.nullable(false), this, "");

    /**
     * Create a <code>direction</code> table reference
     */
    public Direction() {
        this(DSL.name("direction"), null);
    }

    /**
     * Create an aliased <code>direction</code> table reference
     */
    public Direction(String alias) {
        this(DSL.name(alias), DIRECTION);
    }

    /**
     * Create an aliased <code>direction</code> table reference
     */
    public Direction(Name alias) {
        this(alias, DIRECTION);
    }

    private Direction(Name alias, Table<DirectionRecord> aliased) {
        this(alias, aliased, null);
    }

    private Direction(Name alias, Table<DirectionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.DIRECTION_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<DirectionRecord, Integer> getIdentity() {
        return Keys.IDENTITY_DIRECTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<DirectionRecord> getPrimaryKey() {
        return Keys.DIRECTION_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<DirectionRecord>> getKeys() {
        return Arrays.<UniqueKey<DirectionRecord>>asList(Keys.DIRECTION_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction as(String alias) {
        return new Direction(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction as(Name alias) {
        return new Direction(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Direction rename(String name) {
        return new Direction(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Direction rename(Name name) {
        return new Direction(name, null);
    }
}