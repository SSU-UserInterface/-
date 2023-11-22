package com.ssu.kisyuksa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ssu.kisyuksa.databinding.FragmentMainBoardBinding;
import com.ssu.kisyuksa.databinding.FragmentNoticeAndSleepoverBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainBoardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentMainBoardBinding binding;

    public MainBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoticeBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainBoardFragment newInstance(String param1, String param2) {
        MainBoardFragment fragment = new MainBoardFragment();
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
        binding = FragmentMainBoardBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.repairBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repairBoardList.setVisibility(View.VISIBLE);
                binding.lostItemBoardList.setVisibility(View.GONE);
                binding.roomMateBoardList.setVisibility(View.GONE);
            }
        });
        binding.lostItemBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repairBoardList.setVisibility(View.GONE);
                binding.lostItemBoardList.setVisibility(View.VISIBLE);
                binding.roomMateBoardList.setVisibility(View.GONE);
            }
        });
        binding.roomMateBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.repairBoardList.setVisibility(View.GONE);
                binding.lostItemBoardList.setVisibility(View.GONE);
                binding.roomMateBoardList.setVisibility(View.VISIBLE);
            }
        });

        binding.writeBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), WriteBoardActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    // binding 해제
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}