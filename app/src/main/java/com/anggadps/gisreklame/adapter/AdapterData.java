package com.anggadps.gisreklame.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anggadps.gisreklame.DetailActivity;
import com.anggadps.gisreklame.InsertActivity;
import com.anggadps.gisreklame.Model.DataModel;
import com.anggadps.gisreklame.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private List<DataModel> mList;
    private Context ctx;

    public AdapterData (Context ctx, List<DataModel>mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent , int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist,parent,false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.nama_tempat.setText(dm.getNama_tempat());

        //TAMPIL GAMBAR
        //Glide.with(ctx)
                //.load("http://192.168.43.70/reklame/imageview/img/" + dm.getGambar())
               // .asBitmap()
               // .fitCenter()
                //.into(holder.gambar);

        holder.lat.setText(dm.getLat());
        holder.lng.setText(dm.getLng());
        holder.lokasi.setText(dm.getLokasi());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    class HolderData extends RecyclerView.ViewHolder{
        TextView nama_tempat, lat, lng, lokasi;
        ImageView gambar;
        DataModel dm;



        public HolderData (View v)
        {
            super(v);

            nama_tempat = (TextView) v.findViewById(R.id.tvNama_tempat);
            //gambar = (ImageView) v.findViewById(R.id.imgplace);
            lat = (TextView) v.findViewById(R.id.tvLat);
            lng = (TextView) v.findViewById(R.id.tvLng);
            lokasi = (TextView) v.findViewById(R.id.tvLokasi);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx, InsertActivity.class);
                    goInput.putExtra("id_tempat", dm.getId_tempat());
                    goInput.putExtra("nama_tempat", dm.getNama_tempat());
                    goInput.putExtra("lat", dm.getLat());
                    goInput.putExtra("lng", dm.getLng());
                    goInput.putExtra("lokasi", dm.getLokasi());
                    goInput.putExtra("keterangan", dm.getKeterangan());

                    ctx.startActivity(goInput);

                }
            });
        }
    }
}
