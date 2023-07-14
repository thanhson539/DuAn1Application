package md18202.nhom2.duan1application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.LoaiSanPham;
import md18202.nhom2.duan1application.Models.SanPham;

public class LoaiSanPhamDAO {

    DBHelper dbHelper;

    public LoaiSanPhamDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<LoaiSanPham> getDsLoaiSanPham(){
        ArrayList<LoaiSanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from LOAISANPHAM", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new LoaiSanPham(
                        cursor.getInt(0),    //loaiSanPham_id
                        cursor.getString(1)  //tenLoai
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }

    public long SuaLoaiSP(LoaiSanPham loaiSanPham){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values  =new ContentValues();
        values.put("tenLoai", loaiSanPham.getTenLoai());
        return  db.update("LOAISANPHAM", values, "loaiSanPham_id=?", new String[]{String.valueOf(loaiSanPham.getLoaiSanPham_id())});

    }
}
