package md18202.nhom2.duan1application.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.SanPham;

public class SanPhamDAO {
    DBHelper dbHelper;

    public SanPhamDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<SanPham> getDsSanPham(){
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new SanPham(
                        cursor.getInt(0), //sanPham_id
                        cursor.getInt(1), //loaiSanPham_id
                        cursor.getString(2), //tenSanPham
                        cursor.getString(3), //anhSanPham
                        cursor.getInt(4),    //giaSanPham
                        cursor.getString(5), //moTa
                        cursor.getInt(6)     //soLuongConLai
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }



    public ArrayList<SanPham> getDsSanPhamADM() {
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();

        // Include the category name (tenLoai) in the SQL query
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sp.sanPham_id, lp.tenLoai, sp.tenSanPham, sp.anhSanPham, sp.giaSanPham, sp.moTa, sp.soLuongConLai FROM SANPHAM sp INNER JOIN LOAISANPHAM lp ON sp.loaiSanPham_id = lp.loaiSanPham_id", null);

        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new SanPham(
                        cursor.getInt(0),          // sanPham_id
                        cursor.getString(1),       // tenLoaiSanPham (category name)
                        cursor.getString(2),       // tenSanPham
                        cursor.getString(3),       // anhSanPham
                        cursor.getInt(4),          // giaSanPham
                        cursor.getString(5),       // moTa
                        cursor.getInt(6)           // soLuongConLai
                ));
            } while (cursor.moveToNext());
        }

        cursor.close(); // Close the cursor after use
        return listResult;
    }


    public ArrayList<SanPham> getDsVoCoThap(){
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=1", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new SanPham(
                        cursor.getInt(0), //sanPham_id
                        cursor.getInt(1), //loaiSanPham_id
                        cursor.getString(2), //tenSanPham
                        cursor.getString(3), //anhSanPham
                        cursor.getInt(4),    //giaSanPham
                        cursor.getString(5), //moTa
                        cursor.getInt(6)     //soLuongConLai
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
    public ArrayList<SanPham> getDSVoCoCao(){
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=2", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new SanPham(
                        cursor.getInt(0), //sanPham_id
                        cursor.getInt(1), //loaiSanPham_id
                        cursor.getString(2), //tenSanPham
                        cursor.getString(3), //anhSanPham
                        cursor.getInt(4),    //giaSanPham
                        cursor.getString(5), //moTa
                        cursor.getInt(6)     //soLuongConLai
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
    public ArrayList<SanPham> getDsVoCoTrung(){
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=3", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new SanPham(
                        cursor.getInt(0), //sanPham_id
                        cursor.getInt(1), //loaiSanPham_id=
                        cursor.getString(2), //tenSanPham
                        cursor.getString(3), //anhSanPham
                        cursor.getInt(4),    //giaSanPham
                        cursor.getString(5), //moTa
                        cursor.getInt(6)     //soLuongConLai
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
    public ArrayList<SanPham> getDsVoLuoi(){
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=4", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new SanPham(
                        cursor.getInt(0), //sanPham_id
                        cursor.getInt(1), //loaiSanPham_id=
                        cursor.getString(2), //tenSanPham
                        cursor.getString(3), //anhSanPham
                        cursor.getInt(4),    //giaSanPham
                        cursor.getString(5), //moTa
                        cursor.getInt(6)     //soLuongConLai
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
    public ArrayList<SanPham> getDsVoBasic(){
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=5", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new SanPham(
                        cursor.getInt(0), //sanPham_id
                        cursor.getInt(1), //loaiSanPham_id=
                        cursor.getString(2), //tenSanPham
                        cursor.getString(3), //anhSanPham
                        cursor.getInt(4),    //giaSanPham
                        cursor.getString(5), //moTa
                        cursor.getInt(6)     //soLuongConLai
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }
    public ArrayList<SanPham> getDsVoHoaTiet(){
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=6", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listResult.add(new SanPham(
                        cursor.getInt(0), //sanPham_id
                        cursor.getInt(1), //loaiSanPham_id=
                        cursor.getString(2), //tenSanPham
                        cursor.getString(3), //anhSanPham
                        cursor.getInt(4),    //giaSanPham
                        cursor.getString(5), //moTa
                        cursor.getInt(6)     //soLuongConLai
                ));
            }while (cursor.moveToNext());
        }
        return listResult;
    }

    public ArrayList<SanPham> getDsSanPhamYeuThich() {
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM where isYeuThich = 1", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                listResult.add(new SanPham(
                        cursor.getInt(0), //sanPham_id
                        cursor.getInt(1), //loaiSanPham_id=
                        cursor.getString(2), //tenSanPham
                        cursor.getString(3), //anhSanPham
                        cursor.getInt(4),    //giaSanPham
                        cursor.getString(5), //moTa
                        cursor.getInt(6),     //soLuongConLai
                        cursor.getInt(7)     //isYeuThich
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }


    public long SuaSanPham(SanPham sanPham){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenSanPham", sanPham.getTenSanPham());
        contentValues.put("giaSanPham", sanPham.getGiaSanPham());
        contentValues.put("moTa", sanPham.getMoTa());
        contentValues.put("soLuongConLai", sanPham.getSoLuongConLai());
        return db.update("SANPHAM", contentValues,"sanPham_id=?", new String[]{String.valueOf(sanPham.getLoaiSanPham_id())});

    }

    public boolean changeIsYeuThich(int sanPham_id, int newIsYeuThich){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("sanPham_id", sanPham_id);
        contentValues.put("isYeuThich", newIsYeuThich);
        long check = sqLiteDatabase.update("SANPHAM", contentValues,"sanPham_id = ?", new String[]{String.valueOf(sanPham_id)});
        return check > 0;
    }

}
