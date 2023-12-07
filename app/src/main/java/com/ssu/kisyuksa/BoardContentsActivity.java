package com.ssu.kisyuksa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ssu.kisyuksa.databinding.ActivityBoardContentsBinding;

import java.util.HashMap;
import java.util.Map;

public class BoardContentsActivity extends AppCompatActivity {
    ActivityBoardContentsBinding binding;
    String user_id = SignInActivity.email;
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

        Intent intent = getIntent();
        String text = intent.getStringExtra("CONTENTS");
        title = intent.getStringExtra("WRITETIME");
        String user = intent.getStringExtra("USER");
        type = intent.getStringExtra("TYPE");

        binding.user.setText(user);
        binding.contents.setText(text);
        binding.date.setText(title);

        if(!(user.equals(user_id))) {
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
                Log.d("jscj", "댓글 전송 완료");
                subtext = binding.editText.getText().toString();
                addData();
                getADocument();
                Toast.makeText(BoardContentsActivity.this, "댓글이 작성되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeCloudFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    private void getADocument() {
        DocumentReference docRef = db.collection("board").document(type).collection(type).document(title).collection(subtext).document(subtext);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();

                        String contents = (String) data.get("contents");
                        String uesr = (String) data.get("uesr");

                        binding.box1.setVisibility(View.VISIBLE);
                        binding.text1.setText(contents);
                        binding.user1.setText(user_id);
                    } else {
                        Log.d("jsc", "문서 없음");
                    }
                } // get 실패했을 때
                else {
                    Log.d("jsc", "데이터 get 실패 ", task.getException());
                }
            }
        });
    }

    private void addData() {
        CollectionReference board = db.collection("board");
        Map<String, Object> board_sub_text = new HashMap<>(); // 작성 게시글 정보

        String contents = binding.editText.getText().toString();

        board_sub_text.put("user", user_id);
        board_sub_text.put("contents", contents);
        board.document(type).collection(type).document(title).collection(subtext).document(subtext).set(board_sub_text);
    }
}