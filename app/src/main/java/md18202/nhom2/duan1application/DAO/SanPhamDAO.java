package md18202.nhom2.duan1application.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.SanPham;

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
                        cursor.getInt(0), //sanPham_id
                        cursor.getInt(1), //loaiSanPham_id
                        cursor.getString(2), //tenSanPham
                        cursor.getString(3), //anhSanPham
                        cursor.getInt(4),    //giaSanPham
                        cursor.getString(5), //moTa
                        cursor.getInt(6)     //soLuongConLai
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
}
