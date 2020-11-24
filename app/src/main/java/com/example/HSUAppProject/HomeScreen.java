package com.example.HSUAppProject;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.HSUAppProject.ui.UserSettings;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeScreen extends AppCompatActivity {
    ImageButton userButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        //        R.id.ic_home, R.id.ic_notification, R.id.addappt, R.id.ic_prescription, R.id.ic_medrecord)
        //.build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        userButton = (ImageButton) findViewById(R.id.imageButton);
        userButton.setOnClickListener(new ImageButton_Clicker());
    }

    class ImageButton_Clicker implements ImageButton.OnClickListener {
        @Override
        public void onClick(View v) {

            if (v == userButton) {
                Context context = getApplicationContext();
                Intent intent = new Intent(context, UserSettings.class);

                startActivity(intent);
            }
        }
    }
}