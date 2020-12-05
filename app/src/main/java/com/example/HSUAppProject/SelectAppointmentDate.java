package com.example.HSUAppProject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

@RequiresApi(api = Build.VERSION_CODES.N)
public class SelectAppointmentDate extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);

        CalendarView view = new CalendarView(this);
        //view = (CalendarView) findViewById(R.id.calendarView);

        setContentView(view);

        view.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                            int date) {
                Toast.makeText(getApplicationContext(),date+ "/"+month+"/"+year,Toast.LENGTH_LONG).show();

            }
        });
        //setContentView(cv);
    }
}