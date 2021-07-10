package com.david.utskakdwi.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.david.utskakdwi.R;
import com.david.utskakdwi.Session.PrefSetting;
import com.david.utskakdwi.Session.SessionManager;
import com.david.utskakdwi.User.login;

public class homeAdmin extends AppCompatActivity {

    CardView cardInput, cardEdit, cardProfile, cardExit;
    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        getSupportActionBar().hide();

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreferances();

        session = new SessionManager(homeAdmin.this);

        prefSetting.isLogin(session, prefs);

        cardInput = (CardView) findViewById(R.id.cardInputData);
        cardEdit = (CardView) findViewById(R.id.cardEditData);
        cardProfile = (CardView) findViewById(R.id.cardProfile);
        cardExit = (CardView) findViewById(R.id.cardExit);

        cardInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homeAdmin.this, InputData.class);
                startActivity(i);
                finish();
            }
        });

        cardEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homeAdmin.this, DataMobil.class);
                startActivity(i);
                finish();
            }
        });

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homeAdmin.this, Profile.class);
                startActivity(i);
                finish();
            }
        });

        cardExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(homeAdmin.this, login.class);
                startActivity(i);
                finish();
            }
        });
    }
}