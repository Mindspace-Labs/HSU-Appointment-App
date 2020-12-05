package com.example.HSUAppProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CreateAppointment extends AppCompatActivity {

    private static final String TAG = "CreateAppointment";

    FirebaseDatabase root;
    DatabaseReference userRef, appRef;
    private TextView displayDate;
    TextInputEditText description;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    Button createSubmit;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        sp = getSharedPreferences("UserPref", Context.MODE_PRIVATE);

        displayDate = (TextView) findViewById(R.id.createAppDate);
        description = findViewById(R.id.createAppDisc);
        createSubmit = (Button) findViewById(R.id.createAppSubmit);

        displayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreateAppointment.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/" + year;
                displayDate.setText(date);
            }
        };

        createSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root = FirebaseDatabase.getInstance();
                userRef = root.getReference("Users");
                appRef = root.getReference("Appointments");

                String ID = sp.getString("uID", null);

                Query check = userRef.orderByChild("id").equalTo(Integer.parseInt(ID)).limitToFirst(1);
                check.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String dbFirstName = snapshot.child(ID).child("firstName").getValue(String.class);
                            String dbLastName = snapshot.child(ID).child("lastName").getValue(String.class);
                            String name = dbFirstName + " " + dbLastName;

                            String appDate = displayDate.getText().toString().trim();
                            String appDisc = description.getEditableText().toString().trim();

                            AppointHelperClass helper = new AppointHelperClass(ID, name, appDisc, appDate);
                            appRef.push().setValue(helper);

                            Toast.makeText(CreateAppointment.this, "Appointment submitted for approval!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

    }
}