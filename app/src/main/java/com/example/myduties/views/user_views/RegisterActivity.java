package com.example.myduties.views.user_views;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.myduties.MainActivity;
import com.example.myduties.R;
import com.example.myduties.databinding.ActivityRegisterBinding;
import com.example.myduties.models.User;

public class RegisterActivity extends UserFormsBaseActivity {

    ActivityRegisterBinding registerBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerBinding.setLifecycleOwner(this);
        registerBinding.setRegisterViewModel(getUserFormsViewModel());
        registerBinding.toolbar.setTitle(getString(R.string.register));
        initializeFormFields();
        observeOnChanges();
    }

    @Override
    protected void initializeFormFields() {
        getUserFormsViewModel().firstName.setValue("");
        getUserFormsViewModel().lastName.setValue("");
        getUserFormsViewModel().userName.setValue("");
        getUserFormsViewModel().email.setValue("");
        getUserFormsViewModel().password.setValue("");
        getUserFormsViewModel().confirmPassword.setValue("");

    }

    private void observeOnChanges(){
        getUserFormsViewModel().firstNameErrorType.observe(this, this);
        getUserFormsViewModel().lastNameErrorType.observe(this, this);
        getUserFormsViewModel().emailErrorType.observe(this, this);
        getUserFormsViewModel().passwordErrorType.observe(this, this);
        getUserFormsViewModel().confirmPasswordErrorType.observe(this, this);
        getUserFormsViewModel().actionFailed.observe(this, this);
        getUserFormsViewModel().user.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                navigateToScreen(MainActivity.class);
            }
        });
    }

}
