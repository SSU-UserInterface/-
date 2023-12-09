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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ssu.kisyuksa.databinding.ActivitySleepOutApplicationBinding;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import java.util.UUID;

public class SleepOutApplicationActivity extends AppCompatActivity {

    ActivitySleepOutApplicationBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Intent intent;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySleepOutApplicationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeCloudFirestore();

        intent = getIntent();

        // FirebaseAuth 인스턴스 가져오기
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            user_id = currentUser.getEmail();
            // 필요한 사용자 정보를 사용
        }
        Log.d("jscjj", user_id);

        // fix탭으로 로 열었을 때
        if (intent.hasExtra("FIX") && intent.hasExtra("TITLE")) {
            binding.contents.setText(intent.getStringExtra("CONTENTS"));
        }
        binding.backBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
                if (intent.hasExtra("FIX") && intent.hasExtra("TITLE")){
                    FirebaseFirestore.getInstance().collection("sleep_out_application").
                            document(user_id).collection("sleep_out_application").document(intent.getStringExtra("TITLE")).delete();
                    Intent intent2 = new Intent(SleepOutApplicationActivity.this, SleepOutListActivity.class);
                    intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent2);
                    Log.d("jsc", "리스트화면으로");
                }
                else {
                    finish();
                    Log.d("jsc", "finish");
                }
                Toast.makeText(SleepOutApplicationActivity.this, "외박신청이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //외박 시작 날짜 선택
        binding.calendarStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();

                // 오늘 날짜 임시 저장
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // 일자 선택
                DatePickerDialog dateDialog = new DatePickerDialog(SleepOutApplicationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        binding.dateStart.setText(year + "." + (monthOfYear + 1) + "." + dayOfMonth);
                    }
                }, year, month, day);
                dateDialog.show();
            }
        });

        //외박 종료 날짜 선택
        binding.calendarEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();

                // 오늘 날짜 임시 저장
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // 일자 선택
                DatePickerDialog dateDialog = new DatePickerDialog(SleepOutApplicationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                        binding.dateEnd.setText(year + "." + (monthOfYear + 1) + "." + dayOfMonth);
                    }
                }, year, month, day);
                dateDialog.show();
            }
        });
    }

    private void initializeCloudFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    private void addData() {
        CollectionReference sleep_out_application = db.collection("sleep_out_application");
        Map<String, Object> user_data = new HashMap<>();  // 작성 유저 정보
        Map<String, Object> board_detail_data = new HashMap<>(); // 작성 게시글 정보

        user_data.put("name", user_id);

        board_detail_data.put("contents", binding.contents.getText().toString());
        board_detail_data.put("timestamp", FieldValue.serverTimestamp());
        //board_detail_data.put("date", binding.dateStart.getText().toString());
        board_detail_data.put("start", binding.dateStart.getText().toString());
        board_detail_data.put("end", binding.dateEnd.getText().toString());
        //board_detail_data.put("application_num", );
        //sleep_out_application.document(user_id).set(user_data);

        //UUID.randomUUID() 임시로 게시글 고유 키 설정
        sleep_out_application.document(user_id).collection("sleep_out_application").document(binding.dateStart.getText().toString()).set(board_detail_data);

    }

    // 외박 신청 게시글 수 업데이트
    /*private void get_board_num() {
        DocumentReference docRef = db.collection("sleep_out_application").document(user_id);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    CollectionReference sleep_out_application = db.collection("sleep_out_application");
                    Map<String, Object> user_data = new HashMap<>();
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Map<String, Object> data = document.getData();
                        Log.d("JSC", "DocumentSnapshot data: " + data);

                        application_num = Integer.parseInt(data.get("application_num").toString());
                        application_num += 1;

                    } else {
                        application_num = 1;
                    }
                    Log.d("jsc3", "b" + application_num);
                    user_data.put("num", application_num);
                    sleep_out_application.document(user_id).set(user_data);
                } // get 실패했을 때
                else {
                    Log.d("sleep_out", "게시판 개수 정보 get실패");
                }
            }
        });
    }*/
}