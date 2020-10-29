package com.example.myduties.views.user_views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.example.myduties.MainActivity;
import com.example.myduties.R;
import com.example.myduties.databinding.ActivityForgetPasswordBinding;
import com.example.myduties.databinding.ActivityLoginBinding;
import com.example.myduties.models.User;
import com.example.myduties.view_models.UserFormsViewModel;

public class ForgetPasswordActivity extends UserFormsBaseActivity {
    ActivityForgetPasswordBinding forgetPasswordBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgetPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password);
        forgetPasswordBinding.setLifecycleOwner(this);
        forgetPasswordBinding.setForgetPasswordViewModel(getUserFormsViewModel());
        forgetPasswordBinding.toolbar.setTitle(getString(R.string.reset_password));
        initializeFormFields();
        observeOnChanges();
    }

    @Override
    protected void initializeFormFields() {
        getUserFormsViewModel().email.setValue("");
    }

    private void observeOnChanges(){
        getUserFormsViewModel().emailErrorType.observe(this, this);
        getUserFormsViewModel().actionFailed.observe(this, this);
        getUserFormsViewModel().actionSucceeded.observe(this, this);

    }
}
