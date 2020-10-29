package com.example.myduties.views.user_views;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.myduties.MainActivity;
import com.example.myduties.R;
import com.example.myduties.databinding.ActivityLoginBinding;
import com.example.myduties.models.User;

public class LoginActivity extends UserFormsBaseActivity {

    ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginBinding.setLifecycleOwner(this);
        loginBinding.setLoginViewModel(getUserFormsViewModel());
        loginBinding.toolbar.setTitle(getString(R.string.login));
        initializeFormFields();
        observeOnChanges();
    }

    @Override
    protected void initializeFormFields() {
        getUserFormsViewModel().email.setValue("");
        getUserFormsViewModel().password.setValue("");
    }

    private void observeOnChanges(){
        getUserFormsViewModel().emailErrorType.observe(this, this);
        getUserFormsViewModel().passwordErrorType.observe(this, this);
        getUserFormsViewModel().viewClick.observe(this, this);
        getUserFormsViewModel().actionFailed.observe(this, this);
        getUserFormsViewModel().user.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                navigateToScreen(MainActivity.class);
            }
        });
    }

}
