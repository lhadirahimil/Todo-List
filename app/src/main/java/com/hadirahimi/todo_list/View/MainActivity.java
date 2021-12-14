package com.hadirahimi.todo_list.View;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hadirahimi.todo_list.Adapter.RecentAdapter;
import com.hadirahimi.todo_list.Database.MyDatabase;
import com.hadirahimi.todo_list.Dialog.Dialog_Insert;
import com.hadirahimi.todo_list.Interface.checkBoxClick;
import com.hadirahimi.todo_list.Interface.response_Interface;
import com.hadirahimi.todo_list.Model.ModelCategory;
import com.hadirahimi.todo_list.Model.ModelTodo;
import com.hadirahimi.todo_list.R;
import com.hadirahimi.todo_list.Utils.Category_Filler;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private MyDatabase database;
    private ArrayList<ModelTodo> list;
    private RecyclerView recy;
    private ArrayList<ModelCategory> listCategory;
    private TextView tvAll, tvWork, tvSport, tvLesson, tvOther;
    private LinearLayout linear_insert;
    private Dialog_Insert dialog_insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
        initRecy();
        getRecentItems();
        initCategoryList();
        initViews();
        onClickEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getRecentItems();
    }

    private void onClickEvent() {
        tvAll.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Activity_todo_list.class);
            intent.putExtra("categoryID", listCategory.get(0).getCategory_id());
            startActivity(intent);
        });
        tvSport.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Activity_todo_list.class);
            intent.putExtra("categoryID", listCategory.get(1).getCategory_id());
            startActivity(intent);
        });
        tvLesson.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Activity_todo_list.class);
            intent.putExtra("categoryID", listCategory.get(2).getCategory_id());
            startActivity(intent);
        });
        tvWork.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Activity_todo_list.class);
            intent.putExtra("categoryID", listCategory.get(3).getCategory_id());
            startActivity(intent);
        });
        tvOther.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Activity_todo_list.class);
            intent.putExtra("categoryID", listCategory.get(4).getCategory_id());
            startActivity(intent);
        });
        linear_insert.setOnClickListener(view -> {
            dialog_insert = new Dialog_Insert(this, new response_Interface() {
                @Override
                public void refresh() {
                    getRecentItems();
                }
            });
            dialog_insert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog_insert.show();
            initDialogWinnerSize();

        });
    }

    private void initDialogWinnerSize() {
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        dialog_insert.getWindow().setLayout(width, ConstraintLayout.LayoutParams.WRAP_CONTENT);
    }

    private void initViews() {
        tvAll = findViewById(R.id.tvAll);
        tvLesson = findViewById(R.id.tvLesson);
        tvOther = findViewById(R.id.tvOther);
        tvSport = findViewById(R.id.tvSport);
        tvWork = findViewById(R.id.tvWork);
        linear_insert = findViewById(R.id.linear_insert);
    }

    private void initCategoryList() {
        Category_Filler category_filler = new Category_Filler();
        listCategory = new ArrayList<>();
        listCategory = category_filler.getCategory_WithAll();
    }

    private void initRecy() {
        recy = findViewById(R.id.recy_recent);
    }


    private void getRecentItems() {
        Cursor cursor = database.recentData();
        list = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(database.ID));
            String title = cursor.getString(cursor.getColumnIndex(database.TITLE));
            int checked = cursor.getInt(cursor.getColumnIndex(database.CHECKED));
            int category_id = cursor.getInt(cursor.getColumnIndex(database.CATEGORY));
            list.add(new ModelTodo(id, title, checked, category_id));
        }
        pushToRecy();
    }

    private void pushToRecy() {
        RecentAdapter adapter = new RecentAdapter(list, new checkBoxClick() {
            @Override
            public void onClick(int id, boolean isClicked, int Position) {
                int checked;
                if (isClicked)
                    checked = 1;
                else
                    checked = 0;
                database.isChecked(id, checked);
            }
        });
        recy.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recy.setHasFixedSize(true);
        recy.setAdapter(adapter);
    }

    private void initDatabase() {
        database = new MyDatabase(this);
        database.getWritableDatabase();
    }

}