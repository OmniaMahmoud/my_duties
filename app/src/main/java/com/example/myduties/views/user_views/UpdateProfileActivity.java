package com.example.myduties.views.user_views;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.example.myduties.R;
import com.example.myduties.databinding.ActivityUpdateProfileBinding;

public class UpdateProfileActivity extends UserFormsBaseActivity{
    ActivityUpdateProfileBinding updateProfileBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateProfileBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_profile);
        updateProfileBinding.setLifecycleOwner(this);
        updateProfileBinding.setUpdateProfileViewModel(getUserFormsViewModel());
        updateProfileBinding.toolbar.setTitle(getString(R.string.update_profile));
        initializeFormFields();
        observeOnChanges();
    }

    @Override
    protected void initializeFormFields() {
        getUserFormsViewModel().firstName.setValue("");
        getUserFormsViewModel().lastName.setValue("");
        getUserFormsViewModel().userName.setValue("");
        getUserFormsViewModel().email.setValue("");
    }

    private void observeOnChanges(){
        getUserFormsViewModel().firstNameErrorType.observe(this, this);
        getUserFormsViewModel().lastNameErrorType.observe(this, this);
        getUserFormsViewModel().emailErrorType.observe(this, this);
        getUserFormsViewModel().viewClick.observe(this, this);
    }
}
