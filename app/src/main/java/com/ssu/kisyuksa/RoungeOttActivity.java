package com.ssu.kisyuksa;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ssu.kisyuksa.databinding.ActivityRoungeDeliveryBinding;
import com.ssu.kisyuksa.databinding.ActivityRoungeOttBinding;

public class RoungeOttActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRoungeOttBinding binding = ActivityRoungeOttBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // launcher 생성
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent intent = result.getData();
//                        FireStoreActivity fireStoreActivity = new FireStoreActivity();
//                        addDataOne(intent.getStringExtra("menu"),intent.getStringExtra("num"));
//                        refreshScreen();
                        Log.d("TAG", intent.getStringExtra("ottText"));
                    }
                }
        );
        
        binding.ottWriteButton.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeOttWriteActivity.class);
//            intent.putExtra("time",1);
            launcher.launch(intent);
        });

        binding.backButtonOtt.setOnClickListener(view -> {
            finish();
        });
    }
}