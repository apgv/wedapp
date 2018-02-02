/*
 * This file is generated by jOOQ.
*/
package codes.foobar.wedapp.jooq.tables;


import codes.foobar.wedapp.jooq.DefaultSchema;
import codes.foobar.wedapp.jooq.Indexes;
import codes.foobar.wedapp.jooq.Keys;
import codes.foobar.wedapp.jooq.tables.records.ContactRecord;

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
public class Contact extends TableImpl<ContactRecord> {

    private static final long serialVersionUID = -1802495801;

    /**
     * The reference instance of <code>contact</code>
     */
    public static final Contact CONTACT = new Contact();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ContactRecord> getRecordType() {
        return ContactRecord.class;
    }

    /**
     * The column <code>contact.id</code>.
     */
    public final TableField<ContactRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('contact_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>contact.name</code>.
     */
    public final TableField<ContactRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(40).nullable(false), this, "");

    /**
     * The column <code>contact.phone</code>.
     */
    public final TableField<ContactRecord, String> PHONE = createField("phone", org.jooq.impl.SQLDataType.VARCHAR(15), this, "");

    /**
     * The column <code>contact.email</code>.
     */
    public final TableField<ContactRecord, String> EMAIL = createField("email", org.jooq.impl.SQLDataType.VARCHAR(40), this, "");

    /**
     * The column <code>contact.last_updated</code>.
     */
    public final TableField<ContactRecord, OffsetDateTime> LAST_UPDATED = createField("last_updated", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE.nullable(false), this, "");

    /**
     * Create a <code>contact</code> table reference
     */
    public Contact() {
        this(DSL.name("contact"), null);
    }

    /**
     * Create an aliased <code>contact</code> table reference
     */
    public Contact(String alias) {
        this(DSL.name(alias), CONTACT);
    }

    /**
     * Create an aliased <code>contact</code> table reference
     */
    public Contact(Name alias) {
        this(alias, CONTACT);
    }

    private Contact(Name alias, Table<ContactRecord> aliased) {
        this(alias, aliased, null);
    }

    private Contact(Name alias, Table<ContactRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.CONTACT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ContactRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CONTACT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ContactRecord> getPrimaryKey() {
        return Keys.CONTACT_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ContactRecord>> getKeys() {
        return Arrays.<UniqueKey<ContactRecord>>asList(Keys.CONTACT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Contact as(String alias) {
        return new Contact(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Contact as(Name alias) {
        return new Contact(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Contact rename(String name) {
        return new Contact(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Contact rename(Name name) {
        return new Contact(name, null);
    }
}
