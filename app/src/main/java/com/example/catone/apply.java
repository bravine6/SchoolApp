package com.example.catone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class apply extends AppCompatActivity {
EditText firstname,secondname,marks,course,email;
FirebaseAuth firebaseAuth;
Button apply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        firstname = findViewById(R.id.firstname);
        email = findViewById(R.id.applyemail);
        secondname = findViewById(R.id.secondname);
        firebaseAuth = FirebaseAuth.getInstance();
        marks = findViewById(R.id.marks);
        course = findViewById(R.id.course);
        apply = findViewById(R.id.submit2);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                FirebaseDatabase database2 = FirebaseDatabase.getInstance();

                DatabaseReference myRef = database.getReference("users").child(firebaseAuth.getCurrentUser().getUid()).child(firebaseAuth.getCurrentUser().getUid());
                String id = myRef.push().getKey();
                DatabaseReference myRef2 = database2.getReference("users").child("application").child(firebaseAuth.getCurrentUser().getUid());
                // push key to admin side also
                String status = "Pending";
                String uid = firebaseAuth.getCurrentUser().getUid();
                Model model = new Model(id,
                        firstname.getText().toString().trim(),
                        secondname.getText().toString().trim(),
                        marks.getText().toString().trim(),
                        course.getText().toString().trim(),
                        status,
                        email.getText().toString(),
                        uid);
                myRef2.setValue(model);
                myRef.setValue(model);
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),panel.class));
            }
        });



    }
}