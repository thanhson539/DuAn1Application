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

    public ArrayList<SanPham> getDsSanPham() {
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM where isXoaMem=0", null);
        if (cursor.getCount() != 0) {
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
            } while (cursor.moveToNext());
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


    public ArrayList<SanPham> getDsVoCoThap() {
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=1", null);
        if (cursor.getCount() != 0) {
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
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    public ArrayList<SanPham> getDSVoCoCao() {
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=2", null);
        if (cursor.getCount() != 0) {
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
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    public ArrayList<SanPham> getDsVoCoTrung() {
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=3", null);
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
                        cursor.getInt(6)     //soLuongConLai
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    public ArrayList<SanPham> getDsVoLuoi() {
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=4", null);
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
                        cursor.getInt(6)     //soLuongConLai
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    public ArrayList<SanPham> getDsVoBasic() {
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=5", null);
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
                        cursor.getInt(6)     //soLuongConLai
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

    public ArrayList<SanPham> getDsVoHoaTiet() {
        ArrayList<SanPham> listResult = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM sp inner join LOAISANPHAM ls on sp.loaiSanPham_id = ls.loaiSanPham_id where ls.loaiSanPham_id=6", null);
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
                        cursor.getInt(6)     //soLuongConLai
                ));
            } while (cursor.moveToNext());
        }
        return listResult;
    }

//    public ArrayList<SanPham> getDsSanPhamYeuThich() {
//        ArrayList<SanPham> listResult = new ArrayList<>();
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM where isYeuThich = 1", null);
//        if (cursor.getCount() != 0) {
//            cursor.moveToFirst();
//            do {
//                listResult.add(new SanPham(
//                        cursor.getInt(0), //sanPham_id
//                        cursor.getInt(1), //loaiSanPham_id=
//                        cursor.getString(2), //tenSanPham
//                        cursor.getString(3), //anhSanPham
//                        cursor.getInt(4),    //giaSanPham
//                        cursor.getString(5), //moTa
//                        cursor.getInt(6),     //soLuongConLai
//                        cursor.getInt(7)     //isYeuThich
//                ));
//            } while (cursor.moveToNext());
//        }
//        return listResult;
//    }

    public long SuaSanPham(SanPham sanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("loaiSanPham_id", sanPham.getLoaiSanPham_id());
        contentValues.put("anhSanPham", sanPham.getAnhSanPham());
        contentValues.put("tenSanPham", sanPham.getTenSanPham());
        contentValues.put("giaSanPham", sanPham.getGiaSanPham());
        contentValues.put("moTa", sanPham.getMoTa());
        contentValues.put("soLuongConLai", sanPham.getSoLuongConLai());
        return db.update("SANPHAM", contentValues, "sanPham_id=?", new String[]{String.valueOf(sanPham.getSanPham_id())});
    }

//    public boolean changeIsYeuThich(int sanPham_id, int newIsYeuThich) {
//        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("sanPham_id", sanPham_id);
//        contentValues.put("isYeuThich", newIsYeuThich);
//        long check = sqLiteDatabase.update("SANPHAM", contentValues, "sanPham_id = ?", new String[]{String.valueOf(sanPham_id)});
//        return check > 0;
//    }

    public long insertSanPham(SanPham sanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("loaiSanPham_id", sanPham.getLoaiSanPham_id());
        values.put("tenSanPham", sanPham.getTenSanPham());
        values.put("anhSanPham", sanPham.getAnhSanPham());
        values.put("giaSanPham", sanPham.getGiaSanPham());
        values.put("moTa", sanPham.getMoTa());
        values.put("soLuongConLai", sanPham.getSoLuongConLai());
        values.put("isYeuThich", sanPham.getIsYeuThich());
        values.put("isXoaMem", sanPham.getXoamen());
        return db.insert("SANPHAM", null, values);
    }

    public int xoaMemSP(int xoamem) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("isXoaMem", 1);
        return db.update("SANPHAM", values, "sanPham_id=?", new String[]{String.valueOf(xoamem)});
    }

//    public int getTrangThaiYeuThichBySanPhamId(int sanPham_id) {
//        int trangThaiYeuThich = -1;
//        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM where sanPham_id = ?", new String[]{String.valueOf(sanPham_id)});
//        if (cursor.getCount() != 0) {
//            cursor.moveToFirst();
//            trangThaiYeuThich = cursor.getInt(7);
//        }
//        return trangThaiYeuThich;
//    }

    public int soLuongConLai(SanPham sanPham) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soLuongConLai", sanPham.getSoLuongConLai());
        return db.update("SANPHAM", values, "sanPham_id=?", new String[]{String.valueOf(sanPham.getSanPham_id())});
    }

    public SanPham getSanPham(int sanPham_id) {
        SanPham sanPham = new SanPham();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from SANPHAM where isXoaMem=0 and sanPham_id = ?", new String[]{String.valueOf(sanPham_id)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                sanPham = new SanPham(
                        cursor.getInt(0), //sanPham_id
                        cursor.getInt(1), //loaiSanPham_id
                        cursor.getString(2), //tenSanPham
                        cursor.getString(3), //anhSanPham
                        cursor.getInt(4),    //giaSanPham
                        cursor.getString(5), //moTa
                        cursor.getInt(6)     //soLuongConLai
                );
            } while (cursor.moveToNext());
        }
        return sanPham;
    }

}
