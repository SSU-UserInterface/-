package com.ssu.kisyuksa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.firestore.FirebaseFirestore;
import com.ssu.kisyuksa.databinding.ActivityBoardContentsBinding;

public class BoardContentsActivity extends AppCompatActivity {
    ActivityBoardContentsBinding binding;
    String user_id= "TEST";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardContentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String text = intent.getStringExtra("CONTENTS");
        String title = intent.getStringExtra("WRITETIME");
        String user = intent.getStringExtra("USER");
        String type = intent.getStringExtra("TYPE");

        binding.user.setText(user);
        binding.contents.setText(text);
        binding.date.setText(title);

        if(!(user.equals(user_id))) {
            binding.fixDelBox.setVisibility(View.INVISIBLE);
        }
        binding.fixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BoardContentsActivity.this, WriteBoardActivity.class);
                intent.putExtra("TYPE", type);
                intent.putExtra("CONTENTS", text);
                intent.putExtra("TITLE", title);
                intent.putExtra("FIX", true);
                startActivity(intent);
            }
        });
        binding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore.getInstance().collection("board").
                        document(type).collection(type).document(title).delete();
                Log.d("jjsc", "delete");
                finish();
            }
        });
    }
}