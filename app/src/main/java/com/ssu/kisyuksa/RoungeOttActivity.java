package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ssu.kisyuksa.databinding.ActivityRoungeDeliveryBinding;
import com.ssu.kisyuksa.databinding.ActivityRoungeOttBinding;

public class RoungeOttActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRoungeOttBinding binding = ActivityRoungeOttBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        
        binding.ottWriteButton.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeOttWriteActivity.class);
            startActivity(intent);
        });
    }
}