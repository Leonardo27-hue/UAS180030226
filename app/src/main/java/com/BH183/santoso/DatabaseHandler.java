package com.BH183.santoso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "db_buku";
    private final static String TABLE_BUKU = "t_buku";
    private final static String KEY_ID_BUKU = "ID_Buku";
    private final static String KEY_JUDUL = "Judul";
    private final static String KEY_PENULIS = "Penulis";
    private final static String KEY_TGL = "Tanggal";
    private final static String KEY_GAMBAR = "Gambar";
    private final static String KEY_SINOPSIS_BUKU = "Sinopsis_buku";
    private final static String KEY_LINK = "Link";
    private SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy", Locale.getDefault());
    private Context context;

    public DatabaseHandler (Context ctx){
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_BUKU = "CREATE TABLE " + TABLE_BUKU
                + "(" + KEY_ID_BUKU + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_JUDUL + " TEXT, " + KEY_PENULIS + " TEXT, "
                + KEY_TGL + " DATE, " + KEY_GAMBAR + " TEXT, "
                + KEY_SINOPSIS_BUKU + " TEXT, "
                + KEY_LINK + " TEXT);";

        db.execSQL(CREATE_TABLE_BUKU);
        inisialisasiBukuAwal(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_BUKU;
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void tambahBuku(Buku dataBuku){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_TGL, sdFormat.format(dataBuku.getTanggal()));
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_SINOPSIS_BUKU, dataBuku.getSinopsis_buku());
        cv.put(KEY_LINK, dataBuku.getLink());
        db.insert(TABLE_BUKU, null, cv);
        db.close();
    }

    public void tambahBuku(Buku dataBuku, SQLiteDatabase db){
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_TGL, sdFormat.format(dataBuku.getTanggal()));
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_SINOPSIS_BUKU, dataBuku.getSinopsis_buku());
        cv.put(KEY_LINK, dataBuku.getLink());
        db.insert(TABLE_BUKU, null, cv);
    }

    public void editBuku(Buku dataBuku) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_JUDUL, dataBuku.getJudul());
        cv.put(KEY_PENULIS, dataBuku.getPenulis());
        cv.put(KEY_TGL, sdFormat.format(dataBuku.getTanggal()));
        cv.put(KEY_GAMBAR, dataBuku.getGambar());
        cv.put(KEY_SINOPSIS_BUKU, dataBuku.getSinopsis_buku());
        cv.put(KEY_LINK, dataBuku.getLink());

        db.update(TABLE_BUKU, cv, KEY_ID_BUKU + "=?", new String[]{String.valueOf(dataBuku.getIdBuku())});
        db.close();
    }

    public void hapusBuku(int idBuku){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_BUKU, KEY_ID_BUKU + "=?", new String[]{String.valueOf(idBuku)});
        db.close();
    }

    public ArrayList<Buku> getAllBuku(){
        ArrayList<Buku> dataBuku = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_BUKU;
        SQLiteDatabase db = getReadableDatabase();
        Cursor csr = db.rawQuery(query, null);
        if (csr.moveToFirst()){
            do {
                Date tempDate = new Date();
                try {
                    tempDate = sdFormat.parse(csr.getString(2));
                } catch (ParseException er){
                    er.printStackTrace();
                }

                Buku tempBuku = new Buku(
                        csr.getInt(0),
                        csr.getString(1),
                        csr.getString(2),
                        tempDate,
                        csr.getString(4),
                        csr.getString(5),
                        csr.getString(6)
                );

                dataBuku.add(tempBuku);
            } while (csr.moveToNext());
        }

        return dataBuku;
    }

    private String storeImageFile(int id) {
        String location;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), id);
        location = InputActivity.saveImageToInternalStorage(image, context);
        return location;
    }

    private void inisialisasiBukuAwal(SQLiteDatabase db) {
        int idBuku = 0;
        Date tempDate = new Date();

        try {
            tempDate = sdFormat.parse("2014");
        } catch (ParseException er) {
            er.printStackTrace();
        }


        Buku buku1 = new Buku(
                idBuku,
                "Skripsick : Derita Mahasiswa Abadi.",
                "Chara Pradana.",
                tempDate,
                "skripsick",
                "Sinopsis : Gue udah mulai galau, selain karena gue jadi jomblo akut, gue juga terdesak buat cepet-cepet lulus. Alasan gue lulus cukup berlogika. 95 persen penghuni di kampus gue cowok, 2,5 persen cowok ngondek, 2 persen waria, nah  yang 0,5 persen cewek jadi jadian (sepertinya operasi kelamin mereka terkendala dana). Statistika itu jelas membuat gue nggak betah di kampus.\n" +
                        "Gue telat! Loe hamil Char?\n" +
                        "Bukan! Bukan itu! Gue telat masuk sekolah! Gue telat masuk TK!!  Bener-bener ngebohay banget! *hening\n" +
                        " Ok, alasan-alasan itu cukup membuat gue untuk bertindak nekat!! Gue harus lulus cepet sebelum gue berubah orientasi seksual atau berpikir buat operasi kelamin. Tapi ternyata, hidup tidak semudah itu.\n" +
                        "Cara kerja Tuhan memang aneh, satu-satunya cara kita memahaminya hanyalah dengan bersyukur….\n",
                "https://www.belbuk.com/skripsick-derita-mahasiswa-abadi-p-49131.html"
        );

        tambahBuku(buku1, db);
        idBuku++;

        try {
            tempDate = sdFormat.parse("2014");
        } catch (ParseException er){
            er.printStackTrace();
        }

        Buku buku2 = new Buku(
                idBuku,
                "Ngenest.",
                "Ernest Prakasa.",
                tempDate,
                "ngenest",
                "“Ngenest: Ngetawain Hidup Ala Ernest” bercerita tentang serba-serbi hidup Ernest Prakasa, dibagi kepada dua puluh tiga cerita pendek di dalamnya. Ringan. Bikin ketagihan tapi tidak terasa kosong. Yang pasti isinya humor berat. Dari mulai menyinggung ras, politik, lantas beranjak ke sosial. Dari yang sehari-hari sampai yang tidak pernah diperhatikan, karena sepele, dibahas oleh Ernest Prakasa dengan gaya humor kritisnya.",
                "https://janebookienary.wordpress.com/2014/06/24/ngenest-ngetawain-hidup-ala-ernest-ernest-prakasa/"
        );

        tambahBuku(buku2, db);
        idBuku++;

        try {
            tempDate = sdFormat.parse("2018");
        } catch (ParseException er){
            er.printStackTrace();
        }

        Buku buku3 = new Buku(
                idBuku,
                "Ubur_ubur Lembur.",
                "Raditya Dika.",
                tempDate,
                "ubur_ubur_lembur",
                "Buku ini menceritakan pengalaman Raditya Dika hidup dari apa yang dicintainya. Pada prakata buku tersebut, Radit menceritakan bahwa dari semua pekerjaan yang Ia jalani, menulis adalah pekerjaan yang paling Ia senangi. Tetapi, seiring dengan kesibukannya di dunia hiburan, menulis menjadi sebuah kemewahan tersendiri bagi Radit. Sampai suatu ketika Ia bertemu seorang komedian dari Australia di acara Ubud Writers and Readers Festival.Obrolan pada pertemuan tersebut cukup membekas bagi Raditya Dika saat Ia bertanya kepada komedian tersebut; mengapa Ia masih tetap menjadi komedian. Sang komedian menjawab kalau Ia tetap melakukannya karena ada hal penting yang ingin Ia sampaikan. Radit pun ingin bisa kembali menulis karena Ia sadar ada sesuatu yang penting dan ingin Ia sampaikan. Seperti pada buku-bukunya yang lain, Raditya Dika selalu menyelipkan persoalan remeh-temeh yang menarik untuk ditertawakan. ",
                "https://id.wikipedia.org/wiki/Ubur-Ubur_Lembur"
        );

        tambahBuku(buku3, db);
        idBuku++;

        try {
            tempDate = sdFormat.parse("2010");
        } catch (ParseException er){
            er.printStackTrace();
        }

        Buku buku4 = new Buku(
                idBuku,
                "Marmut Merah Jambu.",
                "Raditya Dika.",
                tempDate,
                "marmut_merah_jambu",
                "Suatu hari Dika bertemu dengan Bapak dari Ina Mangunkusumo, cinta pertamanya di SMA. Kepada Bapak Ina, Dika menceritakan tentang usahanya di SMA membuat grup detektif untuk menarik perhatian Ina, bersama Bertus, temannya yang sama-sama anak terbuang di sekolah. Dika juga bercerita tentang persahabatannya dengan cewek unik bernama Cindy di SMA.\n" +
                        "Lalu, seiring dengan Dika bercerita, seiring itu pula dia sadar: ada kasus di masa lalunya yang belum selesai hingga dia dewasa. Seiring dia berusaha memecahkannya, seiring itu pula dia bertanya, benarkah cinta pertama enggak kemana-mana?\n",
                "https://id.wikipedia.org/wiki/Marmut_Merah_Jambu"
        );

        tambahBuku(buku4, db);
    }
}
