package com.ssu.kisyuksa;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ssu.kisyuksa.databinding.ActivityMyPageBinding;

public class MyPageActivity extends AppCompatActivity {
    private ActivityMyPageBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            // Firestore에서 해당 사용자 정보 가져오기
            DocumentReference userRef = db.collection("users").document(userId);
            userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String name = document.getString("name");
                            String nickname = document.getString("nickname");
                            String stId = document.getString("stId");
                            String room = document.getString("room");

                            // 가져온 정보를 화면에 반영
                            binding.tvMypageName.setText(name);
                            binding.tvMypageNickname.setText(nickname);
                            binding.tvMypageStId.setText(stId);
                            if (room != null && !room.isEmpty() && room.length() >= 2) {
                                String number = room.replaceAll("[^0-9]", ""); // 숫자 추출
                                String alpha = room.replaceAll("[^a-zA-Z]", ""); // 문자 추출

                                binding.tvMypageNumber.setText(number);
                                binding.tvMypageAlpha.setText(alpha);
                            }
                        }
                    } else {
                        // 사용자 정보 가져오기 실패 시 처리
                        Toast.makeText(MyPageActivity.this, "사용자 정보 가져오기 실패", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        // btn_mypage_name_change 클릭 시 이벤트 처리
        binding.btnMypageNameChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다이얼로그 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPageActivity.this);
                builder.setIcon(R.drawable.ic_dialog);
                builder.setTitle("이름 변경");

                // 다이얼로그에 EditText 추가
                final EditText input = new EditText(MyPageActivity.this);
                input.setHint("새로운 이름을 입력하세요");
                builder.setView(input);

                // 다이얼로그 버튼 설정
                builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String newName = input.getText().toString().trim();

                        // Firebase에서 현재 사용자 가져오기
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if (currentUser != null) {
                            String userId = currentUser.getUid();

                            // Firestore에서 해당 사용자 문서 가져오기
                            DocumentReference userRef = db.collection("users").document(userId);
                            userRef.update("name", newName)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // 이름 업데이트 성공
                                                Toast.makeText(MyPageActivity.this, "이름이 업데이트되었습니다.", Toast.LENGTH_SHORT).show();

                                                // 이름을 TextView에 반영
                                                binding.tvMypageName.setText(newName); // 변경된 이름을 tv_mypage_name에 반영
                                            } else {
                                                // 이름 업데이트 실패
                                                Toast.makeText(MyPageActivity.this, "이름 업데이트 실패: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 취소 버튼을 누르면 다이얼로그를 닫습니다.
                        dialog.cancel();
                    }
                });

                // 다이얼로그 생성 및 표시
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
            }
        });

        // btn_mypage_nickname_change 클릭 시 이벤트 처리
        binding.btnMypageNicknameChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다이얼로그 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPageActivity.this);
                builder.setIcon(R.drawable.ic_dialog);
                builder.setTitle("닉네임 변경");

                // 다이얼로그에 EditText 추가
                final EditText input = new EditText(MyPageActivity.this);
                input.setHint("새로운 닉네임을 입력하세요");
                builder.setView(input);

                // 다이얼로그 버튼 설정
                builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String newNickname = input.getText().toString().trim();

                        // Firebase에서 현재 사용자 가져오기
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if (currentUser != null) {
                            String userId = currentUser.getUid();

                            // Firestore에서 해당 사용자 문서 가져오기
                            DocumentReference userRef = db.collection("users").document(userId);
                            userRef.update("nickname", newNickname)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // 닉네임 업데이트 성공
                                                Toast.makeText(MyPageActivity.this, "닉네임이 업데이트되었습니다.", Toast.LENGTH_SHORT).show();

                                                // 닉네임을 TextView에 반영
                                                binding.tvMypageNickname.setText(newNickname); // 변경된 닉네임을 tv_mypage_nickname에 반영
                                            } else {
                                                // 닉네임 업데이트 실패
                                                Toast.makeText(MyPageActivity.this, "닉네임 업데이트 실패: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 취소 버튼을 누르면 다이얼로그를 닫습니다.
                        dialog.cancel();
                    }
                });

                // 다이얼로그 생성 및 표시
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
            }
        });

        // btn_mypage_status_change 클릭 시 이벤트 처리
        binding.btnMypageStatusChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 다이얼로그 생성
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPageActivity.this);
                builder.setIcon(R.drawable.ic_dialog);
                builder.setTitle("방 번호 변경");

                // 다이얼로그에 EditText 추가
                final EditText input = new EditText(MyPageActivity.this);
                input.setHint("새로운 방 번호를 입력하세요");
                builder.setView(input);

                // 다이얼로그 버튼 설정
                builder.setPositiveButton("저장", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String newRoom = input.getText().toString().trim();

                        // Firebase에서 현재 사용자 가져오기
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        if (currentUser != null) {
                            String userId = currentUser.getUid();

                            // Firestore에서 해당 사용자 문서 가져오기
                            DocumentReference userRef = db.collection("users").document(userId);
                            userRef.update("room", newRoom)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // 방 번호 업데이트 성공
                                                Toast.makeText(MyPageActivity.this, "방 번호가 업데이트되었습니다.", Toast.LENGTH_SHORT).show();

                                                // 방 번호를 숫자와 문자로 분리하여 TextView에 반영
                                                if (newRoom != null && !newRoom.isEmpty() && newRoom.length() >= 2) {
                                                    String number = newRoom.replaceAll("[^0-9]", ""); // 숫자 추출
                                                    String alpha = newRoom.replaceAll("[^a-zA-Z]", ""); // 문자 추출

                                                    binding.tvMypageNumber.setText(number); // 숫자를 tv_mypage_number에 반영
                                                    binding.tvMypageAlpha.setText(alpha); // 문자를 tv_mypage_alpha에 반영
                                                }
                                            } else {
                                                // 방 번호 업데이트 실패
                                                Toast.makeText(MyPageActivity.this, "방 번호 업데이트 실패: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 취소 버튼을 누르면 다이얼로그를 닫습니다.
                        dialog.cancel();
                    }
                });

                // 다이얼로그 생성 및 표시
                AlertDialog dialog = builder.create();
                dialog.setCancelable(false);
                dialog.show();
            }
        });





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

                                mAuth.signOut();

                                //첫 화면으로 이동
                                Intent intent = new Intent(MyPageActivity.this, FirstActivity.class);
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

                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                if (currentUser != null) {
                                    // 사용자 삭제
                                    currentUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // 사용자 계정 삭제 성공

                                                // Firestore에서 해당 사용자 데이터 삭제
                                                String userId = currentUser.getUid();
                                                db.collection("users").document(userId)
                                                        .delete()
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    // Firestore에서 사용자 데이터 삭제 성공
                                                                    Toast.makeText(MyPageActivity.this, "계정과 데이터가 삭제되었습니다.", Toast.LENGTH_SHORT).show();

                                                                    // 로그인 화면으로 이동
                                                                    Intent intent = new Intent(MyPageActivity.this, SignInActivity.class);
                                                                    startActivity(intent);
                                                                    finish();
                                                                } else {
                                                                    // Firestore에서 사용자 데이터 삭제 실패
                                                                    Toast.makeText(MyPageActivity.this, "Firestore 데이터 삭제 실패: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                            } else {
                                                // 계정 삭제 실패
                                                Toast.makeText(MyPageActivity.this, "계정 삭제 실패: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
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