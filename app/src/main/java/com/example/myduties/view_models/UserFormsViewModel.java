package com.example.myduties.view_models;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myduties.R;
import java.util.Objects;

public class UserFormsViewModel extends ViewModel {

    public MutableLiveData<String> firstName = new MutableLiveData<>();
    public MutableLiveData<String> lastName = new MutableLiveData<>();
    public MutableLiveData<String> userName = new MutableLiveData<>();
    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> confirmPassword = new MutableLiveData<>();

    public MutableLiveData<String> firstNameError = new MutableLiveData<>();
    public MutableLiveData<FormsValidationResults> firstNameErrorType = new MutableLiveData<>();

    public MutableLiveData<String> lastNameError = new MutableLiveData<>();
    public MutableLiveData<FormsValidationResults> lastNameErrorType = new MutableLiveData<>();

    public MutableLiveData<String> emailError = new MutableLiveData<>();
    public MutableLiveData<FormsValidationResults> emailErrorType = new MutableLiveData<>();

    public MutableLiveData<String> passwordError = new MutableLiveData<>();
    public MutableLiveData<FormsValidationResults> passwordErrorType = new MutableLiveData<>();

    public MutableLiveData<String> confirmPasswordError = new MutableLiveData<>();
    public MutableLiveData<FormsValidationResults> confirmPasswordErrorType = new MutableLiveData<>();

    public MutableLiveData<FormsClicks> viewClick = new MutableLiveData<>();

    public enum FormsValidationResults {
        EMPTY_FIRST_NAME,
        VALID_FIRST_NAME,
        EMPTY_LAST_NAME,
        VALID_LAST_NAME,
        EMPTY_EMAIL,
        INVALID_EMAIL_FORMAT,
        VALID_EMAIL,
        EMPTY_PASSWORD,
        INVALID_PASSWORD_LENGTH,
        VALID_PASSWORD,
        CONFIRMATION_MISMATCH,
        VALID_CONFIRM_PASSWORD,
    };
    public enum FormsClicks {
        RESET, REGISTER, LOGIN
    }
    public void onClick(View view){
        if(view.getId() == R.id.btn_login){
            if(isValidForm()){
                loginUser();
            }
        } else if(view.getId() == R.id.btn_register){
            if(isValidForm()){
                registerUser();
            }
        } else if(view.getId() == R.id.tv_reset){
            viewClick.setValue(FormsClicks.RESET);

        } else if(view.getId() == R.id.tv_register){
            viewClick.setValue(FormsClicks.REGISTER);
        }

    }

    public boolean isValidForm(){
        boolean isValid;
        if(firstName.getValue() == null || TextUtils.isEmpty(Objects.requireNonNull(firstName.getValue()))){
            isValid = false;
            firstNameErrorType.setValue(FormsValidationResults.EMPTY_FIRST_NAME);
        } else {
            isValid = true;
            firstNameErrorType.setValue(FormsValidationResults.VALID_FIRST_NAME);
        }

        if(lastName.getValue() == null || TextUtils.isEmpty(Objects.requireNonNull(lastName.getValue()))){
            isValid = false;
            lastNameErrorType.setValue(FormsValidationResults.EMPTY_LAST_NAME);

        } else {
            isValid = true;
            lastNameErrorType.setValue(FormsValidationResults.VALID_LAST_NAME);
        }

        if(email.getValue() == null || TextUtils.isEmpty(Objects.requireNonNull(email.getValue()))){
            isValid = false;
            emailErrorType.setValue(FormsValidationResults.EMPTY_EMAIL);
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email.getValue()).matches()){
            isValid = false;
            emailErrorType.setValue(FormsValidationResults.INVALID_EMAIL_FORMAT);
        } else {
            isValid = true;
            emailErrorType.setValue(FormsValidationResults.VALID_EMAIL);
        }

        if(password.getValue() == null || TextUtils.isEmpty(Objects.requireNonNull(password.getValue()))){
            isValid = false;
            passwordErrorType.setValue(FormsValidationResults.EMPTY_PASSWORD);
        } else if(Objects.requireNonNull(password.getValue()).length() < 8){
            isValid = false;
            passwordErrorType.setValue(FormsValidationResults.INVALID_PASSWORD_LENGTH);
        } else {
            isValid = true;
            passwordErrorType.setValue(FormsValidationResults.VALID_PASSWORD);
        }

        if(password.getValue() != null && confirmPassword.getValue() != null){
            if(!Objects.requireNonNull(confirmPassword.getValue()).equals(
                    Objects.requireNonNull(password.getValue()))){
                isValid = false;
                confirmPasswordErrorType.setValue(FormsValidationResults.CONFIRMATION_MISMATCH);
            } else {
                isValid = true;
                confirmPasswordErrorType.setValue(FormsValidationResults.VALID_CONFIRM_PASSWORD);
            }
        }
        return isValid;

    }

    private void loginUser(){
        Log.e("login","login");
        viewClick.setValue(FormsClicks.LOGIN);

    }

    private void registerUser(){
        Log.e("register","register");
    }

    private void forgetPassword(){

    }
}
