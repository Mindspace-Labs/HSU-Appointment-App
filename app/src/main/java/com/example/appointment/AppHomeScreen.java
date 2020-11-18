package com.example.appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AppHomeScreen extends AppCompatActivity {
    private ImageButton addNew,viewAppt,menu,cancel,chat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_home_screen);

        addNew = findViewById(R.id.createApptButton);
        addNew.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){

                    Intent intent = new Intent(AppHomeScreen.this, EnterAppointmentDetails.class);
                    startActivity(intent);

            }
        });
    }





}