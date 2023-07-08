package md18202.nhom2.duan1application.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.HoaDon;

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
                        cursor.getInt(0),    //hoaDon_id
                        cursor.getInt(1),    //nguoiDung_id
                        cursor.getString(2), //ngayMua
                        cursor.getInt(3)     //tongTien
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
}
