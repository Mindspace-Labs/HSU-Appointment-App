package com.example.HSUAppProject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {

    TextInputEditText regID, regFirstName, regLastName, regPassword;
    Button regBtn, goToLoginBtn;
    FirebaseDatabase root;
    DatabaseReference ref;
    SharedPreferences sp;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sp = getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        if(sp.getString("uID", null) != null){
            if(sp.getString("type", null).equals("Student")) {
                startActivity(new Intent(getApplicationContext(), HomeScreen.class));
            } else {
                startActivity(new Intent(getApplicationContext(), FullAppointmentList.class));
            }
        }

        regID = findViewById(R.id.signUpID);
        regFirstName = findViewById(R.id.signUpFirstName);
        regLastName = findViewById(R.id.signUpLastName);
        regPassword = findViewById(R.id.signUpPassword);
        regBtn = findViewById(R.id.signUpBtn);
        goToLoginBtn = findViewById(R.id.goToLogin);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                root = FirebaseDatabase.getInstance();
                ref = root.getReference("Users");

                String ID = regID.getEditableText().toString().trim();
                String firstName = regFirstName.getEditableText().toString().trim();
                String lastName = regLastName.getEditableText().toString().trim();
                String password = regPassword.getEditableText().toString().trim();

                if(TextUtils.isEmpty(ID)) {
                    regID.setError("ID is required");
                    regID.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(firstName)) {
                    regFirstName.setError("First name is required");
                    regFirstName.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(lastName)) {
                    regLastName.setError("Last name is required");
                    regLastName.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(firstName)) {
                    regPassword.setError("Password is required");
                    regPassword.requestFocus();
                    return;
                }

                if(ID.length() != 9) {
                    regID.setError("Invalid ID");
                    regID.requestFocus();
                }

                if(password.length() < 6) {
                    regPassword.setError("Password must be >= 6 characters long");
                    regPassword.requestFocus();
                    return;
                }

                UserHelperClass help = new UserHelperClass(ID, firstName, lastName, password);
                ref.child(ID).setValue(help);

                edit = sp.edit();
                edit.putString("uID", ID);
                if(Character.compare(ID.charAt(0), (char)8) == 0){
                    edit.putString("type", "Student");
                    edit.commit();
                    Toast.makeText(SignUp.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                } else {
                    edit.putString("type", "Staff");
                    edit.commit();

                    Toast.makeText(SignUp.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), FullAppointmentList.class));
                }
            }
        });

        goToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
}