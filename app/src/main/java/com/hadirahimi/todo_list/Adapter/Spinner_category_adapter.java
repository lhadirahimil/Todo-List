package com.hadirahimi.todo_list.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hadirahimi.todo_list.Model.ModelCategory;
import com.hadirahimi.todo_list.R;

import java.util.ArrayList;

public class Spinner_category_adapter extends BaseAdapter {
    private ArrayList<ModelCategory>list;
    private Context context;

    public Spinner_category_adapter(Context context,ArrayList<ModelCategory> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View root = LayoutInflater.from(context).inflate(R.layout.category_item,viewGroup,false);
        TextView tvTitle = root.findViewById(R.id.tvCategoryTitle);
        tvTitle.setText(list.get(i).getCategory_title());
        @SuppressLint("UseCompatLoadingForDrawables") Drawable drawable = context.getResources().getDrawable(list.get(i).getImage());
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable ,null);
        return root;
    }
}
