package com.ssu.kisyuksa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    FirebaseFirestore db = FirebaseFirestore.getInstance(); // 파베 객체

    Integer application_num; // 외박 신청 작성 수

    String user_id = "왤케안돼"; //임시 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySleepOutApplicationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeCloudFirestore();

        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get_board_num();
                Log.d("jsc2", "a" + application_num);
                addData();
                //update_board_num();
                finish();
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

                        binding.dateStart.setText(year + "년 " + (monthOfYear + 1) + "월" + dayOfMonth + "일");
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

                        binding.dateEnd.setText(year + "년 " + (monthOfYear + 1) + "월" + dayOfMonth + "일");
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
        board_detail_data.put("write_time", FieldValue.serverTimestamp());
        board_detail_data.put("date", binding.dateStart.getText().toString());
        board_detail_data.put("sleepOutStartDate", binding.dateStart.getText().toString());
        board_detail_data.put("sleepOutEndDate", binding.dateEnd.getText().toString());
        //board_detail_data.put("application_num", );
        //sleep_out_application.document(user_id).set(user_data);

        //UUID.randomUUID() 임시로 게시글 고유 키 설정
        sleep_out_application.document(user_id).collection("sleep_out_application").document(UUID.randomUUID().toString()).set(board_detail_data);

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