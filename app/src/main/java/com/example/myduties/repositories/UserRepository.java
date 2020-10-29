package com.example.myduties.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.myduties.MApplication;
import com.example.myduties.models.User;
import com.example.myduties.view_models.UserFormsViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository {

    public void registerUser(final String firstName, final String lastName, final String userName,
                             final String email, String password,
                             final MutableLiveData<User> result,
                             final MutableLiveData<String> actionFailed){
        if(MApplication.getAuth().getCurrentUser() != null){
            MApplication.getAuth().signOut();
        }
        MApplication.getAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("register user", "createUserWithEmail:success");
                            FirebaseUser firebaseUser = MApplication.getAuth().getCurrentUser();
                            User userData = new User(
                                    firebaseUser.getUid(), firstName, lastName,
                                    userName, email);
                            result.setValue(userData);
                            MApplication.getUserRef().setValue(userData);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("register user","createUserWithEmail:failure", task.getException());
                            actionFailed.setValue(task.getException().getMessage());
                        }
                    }
                });
    }

    public void loginUser(final String email, String password,
                          final MutableLiveData<User> result,
                          final MutableLiveData<String> actionFailed){
        if(MApplication.getAuth().getCurrentUser() != null){
            MApplication.getAuth().signOut();
        }
        MApplication.getAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("login user", "login user:success");
                            FirebaseUser firebaseUser = MApplication.getAuth().getCurrentUser();
                            User userData = new User(
                                    firebaseUser.getUid(), email);
                            result.setValue(userData);
                            MApplication.getUserRef().setValue(userData);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("login user","login user:failure", task.getException());
                            actionFailed.setValue(task.getException().getMessage());
                        }
                    }
                });
    }

    public void resetUserPassword(final String email,
                                  final MutableLiveData<UserFormsViewModel.FormsSucceededActions> actionSucceeded,
                                  final MutableLiveData<String> actionFailed){
        if(MApplication.getAuth().getCurrentUser() != null){
            MApplication.getAuth().signOut();
        }
        MApplication.getAuth().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("forget user password", "forget user password");
                            actionSucceeded.setValue(UserFormsViewModel.FormsSucceededActions.SUCCESS_RESET_PASSWORD);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e("forget user password","forget user password", task.getException());
                            actionFailed.setValue(task.getException().getMessage());
                        }
                    }
                });
    }
}
