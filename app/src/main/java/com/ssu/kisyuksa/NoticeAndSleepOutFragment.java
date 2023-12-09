package com.ssu.kisyuksa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssu.kisyuksa.databinding.FragmentNoticeAndSleepoverBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoticeAndSleepOutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeAndSleepOutFragment extends Fragment {
    FragmentNoticeAndSleepoverBinding binding; //binding 변수

    public NoticeAndSleepOutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNoticeAndSleepoverBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.sleepoverApplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), SleepOutApplicationActivity.class);
                startActivity(intent);
            }
        });

        binding.dietButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), DormitoryMenuActivity.class);
                startActivity(intent);
            }
        });

        binding.sleepoverListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), SleepOutListActivity.class);
                startActivity(intent);
            }
        });

        binding.noticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding=null;
    }
}