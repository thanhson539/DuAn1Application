package md18202.nhom2.duan1application.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.BinhLuan;

public class BinhLuanDAO {
    DBHelper dbHelper;

    public BinhLuanDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    @SuppressLint("Range")
    public ArrayList<BinhLuan> getDsBinhLuan(int sanPham_id) {
        ArrayList<BinhLuan> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select BINHLUAN.binhLuan_id, BINHLUAN.nguoiDung_id, BINHLUAN.sanPham_id, BINHLUAN.noiDung, BINHLUAN.thoiGian, NGUOIDUNG.hoTen as tenNguoiDung from BINHLUAN inner join NGUOIDUNG on BINHLUAN.nguoiDung_id = NGUOIDUNG.nguoiDung_id where BINHLUAN.sanPham_id = ?", new String[]{String.valueOf(sanPham_id)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new BinhLuan(
                        cursor.getInt(cursor.getColumnIndex("binhLuan_id")),    //binhLuan_id
                        cursor.getInt(cursor.getColumnIndex("nguoiDung_id")),    //nguoiDung_id
                        cursor.getInt(cursor.getColumnIndex("sanPham_id")),    //sanPham_id
                        cursor.getString(cursor.getColumnIndex("noiDung")), //noiDung
                        cursor.getString(cursor.getColumnIndex("thoiGian")), //thoiGian
                        cursor.getString(cursor.getColumnIndex("tenNguoiDung")) //ten nguoi dung
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    public long themBinhLuan(BinhLuan binhLuan) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("nguoiDung_id", binhLuan.getNguoiDung_id());
        values.put("sanPham_id", binhLuan.getSanPham_id());
        values.put("noiDung", binhLuan.getNoiDung());
        values.put("thoiGian", binhLuan.getThoiGian());

        return sqLiteDatabase.insert("BINHLUAN", null, values);
    }

    public long upDateBinhLuan(BinhLuan binhLuan) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("noiDung", binhLuan.getNoiDung());
        values.put("thoiGian", binhLuan.getThoiGian());

        return sqLiteDatabase.update("BINHLUAN", values, "binhLuan_id = ?", new String[]{String.valueOf(binhLuan.getBinhLuan_id())});
    }

    public long deleteBinhLuan(String binhLuan_id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        return sqLiteDatabase.delete("BINHLUAN", "binhLuan_id = ?", new String[]{binhLuan_id});
    }

    //Lấy toàn bộ danh sách bình luận theo binhLuan_id
    public ArrayList<BinhLuan> getDSBinhLuanCach1() {
        ArrayList<BinhLuan> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("" +
                "select bl.binhLuan_id, nd.nguoiDung_id, sp.sanPham_id, bl.noiDung, bl.thoiGian, sp.anhSanPham, sp.tenSanPham, nd.hoTen " +
                "from BINHLUAN bl " +
                "inner join NGUOIDUNG nd on bl.nguoiDung_id = nd.nguoiDung_id " +
                "inner join SANPHAM sp on bl.sanPham_id = sp.sanPham_id", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new BinhLuan(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    //Lấy tổng số bình luận theo sanPham_id
    public ArrayList<BinhLuan> getDSBinhLuanCach2() {
        ArrayList<BinhLuan> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("" +
                "select bl.binhLuan_id, nd.nguoiDung_id, sp.sanPham_id, sp.anhSanPham, sp.tenSanPham, count(sp.sanPham_id) as tongBinhLuan " +
                "from BINHLUAN bl " +
                "inner join NGUOIDUNG nd on bl.nguoiDung_id = nd.nguoiDung_id " +
                "inner join SANPHAM sp on bl.sanPham_id = sp.sanPham_id " +
                "group by sp.sanPham_id", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new BinhLuan(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    //Lấy danh sách bình luận
    public ArrayList<BinhLuan> getDsBinhLuanTheoSanPham_id(int sanPham_id) {
        ArrayList<BinhLuan> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("" +
                "select bl.binhLuan_id, nd.nguoiDung_id, sp.sanPham_id, bl.noiDung, bl.thoiGian, sp.anhSanPham, sp.tenSanPham, nd.hoTen " +
                "from BINHLUAN bl " +
                "inner join NGUOIDUNG nd on bl.nguoiDung_id = nd.nguoiDung_id " +
                "inner join SANPHAM sp on bl.sanPham_id = sp.sanPham_id where sp.sanPham_id = ?", new String[]{String.valueOf(sanPham_id)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new BinhLuan(
                        cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    //Xoa binh luan theo binhLuan_id
    public boolean xoaBinhLuanTheoBinhLuan_id(int binhLuan_id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("BINHLUAN", "binhLuan_id = ?", new String[]{String.valueOf(binhLuan_id)});
        return check > 0;
    }




}
