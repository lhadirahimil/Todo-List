package com.hadirahimi.todo_list.Dialog;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.hadirahimi.todo_list.Interface.OnDeleteButtonClick;
import com.hadirahimi.todo_list.R;

public class DialogDeleteConfirm extends Dialog {
    private OnDeleteButtonClick onDeleteButtonClick;
    private Button btnDelete,btnCancel;
    private ImageView ivClose;
    public DialogDeleteConfirm(@NonNull Context context,OnDeleteButtonClick onDeleteButtonClick) {
        super(context);
        this.onDeleteButtonClick = onDeleteButtonClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_delete_confirm);
        initView();
        onClickEvent();
    }

    private void onClickEvent()
    {
        btnDelete.setOnClickListener(view -> onDeleteButtonClick.delete_pressed());
        btnCancel.setOnClickListener(view -> onDeleteButtonClick.cancel_pressed());
        ivClose.setOnClickListener(view -> this.dismiss());
    }

    private void initView()
    {
        btnCancel = findViewById(R.id.btnDelete_cancel);
        btnDelete = findViewById(R.id.btnDeleteConfirm);
        ivClose = findViewById(R.id.iv_dialogDelete_Close);

    }
}