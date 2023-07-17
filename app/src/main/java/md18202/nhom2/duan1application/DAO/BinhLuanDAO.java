package md18202.nhom2.duan1application.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.BinhLuan;

public class BinhLuanDAO {
    DBHelper dbHelper;

    public BinhLuanDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    @SuppressLint("Range")
    public ArrayList<BinhLuan> getDsBinhLuan(int sanPham_id){
        ArrayList<BinhLuan> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select BINHLUAN.binhLuan_id, BINHLUAN.nguoiDung_id, BINHLUAN.sanPham_id, BINHLUAN.noiDung, BINHLUAN.thoiGian, NGUOIDUNG.hoTen as tenNguoiDung from BINHLUAN inner join NGUOIDUNG on BINHLUAN.nguoiDung_id = NGUOIDUNG.nguoiDung_id where BINHLUAN.sanPham_id = ?", new String[]{String.valueOf(sanPham_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new BinhLuan(
                        cursor.getInt(cursor.getColumnIndex("binhLuan_id")),    //binhLuan_id
                        cursor.getInt(cursor.getColumnIndex("nguoiDung_id")),    //nguoiDung_id
                        cursor.getInt(cursor.getColumnIndex("sanPham_id")),    //sanPham_id
                        cursor.getString(cursor.getColumnIndex("noiDung")), //noiDung
                        cursor.getString(cursor.getColumnIndex("thoiGian")), //thoiGian
                        cursor.getString(cursor.getColumnIndex("tenNguoiDung")) //ten nguoi dung
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }

    public long themBinhLuan(BinhLuan binhLuan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("nguoiDung_id", binhLuan.getNguoiDung_id());
        values.put("sanPham_id", binhLuan.getSanPham_id());
        values.put("noiDung", binhLuan.getNoiDung());
        values.put("thoiGian", binhLuan.getThoiGian());

        return sqLiteDatabase.insert("BINHLUAN", null, values);
    }

    public long upDateBinhLuan(BinhLuan binhLuan){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("noiDung", binhLuan.getNoiDung());
        values.put("thoiGian", binhLuan.getThoiGian());

        return sqLiteDatabase.update("BINHLUAN", values, "binhLuan_id = ?", new String[]{String.valueOf(binhLuan.getBinhLuan_id())});
    }

    public long deleteBinhLuan(String binhLuan_id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        return sqLiteDatabase.delete("BINHLUAN", "binhLuan_id = ?", new String[]{binhLuan_id});
    }
}
