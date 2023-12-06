package com.ssu.kisyuksa;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

public class Chat {
    private String mName;
    private String mMessage;
    private String mUid;
    @ServerTimestamp private Timestamp mTimestamp;

    public Chat() { } // Needed for Firebase

    public Chat(String name, String message, String uid) {
        mName = name;
        mMessage = message;
        mUid = uid;
    }

    public String getName() { return mName; }

    public void setName(String name) { mName = name; }

    public String getMessage() { return mMessage; }

    public void setMessage(String message) { mMessage = message; }

    public String getUid() { return mUid; }

    public void setUid(String uid) { mUid = uid; }

    @ServerTimestamp
    public Timestamp getTimestamp() { return mTimestamp; }

    public void setTimestamp(Timestamp timestamp) { mTimestamp = timestamp; }
}
