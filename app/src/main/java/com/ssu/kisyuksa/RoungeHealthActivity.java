package com.ssu.kisyuksa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ssu.kisyuksa.databinding.ActivityRoungeHealthBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RoungeHealthActivity extends AppCompatActivity {
    String TAG = "TAG";
    ActivityRoungeHealthBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoungeHealthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backButtonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG","등록증 클릭");
                showAlertDialog(RoungeHealthActivity.this, "파일 업로드하기", "내용을 입력하세요");
            }
        });
    }

    private void showAlertDialog(Context context, String title, String message) {
        // AlertDialog.Builder를 사용하여 다이얼로그 생성
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // 제목 설정
        alertDialogBuilder.setTitle(title);

        // 메시지 설정
        alertDialogBuilder.setMessage(message);

        // "확인" 버튼 설정 및 클릭 이벤트 처리
        alertDialogBuilder.setPositiveButton("업로드하기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 여기에 "확인" 버튼이 클릭되었을 때의 동작을 추가
                binding.uploadButton.setVisibility(View.INVISIBLE);
                uploadFromFile();
                dialog.dismiss(); // 다이얼로그 닫기
            }
        });

        // "취소" 버튼 설정 및 클릭 이벤트 처리
        alertDialogBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 여기에 "취소" 버튼이 클릭되었을 때의 동작을 추가
                dialog.dismiss(); // 다이얼로그 닫기
            }
        });

        // 다이얼로그 생성
        AlertDialog alertDialog = alertDialogBuilder.create();

        // 다이얼로그 표시
        alertDialog.show();
    }

    private void uploadFromFile() {
        // Get a default Storage bucket
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Points to the root reference
        StorageReference storageRef = storage.getReference();

        // Create a reference for a new image
        StorageReference riversImagesRef = storageRef.child(getPath("jpg"));

        File file = new File(getFilesDir(), "rivers.jpg");  //getFilesDir() 물리적인 경로가 달라져도 동적으로 찾아줌
        Uri fileUri = Uri.fromFile(file);

        UploadTask uploadTask = riversImagesRef.putFile(fileUri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.d(TAG, "파일 이미지 데이터 업로드 실패");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.d(TAG, "파일 이미지 데이터 업로드 성공");

//                launchDownloadActivity(taskSnapshot.getMetadata().getReference().toString());
                downloadImageTo(taskSnapshot.getMetadata().getReference().toString());
            }
        });
    }

    private void uploadFromStream() {
        // Get a default Storage bucket
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Points to the root reference
        StorageReference storageRef = storage.getReference();

        // Create a reference for a new image
        StorageReference riversImagesRef = storageRef.child(getPath("jpg"));

        try {
            File file = new File(getFilesDir(), "rivers.jpg");
            InputStream stream = new FileInputStream(file);

            UploadTask uploadTask = riversImagesRef.putStream(stream);  //스토리지만 주면 알아서~
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    Log.d(TAG, "스트림으로 빼온 이미지 데이터 업로드 실패");
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                    // ...
                    Log.d(TAG, "스트림으로 빼온 이미지 데이터 업로드 성공");
//                    launchDownloadActivity(taskSnapshot.getMetadata().getReference().toString());
                    downloadImageTo(taskSnapshot.getMetadata().getReference().toString());
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getPath(String extension) {
        String uid = getUidOfCurrentUser();

        String dir = (uid != null) ? uid : "public";

        String fileName = (uid != null) ? (uid + "_" + System.currentTimeMillis() + "." + extension)
                : ("anonymous" + "_" + System.currentTimeMillis() + "." + extension);

        return dir + "/" + fileName;
    }

//    private void launchDownloadActivity(String referenceForDownload) {
////        Intent intent = new Intent(this, RoungeHealthActivity.class);   // 임시 수정
////        intent.putExtra("DOWNLOAD_REF", referenceForDownload);
////        startActivity(intent);
//        downloadImageTo(referenceForDownload);
//    }

    private void signIn() {
        // Initialize Firebase Auth
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String email = "user1@abc.com";
        String password = "123456";

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    private boolean hasSignedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null ? true : false;
    }

    private String getUidOfCurrentUser() {
        return hasSignedIn() ? FirebaseAuth.getInstance().getCurrentUser().getUid() : null;
    }
    private void downloadImageTo(String uri) {
        Log.d(TAG,"다운로드 이미지 함수 실행");
        // Get a default Storage bucket
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a reference to a file from a Google Cloud Storage URI
        StorageReference gsReference = storage.getReferenceFromUrl(uri); // from gs://~~~

        // ImageView in your Activity
        ImageView imageView = binding.downloadedImageview;
        imageView.setVisibility(View.VISIBLE);

        // Download directly from StorageReference using Glide
        // (See MyAppGlideModule for Loader registration)
        Glide.with(this /* context */)
                .load(gsReference)
                .into(imageView);



        Log.d(TAG,"글라이드 함수 실행");
    }

}