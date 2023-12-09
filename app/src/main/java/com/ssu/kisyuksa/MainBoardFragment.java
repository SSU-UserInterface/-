package com.ssu.kisyuksa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.paging.PagingConfig;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.ssu.kisyuksa.databinding.FragmentMainBoardBinding;
import com.ssu.kisyuksa.databinding.FragmentNoticeAndSleepoverBinding;
import com.ssu.kisyuksa.databinding.ItemBoardBinding;
public class MainBoardFragment extends Fragment {

    FragmentMainBoardBinding binding;

    public MainBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("jsc", "oncreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("jsc", "oncreateView");
        binding = FragmentMainBoardBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Intent intent = new Intent(getActivity(), MainBoardActivity.class);
        startActivity(intent);
        return view;
    }

    @Override
    public void onStart() {
        Log.d("jsc", "onstart");
        super.onStart();
    }

    // binding 해제
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}