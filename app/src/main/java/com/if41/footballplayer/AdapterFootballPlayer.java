package com.if41.footballplayer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFootballPlayer extends RecyclerView.Adapter<AdapterFootballPlayer.ViewHolderPLayer>{

    private Context ctx;
    private ArrayList arrID, arrNama, arrNomor, arrClub;


    public AdapterFootballPlayer(Context ctx, ArrayList arrID, ArrayList arrNama, ArrayList arrNomor, ArrayList arrClub) {
        this.ctx = ctx;
        this.arrID = arrID;
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
        holder.tvID.setText(arrID.get(position).toString());
        holder.tvNama.setText(arrNama.get(position).toString());
        holder.tvNomor.setText(arrNomor.get(position).toString());
        holder.tvClub.setText(arrClub.get(position).toString());
    }

    @Override
    public int getItemCount() {

        return arrNama.size();
    }

    public class ViewHolderPLayer extends RecyclerView.ViewHolder {

        private TextView tvID, tvNama, tvNomor, tvClub;

        public ViewHolderPLayer(@NonNull View itemView) {
            super(itemView);

            tvID = itemView.findViewById(R.id.tv_id);
            tvNama = itemView.findViewById(R.id.tv_nama);
            tvNomor = itemView.findViewById(R.id.tv_nomor);
            tvClub = itemView.findViewById(R.id.tv_club);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder pesan = new AlertDialog.Builder(ctx);
                    pesan.setTitle("Perhatian");
                    pesan.setMessage("Anda Memilih " + tvNama.getText().toString() + ". Pilih Perintah yang Anda Inginkan");
                    pesan.setCancelable(true);  //jika ga jadi maka bisa klik di sembarang tempat

                    pesan.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {     //new DialogInterface.OnClickListener() bertujuan untuk apa yang terjadi kalau kita ubah
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    pesan.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MyDatabaseHelper myDB = new MyDatabaseHelper(ctx);
                            long eksekusi = myDB.hapusPlayer(tvID.getText().toString());

                            if (eksekusi == -1) {
                                Toast.makeText(ctx, "Gagal Menghapus Data!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(ctx, "Sukses Menghapus Data!", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                ((MainActivity) ctx).onResume();    //Agar saat menghapus maka data yang ada di MainActivity langsung hilang
                            }
                        }
                    });

                    pesan.show();
                    return false;
                }
            });
        }
    }

}
