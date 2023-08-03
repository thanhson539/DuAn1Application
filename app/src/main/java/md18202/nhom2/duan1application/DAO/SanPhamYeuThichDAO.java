package md18202.nhom2.duan1application.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.SanPham;
import md18202.nhom2.duan1application.Models.SanPhamYeuThich;

public class SanPhamYeuThichDAO {
    DBHelper dbHelper;
    Context context;
    SQLiteDatabase sqLiteDatabase;

    public SanPhamYeuThichDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public ArrayList<SanPham> getSanPhamYeuThich(int nguoiDung_id){
        ArrayList<SanPham> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select nd.nguoiDung_id as nguoiDung_id, sp.sanPham_id as sanPham_id, sp.loaiSanPham_id as loaiSanPham_id, " +
                "sp.tenSanPham as tenSanPham, sp.anhSanPham as anhSanPham, sp.giaSanPham as giaSanPham, sp.moTa as moTa, sp.soLuongConLai as soLuongConLai " +
                "from SANPHAMYEUTHICH spyt " +
                "inner join SANPHAM sp on sp.sanPham_id = spyt.sanPham_id " +
                "inner join NGUOIDUNG nd on spyt.nguoiDung_id = nd.nguoiDung_id " +
                "where spyt.nguoiDung_id = ? ", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(
                        cursor.getInt(cursor.getColumnIndex("sanPham_id")), //sanPham_id
                        cursor.getInt(cursor.getColumnIndex("loaiSanPham_id")), //loaiSanPham_id
                        cursor.getString(cursor.getColumnIndex("tenSanPham")), //tenSanPham
                        cursor.getString(cursor.getColumnIndex("anhSanPham")), //anhSanPham
                        cursor.getInt(cursor.getColumnIndex("giaSanPham")),    //giaSanPham
                        cursor.getString(cursor.getColumnIndex("giaSanPham")), //moTa
                        cursor.getInt(cursor.getColumnIndex("soLuongConLai"))//soLuongConLai
                ));
            }while (cursor.moveToNext());
        }

        return list;
    }

    public long boYeuThichSanPham(int sanPham_id, int nguoiDung_id){
        return sqLiteDatabase.delete("SANPHAMYEUTHICH", "sanPham_id =? and nguoiDung_id = ?", new String[]{String.valueOf(sanPham_id), String.valueOf(nguoiDung_id)});
    }

    public long yeuThichSanPham(SanPhamYeuThich sp){
        ContentValues values = new ContentValues();
        values.put("sanPham_id", sp.getSanPham_id());
        values.put("nguoiDung_id", sp.getNguoiDung_id());

        return sqLiteDatabase.insert("SANPHAMYEUTHICH", null, values);
    }
}
