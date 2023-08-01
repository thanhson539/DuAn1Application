package md18202.nhom2.duan1application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.ThongBao;

public class ThongBaoDAO {
    DBHelper dbHelper;

    public ThongBaoDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public boolean themThongBao(ThongBao thongBao) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nguoiDung_id", thongBao.getNguoiDung_id());
        contentValues.put("tieuDe", thongBao.getTieuDe());
        contentValues.put("noiDung", thongBao.getNoiDung());
        contentValues.put("thoiGian", thongBao.getThoiGian());
        contentValues.put("isRead", thongBao.getIsRead());
        contentValues.put("loaiThongBao", thongBao.getLoaiThongBao());
        long check = sqLiteDatabase.insert("THONGBAO", null, contentValues);
        return check > 0;
    }

    public ArrayList<ThongBao> getDsThongBaoByNguoiDung_id(int nguoiDung_id) {
        ArrayList<ThongBao> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from THONGBAO where nguoiDung_id = ?", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new ThongBao(
                        cursor.getInt(0),   //ThongBao_id
                        cursor.getInt(1),   //nguoiDung_id
                        cursor.getInt(2),   //sanPham_id
                        cursor.getString(3),//tieuDe
                        cursor.getString(4),//noiDung
                        cursor.getString(5),//thoiGian
                        cursor.getInt(6),   //isRead
                        cursor.getInt(7)    //loaiThongBao
                ));
            } while (cursor.moveToNext());
        }
        Collections.reverse(listResult);
        return listResult;
    }
}
