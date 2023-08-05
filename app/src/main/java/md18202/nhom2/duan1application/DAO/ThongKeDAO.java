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
    public int getDataInMonth(int nguoiDung_id, int month, int year){
        List<HoaDonChiTiet> list = new ArrayList<>();
        int tien = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select HOADON.hoaDon_id, " +
                "strftime('%m', HOADON.ngayMua) as month, " +
                "strftime('%Y', HOADON.ngayMua) as year, " +
                "HOADONCHITIET.soLuong, " +
                "SANPHAM.giaSanPham, " +
                "HOADON.nguoiDung_id, " +
                "sum((HOADONCHITIET.soLuong * SANPHAM.giaSanPham)) as tien, " +
                "HOADONCHITIET.trangThaiDonHang, " +
                "HOADONCHITIET.trangThaiThanhToan " +
                "from HOADONCHITIET " +
                "inner join SANPHAM on HOADONCHITIET.sanPham_id = SANPHAM.sanPham_id " +
                "inner join HOADON on HOADONCHITIET.hoaDon_id = HOADON.hoaDon_id " +
                "where HOADONCHITIET.trangThaiDonHang = 3 and HOADONCHITIET.trangThaiThanhToan = 1 and HOADON.nguoiDung_id = ? " +
                "group by strftime('%m-%Y', HOADON.ngayMua)", new String[]{String.valueOf(nguoiDung_id)});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDonChiTiet(
                        cursor.getString(cursor.getColumnIndex("month")),
                        cursor.getString(cursor.getColumnIndex("year")),
                        cursor.getInt(cursor.getColumnIndex("tien"))));
            }while (cursor.moveToNext());
        }

        for(HoaDonChiTiet hdct: list){
            if(Integer.parseInt(hdct.getMonth()) == month && Integer.parseInt(hdct.getYear()) == year){
                tien = hdct.getTien();
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

    @SuppressLint("Range")
    public List<SanPham> getSanPhamBanChay(){
        List<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select hdct.sanPham_id as sanPham_id, sp.tenSanPham as tenSanPham, " +
                "sp.giaSanPham as giaSanPham, sp.anhSanPham as anhSanPham, sp.isYeuThich as isYeuThich, sp.moTa as moTa, sp.loaiSanPham_id as loaiSanPham_id, sp.soLuongConLai as soLuongConLai, " +
                "hdct.trangThaiDonHang, hdct.trangThaiThanhToan, " +
                "sum(hdct.soLuong) as soLuong " +
                "from HOADONCHITIET hdct " +
                "inner join SANPHAM sp on hdct.sanPham_id = sp.sanPham_id " +
                "inner join HOADON hd on hdct.hoaDon_id = hd.hoaDon_id " +
                "where hdct.trangThaiDonHang = 3 and hdct.trangThaiThanhToan = 1 " +
                "group by hdct.sanPham_id " +
                "order by soLuong desc limit 1", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(cursor.getColumnIndex("sanPham_id")),
                        cursor.getInt(cursor.getColumnIndex("loaiSanPham_id")),
                        cursor.getString(cursor.getColumnIndex("tenSanPham")),
                        cursor.getString(cursor.getColumnIndex("anhSanPham")),
                        cursor.getInt(cursor.getColumnIndex("giaSanPham")),
                        cursor.getString(cursor.getColumnIndex("moTa")),
                        cursor.getInt(cursor.getColumnIndex("soLuongConLai")),
                        cursor.getInt(cursor.getColumnIndex("isYeuThich"))));
            }while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public List<SanPham> getSanPhamDaBan(){
        List<SanPham> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select hdct.sanPham_id as sanPham_id, sp.tenSanPham as tenSanPham, " +
                "sp.giaSanPham as giaSanPham, sp.anhSanPham as anhSanPham, sp.isYeuThich as isYeuThich, sp.moTa as moTa, sp.loaiSanPham_id as loaiSanPham_id, sp.soLuongConLai as soLuongConLai, " +
                "hdct.trangThaiDonHang, hdct.trangThaiThanhToan " +
                "from HOADONCHITIET hdct " +
                "inner join SANPHAM sp on hdct.sanPham_id = sp.sanPham_id " +
                "inner join HOADON hd on hdct.hoaDon_id = hd.hoaDon_id " +
                "where hdct.trangThaiDonHang = 3 and hdct.trangThaiThanhToan = 1 " +
                "group by hdct.sanPham_id ", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(cursor.getColumnIndex("sanPham_id")),
                        cursor.getInt(cursor.getColumnIndex("loaiSanPham_id")),
                        cursor.getString(cursor.getColumnIndex("tenSanPham")),
                        cursor.getString(cursor.getColumnIndex("anhSanPham")),
                        cursor.getInt(cursor.getColumnIndex("giaSanPham")),
                        cursor.getString(cursor.getColumnIndex("moTa")),
                        cursor.getInt(cursor.getColumnIndex("soLuongConLai")),
                        cursor.getInt(cursor.getColumnIndex("isYeuThich"))));
            }while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public int tongTien(){
        int tongTien = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select HOADONCHITIET.soLuong as soLuong, SANPHAM.giaSanPham as giaSanPham, " +
                "sum(HOADONCHITIET.soLuong * SANPHAM.giaSanPham) as tongTien, " +
                "HOADONCHITIET.trangThaiDonHang as trangThaiDonHang, HOADONCHITIET.trangThaiThanhToan as trangThaiThanhToan from HOADONCHITIET " +
                "inner join SANPHAM on HOADONCHITIET.sanPham_id = SANPHAM.sanPham_id "+
                "where trangThaiDonHang = 3 and trangThaiThanhToan = 1 ", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                tongTien = cursor.getInt(cursor.getColumnIndex("tongTien"));
            }while (cursor.moveToNext());
        }
        return tongTien;
    }
    @SuppressLint("Range")
    public int donHangThanhCong(){
        int donHangThanhCong = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " +
                "count(HOADONCHITIET.hoaDon_id) as donHangThanhCong, " +
                "HOADONCHITIET.trangThaiDonHang as trangThaiDonHang, HOADONCHITIET.trangThaiThanhToan as trangThaiThanhToan from HOADONCHITIET " +
                "inner join SANPHAM on HOADONCHITIET.sanPham_id = SANPHAM.sanPham_id "+
                "where trangThaiDonHang = 3 and trangThaiThanhToan = 1 ", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                donHangThanhCong = cursor.getInt(cursor.getColumnIndex("donHangThanhCong"));
            }while (cursor.moveToNext());
        }
        return donHangThanhCong;
    }

    @SuppressLint("Range")
    public int donHangDaHuy(){
        int donHangThanhCong = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select " +
                "count(HOADONCHITIET.hoaDon_id) as donHangDaHuy, " +
                "HOADONCHITIET.trangThaiDonHang as trangThaiDonHang, HOADONCHITIET.trangThaiThanhToan as trangThaiThanhToan from HOADONCHITIET " +
                "inner join SANPHAM on HOADONCHITIET.sanPham_id = SANPHAM.sanPham_id "+
                "where trangThaiDonHang = 4 ", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                donHangThanhCong = cursor.getInt(cursor.getColumnIndex("donHangDaHuy"));
            }while (cursor.moveToNext());
        }
        return donHangThanhCong;
    }

    @SuppressLint("Range")
    public int getDataInMonthForAdmin(int month, int year){
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
                "where trangThaiDonHang = 3 and trangThaiThanhToan = 1 " +
                "group by month, year", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new HoaDonChiTiet(cursor.getString(cursor.getColumnIndex("month")),
                        cursor.getString(cursor.getColumnIndex("year")),
                        cursor.getInt(cursor.getColumnIndex("tien"))));
            }while (cursor.moveToNext());
        }

        for(HoaDonChiTiet hdct: list){
            if(Integer.parseInt(hdct.getMonth()) == month && Integer.parseInt(hdct.getYear()) == year){
                tien = hdct.getGiaSanPham();
            }
        }
        return tien;
    }
}