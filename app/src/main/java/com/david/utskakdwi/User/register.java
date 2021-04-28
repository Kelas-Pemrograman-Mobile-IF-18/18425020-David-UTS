package com.david.utskakdwi.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.david.utskakdwi.R;

public class register extends AppCompatActivity {
    Button btnKembaliLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        btnKembaliLogin = (Button) findViewById(R.id.kembaliLogin);

        btnKembaliLogin.setOnClickListener((v) -> {
            Intent i = new Intent(register.this, login.class);
            startActivity(i);
            finish();
        });

    }
}