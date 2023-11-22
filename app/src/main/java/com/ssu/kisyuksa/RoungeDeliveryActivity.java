package com.ssu.kisyuksa;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ssu.kisyuksa.databinding.ActivityRoungeDeliveryBinding;

public class RoungeDeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRoungeDeliveryBinding binding = ActivityRoungeDeliveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.branch.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeDelivery2Activity.class);
            startActivity(intent);
        });

        binding.lunch.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeDelivery2Activity.class);
            startActivity(intent);
        });

        binding.dinner.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeDelivery2Activity.class);
            startActivity(intent);
        });

    }
}