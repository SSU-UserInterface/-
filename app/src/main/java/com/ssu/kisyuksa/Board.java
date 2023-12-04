package com.ssu.kisyuksa;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Calendar;
import java.util.Date;

public class Board {
    private String type;
    private String contents;
    private String date;
    private String user;
    private String title;
    private String writetime;
    @ServerTimestamp
    private Timestamp timestamp;

    public Board() {
        this.type = "";
        this.title = "";
        this.contents = "";
        this.date = "";
        this.user = "";
        this.writetime = "";
    }
    public Board(String type, String title, String contents, String date, String user, String writetime) {
        this.type = type;
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.user = user;
        this.writetime = writetime;
    }

    public String getType() {
        return type;
    }
    public String getTitle() {return title;}
    public String getContents() {
        return contents;
    }
    public String getDate() {
        return date;
    }
    public String getUser() {
        return user;
    }

    public String getWritetime() { return writetime;}
    public String getTimestamp() {
        Date date = this.timestamp.toDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year + "." + month + "." + day;
    }
}
