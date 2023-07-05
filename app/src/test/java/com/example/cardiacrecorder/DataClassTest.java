package com.example.cardiacrecorder;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.cardiacrecorder.DataClass;

public class DataClassTest {

    @Test
    public void testGettersAndSetters() {
        String date = "2023-07-05";
        String time = "10:30 AM";
        String sys = "120";
        String dis = "80";
        String bpm = "70";
        String cmnt = "Normal";
        String passeduser = "JohnDoe";
        String id = "12345";

        DataClass data = new DataClass(date, time, sys, dis, bpm, cmnt, passeduser, id);

        assertEquals("2023-07-05", data.getDate());
        assertEquals("10:30 AM", data.getTime());
        assertEquals("120", data.getSys());
        assertEquals("80", data.getDis());
        assertEquals("70", data.getBpm());
        assertEquals("Normal", data.getCmnt());
        assertEquals("JohnDoe", data.getPasseduser());
        assertEquals("12345", data.getId());

        String newSys = "130";
        data.setSystolicPressure(newSys);
        assertEquals("130", data.getSys());

        String newDis = "90";
        data.setDiastolicPressure(newDis);
        assertEquals("90", data.getDis());

        String newBpm = "80";
        data.setHeartRate(newBpm);
        assertEquals("80", data.getBpm());
    }

    @Test
    public void testFormattedSys() {

        String sys = "120";
        DataClass data = new DataClass();
        data.setSystolicPressure(sys);


        String expectedFormattedSys = sys + " mmHg";
        assertEquals("120 mmHg", data.getFormattedSys());
    }

    @Test
    public void testFormattedDys() {

        String dis = "80";
        DataClass data = new DataClass();
        data.setDiastolicPressure(dis);


        String expectedFormattedDys = dis + " mmHg";
        assertEquals("80 mmHg", data.getFormattedDys());
    }

    @Test
    public void testFormattedHeartRate() {

        String bpm = "70";
        DataClass data = new DataClass();
        data.setHeartRate(bpm);


        String expectedFormattedHeartRate = bpm + " bpm";
        assertEquals("70 bpm", data.getFormattedHeartRate());
    }
}
