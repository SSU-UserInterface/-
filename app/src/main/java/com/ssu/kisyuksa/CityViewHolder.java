package com.ssu.kisyuksa;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView;
    TextView mValueView;

    CityViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(android.R.id.text1);
        mValueView = itemView.findViewById(android.R.id.text2);
    }

    void bind(@NonNull City city) {
        mTextView.setText(city.getName());
        mValueView.setText(city.getCountry());
    }
}
