package com.ssu.kisyuksa;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

public class Chat {
    private String mName;
    private String mMessage;
    private String mUid;
    @ServerTimestamp private Timestamp timestamp;

    public Chat() { } // Needed for Firebase

    public Chat(String name, String message, String uid) {
        this.mName = name;
        this.mMessage = message;
        this.mUid = uid;
    }

    public String getName() { return mName; }



    public String getMessage() { return mMessage; }



    public String getUid() { return mUid; }

//    @ServerTimestamp
    public Timestamp getTimestamp() { return timestamp; }

}
