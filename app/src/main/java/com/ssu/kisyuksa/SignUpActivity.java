package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ssu.kisyuksa.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding; //뷰바인딩

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater()); // ViewBinding 초기화
        setContentView(binding.getRoot());

        binding.btnSignupNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 입력된 이메일과 비밀번호 가져오기
                String email = binding.etSignupEmail.getText().toString();
                String password = binding.etSignupRepw.getText().toString();

                // 정보를 담을 인텐트 생성
                Intent intent = new Intent(SignUpActivity.this, MyInfoInputActivity.class);
                intent.putExtra("EMAIL", email);
                intent.putExtra("PASSWORD", password);

                // 정보를 가지고 MyInfoInputActivity 시작
                startActivity(intent);
            }
        });
    }
}