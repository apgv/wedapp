/*
 * This file is generated by jOOQ.
*/
package codes.foobar.wedapp.jooq.tables;


import codes.foobar.wedapp.jooq.DefaultSchema;
import codes.foobar.wedapp.jooq.Indexes;
import codes.foobar.wedapp.jooq.Keys;
import codes.foobar.wedapp.jooq.tables.records.IndexPageRecord;

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
public class IndexPage extends TableImpl<IndexPageRecord> {

    private static final long serialVersionUID = -252791801;

    /**
     * The reference instance of <code>index_page</code>
     */
    public static final IndexPage INDEX_PAGE = new IndexPage();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<IndexPageRecord> getRecordType() {
        return IndexPageRecord.class;
    }

    /**
     * The column <code>index_page.id</code>.
     */
    public final TableField<IndexPageRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('index_page_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>index_page.content</code>.
     */
    public final TableField<IndexPageRecord, String> CONTENT = createField("content", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>index_page.last_updated</code>.
     */
    public final TableField<IndexPageRecord, OffsetDateTime> LAST_UPDATED = createField("last_updated", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE.nullable(false), this, "");

    /**
     * Create a <code>index_page</code> table reference
     */
    public IndexPage() {
        this(DSL.name("index_page"), null);
    }

    /**
     * Create an aliased <code>index_page</code> table reference
     */
    public IndexPage(String alias) {
        this(DSL.name(alias), INDEX_PAGE);
    }

    /**
     * Create an aliased <code>index_page</code> table reference
     */
    public IndexPage(Name alias) {
        this(alias, INDEX_PAGE);
    }

    private IndexPage(Name alias, Table<IndexPageRecord> aliased) {
        this(alias, aliased, null);
    }

    private IndexPage(Name alias, Table<IndexPageRecord> aliased, Field<?>[] parameters) {
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
        return Arrays.<Index>asList(Indexes.INDEX_PAGE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<IndexPageRecord, Integer> getIdentity() {
        return Keys.IDENTITY_INDEX_PAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<IndexPageRecord> getPrimaryKey() {
        return Keys.INDEX_PAGE_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<IndexPageRecord>> getKeys() {
        return Arrays.<UniqueKey<IndexPageRecord>>asList(Keys.INDEX_PAGE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IndexPage as(String alias) {
        return new IndexPage(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IndexPage as(Name alias) {
        return new IndexPage(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public IndexPage rename(String name) {
        return new IndexPage(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public IndexPage rename(Name name) {
        return new IndexPage(name, null);
    }
}