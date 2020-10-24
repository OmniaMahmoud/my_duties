package com.example.myduties;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.myduties.databinding.ActivityLoginBinding;

public class LoginActivity extends UserFormsBaseActivity {

    ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginBinding.setLifecycleOwner(this);
        loginBinding.setLoginViewModel(getUserFormsViewModel());
        observeOnChanges();
    }

    private void observeOnChanges(){
        getUserFormsViewModel().emailErrorType.observe(this, this);
        getUserFormsViewModel().passwordErrorType.observe(this, this);
        getUserFormsViewModel().viewClick.observe(this, this);
    }

}
