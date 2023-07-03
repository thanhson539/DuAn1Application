package md18202.nhom2.duan1application.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import md18202.nhom2.duan1application.DBHelper;
import md18202.nhom2.duan1application.Model.HoaDon;

public class HoaDonDAO {
    DBHelper dbHelper;

    public HoaDonDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<HoaDon> getDsHoaDon(){
        ArrayList<HoaDon> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from HOADON", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new HoaDon(
                        cursor.getString(1), //hoaDon_id
                        cursor.getString(2), //nguoiDung_id
                        cursor.getString(3), //ngayMua
                        cursor.getInt(4)     //tongTien
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
}
