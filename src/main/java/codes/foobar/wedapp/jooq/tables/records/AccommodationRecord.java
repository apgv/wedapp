/*
 * This file is generated by jOOQ.
*/
package codes.foobar.wedapp.jooq.tables.records;


import codes.foobar.wedapp.jooq.tables.Accommodation;

import java.time.OffsetDateTime;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
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
public class AccommodationRecord extends UpdatableRecordImpl<AccommodationRecord> implements Record7<Integer, String, String, String, String, String, OffsetDateTime> {

    private static final long serialVersionUID = 1500074193;

    /**
     * Setter for <code>accommodation.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>accommodation.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>accommodation.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>accommodation.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>accommodation.address</code>.
     */
    public void setAddress(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>accommodation.address</code>.
     */
    public String getAddress() {
        return (String) get(2);
    }

    /**
     * Setter for <code>accommodation.phone</code>.
     */
    public void setPhone(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>accommodation.phone</code>.
     */
    public String getPhone() {
        return (String) get(3);
    }

    /**
     * Setter for <code>accommodation.home_page</code>.
     */
    public void setHomePage(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>accommodation.home_page</code>.
     */
    public String getHomePage() {
        return (String) get(4);
    }

    /**
     * Setter for <code>accommodation.info</code>.
     */
    public void setInfo(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>accommodation.info</code>.
     */
    public String getInfo() {
        return (String) get(5);
    }

    /**
     * Setter for <code>accommodation.last_updated</code>.
     */
    public void setLastUpdated(OffsetDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>accommodation.last_updated</code>.
     */
    public OffsetDateTime getLastUpdated() {
        return (OffsetDateTime) get(6);
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
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, String, String, String, String, OffsetDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Integer, String, String, String, String, String, OffsetDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Accommodation.ACCOMMODATION.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Accommodation.ACCOMMODATION.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Accommodation.ACCOMMODATION.ADDRESS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Accommodation.ACCOMMODATION.PHONE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Accommodation.ACCOMMODATION.HOME_PAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Accommodation.ACCOMMODATION.INFO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<OffsetDateTime> field7() {
        return Accommodation.ACCOMMODATION.LAST_UPDATED;
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
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getAddress();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component4() {
        return getPhone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component5() {
        return getHomePage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getInfo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetDateTime component7() {
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
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getAddress();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getPhone();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getHomePage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getInfo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OffsetDateTime value7() {
        return getLastUpdated();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccommodationRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccommodationRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccommodationRecord value3(String value) {
        setAddress(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccommodationRecord value4(String value) {
        setPhone(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccommodationRecord value5(String value) {
        setHomePage(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccommodationRecord value6(String value) {
        setInfo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccommodationRecord value7(OffsetDateTime value) {
        setLastUpdated(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccommodationRecord values(Integer value1, String value2, String value3, String value4, String value5, String value6, OffsetDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccommodationRecord
     */
    public AccommodationRecord() {
        super(Accommodation.ACCOMMODATION);
    }

    /**
     * Create a detached, initialised AccommodationRecord
     */
    public AccommodationRecord(Integer id, String name, String address, String phone, String homePage, String info, OffsetDateTime lastUpdated) {
        super(Accommodation.ACCOMMODATION);

        set(0, id);
        set(1, name);
        set(2, address);
        set(3, phone);
        set(4, homePage);
        set(5, info);
        set(6, lastUpdated);
    }
}
