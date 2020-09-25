/*
 * Copyright  2020 Balazs Kreith
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This file is generated by jOOQ.
 */
package org.observertc.webrtc.observer.jooq.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.observertc.webrtc.observer.jooq.Indexes;
import org.observertc.webrtc.observer.jooq.Keys;
import org.observertc.webrtc.observer.jooq.Webrtcobserver;
import org.observertc.webrtc.observer.jooq.tables.records.ServicesRecord;


/**
 * Services
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Services extends TableImpl<ServicesRecord> {

    private static final long serialVersionUID = 450369440;

    /**
     * The reference instance of <code>WebRTCObserver.Services</code>
     */
    public static final Services SERVICES = new Services();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ServicesRecord> getRecordType() {
        return ServicesRecord.class;
    }

    /**
     * The column <code>WebRTCObserver.Services.id</code>.
     */
    public final TableField<ServicesRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>WebRTCObserver.Services.customer_id</code>.
     */
    public final TableField<ServicesRecord, Integer> CUSTOMER_ID = createField(DSL.name("customer_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>WebRTCObserver.Services.uuid</code>.
     */
    public final TableField<ServicesRecord, byte[]> UUID = createField(DSL.name("uuid"), org.jooq.impl.SQLDataType.BINARY(16), this, "");

    /**
     * The column <code>WebRTCObserver.Services.name</code>.
     */
    public final TableField<ServicesRecord, String> NAME = createField(DSL.name("name"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>WebRTCObserver.Services.description</code>.
     */
    public final TableField<ServicesRecord, String> DESCRIPTION = createField(DSL.name("description"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>WebRTCObserver.Services</code> table reference
     */
    public Services() {
        this(DSL.name("Services"), null);
    }

    /**
     * Create an aliased <code>WebRTCObserver.Services</code> table reference
     */
    public Services(String alias) {
        this(DSL.name(alias), SERVICES);
    }

    /**
     * Create an aliased <code>WebRTCObserver.Services</code> table reference
     */
    public Services(Name alias) {
        this(alias, SERVICES);
    }

    private Services(Name alias, Table<ServicesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Services(Name alias, Table<ServicesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Services"), TableOptions.table());
    }

    public <O extends Record> Services(Table<O> child, ForeignKey<O, ServicesRecord> key) {
        super(child, key, SERVICES);
    }

    @Override
    public Schema getSchema() {
        return Webrtcobserver.WEBRTCOBSERVER;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.SERVICES_CUSTOMER_ID, Indexes.SERVICES_SERVICES_UUID_KEY);
    }

    @Override
    public Identity<ServicesRecord, Integer> getIdentity() {
        return Keys.IDENTITY_SERVICES;
    }

    @Override
    public UniqueKey<ServicesRecord> getPrimaryKey() {
        return Keys.KEY_SERVICES_PRIMARY;
    }

    @Override
    public List<UniqueKey<ServicesRecord>> getKeys() {
        return Arrays.<UniqueKey<ServicesRecord>>asList(Keys.KEY_SERVICES_PRIMARY, Keys.KEY_SERVICES_UUID);
    }

    @Override
    public List<ForeignKey<ServicesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ServicesRecord, ?>>asList(Keys.SERVICES_IBFK_1);
    }

    public Customers customers() {
        return new Customers(this, Keys.SERVICES_IBFK_1);
    }

    @Override
    public Services as(String alias) {
        return new Services(DSL.name(alias), this);
    }

    @Override
    public Services as(Name alias) {
        return new Services(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Services rename(String name) {
        return new Services(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Services rename(Name name) {
        return new Services(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, byte[], String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
