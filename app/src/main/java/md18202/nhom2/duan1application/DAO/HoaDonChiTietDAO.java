package md18202.nhom2.duan1application.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.DBHelper;
import md18202.nhom2.duan1application.Model.HoaDonChiTiet;

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
                        cursor.getString(1), //hoaDonChiTiet_id
                        cursor.getString(2), //hoaDon_id
                        cursor.getString(3), //sanPham_id
                        cursor.getInt(4),    //trangThaiDonHang
                        cursor.getInt(5)     //trangThaiThanhToan
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
}
