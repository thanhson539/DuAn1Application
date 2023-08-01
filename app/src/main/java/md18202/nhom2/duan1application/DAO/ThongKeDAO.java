package md18202.nhom2.duan1application.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import md18202.nhom2.duan1application.Databases.DBHelper;
import md18202.nhom2.duan1application.Models.HoaDonChiTiet;
import md18202.nhom2.duan1application.Models.SanPham;

public class ThongKeDAO {
    DBHelper dbHelper;
    public ThongKeDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    @SuppressLint("Range")
    public int getSoTienDaMua(int nguoiDung_id){
        int tongTien = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select HOADONCHITIET.hoaDon_id as hoaDon_id, HOADONCHITIET.soLuong as soLuong, SANPHAM.giaSanPham as giaSanPham, " +
                "HOADON.nguoiDung_id as nguoiDung_id , " +
                "sum(HOADONCHITIET.soLuong * SANPHAM.giaSanPham) as tongTien, " +
                "HOADONCHITIET.trangThaiDonHang as trangThaiDonHang, HOADONCHITIET.trangThaiThanhToan as trangThaiThanhToan from HOADONCHITIET " +
                "inner join SANPHAM on HOADONCHITIET.sanPham_id = SANPHAM.sanPham_id " +
                "inner join HOADON on HOADONCHITIET.hoaDon_id = HOADON.hoaDon_id " +
                "where trangThaiDonHang = 3 and trangThaiThanhToan = 1 and nguoiDung_id = ? " +
                "group by nguoiDung_id", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                tongTien = cursor.getInt(cursor.getColumnIndex("tongTien"));
            }while (cursor.moveToNext());
        }
        return tongTien;
    }

    @SuppressLint("Range")
    public int getDataInMonth(int nguoiDung_id, int month){
        List<HoaDonChiTiet> list = new ArrayList<>();
        int tien = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select HOADON.hoaDon_id, strftime('%m', HOADON.ngayMua) as month, " +
                "strftime('%Y', HOADON.ngayMua) as year, HOADONCHITIET.soLuong as soLuong, SANPHAM.giaSanPham as giaSanPham, " +
                "HOADON.nguoiDung_id as nguoiDung_id, " +
                "sum((HOADONCHITIET.soLuong * SANPHAM.giaSanPham)) as tien, " +
                "HOADONCHITIET.trangThaiDonHang as trangThaiDonHang, " +
                "HOADONCHITIET.trangThaiThanhToan as trangThaiThanhToan " +
                "from HOADONCHITIET " +
                "inner join SANPHAM on HOADONCHITIET.sanPham_id = SANPHAM.sanPham_id " +
                "inner join HOADON on HOADONCHITIET.hoaDon_id = HOADON.hoaDon_id " +
                "where trangThaiDonHang = 3 and trangThaiThanhToan = 1 and nguoiDung_id = ? " +
                "group by month, year", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDonChiTiet(cursor.getInt(cursor.getColumnIndex("month")),
                        cursor.getInt(cursor.getColumnIndex("year")),
                        cursor.getInt(cursor.getColumnIndex("tien"))));
            }while (cursor.moveToNext());
        }

        for(HoaDonChiTiet hdct: list){
            if(hdct.getMonth() == month){
                tien = hdct.getGiaSanPham();
            }
        }
        return tien;
    }

    @SuppressLint("Range")
    public int tuNgayDenNgay(String tuNgay, String denNgay, int nguoiDung_id){
        int tongTien = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select HOADON.ngayMua as date, HOADONCHITIET.hoaDon_id as hoaDon_id, HOADONCHITIET.soLuong as soLuong, SANPHAM.giaSanPham as giaSanPham, " +
                "HOADON.nguoiDung_id as nguoiDung_id , " +
                "sum(HOADONCHITIET.soLuong * SANPHAM.giaSanPham) as tongTien," +
                "HOADONCHITIET.trangThaiDonHang as trangThaiDonHang, HOADONCHITIET.trangThaiThanhToan as trangThaiThanhToan " +
                "from HOADONCHITIET " +
                "inner join SANPHAM on HOADONCHITIET.sanPham_id = SANPHAM.sanPham_id " +
                "inner join HOADON on HOADONCHITIET.hoaDon_id = HOADON.hoaDon_id " +
                "where trangThaiDonHang = 3 and trangThaiThanhToan = 1 and date between ? and ? and nguoiDung_id = ? " +
                "group by nguoiDung_id", new String[]{tuNgay, denNgay, String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                tongTien = cursor.getInt(cursor.getColumnIndex("tongTien"));
            }while (cursor.moveToNext());
        }
        return tongTien;
    }

}
