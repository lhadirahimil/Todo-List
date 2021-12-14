package com.hadirahimi.todo_list.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hadirahimi.todo_list.Interface.checkBoxClickTodoActivity;
import com.hadirahimi.todo_list.Model.ModelTodo;
import com.hadirahimi.todo_list.R;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.myViewHolder> {
    private ArrayList<ModelTodo> list;
    private checkBoxClickTodoActivity checkboxClick;

    public TodoAdapter(ArrayList<ModelTodo> list, checkBoxClickTodoActivity checkBoxClick) {
        this.list = list;
        this.checkboxClick = checkBoxClick;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo_activity, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.checkBox.setText(list.get(position).getTitle());

        if (list.get(position).getChecked() == 0)
            holder.checkBox.setChecked(false);
        else if (list.get(position).getChecked() == 1)
            holder.checkBox.setChecked(true);

        holder.checkBox.setOnCheckedChangeListener((compoundButton, isChecked) ->{
            checkboxClick.onClick(list.get(position).getId(),isChecked,position);
        });
        holder.ivDelete.setOnClickListener(view -> {
            checkboxClick.onDeleteClicked(list.get(position).getId());
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private ImageView ivDelete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.todo_checkbox);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
}
