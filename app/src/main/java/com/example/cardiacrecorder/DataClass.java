package com.example.cardiacrecorder;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class DataClass {

    /**
     * Default constructor for the `DataClass`.
     */
    public DataClass() {

    }


    private String passeduser,id;
    private String date;
    private String time;
    private String sys;
    private String dis;
    private String bpm;
    private String cmnt;

    /**
     * Retrieves the value of the passeduser.
     *
     * @return The passeduser value.
     */
    public String getPasseduser() {
        return passeduser;
    }

    /**
     * Retrieves the value of the id.
     *
     * @return The id value.
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the value of the date.
     *
     * @return The date value.
     */
    public String getDate() {
        return date;
    }

    /**
     * Retrieves the value of the time.
     *
     * @return The time value.
     */
    public String getTime() {
        return time;
    }

    /**
     * Retrieves the value of the systolic pressure.
     *
     * @return The systolic pressure value.
     */
    public String getSys() {
        return sys;
    }

    /**
     * Retrieves the value of the diastolic pressure.
     *
     * @return The diastolic pressure value.
     */
    public String getDis() {
        return dis;
    }

    /**
     * Retrieves the value of the heart rate.
     *
     * @return The heart rate value.
     */
    public String getBpm() {
        return bpm;
    }

    /**
     * Retrieves the value of the comment.
     *
     * @return The comment value.
     */
    public String getCmnt() {
        return cmnt;
    }

    /**
     * Sets the value of the systolic pressure.
     *
     * @param systolicPressure The systolic pressure value.
     */
    public void setSystolicPressure(String systolicPressure) {
        this.sys = systolicPressure;
    }

    /**
     * Retrieves the formatted value of the systolic pressure.
     *
     * @return The formatted systolic pressure value.
     */
    public String getFormattedSys()
    {
        return sys+" mmHg";
    }

    /**
     * Sets the value of the diastolic pressure.
     *
     * @param diastolicPressure The diastolic pressure value.
     */
    public void setDiastolicPressure(String diastolicPressure) {
        this.dis = diastolicPressure;
    }

    /**
     * Retrieves the formatted value of the diastolic pressure.
     *
     * @return The formatted diastolic pressure value.
     */
    public String getFormattedDys() {
        return dis + " mmHg";
    }

    /**
     * Sets the value of the heart rate.
     *
     * @param heartRate The heart rate value.
     */
    public void setHeartRate(String heartRate) {
        this.bpm= heartRate;
    }

    /**
     * Retrieves the formatted value of the heart rate.
     *
     * @return The formatted heart rate value.
     */
    public String getFormattedHeartRate(){
        return bpm+" bpm";
    }


    public DataClass(String date, String time, String sys, String dis, String bpm, String cmnt, String passeduser, String id) {
        this.date = date;
        this.time = time;
        this.sys = sys;
        this.dis = dis;
        this.bpm = bpm;
        this.cmnt = cmnt;
        this.passeduser = passeduser;
        this.id = id;
    }

}
