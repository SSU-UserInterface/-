package com.ssu.kisyuksa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ssu.kisyuksa.databinding.ActivityMyPageBinding;

public class MyPageActivity extends AppCompatActivity {
    private ActivityMyPageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //로그아웃 다이얼로그
        binding.tvMypageLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(MyPageActivity.this)
                        .setIcon(R.drawable.ic_dialog)
                        .setMessage("정말 로그아웃 하시겠어요?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                //로그인 화면으로 이동
                                Intent intent = new Intent(MyPageActivity.this, SignInActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();

                alertDialog.setTitle("확인 필요");
                alertDialog.setCancelable(false);
                alertDialog.show();
            }
        });

        //회원탈퇴 다이얼로그
        binding.tvMypageWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(MyPageActivity.this)
                        .setIcon(R.drawable.ic_dialog)
                        .setMessage("탈퇴 시 모든 정보가 사라집니다.\n 정말 탈퇴 하시겠어요?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                //로그인 화면으로 이동
                                Intent intent = new Intent(MyPageActivity.this, SignInActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();

                alertDialog.setTitle("확인 필요");
                alertDialog.setCancelable(false);
                alertDialog.show();
            }
        });

    }
}