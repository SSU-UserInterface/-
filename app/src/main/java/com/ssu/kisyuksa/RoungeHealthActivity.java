package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ssu.kisyuksa.databinding.ActivityRoungeHealthBinding;

public class RoungeHealthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRoungeHealthBinding binding = ActivityRoungeHealthBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_rounge_health);

        binding.backButtonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}