/*
 * This file is generated by jOOQ.
 */
package org.observertc.webrtc.service.jooq.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.observertc.webrtc.service.jooq.Keys;
import org.observertc.webrtc.service.jooq.Webrtcobserver;
import org.observertc.webrtc.service.jooq.tables.records.CallpeerconnectionsRecord;


/**
 * CallIDs
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Callpeerconnections extends TableImpl<CallpeerconnectionsRecord> {

    private static final long serialVersionUID = 253750184;

    /**
     * The reference instance of <code>WebRTCObserver.CallPeerconnections</code>
     */
    public static final Callpeerconnections CALLPEERCONNECTIONS = new Callpeerconnections();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CallpeerconnectionsRecord> getRecordType() {
        return CallpeerconnectionsRecord.class;
    }

    /**
     * The column <code>WebRTCObserver.CallPeerconnections.peerConnectionUUID</code>. The UUID of the peer connection the SSRC belongs to
     */
    public final TableField<CallpeerconnectionsRecord, byte[]> PEERCONNECTIONUUID = createField(DSL.name("peerConnectionUUID"), org.jooq.impl.SQLDataType.BINARY(16).nullable(false), this, "The UUID of the peer connection the SSRC belongs to");

    /**
     * The column <code>WebRTCObserver.CallPeerconnections.callUUID</code>. The UUID of the call the peer connection belongs to
     */
    public final TableField<CallpeerconnectionsRecord, byte[]> CALLUUID = createField(DSL.name("callUUID"), org.jooq.impl.SQLDataType.BINARY(16).nullable(false), this, "The UUID of the call the peer connection belongs to");

    /**
     * Create a <code>WebRTCObserver.CallPeerconnections</code> table reference
     */
    public Callpeerconnections() {
        this(DSL.name("CallPeerconnections"), null);
    }

    /**
     * Create an aliased <code>WebRTCObserver.CallPeerconnections</code> table reference
     */
    public Callpeerconnections(String alias) {
        this(DSL.name(alias), CALLPEERCONNECTIONS);
    }

    /**
     * Create an aliased <code>WebRTCObserver.CallPeerconnections</code> table reference
     */
    public Callpeerconnections(Name alias) {
        this(alias, CALLPEERCONNECTIONS);
    }

    private Callpeerconnections(Name alias, Table<CallpeerconnectionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Callpeerconnections(Name alias, Table<CallpeerconnectionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("CallIDs"), TableOptions.table());
    }

    public <O extends Record> Callpeerconnections(Table<O> child, ForeignKey<O, CallpeerconnectionsRecord> key) {
        super(child, key, CALLPEERCONNECTIONS);
    }

    @Override
    public Schema getSchema() {
        return Webrtcobserver.WEBRTCOBSERVER;
    }

    @Override
    public UniqueKey<CallpeerconnectionsRecord> getPrimaryKey() {
        return Keys.KEY_CALLPEERCONNECTIONS_PRIMARY;
    }

    @Override
    public List<UniqueKey<CallpeerconnectionsRecord>> getKeys() {
        return Arrays.<UniqueKey<CallpeerconnectionsRecord>>asList(Keys.KEY_CALLPEERCONNECTIONS_PRIMARY);
    }

    @Override
    public Callpeerconnections as(String alias) {
        return new Callpeerconnections(DSL.name(alias), this);
    }

    @Override
    public Callpeerconnections as(Name alias) {
        return new Callpeerconnections(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Callpeerconnections rename(String name) {
        return new Callpeerconnections(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Callpeerconnections rename(Name name) {
        return new Callpeerconnections(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<byte[], byte[]> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
