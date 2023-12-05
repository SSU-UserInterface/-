package com.ssu.kisyuksa;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.List;

//Each custom class must have a public constructor that takes no arguments. In addition, the class must include a public getter for each property.
/// 필드 잘 만들고 getter setter도 잘 만들어야 한다
public class DeliveryMenu {
    private String menu;
    private String chatRoom;
    private int numText;
    private int maxNum;

    private List<String> list;      //예비
    @ServerTimestamp private Timestamp timestamp; // server timestamp

    public DeliveryMenu() {}

    public DeliveryMenu(String menu, String chatRoom, int numText, int maxNum) {
        this.menu = menu;
        this.chatRoom = chatRoom;
        this.numText = numText;
        this.maxNum = maxNum;
    }

    public String getMenu() {
        return menu;
    }

    public String getChatRoom() {
        return chatRoom;
    }

    public int getNumText() {
        return numText;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public List<String> getList() {
        return list;
    }

    public Timestamp getTimestamp() { return timestamp; }
}
