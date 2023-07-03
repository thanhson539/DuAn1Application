package md18202.nhom2.duan1application.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.DBHelper;
import md18202.nhom2.duan1application.Model.SanPham;

public class SanPhamDAO {
    DBHelper dbHelper;

    public SanPhamDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<SanPham> getDsSanPham(){
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new SanPham(
                        cursor.getString(1), //sanPham_id
                        cursor.getString(2), //loaiSanPham_id
                        cursor.getString(3), //tenSanPham
                        cursor.getString(4), //anhSanPham
                        cursor.getInt(5),    //giaSanPham
                        cursor.getString(6), //moTa
                        cursor.getInt(7)     //soLuongConLai
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
}
