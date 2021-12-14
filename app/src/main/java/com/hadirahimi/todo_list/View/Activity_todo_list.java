package com.hadirahimi.todo_list.View;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hadirahimi.todo_list.Adapter.AdapterCategoryHorizontal;
import com.hadirahimi.todo_list.Adapter.TodoAdapter;
import com.hadirahimi.todo_list.Database.MyDatabase;
import com.hadirahimi.todo_list.Dialog.DialogDeleteConfirm;
import com.hadirahimi.todo_list.Interface.Category_Click;
import com.hadirahimi.todo_list.Interface.OnDeleteButtonClick;
import com.hadirahimi.todo_list.Interface.checkBoxClickTodoActivity;
import com.hadirahimi.todo_list.Model.ModelCategoryHorizontal;
import com.hadirahimi.todo_list.Model.ModelTodo;
import com.hadirahimi.todo_list.R;
import com.hadirahimi.todo_list.Utils.Category_Filler;

import java.util.ArrayList;

public class Activity_todo_list extends AppCompatActivity {
    private int category_id;
    private ArrayList<ModelCategoryHorizontal> categoryList;
    private Category_Filler category_filler;
    private MyDatabase myDatabase;
    private ArrayList<ModelTodo> listTodo;
    private RecyclerView recyCategory, recyTodo;
    private AdapterCategoryHorizontal adapterCategoryHorizontal;
    private TodoAdapter todoAdapter;
    private ArrayList<ModelTodo> temp;
    private DialogDeleteConfirm dialogDeleteConfirm;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        getCategoryFromIntent();
        setupBackButton();
        initCategory();
        initDatabase();
        getTodoList();
        initCategoryRecy();
        fillCategoryRecy();
        firstCategoryItemSelected();
        initTodoRecy();
        fillTodoRecy();
    }

    private void setupBackButton()
    {
        ivBack =findViewById(R.id.ivBack);
        ivBack.setOnClickListener(view -> finish());
    }

    private void fillTodoRecy() {
        //This function values the information received from the database based on the category id
        getTodoList();
        temp = new ArrayList<>();
        for (int i = 0; i < listTodo.size(); i++) {
            //If the category id is equal to 1, the program must display all the information
            if (category_id == 1) {
                //todo show all todo list
                temp = listTodo;
            } else if (category_id == listTodo.get(i).getCategory_id()) {
                //If the category id is equal to any number other than 1, the program must display all items that are equal to this id.
                temp.add(new ModelTodo(
                        listTodo.get(i).getId(),
                        listTodo.get(i).getTitle(),
                        listTodo.get(i).getChecked(),
                        listTodo.get(i).getCategory_id()));
            }
        }
        //init todoAdapter and pass the temp value to this adapter
        todoAdapter = new TodoAdapter(temp, new checkBoxClickTodoActivity() {
            @Override
            public void onClick(int id, boolean isChecked, int Position) {
                //When a checkbox becomes true or false, the id of this checkbox along with other features is sent to this method by the adapter.

                //Convert the value of a check box from a boolean to a numeric value
                int checked;
                if (isChecked)
                    checked = 1;
                else
                    checked = 0;
                //update changes made by the checkbox in the database
                myDatabase.isChecked(id, checked);
            }

            @Override
            public void onDeleteClicked(int itemID) {
                //show dialog
                dialogDeleteConfirm = new DialogDeleteConfirm(Activity_todo_list.this, new OnDeleteButtonClick() {
                    @Override
                    public void delete_pressed() {
                        //user accepted . delete the item with id
                        if (myDatabase.deleteItem(itemID))
                            Toast.makeText(Activity_todo_list.this, "آیتم با موفقیت حذف شد", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Activity_todo_list.this, "خطا در حذف آیتم", Toast.LENGTH_SHORT).show();
                        dialogDeleteConfirm.dismiss();
                        //reload recyclerview items
                        getTodoList();
                        fillTodoRecy();


                    }

                    @Override
                    public void cancel_pressed() {
                        dialogDeleteConfirm.dismiss();
                    }
                });

                dialogDeleteConfirm.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogDeleteConfirm.setCanceledOnTouchOutside(false);
                dialogDeleteConfirm.show();
                initDialogWinnerSize();
            }
        });

        //set the recyclerview features
        recyTodo.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyTodo.setHasFixedSize(true);
        recyTodo.setAdapter(todoAdapter);
    }

    private void initDialogWinnerSize() {
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.70);
        dialogDeleteConfirm.getWindow().setLayout(width, ConstraintLayout.LayoutParams.WRAP_CONTENT);
    }

    private void firstCategoryItemSelected() {
        //The first time we enter this Activity, this method runs and selects an item
        setAllItemFalse();
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getCategory_id() == category_id)
                categoryList.get(i).setSelected(true);
            adapterCategoryHorizontal.notifyItemChanged(category_id);
        }

    }

    private void fillCategoryRecy() {
        //define Category Adapter
        adapterCategoryHorizontal = new AdapterCategoryHorizontal(this, categoryList, new Category_Click() {
            @Override
            public void onSelected(int position) {
                //change category value
                category_id = categoryList.get(position).getCategory_id();
                //fill recyclerview with new category id
                fillTodoRecy();
                //We designed this function so that when one item is selected, the other items are taken out of the selected mode
                setAllItemFalse();
                categoryList.get(position).setSelected(true);
                adapterCategoryHorizontal.notifyItemChanged(position);
            }
        });
        //set the recyclerview features
        recyCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyCategory.setHasFixedSize(true);
        recyCategory.setAdapter(adapterCategoryHorizontal);
    }

    private void setAllItemFalse() {
        //We designed this function so that when one item is selected, the other items are taken out of the selected mode
        for (int i = 0; i < categoryList.size(); i++) {
            categoryList.get(i).setSelected(false);
            adapterCategoryHorizontal.notifyItemChanged(i);
        }
    }

    private void initTodoRecy() {
        //define the todoWork recyclerview
        recyTodo = findViewById(R.id.recy_todo);
    }

    private void initCategoryRecy() {
        //define the category recyclerview
        recyCategory = findViewById(R.id.recyCategory);
    }

    private void getTodoList() {
        //Create an object from the todo list
        listTodo = new ArrayList<>();
        //We receive all the todo's from the database and store them in the cursor
        Cursor cursor = myDatabase.allData();
        //We receive the required values from the cursor and save them in the arraylist
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(myDatabase.ID));
            String title = cursor.getString(cursor.getColumnIndex(myDatabase.TITLE));
            int checked = cursor.getInt(cursor.getColumnIndex(myDatabase.CHECKED));
            int category_id = cursor.getInt(cursor.getColumnIndex(myDatabase.CATEGORY));
            listTodo.add(new ModelTodo(id, title, checked, category_id));
        }
        //End of receiving information
    }

    private void initDatabase() {
        //We create an object from the database to use this database later
        myDatabase = new MyDatabase(this);
    }

    private void initCategory() {
        //We get the category information through the category_filler method we created earlier
        categoryList = new ArrayList<>();
        category_filler = new Category_Filler();
        categoryList = category_filler.getCategoryHorizontal();
    }

    private void getCategoryFromIntent() {
        //When we first enter this activity. We get the category id we selected on the previous page
        category_id = getIntent().getExtras().getInt("categoryID");
    }
}