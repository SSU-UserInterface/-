package com.ssu.kisyuksa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.ssu.kisyuksa.databinding.ActivitySleepOutListBinding;
import com.ssu.kisyuksa.databinding.ItemBoardBinding;

public class SleepOutListActivity extends AppCompatActivity {

    private FirestorePagingAdapter<SleepApplication, SleepApplicationViewHolder> adapter;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("jj", "oncreate");
        super.onCreate(savedInstanceState);

        //user id 얻기
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            user_id = currentUser.getEmail();
        }

        ActivitySleepOutListBinding binding2 = ActivitySleepOutListBinding.inflate(getLayoutInflater());
        setContentView(binding2.getRoot());
        setTitle("FirestorePagingAdapter");

        binding2.backBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        /*setTitle("FirestorePagingAdapter");

        Query baseQuery = FirebaseFirestore.getInstance()
                .collection("sleep_out_application").document(user_id).collection("sleep_out_application")
                .orderBy("start", Query.Direction.DESCENDING);

        PagingConfig config = new PagingConfig(*//* page size *//* 4, *//* prefetchDistance *//* 2,
                *//* enablePlaceHolders *//* false);

        FirestorePagingOptions<SleepApplication> options = new FirestorePagingOptions.Builder<SleepApplication>()
                .setLifecycleOwner(this) // an activity or a fragment
                .setQuery(baseQuery, config, SleepApplication.class) // (data, size, data.class)
                .build();


        adapter = new FirestorePagingAdapter<SleepApplication, SleepApplicationViewHolder>(options) {
            @NonNull
            @Override
            public SleepApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ItemBoardBinding binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new SleepApplicationViewHolder(binding);
            }

            @Override
            protected void onBindViewHolder(@NonNull SleepApplicationViewHolder holder, int position, @NonNull SleepApplication model)
            {
                holder.bind(model);
            }
        };*/
    }

    public class SleepApplicationViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView contents;
        TextView write_date;
        ItemBoardBinding binding;

        SleepApplicationViewHolder(@NonNull ItemBoardBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            contents = binding.contents;
            write_date = binding.writeDate;
            this.binding = binding;
        }

        void bind(@NonNull SleepApplication sa) {
            title.setText(sa.getStart()+" ~ "+sa.getEnd()+"   외박신청");
            contents.setText(sa.getContents());
            //write_date.setText(sa.getTimestamp());

            binding.box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SleepOutListActivity.this, SleepOutListDetailActivity.class);

                    intent.putExtra("CONTENTS", sa.getContents());
                    intent.putExtra("TITLE", sa.getStart());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onStart() {

        Query baseQuery = FirebaseFirestore.getInstance()
                .collection("sleep_out_application").document(user_id).collection("sleep_out_application")
                .orderBy("start", Query.Direction.DESCENDING);

        PagingConfig config = new PagingConfig(/* page size */ 4, /* prefetchDistance */ 2,
                /* enablePlaceHolders */ false);

        FirestorePagingOptions<SleepApplication> options = new FirestorePagingOptions.Builder<SleepApplication>()
                .setLifecycleOwner(this) // an activity or a fragment
                .setQuery(baseQuery, config, SleepApplication.class) // (data, size, data.class)
                .build();


        adapter = new FirestorePagingAdapter<SleepApplication, SleepApplicationViewHolder>(options) {
            @NonNull
            @Override
            public SleepApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ItemBoardBinding binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new SleepApplicationViewHolder(binding);
            }

            @Override
            protected void onBindViewHolder(@NonNull SleepApplicationViewHolder holder, int position, @NonNull SleepApplication model)
            {
                holder.bind(model);
            }
        };
        super.onStart();
        adapter.startListening();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        Log.d("jj", "onstop");
        super.onStop();
        adapter.stopListening();
    }
}