package com.example.appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AppointmentConfirmation extends AppCompatActivity {

    private TextView name,sid, pnum,fac,con;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name = findViewById(R.id.text_name);
        sid = findViewById(R.id.text_SID);
        pnum = findViewById(R.id.text_num);
        fac = findViewById(R.id.text_fac);
        con = findViewById(R.id.condition);
        button = findViewById(R.id.confirmbutton);;

        String username = getIntent().getStringExtra("keyname");
        String userid = getIntent().getStringExtra("keysid");
        String userpnum = getIntent().getStringExtra("keypnum");
        String userfac = getIntent().getStringExtra("keyfac");
        String usercon = getIntent().getStringExtra("keycon");


        name.setText(username);
        sid.setText(userid);
        pnum.setText(userpnum);
        fac.setText(userfac);
        //con.setText(usercon);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = name.getText().toString();
                String usersid = sid.getText().toString();
                String userpnum = pnum.getText().toString();
                String userfac = fac.getText().toString();
                //String usercon = con.getText().toString();

                Intent intent = new Intent(AppointmentConfirmation.this, SelectAppointmentDate.class);
                intent.putExtra("keyname", username);
                intent.putExtra("keysid", usersid);
                intent.putExtra("keypnum", userpnum);
                intent.putExtra("keyfac", userfac);
                startActivity(intent);
            }
        });
    }
}