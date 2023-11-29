package com.ssu.kisyuksa;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ssu.kisyuksa.databinding.ActivityRoungeDeliveryBinding;

public class RoungeDeliveryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRoungeDeliveryBinding binding = ActivityRoungeDeliveryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // launcher 생성
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();

                    }
                }
        );

        binding.branch.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeDelivery2Activity.class);
            intent.putExtra("time",1);
            launcher.launch(intent);
        });

        binding.lunch.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeDelivery2Activity.class);
            intent.putExtra("time",2);
            launcher.launch(intent);
        });

        binding.dinner.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeDelivery2Activity.class);
            intent.putExtra("time",3);
            launcher.launch(intent);
        });

        binding.backButtonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}