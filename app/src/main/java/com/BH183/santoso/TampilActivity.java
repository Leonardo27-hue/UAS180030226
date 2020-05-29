package com.BH183.santoso;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

public class TampilActivity extends AppCompatActivity {
    private ImageView imgBuku;
    private TextView tvJudul, tvPenulis, tvTanggal, tvSinopsis_buku;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy");
    private String linkBuku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        imgBuku = findViewById(R.id.iv_buku);
        tvJudul = findViewById(R.id.tv_judul);
        tvPenulis = findViewById(R.id.tv_penulis);
        tvTanggal = findViewById(R.id.tv_tanggal);
        tvSinopsis_buku = findViewById(R.id.tv_sinopsis_buku);

        Intent terimaData = getIntent();
        String imgLocation = terimaData.getStringExtra("GAMBAR");
        tvJudul.setText(terimaData.getStringExtra("JUDUL"));
        tvPenulis.setText(terimaData.getStringExtra("PENULIS"));
        tvTanggal.setText(terimaData.getStringExtra("TANGGAL"));
        tvSinopsis_buku.setText(terimaData.getStringExtra("SINOPSIS_BUKU"));
        int id = getApplicationContext().getResources().getIdentifier(imgLocation, "drawable", getApplicationContext().getPackageName());
        imgBuku.setImageResource(id);

//        try {
//            File file = new File(imgLocation);
//            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
//            imgBuku.setImageBitmap(bitmap);
//            imgBuku.setContentDescription(imgLocation);
//        } catch (FileNotFoundException er) {
//            er.printStackTrace();
//            Toast.makeText(this, "Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
//        }

        linkBuku = terimaData.getStringExtra("LINK");
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.tampil_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {

            if (item.getItemId() == R.id.item_bagikan){
                Intent bagikanBuku = new Intent(Intent.ACTION_SEND);
                bagikanBuku.putExtra(Intent.EXTRA_SUBJECT, tvJudul.getText().toString());
                bagikanBuku.putExtra(Intent.EXTRA_TEXT, linkBuku);
                bagikanBuku.setType("text/plain");
                startActivity(Intent.createChooser(bagikanBuku, "Bagikan buku"));
            }

            return super.onOptionsItemSelected(item);

    }
}
