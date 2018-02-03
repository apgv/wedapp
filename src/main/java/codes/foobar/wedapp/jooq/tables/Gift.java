/*
 * This file is generated by jOOQ.
*/
package codes.foobar.wedapp.jooq.tables;


import codes.foobar.wedapp.jooq.DefaultSchema;
import codes.foobar.wedapp.jooq.Indexes;
import codes.foobar.wedapp.jooq.Keys;
import codes.foobar.wedapp.jooq.tables.records.GiftRecord;

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
public class Gift extends TableImpl<GiftRecord> {

    private static final long serialVersionUID = -952028359;

    /**
     * The reference instance of <code>gift</code>
     */
    public static final Gift GIFT = new Gift();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GiftRecord> getRecordType() {
        return GiftRecord.class;
    }

    /**
     * The column <code>gift.id</code>.
     */
    public final TableField<GiftRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('gift_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>gift.checkable</code>.
     */
    public final TableField<GiftRecord, Boolean> CHECKABLE = createField("checkable", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>gift.checked</code>.
     */
    public final TableField<GiftRecord, Boolean> CHECKED = createField("checked", org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>gift.title</code>.
     */
    public final TableField<GiftRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR(80).nullable(false), this, "");

    /**
     * The column <code>gift.description</code>.
     */
    public final TableField<GiftRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.VARCHAR(445), this, "");

    /**
     * The column <code>gift.link_url</code>.
     */
    public final TableField<GiftRecord, String> LINK_URL = createField("link_url", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>gift.link_text</code>.
     */
    public final TableField<GiftRecord, String> LINK_TEXT = createField("link_text", org.jooq.impl.SQLDataType.VARCHAR(80), this, "");

    /**
     * The column <code>gift.last_updated</code>.
     */
    public final TableField<GiftRecord, OffsetDateTime> LAST_UPDATED = createField("last_updated", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE.nullable(false), this, "");

    /**
     * Create a <code>gift</code> table reference
     */
    public Gift() {
        this(DSL.name("gift"), null);
    }

    /**
     * Create an aliased <code>gift</code> table reference
     */
    public Gift(String alias) {
        this(DSL.name(alias), GIFT);
    }

    /**
     * Create an aliased <code>gift</code> table reference
     */
    public Gift(Name alias) {
        this(alias, GIFT);
    }

    private Gift(Name alias, Table<GiftRecord> aliased) {
        this(alias, aliased, null);
    }

    private Gift(Name alias, Table<GiftRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.GIFT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<GiftRecord, Integer> getIdentity() {
        return Keys.IDENTITY_GIFT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<GiftRecord> getPrimaryKey() {
        return Keys.GIFT_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<GiftRecord>> getKeys() {
        return Arrays.<UniqueKey<GiftRecord>>asList(Keys.GIFT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Gift as(String alias) {
        return new Gift(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Gift as(Name alias) {
        return new Gift(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Gift rename(String name) {
        return new Gift(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Gift rename(Name name) {
        return new Gift(name, null);
    }
}
