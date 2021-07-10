package com.david.utskakdwi.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.david.utskakdwi.Adapter.AdapterMobil;
import com.david.utskakdwi.Model.ModelMobil;
import com.david.utskakdwi.R;
import com.david.utskakdwi.Server.BaseURL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataMobil extends AppCompatActivity {

    ProgressDialog pDialog;

    AdapterMobil adapter;
    ListView list;

    ArrayList<ModelMobil> newsList = new ArrayList<ModelMobil>();
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mobil);

        getSupportActionBar().setTitle("Data Mobil");
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new AdapterMobil(DataMobil.this, newsList);
        list.setAdapter(adapter);
        getAllMobil();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(DataMobil.this, homeAdmin.class);
        startActivity(i);
        finish();
    }

    private void getAllMobil() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, BaseURL.dataMobil, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                Log.d("data mobil = ", response.toString());
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    final ModelMobil mobil = new ModelMobil();
                                    final String _id = jsonObject.getString("_id");
                                    final String NamaMobil = jsonObject.getString("namaMobil");
                                    final String WarnaMobil = jsonObject.getString("warnaMobil");
                                    final String tahunMobil = jsonObject.getString("tahunMobil");
                                    final String HargaMobil = jsonObject.getString("hargaMobil");
                                    final String gambar = jsonObject.getString("gambar");
                                    mobil.set_id(_id);
                                    mobil.setNamaMobil(NamaMobil);
                                    mobil.setWarnaMobil(WarnaMobil);
                                    mobil.setTahunMobil(tahunMobil);
                                    mobil.setHargaMobil(HargaMobil);
                                    mobil.setGambar(gambar);

                                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            // TODO Auto-generated method stub
                                            Intent a = new Intent(DataMobil.this, EditDanHapusData.class);
                                            a.putExtra("_id", newsList.get(position).get_id());
                                            a.putExtra("namaMobil", newsList.get(position).getNamaMobil());
                                            a.putExtra("warnaMobil", newsList.get(position).getWarnaMobil());
                                            a.putExtra("tahunMobil", newsList.get(position).getTahunMobil());
                                            a.putExtra("hargaMobil", newsList.get(position).getHargaMobil());
                                            a.putExtra("gambar", newsList.get(position).getGambar());
                                            startActivity(a);
                                        }
                                    });
                                    newsList.add(mobil);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}