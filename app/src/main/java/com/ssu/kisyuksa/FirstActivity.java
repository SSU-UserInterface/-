package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.ssu.kisyuksa.databinding.ActivityFirstBinding;

public class FirstActivity extends AppCompatActivity {

    private ActivityFirstBinding binding; //뷰바인딩

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstBinding.inflate(getLayoutInflater()); // ViewBinding 초기화
        setContentView(binding.getRoot());

        // 로그인 버튼 클릭
        binding.btnSigninFirst.setOnClickListener(view -> {
            Intent intent = new Intent(FirstActivity.this, SignInActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        // 회원가입 버튼 클릭
        binding.btnSignupFirst.setOnClickListener(view -> {
            Intent intent = new Intent(FirstActivity.this, SignUpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish(); // 현재 액티비티 종료
        });
    }
}
