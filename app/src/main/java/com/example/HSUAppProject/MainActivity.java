package com.example.HSUAppProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mainBtn, appCreateTest;
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        if(sp.getString("uID", null) == null){
            startActivity(new Intent(getApplicationContext(), Login.class));
        }

        mainBtn = findViewById(R.id.mainLogOut);
        appCreateTest = findViewById(R.id.createAppTest);

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = sp.edit();
                edit.putString("uID", null);
                edit.commit();

                Toast.makeText(MainActivity.this, "Logged Out Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        appCreateTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateAppointment.class));
            }
        });

    }
}