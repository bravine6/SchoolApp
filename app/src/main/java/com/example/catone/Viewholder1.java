package com.example.catone;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Viewholder1 extends RecyclerView.ViewHolder {
    TextView firstname,secondname,course,status;
    public Viewholder1(@NonNull View itemView) {
        super(itemView);
        firstname= itemView.findViewById(R.id.samplefirstname);
        secondname = itemView.findViewById(R.id.samplesecondname);
        course = itemView.findViewById(R.id.samplecourse);
        status = itemView.findViewById(R.id.samplestatus);
    }
}
