package com.example.HSUAppProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewReport extends AppCompatActivity {

    TextView t0,t1,t2,t3;
    FirebaseDatabase root;
    DatabaseReference userRef, appRef, ref;
    Button btn;
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);

        t0 = findViewById(R.id.textViewTest);
        t1 = findViewById(R.id.textViewName);
        t2 = findViewById(R.id.textViewDate);
        t3 = findViewById(R.id.textViewCon);

        sp = getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        root = FirebaseDatabase.getInstance();
        appRef = root.getReference("Appointments");
        String ID = sp.getString("uID", null);

        appRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String dbname = ds.child("name").getValue(String.class);
                    String dateapp = ds.child("date").getValue(String.class);
                    String descrip = ds.child("description").getValue(String.class);

                    t2.setText(dbname);
                    t3.setText(dateapp);
                    t0.setText(descrip);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}