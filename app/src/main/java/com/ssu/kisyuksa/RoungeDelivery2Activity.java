package com.ssu.kisyuksa;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.ssu.kisyuksa.databinding.ActivityRoungeDeliveryBinding;
import com.ssu.kisyuksa.databinding.ActivityRoungeDelivey2Binding;

public class RoungeDelivery2Activity extends AppCompatActivity {

    private FirestorePagingAdapter<City, CityViewHolder> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent_Back = getIntent();
        int time3 = intent_Back.getIntExtra("time",0);
        Log.d("isaac", "delive2 실행");
        ActivityRoungeDelivey2Binding binding = ActivityRoungeDelivey2Binding.inflate(getLayoutInflater());
        if (time3 == 1) {
            binding.branch.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            binding.lunch.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
            binding.dinner.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        } else if (time3 == 2) {
            binding.branch.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
            binding.lunch.setTextColor(ContextCompat.getColor(this, android.R.color.black));
            binding.dinner.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
        } else if (time3 == 3) {
            binding.branch.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
            binding.lunch.setTextColor(ContextCompat.getColor(this, android.R.color.darker_gray));
            binding.dinner.setTextColor(ContextCompat.getColor(this, android.R.color.black));
        }
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

        binding.writeButton.setOnClickListener(view -> {
            //
            Intent intent = new Intent(this, RoungeDeliveryWriteActivity.class);
//            intent.putExtra("time",1);
            launcher.launch(intent);

        });

        binding.backButtonD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // fireStore 연동(리사이클러뷰)
        setTitle("FirestorePagingAdapter");

        // The "base query" is a query with no startAt/endAt/limit clauses that the adapter can use
        // to form smaller queries for each page. It should only include where() and orderBy() clauses
        Query baseQuery = FirebaseFirestore.getInstance()
                // 파이어베이스 접근
                .collection("cities")
                // 양이 어마어마할 수 있다.
                .orderBy("name", Query.Direction.ASCENDING);
        //orderBy는 정렬해주는 것, query는 내가 질문한 것에 대한 대답

        // This configuration comes from the Paging 3 Library
        // https://developer.android.com/reference/kotlin/androidx/paging/PagingConfig
        PagingConfig config = new PagingConfig(/* page size */ 4, /* prefetchDistance */ 2,
                /* enablePlaceHolders */ false);
        /// 페이징 기술을 쓰게되면 Query가 너무 많을 때 적절히 잘라서 가져온다.
        /// 우리는 몇 개씩 가져올지 결정하면 된다. ex page size 한 번에 4개씩, prefetchDistance: 미리 2개정도 예비, enablePlaceHolders: false
        /// 데이터가 도착이 안 됐을 때 이미 스크롤을 해 버린 경우 대신할 뷰, true로 하면 데이터가 없어서 뷰가 보인다.

        // The options for the adapter combine the paging configuration with query information
        // and application-specific options for lifecycle, etc.
        /// options에 쿼리가 들어가 있음!
        FirestorePagingOptions<City> options = new FirestorePagingOptions.Builder<City>()
                /// 요 객체를 만들어야 해서 , adapter를 만들 때 넣어줘야하는 객체
                .setLifecycleOwner(this) // an activity or a fragment
                .setQuery(baseQuery, config, City.class)
                .build();
        /// 어댑터가 파이어베이스와 지속적으로 연결되어 있다. 최초 쿼리가 날라간 이후에 추가되는 데이터도 adapter에 적용이되서 화면에 나온다.
        adapter = new FirestorePagingAdapter<City, CityViewHolder>(options) {
            @NonNull
            @Override
            public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Create the ItemViewHolder
                // ...
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_2, parent, false);
                return new CityViewHolder(view);
            }

            /// 똑같은 형태 모델은 City 타입
            @Override
            protected void onBindViewHolder(@NonNull CityViewHolder holder,
                                            int position,
                                            @NonNull City model) {
                // Bind the item to the view holder
                // ...
                holder.bind(model);
            }
        };

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}

