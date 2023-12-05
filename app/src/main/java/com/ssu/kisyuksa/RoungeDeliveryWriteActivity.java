package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
                String menu = binding.deliveryMenu.getText().toString();
                //int numText = Integer.parseInt(binding.deliveryNum.getText().toString());
                int maxNum = Integer.parseInt(binding.deliveryNum.getText().toString());
                String chatRoom = binding.chatRoomText.getText().toString();


                Toast.makeText(RoungeDeliveryWriteActivity.this, "."+menu+" "+ maxNum, Toast.LENGTH_SHORT).show();

                Intent intent = getIntent();
                intent.putExtra("menu", menu);
                //intent.putExtra("numText",numText);
                intent.putExtra("maxNum" , maxNum);
                intent.putExtra("chatRoom", chatRoom);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }


}