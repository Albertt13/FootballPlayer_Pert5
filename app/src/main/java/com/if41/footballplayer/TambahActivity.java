package com.if41.footballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {

    private EditText et_nama, et_nomor, et_club;
    private Button btnTambah;
    MyDatabaseHelper myDB = new MyDatabaseHelper(TambahActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        et_nama = findViewById(R.id.et_name);
        et_nomor = findViewById(R.id.et_nomor);
        et_club = findViewById(R.id.et_club);
        btnTambah = findViewById(R.id.btn_tambah);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama, nomor, club;

                nama = et_nama.getText().toString();
                nomor = et_nomor.getText().toString();
                club = et_club.getText().toString();

                if (nama.trim().equals("")) {
                    et_nama.setError("Nama Tidak Boleh Kosong");
                }
                else if (nomor.trim().equals("")) {
                    et_nomor.setError("Nomor Punggung Tidak Boleh Kosong");
                }
                else if (club.trim().equals("")) {
                    et_club.setError("Club Tidak Boleh Kosong");
                }
                else {
                    long eks = myDB.tambahPlayer(nama, nomor, club);

                    if (eks == -1) {
                        Toast.makeText(TambahActivity.this, "Gagal Menambah Data", Toast.LENGTH_SHORT).show();
                        et_nama.requestFocus();
                    }
                    else {
                        Toast.makeText(TambahActivity.this, "Berhasil Menambah Data", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                }
            }
        });
    }
}