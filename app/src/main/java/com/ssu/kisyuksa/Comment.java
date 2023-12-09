package com.ssu.kisyuksa;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Calendar;
import java.util.Date;

public class Comment {
    private String contents;
    private String username;
    private String writetime;
    String wt;
    @ServerTimestamp
    private Timestamp timestamp;

    public Comment() {
        this.contents = "";
        this.writetime = "";
    }
    public Comment(String contents, String username, String writetime) {
        this.contents = contents;
        this.username = username;
        this.writetime = writetime;
    }
    public String getContents() {
        return contents;
    }
    public String getUsername() {
        return username;
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

