package md18202.nhom2.duan1application.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.HoaDon;
import md18202.nhom2.duan1application.Models.HoaDonChiTiet;

public class HoaDonDAO {
    DBHelper dbHelper;

    public HoaDonDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long themHoaDon(HoaDon hoaDon){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nguoiDung_id", hoaDon.getNguoiDung_id());
        values.put("ngayMua", hoaDon.getThoiGian());
        values.put("tongTien", hoaDon.getTongTien());
        values.put("diaChi", hoaDon.getDiaChi());

        return sqLiteDatabase.insert("HOADON", null, values);
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

    @SuppressLint("Range")
    public ArrayList<HoaDon> getMonth(int nguoiDung_id){
        ArrayList<HoaDon> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select strftime('%m', ngayMua) as month from HOADON where nguoiDung_id = ?", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new HoaDon(
                        cursor.getString(cursor.getColumnIndex("month"))
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }

    public HoaDon getHoaDonCuoiCung(int nguoiDung_id){
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from HOADON where nguoiDung_id = ?", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(
                        cursor.getInt(0),    //hoaDon_id
                        cursor.getInt(1),    //nguoiDung_id
                        cursor.getString(2), //ngayMua
                        cursor.getInt(3),     //tongTien
                        cursor.getString(4)    //diaChi
                ));
            }while (cursor.moveToNext());
        }
        Collections.reverse(list);
        return list.get(0);
    }

    public ArrayList<HoaDon> getDsHoaDon(int nguoiDung_id){
        ArrayList<HoaDon> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from HOADON where nguoiDung_id = ?", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDon(
                        cursor.getInt(0),    //hoaDon_id
                        cursor.getInt(1),    //nguoiDung_id
                        cursor.getString(2), //ngayMua
                        cursor.getInt(3),     //tongTien
                        cursor.getString(4)    //diaChi
                ));
            }while (cursor.moveToNext());
        }
        Collections.reverse(list);
        return list;
    }
    @SuppressLint("Range")
    public ArrayList<HoaDonChiTiet> getDsHoaDonChiTiet(int nguoiDung_id){
        ArrayList<HoaDonChiTiet> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select hdct.hoaDon_id as hoaDon_id, hdct.sanPham_id as sanPham_id, hdct.trangThaiDonHang as trangThaiDonHang, hdct.trangThaiThanhToan as trangThaiThanhToan, hd.nguoiDung_id as nguoiDung_id" +
                " from HOADONCHITIET hdct " +
                "inner join HOADON hd on hdct.hoaDon_id = hd.hoaDon_id" +
                " where nguoiDung_id =?", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDonChiTiet(
                        cursor.getInt(cursor.getColumnIndex("hoaDon_id")),    //hoaDon_id
                        cursor.getInt(cursor.getColumnIndex("sanPham_id")),    //nguoiDung_id
                        cursor.getInt(cursor.getColumnIndex("trangThaiDonHang")), //ngayMua
                        cursor.getInt(cursor.getColumnIndex("trangThaiThanhToan")),     //tongTien
                        cursor.getInt(cursor.getColumnIndex("nguoiDung_id"))    //diaChi
                ));
            }while (cursor.moveToNext());
        }
        Collections.reverse(list);
        return list;
    }
}
