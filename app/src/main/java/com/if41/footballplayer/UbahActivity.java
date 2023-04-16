package com.if41.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {

    private EditText et_nama, et_nomor, et_club;
    private Button btnUbah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        et_nama = findViewById(R.id.et_name);
        et_nomor = findViewById(R.id.et_nomor);
        et_club = findViewById(R.id.et_club);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent varIntent = getIntent();
        String id = varIntent.getStringExtra("varID");
        String nama = varIntent.getStringExtra("varNama");
        String nomor = varIntent.getStringExtra("varNomor");
        String club = varIntent.getStringExtra("varClub");

        et_nama.setText(nama);
        et_nomor.setText(nomor);
        et_club.setText(club);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNama, getNomor, getClub;

                getNama = et_nama.getText().toString();
                getNomor = et_nomor.getText().toString();
                getClub = et_club.getText().toString();

                if (getNama.trim().equals("")) {
                    et_nama.setError("Nama Tidak Boleh Kosong");
                }
                else if (getNomor.trim().equals("")) {
                    et_nomor.setError("Nomor Punggung Tidak Boleh Kosong");
                }
                else if (getClub.trim().equals("")) {
                    et_club.setError("Club Tidak Boleh Kosong");
                }
                else {
                    long eks = myDB.ubahPlayer(id, getNama, getNomor, getClub);

                    if (eks == -1) {
                        Toast.makeText(UbahActivity.this, "Gagal Mengubah Data", Toast.LENGTH_SHORT).show();
                        et_nama.requestFocus();     //Agar cursor balik ke et_nama
                    }
                    else {
                        Toast.makeText(UbahActivity.this, "Berhasil Mengubah Data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}