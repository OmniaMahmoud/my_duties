package com.example.myduties;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextView registerTv, forgetPasswordTv;
    Button loginBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeView();
    }

    private void initializeView(){
        registerTv = findViewById(R.id.tv_register);
        registerTv.setOnClickListener(this);
        loginBtn = findViewById(R.id.btn_login);
        loginBtn.setOnClickListener(this);
        forgetPasswordTv = findViewById(R.id.tv_reset);
        forgetPasswordTv.setOnClickListener(this);
    }

    private void navigateToScreen(Class toClass){
        Intent screenIntent = new Intent(this, toClass);
        startActivity(screenIntent);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.tv_register){
            navigateToScreen(RegisterActivity.class);
        } else if(view.getId() == R.id.btn_login){
            navigateToScreen(MainActivity.class);
        } else if(view.getId() == R.id.tv_reset){
            navigateToScreen(ChangePasswordActivity.class);
        }

    }


}
