package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ssu.kisyuksa.databinding.ActivityRoungeChatBinding;
import com.ssu.kisyuksa.databinding.ActivityRoungeChatroomBinding;
import com.ssu.kisyuksa.databinding.ActivityRoungeOttBinding;

public class RoungeChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRoungeChatBinding binding = ActivityRoungeChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        binding.chatWriteButton.setOnClickListener(view -> {
//            //
//            Intent intent = new Intent(this, ActivityRoungeChatroomBinding.class);
//            startActivity(intent);
//        });
        binding.chatBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.chatButton1.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeChatRoomActivity.class);
            startActivity(intent);
        });
        binding.chatButton2.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeChatRoomActivity.class);
            startActivity(intent);
        });
        binding.chatButton3.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeChatRoomActivity.class);
            startActivity(intent);
        });
        binding.chatButton4.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeChatRoomActivity.class);
            startActivity(intent);
        });
        binding.chatButton5.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeChatRoomActivity.class);
            startActivity(intent);
        });
        binding.chatButton6.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeChatRoomActivity.class);
            startActivity(intent);
        });

    }
}