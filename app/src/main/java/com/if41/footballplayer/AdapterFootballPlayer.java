package com.if41.footballplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFootballPlayer extends RecyclerView.Adapter<AdapterFootballPlayer.ViewHolderPLayer>{

    private Context ctx;
    private ArrayList arrNama, arrNomor, arrClub;



    public AdapterFootballPlayer(Context ctx, ArrayList arrNama, ArrayList arrNomor, ArrayList arrClub) {
        this.ctx = ctx;
        this.arrNama = arrNama;
        this.arrNomor = arrNomor;
        this.arrClub = arrClub;
    }

    @NonNull
    @Override
    public ViewHolderPLayer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(ctx).inflate(R.layout.list_item_player, parent, false);
        return new ViewHolderPLayer(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPLayer holder, int position) {
        holder.tvNama.setText(arrNama.get(position).toString());
        holder.tvNomor.setText(arrNomor.get(position).toString());
        holder.tvClub.setText(arrClub.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrNama.size();
    }

    public class ViewHolderPLayer extends RecyclerView.ViewHolder {

        private TextView tvNama, tvNomor, tvClub;

        public ViewHolderPLayer(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.et_name);
            tvNomor = itemView.findViewById(R.id.et_nomor);
            tvClub = itemView.findViewById(R.id.et_club);


        }
    }

}
