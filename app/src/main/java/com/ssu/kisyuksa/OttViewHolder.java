package com.ssu.kisyuksa;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OttViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView;
    TextView mValueView;

    OttViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(android.R.id.text1);
        mValueView = itemView.findViewById(android.R.id.text2);
    }

    void bind(@NonNull Ott ott) {
        mTextView.setText(ott.getTitle());
        mValueView.setText(ott.getContent());
    }
}
