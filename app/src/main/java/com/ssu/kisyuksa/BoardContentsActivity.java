package com.ssu.kisyuksa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.ssu.kisyuksa.databinding.ActivityBoardContentsBinding;
import com.ssu.kisyuksa.databinding.CommentBinding;
import com.ssu.kisyuksa.databinding.ItemBoardBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BoardContentsActivity extends AppCompatActivity {
    ActivityBoardContentsBinding binding;
    private FirestorePagingAdapter<Comment, BoardContentsActivity.CommentViewHolder> adapter;
    String user_uid;
    String user_email;
    String user_name;
    String type;
    String title;
    String subtext; // 댓글
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardContentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeCloudFirestore();

        getName();


        // 게시글 정보
        Intent intent = getIntent();
        String text = intent.getStringExtra("CONTENTS");
        title = intent.getStringExtra("WRITETIME");
        String user = intent.getStringExtra("USER");
        type = intent.getStringExtra("TYPE");

        binding.user.setText(user);
        binding.contents.setText(text);
        binding.date.setText(title);

        if(!(user.equals(user_email))) {
            binding.fixDelBox.setVisibility(View.INVISIBLE);
        }
        binding.fixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BoardContentsActivity.this, WriteBoardActivity.class);
                intent.putExtra("TYPE", type);
                intent.putExtra("CONTENTS", text);
                intent.putExtra("TITLE", title);
                intent.putExtra("FIX", true);
                startActivity(intent);
            }
        });
        binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore.getInstance().collection("board").
                        document(type).collection(type).document(title).delete();
                Log.d("jjsc", "delete");
                finish();
            }
        });

        binding.backBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtext = binding.editText.getText().toString();
                addData();
                makeComment();
                Toast.makeText(BoardContentsActivity.this, "댓글이 작성되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        viewComment();
    }

    private void initializeCloudFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    private void makeComment() {
        viewComment();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView contents;
        TextView write_date;
        CommentBinding binding;
        CommentViewHolder(@NonNull CommentBinding binding) {
            super(binding.getRoot());
            title = binding.user;
            contents = binding.text;
            write_date = binding.time;
            this.binding = binding;
        }
        void bind(@NonNull Comment comment) {
            contents.setText(comment.getContents());
            title.setText(comment.getUsername());
            write_date.setText(comment.getWritetime());
        };
    }

    private void viewComment(){
        //document(type).collection(type).document(title).collection("comment").document(subtext).set(board_sub_text);
        Query baseQuery = FirebaseFirestore.getInstance()
                .collection("board").document(type).collection(type).document(title).collection("comment")
                .orderBy("writetime", Query.Direction.DESCENDING);

        PagingConfig config = new PagingConfig(4, 2, false);

        FirestorePagingOptions<Comment> options = new FirestorePagingOptions.Builder<Comment>()
                .setLifecycleOwner(this) // an activity or a fragment
                .setQuery(baseQuery, config, Comment.class) // (data, size, data.class)
                .build();

        adapter = new FirestorePagingAdapter<Comment, CommentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CommentViewHolder holder, int position, @NonNull Comment model) {
                holder.bind(model);
            }
            @NonNull
            @Override
            public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                CommentBinding binding = CommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new CommentViewHolder(binding);
            }
        };

        adapter.startListening();
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void addData() {
        CollectionReference board = db.collection("board");
        Map<String, Object> board_sub_text = new HashMap<>(); // 작성 게시글 정보

        String contents = binding.editText.getText().toString();
        Log.d("jjjsc", contents);
        String writetime = getCurrentTime();

        board_sub_text.put("username", user_name);
        board_sub_text.put("contents", contents);
        board_sub_text.put("timestamp", FieldValue.serverTimestamp());
        board_sub_text.put("writetime", writetime);
        board_sub_text.put("type", type);
        board.document(type).collection(type).document(title).collection("comment").document(subtext).set(board_sub_text);
    }

    private void getName() {
        //user id 얻기 및 닉네임 추출
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            user_email = currentUser.getEmail();
            user_uid = currentUser.getUid();
            Log.d("jjsc", user_uid);
        }

        DocumentReference docRef = db.collection("users").document(user_uid);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    user_name = document.get("nickname").toString();
                }
            }
        });
    }

    private String getCurrentTime() {
        Date currentDate = new Date();

        // 출력 형식 지정
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
        String formattedDate = dateFormat.format(currentDate);
        return formattedDate;
    }
}