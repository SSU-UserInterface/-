package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ssu.kisyuksa.databinding.ActivityFirstBinding;
import com.ssu.kisyuksa.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding; //뷰바인딩
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater()); // ViewBinding 초기화
        setContentView(binding.getRoot());

        // 로그인 버튼 클릭
        binding.btnSignin.setOnClickListener(view -> {
            Intent intent = new Intent(SignInActivity.this, BnvActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}