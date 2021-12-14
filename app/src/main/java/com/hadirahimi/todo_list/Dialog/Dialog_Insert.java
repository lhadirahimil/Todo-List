package com.hadirahimi.todo_list.Dialog;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hadirahimi.todo_list.Adapter.Spinner_category_adapter;
import com.hadirahimi.todo_list.Database.MyDatabase;
import com.hadirahimi.todo_list.Interface.response_Interface;
import com.hadirahimi.todo_list.Model.ModelCategory;
import com.hadirahimi.todo_list.R;
import com.hadirahimi.todo_list.Utils.Category_Filler;

import java.util.ArrayList;

public class Dialog_Insert extends Dialog {
    private ImageView ivClose;
    private Spinner spinnerCategory;
    private Button btnInsert;
    private EditText etTitle;
    private MyDatabase database;
    private Category_Filler category_filler;
    private ArrayList<ModelCategory> categoryList;
    private response_Interface response_interface;
    public Dialog_Insert(@NonNull Context context, response_Interface response_interface) {
        super(context);
        this.response_interface = response_interface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_insert);
        initView();
        initCategoryFiller();
        initCategoryList();
        initDatabase();
        OnClickEvent();
        initCategorySpinner();
    }

    private void initCategoryList()
    {
        categoryList = new ArrayList<>();
        categoryList = category_filler.getCategory();
    }

    private void initCategoryFiller()
    {
        category_filler = new Category_Filler();
    }

    private void initDatabase()
    {
        database = new MyDatabase(getContext());
    }

    private void initCategorySpinner()
    {

        Spinner_category_adapter adapter = new Spinner_category_adapter(getContext(),category_filler.getCategory());
        spinnerCategory.setAdapter(adapter);
    }

    private void OnClickEvent()
    {
        ivClose.setOnClickListener(view -> this.dismiss());
        btnInsert.setOnClickListener(view -> {
            if (etTitle.getText().toString().trim().isEmpty())
            {
                etTitle.setError("نمی تواند خالی باشد");
                etTitle.requestFocus();
            }else
            {
                database.insert(etTitle.getText().toString(),categoryList.get(spinnerCategory.getSelectedItemPosition()).getCategory_id());
                Toast.makeText(getContext(), "فعالیت با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                response_interface.refresh();
                dismiss();
            }
        });
    }

    private void initView()
    {
        ivClose = findViewById(R.id.iv_close_DialogInsert);
        spinnerCategory = findViewById(R.id.spinner_category);
        btnInsert = findViewById(R.id.btn_Insert);
        etTitle = findViewById(R.id.etTitle_Dialog);
    }
}