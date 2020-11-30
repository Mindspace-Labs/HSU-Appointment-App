package com.example.HSUAppProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.se.omapi.Session;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    TextInputEditText lgnID, lgnPassword;
    Button lgnBtn, goToSignUpBtn;
    FirebaseDatabase root;
    DatabaseReference ref;
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        if(sp.getString("uID", null) != null){
            startActivity(new Intent(getApplicationContext(), HomeScreen.class));
        }

        lgnID = findViewById(R.id.loginID);
        lgnPassword = findViewById(R.id.loginPassword);
        lgnBtn = findViewById(R.id.loginBtn);
        goToSignUpBtn = findViewById(R.id.goToSignUp);

        lgnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root = FirebaseDatabase.getInstance();
                ref = root.getReference("Users");

                String ID = lgnID.getEditableText().toString().trim();
                String password = lgnPassword.getEditableText().toString().trim();

                if(TextUtils.isEmpty(ID)) {
                    lgnID.setError("ID is required");
                    lgnID.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    lgnPassword.setError("Password is required");
                    lgnPassword.requestFocus();
                    return;
                }

                Query check = ref.orderByChild("id").equalTo(Integer.parseInt(ID)).limitToFirst(1);
                check.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            String dbPassword = snapshot.child(ID).child("password").getValue(String.class);
                            if(dbPassword.equals(password)) {
                                edit = sp.edit();
                                edit.putString("uID", ID);
                                if(Character.compare(ID.charAt(0), (char)8) == 0){
                                    edit.putString("type", "Student");
                                    edit.commit();
                                    Toast.makeText(Login.this, "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), HomeScreen.class));

                                } else {
                                    edit.putString("type", "Staff");
                                    edit.commit();

                                    Toast.makeText(Login.this, "Logged In Successfully!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                                }
                            } else {
                                lgnPassword.setError("Incorrect password");
                                lgnPassword.requestFocus();
                                return;
                            }
                        } else {
                            lgnID.setError("No user with this ID");
                            lgnID.requestFocus();
                            return;
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        goToSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
            }
        });

    }
}