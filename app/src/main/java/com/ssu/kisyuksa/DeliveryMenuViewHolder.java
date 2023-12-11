package com.ssu.kisyuksa;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DeliveryMenuViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView;
    TextView mValueView;

    DeliveryMenuViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(android.R.id.text1);
        mValueView = itemView.findViewById(android.R.id.text2);
    }

    void bind(@NonNull DeliveryMenu deliveryMenu) {
        String message = 1 + " / " + deliveryMenu.getMaxNum() ;
        mTextView.setText(deliveryMenu.getMenu());
        mValueView.setText(message);
    }
}
