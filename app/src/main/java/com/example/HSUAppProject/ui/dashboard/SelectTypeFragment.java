package com.example.HSUAppProject.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.HSUAppProject.CreateAppointment;
import com.example.HSUAppProject.EnterAppointmentDetails;
import com.example.HSUAppProject.HomeScreen;
import com.example.HSUAppProject.R;
import com.example.HSUAppProject.ui.UserSettings;

public class SelectTypeFragment extends Fragment {
    private Button newButton;

    private SelectTypeViewModel selectTypeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        selectTypeViewModel =
                new ViewModelProvider(this).get(SelectTypeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_select_type, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        
        selectTypeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        newButton = (Button) root.findViewById(R.id.button2);
        newButton.setOnClickListener(new Button_Clicker());


        return root;
    }

    class Button_Clicker implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v == newButton) {

                Intent intent = new Intent(getActivity(), CreateAppointment.class);
                startActivity(intent);

            }

        }

    }
}