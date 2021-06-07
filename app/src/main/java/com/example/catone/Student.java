package com.example.catone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Student extends AppCompatActivity {
TextView register;
EditText enail,password;
Button login ;
private ProgressDialog md;
FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        enail = findViewById(R.id.username);
        login = findViewById(R.id.login2);
        md = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        password = findViewById(R.id.password2);
        register = findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(enail.getText().toString().isEmpty())
                {
                    enail.setError("Required");
                    return;
                }
                if(password.getText().toString().isEmpty())
                {
                    password.setError("Required");
                    return;
                }
                md.setMessage("PROCESSING...");
                md.show();
                firebaseAuth.signInWithEmailAndPassword(enail.getText().toString().trim(),password.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())

                        {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(getApplicationContext(),panel.class));
                            enail.setText("");
                            password.setText("");
                            md.dismiss();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Please Try Again",Toast.LENGTH_SHORT).show();
                            md.dismiss();


                        }

                    }
                });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Studentregister.class));
            }
        });

    }
}