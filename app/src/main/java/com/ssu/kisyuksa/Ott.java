package com.ssu.kisyuksa;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.List;

//Each custom class must have a public constructor that takes no arguments. In addition, the class must include a public getter for each property.
/// 필드 잘 만들고 getter setter도 잘 만들어야 한다
public class Ott {
    private String title;
    private String content;

    private int partyNum;
    private int currentNum;

    private List<String> list;
    @ServerTimestamp private Timestamp timestamp; // server timestamp

    public Ott() {}

    public Ott(String title, String content, int partyNum, int currentNum, List<String> list) {
        this.title = title;
        this.content = content;
        this.partyNum = partyNum;
        this.currentNum = currentNum;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getPartyNum() {
        return partyNum;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public List<String> getList() {
        return list;
    }

    public Timestamp getTimestamp() { return timestamp; }
}
