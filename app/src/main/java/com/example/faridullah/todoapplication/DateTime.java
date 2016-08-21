package com.example.faridullah.todoapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Farid ullah on 6/20/2016.
 */
public class DateTime extends Activity{
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minutes;
    private int format;

    public int getDay() {

        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getYear() {

        return year;
    }

    public int getMinute() {
        return minutes;
        
    }

    public int getMonth() {

        return month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDate(int day, int month , int year){
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
    }

    public void setTime(int hour, int minutes, int format){
        this.setMinutes(minutes);
        this.setHour(hour);
        this.setFormat(format);
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getFormat() {
        return format;
    }
}

