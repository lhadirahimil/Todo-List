package com.hadirahimi.todo_list.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hadirahimi.todo_list.Interface.Category_Click;
import com.hadirahimi.todo_list.Model.ModelCategoryHorizontal;
import com.hadirahimi.todo_list.R;

import java.util.ArrayList;

public class AdapterCategoryHorizontal extends RecyclerView.Adapter<AdapterCategoryHorizontal.myViewHolder> {
    private Context context;
    private ArrayList<ModelCategoryHorizontal> list;
    private Category_Click category_click;

    public AdapterCategoryHorizontal(Context context, ArrayList<ModelCategoryHorizontal> list, Category_Click category_click) {
        this.context = context;
        this.list = list;
        this.category_click = category_click;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new myViewHolder(root);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.tvTitleItem.setText(list.get(position).getCategory_title());

        if (list.get(position).isSelected()) {
            holder.tvTitleItem.setBackground(context.getResources().getDrawable(R.drawable.design_category_item_selected));
            holder.tvTitleItem.setTextColor(Color.WHITE);
        } else {
            holder.tvTitleItem.setBackground(null);
            holder.tvTitleItem.setTextColor(context.getResources().getColor(R.color.et_text_color));
        }

        //item CLick listener
        holder.tvTitleItem.setOnClickListener(view -> {
            category_click.onSelected(position);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitleItem;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitleItem = itemView.findViewById(R.id.tvTitle_item);
        }
    }
}
