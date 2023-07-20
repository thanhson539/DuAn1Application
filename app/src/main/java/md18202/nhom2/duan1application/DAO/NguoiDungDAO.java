package md18202.nhom2.duan1application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.NguoiDung;

public class NguoiDungDAO {
    DBHelper dbHelper;
    SharedPreferences sharedPreferences;

    public NguoiDungDAO(Context context) {
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("NGUOIDUNG", Context.MODE_PRIVATE);
    }

    public ArrayList<NguoiDung> getDsNguoiDung() {
        ArrayList<NguoiDung> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from NGUOIDUNG", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new NguoiDung(
                        cursor.getInt(0),    //nguoiDung_id
                        cursor.getString(1), //imgSrc
                        cursor.getString(2), //hoTen
                        cursor.getString(3), //soDienThoai
                        cursor.getString(4), //email
                        cursor.getString(5), //taiKhoan
                        cursor.getString(6), //matKhau
                        cursor.getInt(7),    //loaiTaiKhoan
                        cursor.getInt(8)     //isXoaMem
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    public boolean themTaiKhoan(NguoiDung nguoiDung) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("imgSrc", nguoiDung.getImgSrc());
        contentValues.put("hoTen", nguoiDung.getHoTen());
        contentValues.put("soDienThoai", nguoiDung.getSoDienThoai());
        contentValues.put("email", nguoiDung.getEmail());
        contentValues.put("taiKhoan", nguoiDung.getTaiKhoan());
        contentValues.put("matKhau", nguoiDung.getMatKhau());
        contentValues.put("loaiTaiKhoan", nguoiDung.getLoaiTaiKhoan());
        contentValues.put("isXoaMem", nguoiDung.getIsXoaMem());
        long check = sqLiteDatabase.insert("NGUOIDUNG", null, contentValues);
        return check > 0;
    }

    public int kiemTraDangNhap(String taikhoan, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from NGUOIDUNG where taikhoan = ? and matkhau = ?", new String[]{taikhoan, matkhau});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            //Lưu Thông tin
            if (cursor.getInt(8) == 0) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("nguoiDung_id", cursor.getInt(0));
                editor.putString("imgSrc", cursor.getString(1));
                editor.putString("hoTen", cursor.getString(2));
                editor.putString("soDienThoai", cursor.getString(3));
                editor.putString("email", cursor.getString(4));
                editor.putString("taiKhoan", cursor.getString(5));
                editor.putString("matKhau", cursor.getString(6));
                editor.putInt("loaiTaiKhoan", cursor.getInt(7));
                editor.putInt("isXoaMem", cursor.getInt(8));
                editor.commit();
                return 1; //Dang nhap thanh cong
            }else {
                return -1; //Tai khoan da xoa mem
            }
        }
        return 0; //Tai khoan hoac mat khau khong chinh xac
    }

    public boolean doiMatKhau(int nguoiDung_id, String newPassword){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("matKhau", newPassword);
        long check =  sqLiteDatabase.update("NGUOIDUNG", contentValues, "nguoiDung_id = ?", new String[]{String.valueOf(nguoiDung_id)});
        return check > 0;
    }

    public boolean thayDoiThongTin(int nguoiDung_id, String imgSrc, String hoTen, String soDienThoai, String email){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("imgSrc", imgSrc);
        contentValues.put("hoTen", hoTen);
        contentValues.put("soDienThoai", soDienThoai);
        contentValues.put("email", email);
        long check = sqLiteDatabase.update("NGUOIDUNG", contentValues, "nguoiDung_id = ?", new String[]{String.valueOf(nguoiDung_id)});
        return check > 0;
    }
    public boolean actionIsXoaMem(int nguoiDung_id, int newIsXoaMem){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isXoaMem", newIsXoaMem);
        long check = sqLiteDatabase.update("NGUOIDUNG", contentValues, "nguoiDung_id = ?", new String[]{String.valueOf(nguoiDung_id)});
        return check > 0;
    }

    public boolean checkTaiKhoanTonTai(String username){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from NGUOIDUNG where taiKhoan = ?", new String[]{String.valueOf(username)});
        if (cursor.getCount()!=0){
            return true; //Tài khoản đã tồn tại
        }
        return false; //Tài khoản chưa tồn tại
    }
}
