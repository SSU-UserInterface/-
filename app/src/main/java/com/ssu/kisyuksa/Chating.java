package com.ssu.kisyuksa;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.List;

//Each custom class must have a public constructor that takes no arguments. In addition, the class must include a public getter for each property.
/// 필드 잘 만들고 getter setter도 잘 만들어야 한다
public class Chating {


    private String name;
    private String message;

    private List<String> list;
    @ServerTimestamp private Timestamp timestamp; // server timestamp

    public Chating() {}

    public Chating(String name, String message) {
        this.name = name;
        this.message = message;

    }

    public String getName() {
        return name;
    }

    public String getMessage() {return message;}

    public List<String> getRegions() {
        return list;
    }

    public Timestamp getTimestamp() { return timestamp; }
}
