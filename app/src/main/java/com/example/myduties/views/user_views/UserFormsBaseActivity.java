package com.example.myduties.views.user_views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myduties.MainActivity;
import com.example.myduties.R;
import com.example.myduties.view_models.UserFormsViewModel;

public abstract class UserFormsBaseActivity extends AppCompatActivity implements Observer<Object> {

    private UserFormsViewModel userFormsViewModel;

    public UserFormsViewModel getUserFormsViewModel() {
        return userFormsViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userFormsViewModel = new ViewModelProvider(this).get(UserFormsViewModel.class);
    }

    @Override
    public void onChanged(Object object) {
        if(object instanceof UserFormsViewModel.FormsValidationResults){
            UserFormsViewModel.FormsValidationResults formValidationResult =
                    (UserFormsViewModel.FormsValidationResults) object;
            switch (formValidationResult){
                case EMPTY_FIRST_NAME:
                    userFormsViewModel.firstNameError.setValue(getString(R.string.empty_first_name_error));
                    break;
                case VALID_FIRST_NAME:
                    userFormsViewModel.firstNameError.setValue("");
                    break;
                case EMPTY_LAST_NAME:
                    userFormsViewModel.lastNameError.setValue(getString(R.string.empty_last_name_error));
                    break;
                case VALID_LAST_NAME:
                    userFormsViewModel.lastNameError.setValue("");
                    break;
                case EMPTY_EMAIL:
                    userFormsViewModel.emailError.setValue(getString(R.string.empty_email_error));
                    break;
                case INVALID_EMAIL_FORMAT:
                    userFormsViewModel.emailError.setValue(getString(R.string.email_format_error));
                    break;
                case VALID_EMAIL:
                    userFormsViewModel.emailError.setValue("");
                    break;
                case EMPTY_PASSWORD:
                    userFormsViewModel.passwordError.setValue(getString(R.string.empty_password_error));
                    break;
                case INVALID_PASSWORD_LENGTH:
                    userFormsViewModel.passwordError.setValue(getString(R.string.password_length_error));
                    break;
                case VALID_PASSWORD:
                    userFormsViewModel.passwordError.setValue("");
                    break;
                case CONFIRMATION_MISMATCH:
                    userFormsViewModel.confirmPasswordError.setValue(getString(R.string.confirm_password_match_error));
                    break;
                case VALID_CONFIRM_PASSWORD:
                    userFormsViewModel.confirmPasswordError.setValue("");
                    break;
                default:
                    userFormsViewModel.emailError.setValue("");
                    userFormsViewModel.passwordError.setValue("");
                    break;

            }
        }
        else if(object instanceof UserFormsViewModel.FormsClicks){
            UserFormsViewModel.FormsClicks formClick =
                    (UserFormsViewModel.FormsClicks) object;
            switch (formClick) {
                case RESET_REDIRECT:
                    navigateToScreen(ForgetPasswordActivity.class);
                    break;
                case REGISTER_REDIRECT:
                    navigateToScreen(RegisterActivity.class);
                    break;
                case LOGIN_REDIRECT:
                    navigateToScreen(LoginActivity.class);
                case HOME_REDIRECT:
                    navigateToScreen(MainActivity.class);
            }
        }
        else if(object instanceof UserFormsViewModel.FormsSucceededActions){
            UserFormsViewModel.FormsSucceededActions action = (UserFormsViewModel.FormsSucceededActions)object;
            switch (action){
                case SUCCESS_RESET_PASSWORD:
                    Toast.makeText(UserFormsBaseActivity.this, R.string.reset_success_message, Toast.LENGTH_LONG).show();
                    break;
            }
        }
        else if(object instanceof String){
            String actionFailedMessage = (String) object;
            Toast.makeText(UserFormsBaseActivity.this, actionFailedMessage, Toast.LENGTH_LONG).show();
        }

    }

    protected void navigateToScreen(Class toClass){
        Intent screenIntent = new Intent(this, toClass);
        startActivity(screenIntent);
    }

    abstract protected void initializeFormFields();

}
