package com.example.HSUAppProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EnterAppointmentDetails extends AppCompatActivity {

    private Button button;
    private EditText name, sid, pnum, fac, con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        name = findViewById(R.id.name);

        sid = findViewById(R.id.sid);
        pnum = findViewById(R.id.pnumber);

        fac = findViewById(R.id.faculty);
        con = findViewById(R.id.condition);
        button = findViewById(R.id.submitButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = name.getText().toString();
                String usersid = sid.getText().toString();
                String userpnum = pnum.getText().toString();
                String userfac = fac.getText().toString();
                String usercon = con.getText().toString();

                Intent intent = new Intent(EnterAppointmentDetails.this, AppointmentConfirmation.class);
                intent.putExtra("keyname",username);
                intent.putExtra("keysid",usersid);
                intent.putExtra("keypnum",userpnum);
                intent.putExtra("keyfac",userfac);
                intent.putExtra("keycon",usercon);
                startActivity(intent);
            }
        });

        }



}