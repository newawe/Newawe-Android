package com.Newawe.deviceidparser;

public class DeviceIdParameters implements Cloneable {
    private String _advid;
    private String _aid;
    LimitAdTrackingEnabledStates _limitAdTrackingEnabled;

    public DeviceIdParameters() {
        this._advid = null;
        this._aid = null;
        this._limitAdTrackingEnabled = null;
    }

    public DeviceIdParameters(String hid, String advid, String aid, LimitAdTrackingEnabledStates limitAdTrackingEnabled) {
        this._advid = null;
        this._aid = null;
        this._limitAdTrackingEnabled = null;
        this._advid = advid;
        this._aid = aid;
        this._limitAdTrackingEnabled = limitAdTrackingEnabled;
    }

    public boolean isEmpty() {
        return this._aid == null && this._advid == null && this._limitAdTrackingEnabled == null;
    }

    public void clear() {
        this._advid = null;
        this._aid = null;
        this._limitAdTrackingEnabled = null;
    }

    public String getAdvid() {
        return this._advid;
    }

    protected void setAdvid(String advid) {
        this._advid = advid;
    }

    public String getAid() {
        return this._aid;
    }

    protected void setAid(String aid) {
        this._aid = aid;
    }

    public LimitAdTrackingEnabledStates getLimitAdTrackingEnabled() {
        return this._limitAdTrackingEnabled;
    }

    public void setLimitAdTrackingEnabled(LimitAdTrackingEnabledStates _limitAdTrackingEnabled) {
        this._limitAdTrackingEnabled = _limitAdTrackingEnabled;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
