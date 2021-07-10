package com.david.utskakdwi.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.david.utskakdwi.Model.ModelMobil;
import com.david.utskakdwi.R;
import com.david.utskakdwi.Server.BaseURL;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterMobil extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<ModelMobil> item;

    public AdapterMobil(Activity activity, List<ModelMobil> item) {
        this.activity = activity;
        this.item = item;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content_mobil, null);


        TextView NamaMobil = (TextView) convertView.findViewById(R.id.txNamaMobil);
        TextView WarnaMobil = (TextView) convertView.findViewById(R.id.txWarnaMobil);
        TextView TahunMobil = (TextView) convertView.findViewById(R.id.txTahunMobil);
        TextView HargaMobil = (TextView) convertView.findViewById(R.id.txHargaMobil);
        ImageView GambarMobil = (ImageView) convertView.findViewById(R.id.gambarMobil);

        NamaMobil.setText(item.get(position).getNamaMobil());
        WarnaMobil.setText(item.get(position).getWarnaMobil());
        TahunMobil.setText(item.get(position).getTahunMobil());
        HargaMobil.setText("Rp." + item.get(position).getHargaMobil());
        Picasso.get().load(BaseURL.baseUrl + "gambar/" + item.get(position).getGambar())
                .resize(40, 40)
                .centerCrop()
                .into(GambarMobil);
        return convertView;
    }
}
