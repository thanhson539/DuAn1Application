package md18202.nhom2.duan1application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.HoaDonChiTiet;

public class HoaDonChiTietDAO {
    DBHelper dbHelper;
    public HoaDonChiTietDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<HoaDonChiTiet> getDsHoaDonChiTiet(){
        ArrayList<HoaDonChiTiet> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select hdct.hoaDon_id as hoaDon_id, hdct.sanPham_id as sanPham_id," +
                "hdct.trangThaiThanhToan , hdct.trangThaiDonHang  from HOADONCHITIET hdct " +
                "inner join HOADON hd on hdct.hoaDon_id = hd.hoaDon_id", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new HoaDonChiTiet(
                        cursor.getInt(0), //hoaDonChiTiet_id
                        cursor.getInt(1), //hoaDon_id
                        cursor.getInt(2), //sanPham_id
                        cursor.getInt(3), //trangThaiDonHang
                        cursor.getInt(4)  //trangThaiThanhToan
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }

    public long themHoaDonChiTiet(HoaDonChiTiet hdct){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoaDon_id", hdct.getHoaDon_id());
        values.put("sanPham_id", hdct.getSanPham_id());
        values.put("soLuong", hdct.getSoLuong());
        values.put("trangThaiDonHang", hdct.getTrangThaiThanhToan());
        values.put("trangThaiThanhToan", hdct.getTrangThaiDonHang());

        return sqLiteDatabase.insert("HOADONCHITIET", null, values);

    }
}
