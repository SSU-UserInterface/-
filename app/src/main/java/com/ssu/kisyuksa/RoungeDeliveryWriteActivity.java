package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.ssu.kisyuksa.databinding.ActivityRoungeDeliveryWriteBinding;

public class RoungeDeliveryWriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRoungeDeliveryWriteBinding binding = ActivityRoungeDeliveryWriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backButtonW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.deliveryWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String menuText = binding.deliveryMenu.getText().toString();
                String numText = binding.deliveryNum.getText().toString();
                Toast.makeText(RoungeDeliveryWriteActivity.this, "."+menuText+" "+ numText, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}