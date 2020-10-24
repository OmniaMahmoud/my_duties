package com.example.myduties;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.myduties.databinding.ActivityLoginBinding;
import com.example.myduties.databinding.ActivityRegisterBinding;

public class RegisterActivity extends UserFormsBaseActivity {

    ActivityRegisterBinding registerBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerBinding.setLifecycleOwner(this);
        registerBinding.setRegisterViewModel(getUserFormsViewModel());
        observeOnChanges();
    }

    private void observeOnChanges(){
        getUserFormsViewModel().firstNameErrorType.observe(this, this);
        getUserFormsViewModel().lastNameErrorType.observe(this, this);
        getUserFormsViewModel().emailErrorType.observe(this, this);
        getUserFormsViewModel().passwordErrorType.observe(this, this);
        getUserFormsViewModel().confirmPasswordErrorType.observe(this, this);
        getUserFormsViewModel().viewClick.observe(this, this);
    }

}
