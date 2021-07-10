package com.david.utskakdwi.Pembeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.david.utskakdwi.R;
import com.david.utskakdwi.Server.BaseURL;
import com.squareup.picasso.Picasso;

public class DetailMobil extends AppCompatActivity {

    EditText edtNamaMobil, edtWarnaMobil, edtTahun, edtHarga;
    ImageView imgGambarMobil;

    String strNamaMobil, strWarnaMobil, strTahun, strHarga, strGambar, _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_mobil);

        edtNamaMobil = (EditText) findViewById(R.id.edNamaMobil1);
        edtWarnaMobil = (EditText) findViewById(R.id.edWarnaMobil1);
        edtTahun = (EditText) findViewById(R.id.edTahunMobil1);
        edtHarga = (EditText) findViewById(R.id.edHargaMobil1);

        imgGambarMobil = (ImageView) findViewById(R.id.gambar);

        Intent i = getIntent();
        strNamaMobil = i.getStringExtra("namaMobil");
        strWarnaMobil = i.getStringExtra("warnaMobil");
        strTahun = i.getStringExtra("tahunMobil");
        strHarga = i.getStringExtra("hargaMobil");
        strGambar = i.getStringExtra("gambar");
        _id = i.getStringExtra("_id");

        edtNamaMobil.setText(strNamaMobil);
        edtWarnaMobil.setText(strWarnaMobil);
        edtTahun.setText(strTahun);
        edtHarga.setText(strHarga);
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + strGambar)
                .into(imgGambarMobil);
    }
}