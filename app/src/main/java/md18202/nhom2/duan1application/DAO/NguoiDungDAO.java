package md18202.nhom2.duan1application.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.DBHelper;
import md18202.nhom2.duan1application.Model.NguoiDung;

public class NguoiDungDAO {
    DBHelper dbHelper;
    public NguoiDungDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<NguoiDung> getDsNguoiDung(){
        ArrayList<NguoiDung> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from NGUOIDUNG", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new NguoiDung(
                        cursor.getString(1), //nguoiDung_id
                        cursor.getString(2), //hoTen
                        cursor.getString(3), //soDienThoai
                        cursor.getString(4), //email
                        cursor.getString(5), //taiKhoan
                        cursor.getString(6), //matKhau
                        cursor.getInt(7)     //loaiTaiKhoan
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
}
