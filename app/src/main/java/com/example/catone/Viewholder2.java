package com.example.catone;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Viewholder2 extends RecyclerView.ViewHolder {
    TextView firstname,secondname,email,status,grade;
    Button app,dis;
    public Viewholder2(@NonNull View itemView) {
        super(itemView);
        firstname = itemView.findViewById(R.id.adminfirstname);
        secondname = itemView.findViewById(R.id.adminsecondname);
        email= itemView.findViewById(R.id.adminemail);
        grade = itemView.findViewById(R.id.grade);
        status = itemView.findViewById(R.id.adminstatus);
        app = itemView.findViewById(R.id.app);
        dis = itemView.findViewById(R.id.dis);
    }
}
