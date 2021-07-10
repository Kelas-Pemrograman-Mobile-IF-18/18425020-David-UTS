package com.david.utskakdwi.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.david.utskakdwi.R;
import com.david.utskakdwi.Server.BaseURL;
import com.ornach.nobobutton.NoboButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class register extends AppCompatActivity {
    Button btnKembaliLogin;

    NoboButton btnRegistrasi;
    EditText edtUsername, edtPassword, edtNamaLengkap, edtAlamatLengkap;
    ProgressDialog pDialog;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        edtUsername = (EditText) findViewById(R.id.edUsername);
        edtPassword = (EditText) findViewById(R.id.edPassword);
        edtNamaLengkap = (EditText) findViewById(R.id.edNamaLengkap);
        edtAlamatLengkap = (EditText) findViewById(R.id.edAlamatLengkap);

        btnKembaliLogin = (Button) findViewById(R.id.kembaliLogin);
        btnRegistrasi = (NoboButton) findViewById(R.id.btRegister);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnKembaliLogin.setOnClickListener((v) -> {
            Intent i = new Intent(register.this, login.class);
            startActivity(i);
            finish();
        });

        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername      = edtUsername.getText().toString();
                String strPassword      = edtPassword.getText().toString();
                String strNamaLengkap   = edtNamaLengkap.getText().toString();
                String strAlamatLengkap = edtAlamatLengkap.getText().toString();

                if(strUsername.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strPassword.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strNamaLengkap.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strAlamatLengkap.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Alamat Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else {
                    registrasi(strUsername, strPassword, strNamaLengkap, strAlamatLengkap);
                }
            }
        });
    }

    public void registrasi(String username, String password, String namaLengkap, String AlamatLengkap){

        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("userName", username);
        params.put("password", password);
        params.put("namaLengkap", namaLengkap);
        params.put("alamatLengkap", AlamatLengkap);
        params.put("role", "2");


        pDialog.setMessage("Mohon Tunggu.....");
        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(BaseURL.Register, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            String strMsg = response.getString("msg");
                            boolean status= response.getBoolean("error");
                            if(status == false){
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                                Intent i = new Intent(register.this, login.class);
                                startActivity(i);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), strMsg, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });
        mRequestQueue.add(req);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(register.this, login.class);
        startActivity(i);
        finish();
    }

    private void showDialog(){
        if(!pDialog.isShowing()){
            pDialog.show();
        }
    }

    private void hideDialog(){
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }
}