/*
 * This file is generated by jOOQ.
*/
package codes.foobar.wedapp.jooq.tables.records;


import codes.foobar.wedapp.jooq.tables.Guest;

import java.time.OffsetDateTime;

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
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class GuestRecord extends UpdatableRecordImpl<GuestRecord> implements Record5<Integer, String, Boolean, String, OffsetDateTime> {

    private static final long serialVersionUID = -1501613903;

    /**
     * Setter for <code>guest.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>guest.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>guest.full_name</code>.
     */
    public void setFullName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>guest.full_name</code>.
     */
    public String getFullName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>guest.attending</code>.
     */
    public void setAttending(Boolean value) {
        set(2, value);
    }

    /**
     * Getter for <code>guest.attending</code>.
     */
    public Boolean getAttending() {
        return (Boolean) get(2);
    }

    /**
     * Setter for <code>guest.note</code>.
     */
    public void setNote(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>guest.note</code>.
     */
    public String getNote() {
        return (String) get(3);
    }

    /**
     * Setter for <code>guest.last_updated</code>.
     */
    public void setLastUpdated(OffsetDateTime value) {
        set(4, value);
    }

    /**
     * Getter for <code>guest.last_updated</code>.
     */
    public OffsetDateTime getLastUpdated() {
        return (OffsetDateTime) get(4);
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
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, Boolean, String, OffsetDateTime> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, Boolean, String, OffsetDateTime> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Guest.GUEST.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Guest.GUEST.FULL_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field3() {
        return Guest.GUEST.ATTENDING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Guest.GUEST.NOTE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<OffsetDateTime> field5() {
        return Guest.GUEST.LAST_UPDATED;
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
    public String component2() {
        return getFullName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component3() {
        return getAttending();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getNote();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetDateTime component5() {
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
    public String value2() {
        return getFullName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value3() {
        return getAttending();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getNote();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetDateTime value5() {
        return getLastUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GuestRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GuestRecord value2(String value) {
        setFullName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GuestRecord value3(Boolean value) {
        setAttending(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GuestRecord value4(String value) {
        setNote(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GuestRecord value5(OffsetDateTime value) {
        setLastUpdated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GuestRecord values(Integer value1, String value2, Boolean value3, String value4, OffsetDateTime value5) {
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
     * Create a detached GuestRecord
     */
    public GuestRecord() {
        super(Guest.GUEST);
    }

    /**
     * Create a detached, initialised GuestRecord
     */
    public GuestRecord(Integer id, String fullName, Boolean attending, String note, OffsetDateTime lastUpdated) {
        super(Guest.GUEST);

        set(0, id);
        set(1, fullName);
        set(2, attending);
        set(3, note);
        set(4, lastUpdated);
    }
}
