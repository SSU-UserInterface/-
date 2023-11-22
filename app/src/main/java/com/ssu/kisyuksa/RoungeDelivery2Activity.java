package com.ssu.kisyuksa;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ssu.kisyuksa.databinding.ActivityRoungeDeliveryBinding;
import com.ssu.kisyuksa.databinding.ActivityRoungeDelivey2Binding;

public class RoungeDelivery2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("isaac", "delive2 실행");
        ActivityRoungeDelivey2Binding binding = ActivityRoungeDelivey2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.writeButton.setOnClickListener(view -> {
            //
            Log.d("isaac", "wirteButton");
            Intent intent = new Intent(this, RoungeDeliveryWriteActivity.class);
            startActivity(intent);
        });


    }
}

//        ActivityRoungeDelivey2Binding binding = ActivityRoungeDelivey2Binding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult o) {    //o.getData()가 인텐트
//                        if (o.getResultCode() == RESULT_OK)
//                            Log.d("issac", "callback");
//                        Toast.makeText(RoungeDelivery2Activity.this, "callback ok", Toast.LENGTH_SHORT).show();
//                    }
//                });  //등록해야함
//
//        binding.writeButton.setOnClickListener( v ->  {
//            Intent intent = new Intent(RoungeDelivery2Activity.this, RoungeDeliveryWriteActivity.class);
//            startActivity(intent);
//        });