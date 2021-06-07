package com.example.catone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HeadTeacher extends AppCompatActivity {
EditText email1,password1;
Button log1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_teacher);
        email1 = findViewById(R.id.email1);
        password1 = findViewById(R.id.password1);
        log1 = findViewById(R.id.login1);
        log1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email1.getText().toString().isEmpty())
                {
                    email1.setError("Required");
                    return;
                }
                if(password1.getText().toString().isEmpty())
                {
                    password1.setError("Required");
                    return;
                }

                if(email1.getText().toString().trim().equals("group@gmail.com") && password1.getText().toString().trim().equals("qwerty1234"))
                {
                  startActivity(new Intent(getApplicationContext(),Peopleinfo.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}