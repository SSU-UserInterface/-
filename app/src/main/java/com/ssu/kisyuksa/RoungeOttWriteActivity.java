package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.ssu.kisyuksa.databinding.ActivityRoungeOttWriteBinding;

public class RoungeOttWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRoungeOttWriteBinding binding = ActivityRoungeOttWriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backButtonOttW.setOnClickListener(v -> {
            finish();
        });

        binding.btnOttWrite.setOnClickListener(v -> {
            String ottText = binding.contentWriteOtt.getText().toString();

            Intent intent = getIntent();
            intent.putExtra("title", "사람 구함");
            intent.putExtra("content", ottText);
            setResult(RESULT_OK,intent);
            finish();
        });
    }
}