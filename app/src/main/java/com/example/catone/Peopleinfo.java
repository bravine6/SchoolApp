package com.example.catone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Peopleinfo extends AppCompatActivity {
RecyclerView recyclerView;
FirebaseAuth firebaseAuth;
DatabaseReference myref;
DatabaseReference myref2;
DatabaseReference myref3;
    private FirebaseRecyclerAdapter<Model,Viewholder2> adapter;
    FirebaseRecyclerOptions<Model> options ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peopleinfo);
        recyclerView = findViewById(R.id.users);
        recyclerView.setHasFixedSize(true);
        firebaseAuth = FirebaseAuth.getInstance();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Peopleinfo.this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        fetch();
    }

    private void fetch() {
       myref= FirebaseDatabase.getInstance().getReference().child("users").child("application");
        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(myref,Model.class).build();
        adapter = new FirebaseRecyclerAdapter<Model, Viewholder2>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Viewholder2 holder, int position, @NonNull Model model) {
                holder.status.setText(model.getStatus());
                holder.email.setText(model.getEmail());
                holder.grade.setText(model.getMarks());
                holder.firstname.setText(model.getFirstname());
                holder.secondname.setText(model.getSecondname());


                holder.app.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onClick(View v) {
                        myref2 = FirebaseDatabase.getInstance().getReference().child("users").child(model.getUid()).child(model.getUid());
                        myref3 = FirebaseDatabase.getInstance().getReference().child("users").child("application").child(model.getUid());
                        Model model2 = new Model("id",
                                model.getFirstname(),
                                model.getSecondname(),
                                model.getMarks(),
                                model.getCourse(),
                                "APPROVED !",
                                model.getEmail(),
                                model.getUid()
                                );
                        myref2.setValue(model2);
                        Model model3 = new Model("id",
                                model.getFirstname(),
                                model.getSecondname(),
                                model.getMarks(),
                                model.getCourse(),
                                "You approved this course !",
                                model.getEmail(),
                                model.getUid()

                        );
                        myref3.setValue(model3);
                        Toast.makeText(getApplicationContext(),"Course Aproved",Toast.LENGTH_SHORT).show();
                        
                      //  myref2= FirebaseDatabase.getInstance().getReference().child("users").child("application").child(editid);
                        String[] TO = {model.getEmail()};
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Congratulations Course Approved");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Thankyou for Applying your course " +model.getCourse()+ "" + "has been approved");
                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            finish();
                            Log.i("Finished sending email...", "");
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }




                  }
                });
                holder.dis.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onClick(View v) {
                        myref2 = FirebaseDatabase.getInstance().getReference().child("users").child(model.getUid()).child(model.getUid());
                        myref3 = FirebaseDatabase.getInstance().getReference().child("users").child("application").child(model.getUid());
                        Model model2 = new Model("id",
                                model.getFirstname(),
                                model.getSecondname(),
                                model.getMarks(),
                                model.getCourse(),
                                "REJECTED !",
                                model.getEmail(),
                                model.getUid()
                        );
                        myref2.setValue(model2);
                        Model model3 = new Model("id",
                                model.getFirstname(),
                                model.getSecondname(),
                                model.getMarks(),
                                model.getCourse(),
                                "You Rejected this application!",
                                model.getEmail(),
                                model.getUid()

                        );
                        myref3.setValue(model3);
                        Toast.makeText(getApplicationContext(),"Course Rejected",Toast.LENGTH_SHORT).show();
                        String[] TO = {model.getEmail()};
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.setType("text/plain");
                        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "We are sorry");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, "We appologize your course " +model.getCourse()+ "" + "Could not be approved");
                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            finish();
                            Log.i("Finished sending email...", "");
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getApplicationContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }

            @NonNull
            @Override
            public Viewholder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sampleadminpanel,parent,false);
                return new Viewholder2(view);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}