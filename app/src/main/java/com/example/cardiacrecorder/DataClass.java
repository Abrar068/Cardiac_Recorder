package com.example.cardiacrecorder;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class DataClass {

    public DataClass() {

    }
    private String passeduser,id;
    private String date;
    private String time;
    private String sys;
    private String dis;
    private String bpm;
    private String cmnt;

    public String getPasseduser() {
        return passeduser;
    }
    public String getId() {
        return id;
    }
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSys() {
        return sys;
    }

    public String getDis() {
        return dis;
    }

    public String getBpm() {
        return bpm;
    }

    public String getCmnt() {
        return cmnt;
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
