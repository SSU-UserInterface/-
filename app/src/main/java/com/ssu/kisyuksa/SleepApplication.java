package com.ssu.kisyuksa;
import android.util.Log;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SleepApplication {
    private String contents;
    private String date;
    private String start;
    private String end;
    @ServerTimestamp
    private Timestamp timestamp;

    public SleepApplication() {
        this.contents = "";
        this.date = "";
        this.start = "";
        this.end = "";
    }
    public SleepApplication(String contents, String date, String start, String end, Timestamp timestamp) {
        this.contents = contents;
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public String getContents() {
        return contents;
    }
    public String getDate() {
        return date;
    }
    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
    public String getTimestamp() {
        Date date = timestamp.toDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "." + month + "." + day;
    }
}
