package com.example.pancaranticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class loginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    final int MIN_PASSWORD_LENGTH = 6;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        viewInitializations();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            getFireData(currentUser.getEmail());
        }
    }

    void viewInitializations() {
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        // To show back button in actionbar
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Checking if the input in form is valid
    boolean validateInput() {

        if (etEmail.getText().toString().equals("")) {
            etEmail.setError("Please Enter Email");
            return false;
        }
        if (etPassword.getText().toString().equals("")) {
            etPassword.setError("Please Enter Password");
            return false;
        }

        // checking the proper email format
        if (!isEmailValid(etEmail.getText().toString())) {
            etEmail.setError("Please Enter Valid Email");
            return false;
        }

        // checking minimum password Length
        if (etPassword.getText().length() < MIN_PASSWORD_LENGTH) {
            etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + " characters");
            return false;
        }

        return true;
    }

    boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Hook Click Event

    public void performSignUp (View v) {
        if (validateInput()) {

            // Input is valid, here send data to your server

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();



            // Here you can call you API
            // Check this tutorial to call server api through Google Volley Library https://handyopinion.com
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(loginActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();

                                FirebaseFirestore db = FirebaseFirestore.getInstance();

                                db.collection("User")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        if(document.getData().get("email").toString().equals(email))
                                                        {
                                                            setUserInfo(document);
                                                            Intent intent = new Intent(loginActivity.this,mainPage.class);
                                                            startActivity(intent);
                                                        }
                                                    }
                                                } else {
                                                    Log.w("ERROR", "Error getting documents.", task.getException());
                                                }
                                            }
                                        });
                                //test

                                //end data
                            }
                            else{
                                Toast.makeText(loginActivity.this,"Incorrect Email/Password",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


        }

    }

    private void getFireData(String email)
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("User")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getData().get("email").toString().equals(email))
                                {
                                    setUserInfo(document);
                                    Intent intent = new Intent(loginActivity.this,mainPage.class);
                                    startActivity(intent);
                                }
                            }
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void setUserInfo(QueryDocumentSnapshot doc)
    {
        userInfo.setFullname(doc.getData().get("fullname").toString());
        userInfo.setEmail(doc.getData().get("email").toString());
        userInfo.setUsername(doc.getData().get("username").toString());
        userInfo.setRole(doc.getData().get("role").toString());

        Log.d("TESTINFO",userInfo.getEmail());
    }

    public void goToSignup(View v) {

        Intent intent = new Intent(this, signUpActivity.class);
        startActivity(intent);
    }


}