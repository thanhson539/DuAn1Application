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
        contentValues.put("sanPham_id", thongBao.getSanPham_id());
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
        Cursor cursor = sqLiteDatabase.rawQuery("" +
                        "select tb.thongBao_id, tb.nguoiDung_id, tb.sanPham_id, tb.tieuDe, tb.noiDung, tb.thoiGian, tb.loaiThongBao, tb.isRead," +
                        "sp.anhSanPham from THONGBAO tb inner join SANPHAM sp on tb.sanPham_id = sp.sanPham_id " +
                        "where nguoiDung_id = ?",
                new String[]{String.valueOf(nguoiDung_id)});
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
                        cursor.getInt(7),   //loaiThongBao
                        cursor.getString(8) //anhSanPham
                ));
            } while (cursor.moveToNext());
        }
        Collections.reverse(listResult);
        return listResult;
    }

    public ArrayList<ThongBao> getAllListThongBao(){
        ArrayList<ThongBao> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from THONGBAO", null);
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

    //Thay thay đổi trang thái isRead
    public boolean thayDoiTrangThaiIsRead(int thongBao_id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isRead", 1);
        long check = sqLiteDatabase.update("THONGBAO", contentValues, "thongBao_id = ?", new String[]{String.valueOf(thongBao_id)});
        return check > 0;
    }
}
