package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ssu.kisyuksa.databinding.ActivitySleepOutListBinding;

public class SleepOutListActivity extends AppCompatActivity {

    ActivitySleepOutListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySleepOutListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sleepOutApplication1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SleepOutListActivity.this, SleepOutListDetailActivity.class);
                startActivity(intent);
            }
        });
    }
}