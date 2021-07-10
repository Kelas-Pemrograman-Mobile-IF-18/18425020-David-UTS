package com.david.utskakdwi.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.david.utskakdwi.R;
import com.david.utskakdwi.Session.PrefSetting;

public class Profile extends AppCompatActivity {

    TextView txtUserName, txtNamaLengkap, txtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile User");

        txtUserName = (TextView) findViewById(R.id.userName);
        txtNamaLengkap = (TextView) findViewById(R.id.namaLengkap);
        txtAlamat = (TextView) findViewById(R.id.alamatadmin);

        txtUserName.setText(PrefSetting.userName);
        txtNamaLengkap.setText(PrefSetting.namaLengkap);
        txtAlamat.setText(PrefSetting.alamatLengkap);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, homeAdmin.class);
        startActivity(i);
        finish();
    }
}