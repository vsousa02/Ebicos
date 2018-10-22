package com.example.util.ebicos.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.util.ebicos.Interface.ItemClickListener;
import com.example.util.ebicos.R;

public class ProfViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView item_name, item_sobrenome, item_prof, item_desc;
    public ImageView item_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ProfViewHolder(View itemView) {
        super(itemView);

        item_name = itemView.findViewById(R.id.item_name);
        item_sobrenome = itemView.findViewById(R.id.item_sobrenome);
        item_prof = itemView.findViewById(R.id.item_prof);
        item_desc = itemView.findViewById(R.id.item_desc);
        item_image = itemView.findViewById(R.id.item_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }
}
