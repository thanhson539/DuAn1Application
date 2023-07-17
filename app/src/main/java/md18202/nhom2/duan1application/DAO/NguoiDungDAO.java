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
    public NguoiDungDAO(Context context){
        dbHelper = new DBHelper(context);
        sharedPreferences = context.getSharedPreferences("NGUOIDUNG",Context.MODE_PRIVATE);
    }

    public ArrayList<NguoiDung> getDsNguoiDung(){
        ArrayList<NguoiDung> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from NGUOIDUNG", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new NguoiDung(
                        cursor.getInt(0), //nguoiDung_id
                        cursor.getString(1), //hoTen
                        cursor.getString(2), //soDienThoai
                        cursor.getString(3), //email
                        cursor.getString(4), //taiKhoan
                        cursor.getString(5), //matKhau
                        cursor.getInt(6)     //loaiTaiKhoan
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
    public boolean themTaiKhoan(String name, String phoneNumber, String email, String username, String password, int typeAccount){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hoTen", name);
        contentValues.put("soDienThoai", phoneNumber);
        contentValues.put("email", email);
        contentValues.put("taiKhoan", username);
        contentValues.put("matKhau", password);
        contentValues.put("loaiTaiKhoan", typeAccount);
        long check = sqLiteDatabase.insert("NGUOIDUNG", null, contentValues);
        return check > 0;
    }

    public boolean kiemTraDangNhap(String taikhoan, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from NGUOIDUNG where taikhoan = ? and matkhau = ?", new String[]{taikhoan, matkhau});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            //Lưu Thông tin
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("hoTen",cursor.getString(1));
            editor.putString("sdt",cursor.getString(2));
            editor.putString("email",cursor.getString(3));
            editor.putString("taikhoan",cursor.getString(4));
            editor.putString("matkhau",cursor.getString(5));
            editor.putInt("loaitaikhoan",cursor.getInt(6));
            editor.commit();
            return true;
        }
        return false;
    }

    public boolean doiMatKhau(String taikhoan, String matkhaucu, String matkhaumoi){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from NGUOIDUNG where taikhoan = ? and matkhau = ?",new String[]{taikhoan,matkhaucu});
        if (cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau",matkhaumoi);
            long check = sqLiteDatabase.update("NGUOIDUNG",contentValues,"taikhoan = ?", new String[]{taikhoan});
            if(check == -1)
                return false;

            return true;
        }
        return false;
    }

    public boolean doiThongTinNguoiDung(String taikhoan, String hoTen, String email, String Sdt){
      SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
      Cursor cursor = sqLiteDatabase.rawQuery("select * from NGUOIDUNG where taikhoan = ?", new String[]{taikhoan});
      if (cursor.getCount() > 0){
          ContentValues contentValues = new ContentValues();
          contentValues.put("hoTen",hoTen);
          contentValues.put("email",email);
          contentValues.put("soDienThoai",Sdt);
          long check = sqLiteDatabase.update("NGUOIDUNG",contentValues,"taikhoan = ?",new String[]{taikhoan});
          if (check == -1)
              return false;
          return true;
      }
      return false;
    };
}
