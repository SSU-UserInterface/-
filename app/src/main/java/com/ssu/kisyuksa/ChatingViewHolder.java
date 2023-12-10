package com.ssu.kisyuksa;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatingViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView;
    TextView mValueView;

    ChatingViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(android.R.id.text1);
        mValueView = itemView.findViewById(android.R.id.text2);
    }

    void bind(@NonNull Chating chating) {
        mTextView.setText(chating.getName());
        mValueView.setText(chating.getMessage());
    }
}
