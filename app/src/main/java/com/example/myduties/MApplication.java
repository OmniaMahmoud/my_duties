package com.example.myduties;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MApplication extends Application {

    private static FirebaseAuth auth;
    private static FirebaseDatabase database;
    private static DatabaseReference userRef;

    @Override
    public void onCreate() {
        super.onCreate();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(false);
        userRef = database.getReference("user");
    }

    public static FirebaseAuth getAuth() {
        return auth;
    }

    public static DatabaseReference getUserRef() {
        return userRef;
    }
}
