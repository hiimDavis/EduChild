package com.example.asus.educhild;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.R.id.message;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        final TextView etUsername = (TextView) findViewById(R.id.email);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterHere);
        final Button bLogin = (Button) findViewById(R.id.bLogin);

        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, Register.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                if(!email.equals("") && !pass.equals("")){
                    mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>()  {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "User Login Failed" , Toast.LENGTH_LONG).show();
                            }else{
                                Intent WelcomeIntent = new Intent(LoginActivity.this, UserArea.class);
                                LoginActivity.this.startActivity(WelcomeIntent);
                            }

                            // ...
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "You need to fill all fields" , Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}