package com.ssu.kisyuksa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssu.kisyuksa.databinding.FragmentRoungeMainBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RoungeMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RoungeMainFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RoungeMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoungeMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoungeMainFragment newInstance(String param1, String param2) {
        RoungeMainFragment fragment = new RoungeMainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRoungeMainBinding binding = FragmentRoungeMainBinding.inflate(inflater, container, false);

        binding.chatButton.setOnClickListener(view -> {
            // chatButton 눌렸을 때 작동하는 함수
            Intent intent = new Intent(getActivity(), RoungeChatActivity.class);
            startActivity(intent);
        });

        binding.deliveryButton.setOnClickListener(view -> {
            // chatButton 눌렸을 때 작동하는 함수
            Intent intent = new Intent(getActivity(), RoungeDeliveryActivity.class);
            startActivity(intent);
        });

        binding.ottButton.setOnClickListener(view -> {
            // chatButton 눌렸을 때 작동하는 함수
            Intent intent = new Intent(getActivity(), RoungeOttActivity.class);
            startActivity(intent);
        });

        binding.healthButton.setOnClickListener(view -> {
            // chatButton 눌렸을 때 작동하는 함수
            Intent intent = new Intent(getActivity(), RoungeHealthActivity.class);
            startActivity(intent);
        });

        // 수정: 이미 바인딩된 View를 반환합니다.
        return binding.getRoot();
    }

}