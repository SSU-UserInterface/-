package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ssu.kisyuksa.databinding.ActivityMyInfoInputBinding;

import java.util.HashMap;
import java.util.Map;

public class MyInfoInputActivity extends AppCompatActivity {

    private ActivityMyInfoInputBinding binding; // 뷰 바인딩
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyInfoInputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        if (intent != null) {
            email = intent.getStringExtra("EMAIL");
            password = intent.getStringExtra("PASSWORD");
        }

        binding.btnSignupEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 사용자가 입력한 값 가져오기
                String name = binding.etMyinfoinputName.getText().toString();
                String room = binding.etMyinfoinputRoom.getText().toString();
                String room2 = binding.etMyinfoinputRoom3.getText().toString();
                String nickname = binding.etMyinfoinputNickname.getText().toString();

                // 사용자 정보 Firestore에 저장하기
                saveUserInfo(name, room, room2, nickname);
            }
        });
    }

    private void saveUserInfo(String name, String room, String room2, String nickname) {
        // 사용자 생성
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 사용자 생성 성공
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            if (currentUser != null) {
                                String userId = currentUser.getUid();

                                // 사용자 정보를 Firestore에 저장
                                Map<String, Object> user = new HashMap<>();
                                user.put("name", name);
                                user.put("room", room + room2);
                                user.put("nickname",nickname);

                                db.collection("users")
                                        .document(userId)
                                        .set(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    // 회원가입 성공
                                                    Toast.makeText(MyInfoInputActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                                    // SignupActivity로 이동
                                                    Intent intent = new Intent(MyInfoInputActivity.this, SignInActivity.class);
                                                    startActivity(intent);
                                                } else {
                                                    // 회원가입 실패
                                                    Toast.makeText(MyInfoInputActivity.this, "회원가입 실패: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        } else {
                            // 사용자 생성 실패
                            Toast.makeText(MyInfoInputActivity.this, "사용자 생성 실패: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
