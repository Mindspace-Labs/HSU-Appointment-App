package com.example.HSUAppProject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.HSUAppProject.ui.UserSettings;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FullAppointmentList extends AppCompatActivity {

    DatabaseReference ref;
    ListView list;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Button logout;
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_appointment_list);
        ref = FirebaseDatabase.getInstance().getReference("Appointments");
        list = (ListView) findViewById(R.id.FullAppList);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
        list.setAdapter(adapter);

        logout = findViewById(R.id.StaffLogOut);
        sp = getSharedPreferences("UserPref", Context.MODE_PRIVATE);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String entry = "Student Name: " + snapshot.child("name").getValue().toString();
                entry += "\n\nStudent ID: " + snapshot.child("studentID").getValue().toString();
                entry += "\n\nDescription:\n" + snapshot.child("description").getValue().toString();
                entry += "\n\n\nDate: " + snapshot.child("date").getValue().toString();
                arrayList.add(entry);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit = sp.edit();
                edit.putString("uID", null);
                edit.putString("type", null);
                edit.commit();

                Toast.makeText(FullAppointmentList.this, "Logged Out Successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}