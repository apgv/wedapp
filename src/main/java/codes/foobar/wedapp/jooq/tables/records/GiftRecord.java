/*
 * This file is generated by jOOQ.
*/
package codes.foobar.wedapp.jooq.tables.records;


import codes.foobar.wedapp.jooq.tables.Gift;

import java.time.OffsetDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


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
public class GiftRecord extends UpdatableRecordImpl<GiftRecord> implements Record8<Integer, Boolean, Boolean, String, String, String, String, OffsetDateTime> {

    private static final long serialVersionUID = -842628445;

    /**
     * Setter for <code>gift.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>gift.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>gift.checkable</code>.
     */
    public void setCheckable(Boolean value) {
        set(1, value);
    }

    /**
     * Getter for <code>gift.checkable</code>.
     */
    public Boolean getCheckable() {
        return (Boolean) get(1);
    }

    /**
     * Setter for <code>gift.checked</code>.
     */
    public void setChecked(Boolean value) {
        set(2, value);
    }

    /**
     * Getter for <code>gift.checked</code>.
     */
    public Boolean getChecked() {
        return (Boolean) get(2);
    }

    /**
     * Setter for <code>gift.title</code>.
     */
    public void setTitle(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>gift.title</code>.
     */
    public String getTitle() {
        return (String) get(3);
    }

    /**
     * Setter for <code>gift.description</code>.
     */
    public void setDescription(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>gift.description</code>.
     */
    public String getDescription() {
        return (String) get(4);
    }

    /**
     * Setter for <code>gift.link_url</code>.
     */
    public void setLinkUrl(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>gift.link_url</code>.
     */
    public String getLinkUrl() {
        return (String) get(5);
    }

    /**
     * Setter for <code>gift.link_text</code>.
     */
    public void setLinkText(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>gift.link_text</code>.
     */
    public String getLinkText() {
        return (String) get(6);
    }

    /**
     * Setter for <code>gift.last_updated</code>.
     */
    public void setLastUpdated(OffsetDateTime value) {
        set(7, value);
    }

    /**
     * Getter for <code>gift.last_updated</code>.
     */
    public OffsetDateTime getLastUpdated() {
        return (OffsetDateTime) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, Boolean, Boolean, String, String, String, String, OffsetDateTime> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, Boolean, Boolean, String, String, String, String, OffsetDateTime> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Gift.GIFT.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field2() {
        return Gift.GIFT.CHECKABLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field3() {
        return Gift.GIFT.CHECKED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Gift.GIFT.TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Gift.GIFT.DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Gift.GIFT.LINK_URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Gift.GIFT.LINK_TEXT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<OffsetDateTime> field8() {
        return Gift.GIFT.LAST_UPDATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component2() {
        return getCheckable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component3() {
        return getChecked();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getLinkUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getLinkText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetDateTime component8() {
        return getLastUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value2() {
        return getCheckable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value3() {
        return getChecked();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getTitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getLinkUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getLinkText();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetDateTime value8() {
        return getLastUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GiftRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GiftRecord value2(Boolean value) {
        setCheckable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GiftRecord value3(Boolean value) {
        setChecked(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GiftRecord value4(String value) {
        setTitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GiftRecord value5(String value) {
        setDescription(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GiftRecord value6(String value) {
        setLinkUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GiftRecord value7(String value) {
        setLinkText(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GiftRecord value8(OffsetDateTime value) {
        setLastUpdated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GiftRecord values(Integer value1, Boolean value2, Boolean value3, String value4, String value5, String value6, String value7, OffsetDateTime value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached GiftRecord
     */
    public GiftRecord() {
        super(Gift.GIFT);
    }

    /**
     * Create a detached, initialised GiftRecord
     */
    public GiftRecord(Integer id, Boolean checkable, Boolean checked, String title, String description, String linkUrl, String linkText, OffsetDateTime lastUpdated) {
        super(Gift.GIFT);

        set(0, id);
        set(1, checkable);
        set(2, checked);
        set(3, title);
        set(4, description);
        set(5, linkUrl);
        set(6, linkText);
        set(7, lastUpdated);
    }
}
