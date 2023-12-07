package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ssu.kisyuksa.databinding.ActivityFirstBinding;

public class FirstActivity extends AppCompatActivity {

    private ActivityFirstBinding binding; //뷰바인딩
    private SharedPreferences sharedPreferences;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        // 이미 유저가 가입했는지 확인
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn || currentUser != null) {
            // If the user is already logged in or there's a current user, go to BnvActivity
            Intent intent = new Intent(FirstActivity.this, BnvActivity.class);
            startActivity(intent);
            finish();
        }

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
