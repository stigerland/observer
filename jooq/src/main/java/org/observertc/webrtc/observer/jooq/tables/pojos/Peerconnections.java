/*
 * This file is generated by jOOQ.
 */
package org.observertc.webrtc.observer.jooq.tables.pojos;


import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * A table to store information related to peer connections
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Peerconnections implements Serializable {

    private static final long serialVersionUID = 883464582;

    private final byte[] peerconnectionuuid;
    private final byte[] calluuid;
    private final byte[] serviceuuid;
    private final Long   joined;
    private final Long   updated;
    private final Long   detached;
    private final String bridgeid;
    private final String browserid;
    private final String provideduserid;
    private final String providedcallid;
    private final String timezone;
    private final String servicename;

    public Peerconnections(Peerconnections value) {
        this.peerconnectionuuid = value.peerconnectionuuid;
        this.calluuid = value.calluuid;
        this.serviceuuid = value.serviceuuid;
        this.joined = value.joined;
        this.updated = value.updated;
        this.detached = value.detached;
        this.bridgeid = value.bridgeid;
        this.browserid = value.browserid;
        this.provideduserid = value.provideduserid;
        this.providedcallid = value.providedcallid;
        this.timezone = value.timezone;
        this.servicename = value.servicename;
    }

    public Peerconnections(
        byte[] peerconnectionuuid,
        byte[] calluuid,
        byte[] serviceuuid,
        Long   joined,
        Long   updated,
        Long   detached,
        String bridgeid,
        String browserid,
        String provideduserid,
        String providedcallid,
        String timezone,
        String servicename
    ) {
        this.peerconnectionuuid = peerconnectionuuid;
        this.calluuid = calluuid;
        this.serviceuuid = serviceuuid;
        this.joined = joined;
        this.updated = updated;
        this.detached = detached;
        this.bridgeid = bridgeid;
        this.browserid = browserid;
        this.provideduserid = provideduserid;
        this.providedcallid = providedcallid;
        this.timezone = timezone;
        this.servicename = servicename;
    }

    @NotNull
    @Size(max = 16)
    public byte[] getPeerconnectionuuid() {
        return this.peerconnectionuuid;
    }

    @Size(max = 16)
    public byte[] getCalluuid() {
        return this.calluuid;
    }

    @Size(max = 16)
    public byte[] getServiceuuid() {
        return this.serviceuuid;
    }

    public Long getJoined() {
        return this.joined;
    }

    public Long getUpdated() {
        return this.updated;
    }

    public Long getDetached() {
        return this.detached;
    }

    @Size(max = 255)
    public String getBridgeid() {
        return this.bridgeid;
    }

    @Size(max = 255)
    public String getBrowserid() {
        return this.browserid;
    }

    @Size(max = 255)
    public String getProvideduserid() {
        return this.provideduserid;
    }

    @Size(max = 255)
    public String getProvidedcallid() {
        return this.providedcallid;
    }

    @Size(max = 255)
    public String getTimezone() {
        return this.timezone;
    }

    @Size(max = 255)
    public String getServicename() {
        return this.servicename;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Peerconnections (");

        sb.append("[binary...]");
        sb.append(", ").append("[binary...]");
        sb.append(", ").append("[binary...]");
        sb.append(", ").append(joined);
        sb.append(", ").append(updated);
        sb.append(", ").append(detached);
        sb.append(", ").append(bridgeid);
        sb.append(", ").append(browserid);
        sb.append(", ").append(provideduserid);
        sb.append(", ").append(providedcallid);
        sb.append(", ").append(timezone);
        sb.append(", ").append(servicename);

        sb.append(")");
        return sb.toString();
    }
}
