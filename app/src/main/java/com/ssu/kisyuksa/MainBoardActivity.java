package com.ssu.kisyuksa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.ssu.kisyuksa.databinding.ActivityMainBoardBinding;
import com.ssu.kisyuksa.databinding.ItemBoardBinding;

public class MainBoardActivity extends AppCompatActivity {
    ActivityMainBoardBinding binding;
    private FirestorePagingAdapter<Board, MainBoardActivity.BoardViewHolder> adapter;
    String user_id;
    String type; // 수리, 분실물, 룸메이트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //user id 얻기
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            user_id = currentUser.getEmail();
        }

        type = "수리";

        binding = ActivityMainBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.writeBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainBoardActivity.this, WriteBoardActivity.class);
                intent.putExtra("TYPE", type);
                startActivity(intent);
            }
        });
    }
    public class BoardViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView contents;
        TextView write_date;
        ItemBoardBinding binding;
        BoardViewHolder(@NonNull ItemBoardBinding binding) {
            super(binding.getRoot());
            title = binding.title;
            contents = binding.contents;
            write_date = binding.writeDate;
            this.binding = binding;
        }
        void bind(@NonNull Board board) {
            contents.setText(board.getContents());
            title.setText(board.getWritetime());

            binding.box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainBoardActivity.this, BoardContentsActivity.class);
                    intent.putExtra("CONTENTS", board.getContents());
                    intent.putExtra("WRITETIME", board.getWritetime());
                    intent.putExtra("USER", board.getUser());
                    intent.putExtra("TYPE", board.getType());
                    startActivity(intent);
                }
            });
        }
    }
    @Override
    protected void onStart() {
        viewBoard();
        super.onStart();
        binding.repairBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "수리";
                binding.recyclerViewFix.setVisibility(View.VISIBLE);
                binding.repairBoard.setTextColor(Color.BLACK);
                binding.recyclerViewLostItem.setVisibility(View.GONE);
                binding.lostItemBoard.setTextColor(Color.parseColor("#b4b4b4"));
                binding.recyclerViewRoomMate.setVisibility(View.GONE);
                binding.roomMateBoard.setTextColor(Color.parseColor("#b4b4b4"));
                viewBoard();
            }
        });
        binding.lostItemBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "분실물";
                viewBoard();
                binding.recyclerViewFix.setVisibility(View.GONE);
                binding.repairBoard.setTextColor(Color.parseColor("#b4b4b4"));
                binding.recyclerViewLostItem.setVisibility(View.VISIBLE);
                binding.lostItemBoard.setTextColor(Color.BLACK);
                binding.recyclerViewRoomMate.setVisibility(View.GONE);
                binding.roomMateBoard.setTextColor(Color.parseColor("#b4b4b4"));
            }
        });
        binding.roomMateBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "룸메이트";
                viewBoard();
                binding.recyclerViewFix.setVisibility(View.GONE);
                binding.repairBoard.setTextColor(Color.parseColor("#b4b4b4"));
                binding.recyclerViewLostItem.setVisibility(View.GONE);
                binding.lostItemBoard.setTextColor(Color.parseColor("#b4b4b4"));
                binding.recyclerViewRoomMate.setVisibility(View.VISIBLE);
                binding.roomMateBoard.setTextColor(Color.BLACK);
            }
        });

    }
    private void viewBoard(){
        Query baseQuery = FirebaseFirestore.getInstance()
                .collection("board").document(type).collection(type)
                .orderBy("writetime", Query.Direction.DESCENDING);

        PagingConfig config = new PagingConfig(4, 2, false);

        FirestorePagingOptions<Board> options = new FirestorePagingOptions.Builder<Board>()
                .setLifecycleOwner(this) // an activity or a fragment
                .setQuery(baseQuery, config, Board.class) // (data, size, data.class)
                .build();

        adapter = new FirestorePagingAdapter<Board, MainBoardActivity.BoardViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BoardViewHolder holder, int position, @NonNull Board model) {
                holder.bind(model);
            }
            @NonNull
            @Override
            public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ItemBoardBinding binding = ItemBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
                return new MainBoardActivity.BoardViewHolder(binding);
            }
        };

        adapter.startListening();
        RecyclerView recyclerView;
        if (type.equals("수리"))
        {recyclerView = binding.recyclerViewFix;}
        else if (type.equals("분실물"))
        {recyclerView = binding.recyclerViewLostItem;Log.d("jjsc", "item");}
        else
        {recyclerView = binding.recyclerViewRoomMate;}
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}