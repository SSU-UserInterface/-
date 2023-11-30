package com.ssu.kisyuksa;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.List;

public class SleepApplication {
    private String contents;
    private String date;
    private String sleepOutStartDate;
    private String sleep_out_end_date;

    private int application_num;

    @ServerTimestamp
    private Timestamp timestamp; // server timestamp

    public SleepApplication() {
        this.contents = "";
        this.date = "";
        this.sleepOutStartDate = "";
        this.sleep_out_end_date = "";
    }
    public SleepApplication(String contents, String date, String start_date, String end_date) {
        this.contents = contents;
        this.date = date;
        this.sleepOutStartDate = start_date;
        /*this.sleep_out_end_date = end_date;*/

    }

    public String getContents() {
        return contents;
    }
    public String getDate() {
        return date;
    }
    public String getStartDate() {
        return sleepOutStartDate;
    }
    public String getEndDate() {
        return sleep_out_end_date;
    }

    public int getApplication_num(){
        return application_num;
    }
    public Timestamp getTimestamp() { return timestamp; }
}
