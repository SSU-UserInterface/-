package com.ssu.kisyuksa;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.ssu.kisyuksa.Chating;
import com.ssu.kisyuksa.ChatingViewHolder;
import com.ssu.kisyuksa.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RoungeChatRoomActivity extends AppCompatActivity {

    private FirestorePagingAdapter<Chating, ChatingViewHolder> adapter;

    FirebaseAuth mAuth;
    FirebaseFirestore db;
    String currentUserUid; // 추가된 부분
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rounge_chatroom);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            currentUserUid = currentUser.getUid();
        } else {
            // Handle the case where the user is not authenticated
            // You might want to redirect the user to the login screen or take appropriate action
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if the user is not authenticated
            return;
        }

        ImageButton backButton = findViewById(R.id.chat_room_back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("FirestorePagingAdapter");

        // The "base query" is a query with no startAt/endAt/limit clauses that the adapter can use
        // to form smaller queries for each page. It should only include where() and orderBy() clauses
        // Firestore에서 데이터 가져오는 부분
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db = FirebaseFirestore.getInstance();
        Query baseQuery = db.collection("chating")
                .orderBy("timestamp", Query.Direction.ASCENDING);

        TextView chat_message = findViewById(R.id.chat_write);
        Button sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetch the nickname from Firestore using the user's UID
                DocumentReference userRef = db.collection("users").document(currentUserUid);
                userRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String nickname = document.getString("nickname");
                            String mMessage = chat_message.getText().toString();
                            addDataOne(nickname, mMessage);
                            chat_message.setText(" ");
                            recreate();
                        } else {
                            Log.d("TAG", "No such document");
                        }
                    } else {
                        Log.d("TAG", "get failed with ", task.getException());
                    }
                });
            }
        });


        // baseQuery로 데이터 가져오기
        baseQuery.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Log.d("TAG", document.getId() + " => " + document.getData());
                        }
                    } else {
                        Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                });


        //orderBy는 정렬해주는 것, query는 내가 질문한 것에 대한 대답

        // This configuration comes from the Paging 3 Library
        // https://developer.android.com/reference/kotlin/androidx/paging/PagingConfig
        PagingConfig config = new PagingConfig(/* page size */ 4, /* prefetchDistance */ 2,
                /* enablePlaceHolders */ false);
        /// 페이징 기술을 쓰게되면 Query가 너무 많을 때 적절히 잘라서 가져온다.
        /// 우리는 몇 개씩 가져올지 결정하면 된다. ex page size 한 번에 4개씩, prefetchDistance: 미리 2개정도 예비, enablePlaceHolders: false
        /// 데이터가 도착이 안 됐을 때 이미 스크롤을 해 버린 경우 대신할 뷰, true로 하면 데이터가 없어서 뷰가 보인다.

        // The options for the adapter combine the paging configuration with query information
        // and application-specific options for lifecycle, etc.
        /// options에 쿼리가 들어가 있음!
        FirestorePagingOptions<Chating> options = new FirestorePagingOptions.Builder<Chating>()
                /// 요 객체를 만들어야 해서 , adapter를 만들 때 넣어줘야하는 객체
                .setLifecycleOwner(this) // an activity or a fragment
                .setQuery(baseQuery, config, Chating.class)
                .build();
        /// 어댑터가 파이어베이스와 지속적으로 연결되어 있다. 최초 쿼리가 날라간 이후에 추가되는 데이터도 adapter에 적용이되서 화면에 나온다.
        adapter = new FirestorePagingAdapter<Chating, ChatingViewHolder>(options) {
            @NonNull
            @Override
            public ChatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Create the ItemViewHolder
                // ...
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_2, parent, false);
                return new ChatingViewHolder(view);
            }

            /// 똑같은 형태 모델은 Chating 타입
            @Override
            protected void onBindViewHolder(@NonNull ChatingViewHolder holder,
                                            int position,
                                            @NonNull Chating model) {
                // Bind the item to the view holder
                // ...
                holder.bind(model);
            }
        };

        RecyclerView recyclerView = findViewById(R.id.recycler_view_chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void addDataOne(String name, String message) {

        CollectionReference cities = db.collection("chating");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("name", name);
        data1.put("message", message);
        data1.put("timestamp", FieldValue.serverTimestamp());

        cities.document().set(data1);    //document 하나가 올라갔다
    }


}