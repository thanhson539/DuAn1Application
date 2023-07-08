package md18202.nhom2.duan1application.DAO;

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
        Cursor cursor = sqLiteDatabase.rawQuery("select * from HOADONCHITIET", null);
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
}
