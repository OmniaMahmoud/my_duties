package com.example.myduties.view_models;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myduties.R;
import com.example.myduties.models.User;
import com.example.myduties.repositories.UserRepository;

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

    public MutableLiveData<User> user = new MutableLiveData<>();
    public MutableLiveData<String> actionFailed = new MutableLiveData<>();
    public MutableLiveData<FormsSucceededActions> actionSucceeded = new MutableLiveData<>();


    private final UserRepository repository = new UserRepository();
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
    public enum FormsSucceededActions {
        SUCCESS_RESET_PASSWORD
    };
    public enum FormsClicks {
        RESET_REDIRECT, REGISTER_REDIRECT, LOGIN_REDIRECT,
        HOME_REDIRECT
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
            viewClick.setValue(FormsClicks.RESET_REDIRECT);

        } else if(view.getId() == R.id.tv_register){
            viewClick.setValue(FormsClicks.REGISTER_REDIRECT);
        } else if(view.getId() == R.id.btn_reset_password){
            if(isValidForm()){
                forgetPassword();
            }
        } else if(view.getId() == R.id.btn_update){
            if(isValidForm()){
                updateUserProfile();
            }
        }

    }

    public boolean isValidForm(){
        boolean isValid = true;
        if(firstName.getValue() != null){
            if(TextUtils.isEmpty(Objects.requireNonNull(firstName.getValue()))){
                isValid = false;
                firstNameErrorType.setValue(FormsValidationResults.EMPTY_FIRST_NAME);
            } else {
                firstNameErrorType.setValue(FormsValidationResults.VALID_FIRST_NAME);
            }
        }

        if(lastName.getValue() != null){
            if(TextUtils.isEmpty(Objects.requireNonNull(lastName.getValue()))){
                isValid = false;
                lastNameErrorType.setValue(FormsValidationResults.EMPTY_LAST_NAME);
            } else {
                lastNameErrorType.setValue(FormsValidationResults.VALID_LAST_NAME);
            }
        }

        if(email.getValue() != null){
            if(TextUtils.isEmpty(Objects.requireNonNull(email.getValue()))){
                isValid = false;
                emailErrorType.setValue(FormsValidationResults.EMPTY_EMAIL);
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(email.getValue()).matches()){
                isValid = false;
                emailErrorType.setValue(FormsValidationResults.INVALID_EMAIL_FORMAT);
            } else {
                emailErrorType.setValue(FormsValidationResults.VALID_EMAIL);
            }
        }

        if(password.getValue() != null){
            if(TextUtils.isEmpty(Objects.requireNonNull(password.getValue()))){
                isValid = false;
                passwordErrorType.setValue(FormsValidationResults.EMPTY_PASSWORD);
            } else if(Objects.requireNonNull(password.getValue()).length() < 8){
                isValid = false;
                passwordErrorType.setValue(FormsValidationResults.INVALID_PASSWORD_LENGTH);
            } else {
                passwordErrorType.setValue(FormsValidationResults.VALID_PASSWORD);
            }
        }

        if(password.getValue() != null &&
                confirmPassword.getValue() != null){
            if(!Objects.requireNonNull(confirmPassword.getValue()).equals(
                    Objects.requireNonNull(password.getValue()))){
                isValid = false;
                confirmPasswordErrorType.setValue(FormsValidationResults.CONFIRMATION_MISMATCH);
            } else {
                confirmPasswordErrorType.setValue(FormsValidationResults.VALID_CONFIRM_PASSWORD);
            }
        }
        return isValid;

    }

    private void loginUser(){
        Log.e("login","login");
        repository.loginUser(
                email.getValue(), password.getValue(),
                user, actionFailed);

    }

    private void registerUser(){
        Log.e("register","register");
        repository.registerUser(
                firstName.getValue(), lastName.getValue(), userName.getValue(),
                email.getValue(), password.getValue(), user, actionFailed);
    }

    private void forgetPassword(){
        Log.e("forgetPassword","forgetPassword");
        repository.resetUserPassword(
                email.getValue(), actionSucceeded, actionFailed);
    }

    private void updateUserProfile(){

    }
}
