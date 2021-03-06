/*
 * This file is generated by jOOQ.
*/
package codes.foobar.wedapp.jooq.tables;


import codes.foobar.wedapp.jooq.DefaultSchema;
import codes.foobar.wedapp.jooq.Indexes;
import codes.foobar.wedapp.jooq.Keys;
import codes.foobar.wedapp.jooq.tables.records.AccommodationRecord;

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
public class Accommodation extends TableImpl<AccommodationRecord> {

    private static final long serialVersionUID = -142297075;

    /**
     * The reference instance of <code>accommodation</code>
     */
    public static final Accommodation ACCOMMODATION = new Accommodation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccommodationRecord> getRecordType() {
        return AccommodationRecord.class;
    }

    /**
     * The column <code>accommodation.id</code>.
     */
    public final TableField<AccommodationRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('accommodation_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>accommodation.name</code>.
     */
    public final TableField<AccommodationRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(40).nullable(false), this, "");

    /**
     * The column <code>accommodation.address</code>.
     */
    public final TableField<AccommodationRecord, String> ADDRESS = createField("address", org.jooq.impl.SQLDataType.VARCHAR(40).nullable(false), this, "");

    /**
     * The column <code>accommodation.phone</code>.
     */
    public final TableField<AccommodationRecord, String> PHONE = createField("phone", org.jooq.impl.SQLDataType.VARCHAR(15), this, "");

    /**
     * The column <code>accommodation.home_page</code>.
     */
    public final TableField<AccommodationRecord, String> HOME_PAGE = createField("home_page", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>accommodation.info</code>.
     */
    public final TableField<AccommodationRecord, String> INFO = createField("info", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>accommodation.last_updated</code>.
     */
    public final TableField<AccommodationRecord, OffsetDateTime> LAST_UPDATED = createField("last_updated", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE.nullable(false), this, "");

    /**
     * Create a <code>accommodation</code> table reference
     */
    public Accommodation() {
        this(DSL.name("accommodation"), null);
    }

    /**
     * Create an aliased <code>accommodation</code> table reference
     */
    public Accommodation(String alias) {
        this(DSL.name(alias), ACCOMMODATION);
    }

    /**
     * Create an aliased <code>accommodation</code> table reference
     */
    public Accommodation(Name alias) {
        this(alias, ACCOMMODATION);
    }

    private Accommodation(Name alias, Table<AccommodationRecord> aliased) {
        this(alias, aliased, null);
    }

    private Accommodation(Name alias, Table<AccommodationRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.ACCOMMODATION_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<AccommodationRecord, Integer> getIdentity() {
        return Keys.IDENTITY_ACCOMMODATION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AccommodationRecord> getPrimaryKey() {
        return Keys.ACCOMMODATION_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AccommodationRecord>> getKeys() {
        return Arrays.<UniqueKey<AccommodationRecord>>asList(Keys.ACCOMMODATION_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Accommodation as(String alias) {
        return new Accommodation(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Accommodation as(Name alias) {
        return new Accommodation(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Accommodation rename(String name) {
        return new Accommodation(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Accommodation rename(Name name) {
        return new Accommodation(name, null);
    }
}
