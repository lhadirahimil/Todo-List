package com.hadirahimi.todo_list.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hadirahimi.todo_list.R;

public class Activity_splash extends AppCompatActivity {
    private ImageView ivLogo;
    private TextView tvTitle,tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
        initAnimation();
        initHandler();

    }

    private void initViews()
    {
        ivLogo = findViewById(R.id.imageView);
        tvTitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_description);
    }

    private void initAnimation()
    {
        Animation animationFade = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        ivLogo.setAnimation(animationFade);
        tvTitle.setAnimation(animationFade);
        tvDescription.setAnimation(animationFade);
    }

    private void initHandler()
    {
       new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMain();
            }
        },4000);
    }

    private void goToMain()
    {
        startActivity(new Intent(Activity_splash.this,MainActivity.class));
        finish();
    }
}