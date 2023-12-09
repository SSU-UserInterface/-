package com.ssu.kisyuksa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.Write;
import com.ssu.kisyuksa.databinding.ActivityWriteBoardBinding;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteBoardActivity extends AppCompatActivity {
    ActivityWriteBoardBinding binding;
    FirebaseFirestore db;
    Intent intent;
    String user_id; //임시 변수
    String type;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWriteBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //user id 얻기
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            user_id = currentUser.getEmail();
        }

        intent = getIntent();

        initializeCloudFirestore();

        if (intent.hasExtra("FIX")) {
            binding.contents.setText(intent.getStringExtra("CONTENTS"));
            title = intent.getStringExtra("TITLE");
        }
        type = intent.getStringExtra("TYPE");
        binding.type.setText(type);

        binding.backBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
                if (intent.hasExtra("FIX")){
                    FirebaseFirestore.getInstance().collection("board").
                            document(type).collection(type).document(title).delete();
                    Intent intent2 = new Intent(WriteBoardActivity.this, MainBoardActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent2);
                }
                else {
                    finish();
                }
                Toast.makeText(WriteBoardActivity.this, "게시글이 작성되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeCloudFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    private void addData() {
        CollectionReference board = db.collection("board");
        Map<String, Object> board_detail_data = new HashMap<>(); // 작성 게시글 정보

        String contents = binding.contents.getText().toString();
        //String type = binding.type.getText().toString();
        String writetime = getCurrentTime();

        board_detail_data.put("user", user_id);
        board_detail_data.put("contents", contents);
        board_detail_data.put("timestamp", FieldValue.serverTimestamp());
        board_detail_data.put("writetime", writetime);
        board_detail_data.put("type", type);

        board.document(type).collection(type).document(writetime).set(board_detail_data);
    }

    private String getCurrentTime() {
        Date currentDate = new Date();

        // 출력 형식 지정
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
        String formattedDate = dateFormat.format(currentDate);
        return formattedDate;
    }
}