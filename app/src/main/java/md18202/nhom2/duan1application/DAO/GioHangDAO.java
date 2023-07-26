package md18202.nhom2.duan1application.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.BinhLuan;
import md18202.nhom2.duan1application.Models.GioHang;

public class GioHangDAO{

    DBHelper dbHelper;

    public GioHangDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long themVaoGioHang(GioHang gioHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nguoiDung_id", gioHang.getNguoiDung_id());
        values.put("sanPham_id", gioHang.getSanPham_id());
        values.put("soLuong", gioHang.getSoLuong());
        values.put("trangThaiMua", gioHang.getSoLuong());

        return sqLiteDatabase.insert("GIOHANG", null, values);
    }

    public long suaSoLuong(GioHang gioHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soLuong", gioHang.getSoLuong());

        return sqLiteDatabase.update("GIOHANG", values, "sanPham_id = ? and nguoiDung_id = ?", new String[]{String.valueOf(gioHang.getSanPham_id()), String.valueOf(gioHang.getNguoiDung_id())});
    }

    public long xoaKhoiGioHang(int sanPham_id, int nguoiDung_id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        return sqLiteDatabase.delete("GIOHANG", "sanPham_id = ? and nguoiDung_id = ?", new String[]{String.valueOf(sanPham_id), String.valueOf(nguoiDung_id)});
    }

    @SuppressLint("Range")
    public ArrayList<GioHang> getDsGioHang(int nguoiDung_id){
        ArrayList<GioHang> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select GIOHANG.nguoiDung_id, GIOHANG.sanPham_id, GIOHANG.soLuong as soLuong, GIOHANG.trangThaiMua as trangThaiMua, SANPHAM.giaSanPham as giaSanPham," +
                " SANPHAM.tenSanPham as tenSanPham, SANPHAM.anhSanPham as anhSanPham from GIOHANG inner join NGUOIDUNG on GIOHANG.nguoiDung_id = NGUOIDUNG.nguoiDung_id" +
                " inner join SANPHAM on GIOHANG.sanPham_id = SANPHAM.sanPham_id where GIOHANG.nguoiDung_id = ?" , new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new GioHang(
                        cursor.getInt(cursor.getColumnIndex("nguoiDung_id")),
                        cursor.getInt(cursor.getColumnIndex("sanPham_id")),
                        cursor.getInt(cursor.getColumnIndex("soLuong")),
                        cursor.getInt(cursor.getColumnIndex("trangThaiMua")),
                        cursor.getInt(cursor.getColumnIndex("giaSanPham")),
                        cursor.getString(cursor.getColumnIndex("tenSanPham")),
                        cursor.getString(cursor.getColumnIndex("anhSanPham"))));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
}
