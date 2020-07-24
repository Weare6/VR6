package com.rohheat.mainvr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button resBtn;
    private EditText email , password , username;

    private FirebaseAuth mAuth;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            Intent toNetworkActivity = new Intent(LoginActivity.this,NetworkActivity.class);
            startActivity(toNetworkActivity);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        resBtn = findViewById(R.id.resBtn);
        email = findViewById(R.id.emailEdit);
        username = findViewById(R.id.usernameEdit);
        password = findViewById(R.id.passwordEdit);

        mAuth = FirebaseAuth.getInstance();

        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    public void register(){

        String emailS = email.getText().toString();
        String passwordS = password.getText().toString();
        String usernameS = username.getText().toString();

        if(!usernameS.isEmpty()){
            if(!emailS.isEmpty()){
                if(!passwordS.isEmpty()){

                    mAuth.createUserWithEmailAndPassword(emailS,passwordS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Authentication success.",
                                        Toast.LENGTH_SHORT).show();
                                Intent toWatchActivity = new Intent(LoginActivity.this , NetworkActivity.class);
                                startActivity(toWatchActivity);
                                finish();
                            }else{
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else{
                    password.setError("Please fill");
                }
            }else{
                password.setError("Please fill");
            }
        }else{
            password.setError("Please fill");
        }
    }

}