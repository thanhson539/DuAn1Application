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
    public List<Integer> getSoTienDaMua(){
        List<Integer> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select HOADONCHITIET.hoaDon_id as hoaDon_id, HOADONCHITIET.soLuong as soLuong, SANPHAM.giaSanPham as giaSanPham, " +
                " HOADON.nguoiDung_id as nguoiDung_id , " +
                " sum(HOADONCHITIET.soLuong * SANPHAM.giaSanPham) as tongTien," +
                " HOADONCHITIET.trangThaiDonHang as trangThaiDonHang, HOADONCHITIET.trangThaiThanhToan as trangThaiThanhToan from HOADONCHITIET" +
                " inner join SANPHAM on HOADONCHITIET.sanPham_id = SANPHAM.sanPham_id " +
                "inner join HOADON on HOADONCHITIET.hoaDon_id = HOADON.hoaDon_id " +
                "where trangThaiDonHang = 3 and trangThaiThanhToan = 1" +
                " group by nguoiDung_id", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Integer(cursor.getInt(cursor.getColumnIndex("tongTien"))));
//                list.add(new HoaDonChiTiet(cursor.getInt(cursor.getColumnIndex("soLuong")),
//                        cursor.getInt(cursor.getColumnIndex("trangThaiDonHang")),
//                        cursor.getInt(cursor.getColumnIndex("trangThaiThanhToan"))));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
