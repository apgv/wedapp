/*
 * This file is generated by jOOQ.
*/
package codes.foobar.wedapp.jooq;


import codes.foobar.wedapp.jooq.tables.Accommodation;
import codes.foobar.wedapp.jooq.tables.Contact;
import codes.foobar.wedapp.jooq.tables.Direction;
import codes.foobar.wedapp.jooq.tables.Gift;
import codes.foobar.wedapp.jooq.tables.Guest;
import codes.foobar.wedapp.jooq.tables.IndexPage;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling indexes of tables of the <code></code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index ACCOMMODATION_PKEY = Indexes0.ACCOMMODATION_PKEY;
    public static final Index CONTACT_PKEY = Indexes0.CONTACT_PKEY;
    public static final Index DIRECTION_PKEY = Indexes0.DIRECTION_PKEY;
    public static final Index GIFT_PKEY = Indexes0.GIFT_PKEY;
    public static final Index GUEST_PKEY = Indexes0.GUEST_PKEY;
    public static final Index INDEX_PAGE_PKEY = Indexes0.INDEX_PAGE_PKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 extends AbstractKeys {
        public static Index ACCOMMODATION_PKEY = createIndex("accommodation_pkey", Accommodation.ACCOMMODATION, new OrderField[] { Accommodation.ACCOMMODATION.ID }, true);
        public static Index CONTACT_PKEY = createIndex("contact_pkey", Contact.CONTACT, new OrderField[] { Contact.CONTACT.ID }, true);
        public static Index DIRECTION_PKEY = createIndex("direction_pkey", Direction.DIRECTION, new OrderField[] { Direction.DIRECTION.ID }, true);
        public static Index GIFT_PKEY = createIndex("gift_pkey", Gift.GIFT, new OrderField[] { Gift.GIFT.ID }, true);
        public static Index GUEST_PKEY = createIndex("guest_pkey", Guest.GUEST, new OrderField[] { Guest.GUEST.ID }, true);
        public static Index INDEX_PAGE_PKEY = createIndex("index_page_pkey", IndexPage.INDEX_PAGE, new OrderField[] { IndexPage.INDEX_PAGE.ID }, true);
    }
}
