package com.BH183.santoso;

import java.util.Date;

public class Buku {

    private int idBuku;
    private String judul;
    private String penulis;
    private Date tanggal;
    private String gambar;
    private String sinopsis_buku;
    private String link;

    public Buku(int idBuku, String judul, String penulis, Date tanggal, String gambar, String sinopsis_buku, String link) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.tanggal = tanggal;
        this.gambar = gambar;
        this.sinopsis_buku = sinopsis_buku;
        this.link = link;
    }


    public int getIdBuku(){
        return idBuku;
    }

    public void setIdBuku(int idBuku){
        this.idBuku = idBuku;
    }

    public String getJudul(){
        return judul;
    }

    public void setJudul(String judul){
        this.judul = judul;
    }

    public String getPenulis(){
        return penulis;
    }

    public void setPenulis(String penulis){
        this.penulis = penulis;
    }

    public Date getTanggal(){
        return tanggal;
    }

    public void setTanggal(Date tanggal){
        this.tanggal = tanggal;
    }

    public String getGambar(){
        return gambar;
    }

    public void setGambar(String gambar){
        this.gambar = gambar;
    }

    public String getSinopsis_buku(){
        return sinopsis_buku;
    }

    public void setSinopsis_buku(String sinopsis_buku){
        this.sinopsis_buku = sinopsis_buku;
    }

    public String getLink(){
        return link;
    }
    public void setLink(String link){
        this.link = link;
    }
}
