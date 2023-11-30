package com.ssu.kisyuksa;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class SleepOutListActivity extends AppCompatActivity {

    private FirestorePagingAdapter<SleepApplication, SleepApplicationViewHolder> adapter;
    String user_id = "왤케안돼";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_out_list);

        setTitle("FirestorePagingAdapter");

        Query baseQuery = FirebaseFirestore.getInstance()
                .collection("sleep_out_application").document(user_id).collection("sleep_out_application")
                .orderBy("contents", Query.Direction.ASCENDING);

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
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_2, parent, false);
                return new SleepApplicationViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull SleepApplicationViewHolder holder,
                                            int position,
                                            @NonNull SleepApplication model) {
                holder.bind(model);
            }
        };

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public class SleepApplicationViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView contents;

        SleepApplicationViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(android.R.id.text1);
            contents = itemView.findViewById(android.R.id.text2);
        }

        void bind(@NonNull SleepApplication sa) {
            date.setText(sa.getDate()+sa.getStartDate());
            contents.setText(sa.getContents());
        }
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