package com.david.utskakdwi.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.david.utskakdwi.R;

public class homeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        getSupportActionBar().hide();
    }
}