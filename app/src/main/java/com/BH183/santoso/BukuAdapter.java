package com.BH183.santoso;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.icu.text.NumberingSystem;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.CollationElementIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import static com.BH183.santoso.R.id.tv_penulis;
import static com.BH183.santoso.R.id.tv_sinopsis_buku;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.BukuViewHolder> {

    private Context context;
    private ArrayList<Buku> dataBuku;
    private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

    public BukuAdapter(Context context, ArrayList<Buku> dataBuku) {
        this.context = context;
        this.dataBuku = dataBuku;
    }


    @NonNull
    @Override
    public BukuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_buku, parent, false);
        return new BukuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BukuViewHolder holder, int position) {
        Buku tempBuku = dataBuku.get(position);
        holder.idBuku = tempBuku.getIdBuku();
        holder.tvJudul.setText(tempBuku.getJudul());
        holder.gambar = tempBuku.getGambar();
        holder.tvPenulis.setText(tempBuku.getPenulis());
        holder.sinopsis_buku = tempBuku.getSinopsis_buku();
        holder.link = tempBuku.getLink();
        int id = context.getResources().getIdentifier(holder.gambar, "drawable", context.getPackageName());
        holder.imgBuku.setImageResource(id);
//        try {
//            File file = new File(holder.gambar);
//            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
//            holder.imgBuku.setImageBitmap(bitmap);
//            holder.imgBuku.setContentDescription(holder.gambar);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            Toast.makeText(context,"Gagal mengambil gambar dari media penyimpanan", Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public int getItemCount() {
        return dataBuku.size();
    }

    public class BukuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private int idBuku;
        private TextView tvJudul, tvPenulis;
        private ImageView imgBuku;
        private String tanggal, penulis, gambar, sinopsis_buku, link;


        public BukuViewHolder(@NonNull View itemView) {
            super(itemView);

            imgBuku = itemView.findViewById(R.id.iv_buku);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvPenulis = itemView.findViewById(R.id.tv_penulis);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Intent bukaBuku = new Intent(context, TampilActivity.class);
            bukaBuku.putExtra("ID", idBuku);
            bukaBuku.putExtra("JUDUL", tvJudul.getText().toString());
            bukaBuku.putExtra("PENULIS", penulis);
            bukaBuku.putExtra("TANGGAL", tanggal);
            bukaBuku.putExtra("GAMBAR", gambar);
            bukaBuku.putExtra("SINOPSIS_BUKU", sinopsis_buku);
            bukaBuku.putExtra("LINK", link);
            context.startActivity(bukaBuku);
        }

        @Override
        public boolean onLongClick(View v) {

            Intent bukaInput = new Intent(context, InputActivity.class);
            bukaInput.putExtra("ID", idBuku);
            bukaInput.putExtra("JUDUL", tvJudul.getText().toString());
            bukaInput.putExtra("PENULIS", penulis);
            bukaInput.putExtra("TANGGAL", tanggal);
            bukaInput.putExtra("GAMBAR", gambar);
            bukaInput.putExtra("SINOPSIS_BUKU", sinopsis_buku);
            bukaInput.putExtra("LINK", link);
            context.startActivity(bukaInput);
            return true;
        }
    }
}
