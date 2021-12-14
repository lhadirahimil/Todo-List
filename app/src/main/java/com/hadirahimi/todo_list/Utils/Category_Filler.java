package com.hadirahimi.todo_list.Utils;

import com.hadirahimi.todo_list.Model.ModelCategory;
import com.hadirahimi.todo_list.Model.ModelCategoryHorizontal;
import com.hadirahimi.todo_list.R;

import java.util.ArrayList;

public class Category_Filler {

    private ArrayList<ModelCategory> list;
    private ArrayList<ModelCategoryHorizontal> listHorizontal;

    public ArrayList<ModelCategory> getCategory_WithAll()
    {
        list = new ArrayList<>();
        list.add(new ModelCategory(1,"نمایش همه",R.drawable.category));
        list.add(new ModelCategory(2,"ورزشی",R.drawable.sport));
        list.add(new ModelCategory(3,"درسی",R.drawable.book));
        list.add(new ModelCategory(4,"کاری",R.drawable.work));
        list.add(new ModelCategory(5,"متفرقه",R.drawable.other));
        return list;
    }
    public ArrayList<ModelCategory> getCategory()
    {
        list = new ArrayList<>();
        list.add(new ModelCategory(2,"ورزشی",R.drawable.sport));
        list.add(new ModelCategory(3,"درسی",R.drawable.book));
        list.add(new ModelCategory(4,"کاری",R.drawable.work));
        list.add(new ModelCategory(5,"متفرقه",R.drawable.other));
        return list;
    }
    public ArrayList<ModelCategoryHorizontal> getCategoryHorizontal()
    {
        listHorizontal = new ArrayList<>();
        listHorizontal.add(new ModelCategoryHorizontal(1,"نمایش همه",R.drawable.category,false));
        listHorizontal.add(new ModelCategoryHorizontal(2,"ورزشی",R.drawable.sport,false));
        listHorizontal.add(new ModelCategoryHorizontal(3,"درسی",R.drawable.book,false));
        listHorizontal.add(new ModelCategoryHorizontal(4,"کاری",R.drawable.work,false));
        listHorizontal.add(new ModelCategoryHorizontal(5,"متفرقه",R.drawable.other,false));
        return listHorizontal;
    }
}
