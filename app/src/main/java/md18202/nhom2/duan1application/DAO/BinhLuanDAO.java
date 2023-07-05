package md18202.nhom2.duan1application.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.DBHelper;
import md18202.nhom2.duan1application.Model.BinhLuan;

public class BinhLuanDAO {
    DBHelper dbHelper;

    public BinhLuanDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<BinhLuan> getDsBinhLuan(){
        ArrayList<BinhLuan> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from BINHLUAN", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new BinhLuan(
                        cursor.getInt(0),    //binhLuan_id
                        cursor.getInt(1),    //nguoiDung_id
                        cursor.getString(2), //noiDung
                        cursor.getString(3)  //thoiGian
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
}
