package md18202.nhom2.duan1application;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "DuAn1_Database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNguoiDung = "create table NGUOIDUNG(" +
                "id integer primary key autoincrement," +
                "nguoiDung_id text not null," +
                "hoTen text not null," +
                "soDienThoai text not null," +
                "email text not null," +
                "taikhoan text not null," +
                "matkhau text not null," +
                "loaiTaiKhoan integer not null)";
        db.execSQL(createNguoiDung);

        String createBinhLuan = "create table BINHLUAN(" +
                "id integer primary key autoincrement," +
                "nguoiDung_id text references NGUOIDUNG(nguoiDung_id)," +
                "noiDung text not null," +
                "thoiGian text not null)";
        db.execSQL(createBinhLuan);

        String createHoaDon = "create table HOADON(" +
                "id integer primary key autoincrement," +
                "hoaDon_id text not null," +
                "nguoiDung_id text references NGUOIDUNG(nguoiDung_id)," +
                "ngayMua text not null," +
                "tongTien integer not null)";
        db.execSQL(createHoaDon);

        String createLoaiSanPham = "create table LOAISANPHAM(" +
                "id integer primary key autoincrement," +
                "loaiSanPham_id text not null," +
                "tenLoai text not null)";
        db.execSQL(createLoaiSanPham);

        String createSanPham = "create table SANPHAM(" +
                "id integer primary key autoincrement," +
                "sanPham_id text not null," +
                "tenSanPham text not null," +
                "anhSanPham text not null," +
                "giaSanPham integer not null," +
                "moTa text not null," +
                "soLuongConLai integer not null," +
                "loaiSanPham_id text references LOAISANPHAM(loaiSanPham_id))";
        db.execSQL(createSanPham);

        String createHoaDonChiTiet = "create table HOADONCHITIET(" +
                "id integer primary key autoincrement," +
                "hoaDonChiTiet_id text not null," +
                "hoaDon_id text references HOADON(hoaDon_id)," +
                "sanPham_id text references SANPHAM(sanPham_id)," +
                "trangThaiDonHang integer not null," +
                "trangThaiThanhToan integer not null)";
        db.execSQL(createHoaDonChiTiet);

        //Thêm dữ liệu ảo để check bảng

        /*1: Bang NGUOIDUNG
        * - loaiTaiKhoan: 1 --> admin; 0 --> nguoi dung*/

        db.execSQL("insert into NGUOIDUNG values" +
                "(1, 'nguoidung01', 'Nguyen Thanh Son', '0963943774', 'hoasua050399@mail.com','thanhson539','123456', 1)," +
                "(2, 'nguoidung02', 'Nguyen Duy Tien', '0123456789', 'nguyenduytienbgg3@gmail.com','duytienbgg3','123456', 0)," +
                "(3, 'nguoidung03', 'Phi Dinh Long', '0123456789', 'philongpdl@gmail.com','philongpdl','123456', 0)," +
                "(4, 'nguoidung04', 'Hoang Minh Quan', '0123456789', 'mquann139@gmail.com','mquann139','123456', 0)," +
                "(5, 'nguoidung05', 'Ha Manh Dung', '0123456789', 'hmdung26@gmail.com','hmdung26','123456', 0)");

        db.execSQL("insert into BINHLUAN values" +
                "(1,'nguoidung01','Thanh Son da comment ve san pham nay', '13:00 - 03/07/2023')," +
                "(2,'nguoidung02','Duy Tien da comment ve san pham nay', '14:00 - 04/07/2023')," +
                "(3,'nguoidung03','Dinh Long da comment ve san pham nay', '15:00 - 05/07/2023')," +
                "(4,'nguoidung04','Minh Quan da comment ve san pham nay', '16:00 - 06/07/2023')," +
                "(5,'nguoidung05','Manh Dung da comment ve san pham nay', '17:00 - 07/07/2023')");

        db.execSQL("insert into LOAISANPHAM values" +
                "(1, 'loai01', 'Vớ cố thấp')," +
                "(2, 'loai02', 'Vớ cổ cao')," +
                "(3, 'loai03', 'Vớ cổ trung')," +
                "(4, 'loai04', 'Vớ lười')," +
                "(5, 'loai05', 'Vớ họa tiết')");

        db.execSQL("insert into SANPHAM values" +
                "(1, 'sanpham01', 'Vỡ nữ cố thấp 01', 'link img1', 15000, 'Vớ nữ cổ thấp chất liệu trơn thoáng mát',50,'loai01')," +
                "(2, 'sanpham02', 'Vớ nam cổ cao 01', 'link img2', 18000, 'Vớ nam cổ cao chất liệu cotton khử mùi, thoáng mát',30,'loai02')," +
                "(3, 'sanpham03', 'Vỡ nam thể thao cổ trung 01', 'link img3', 17000, 'Vớ thể thao nam cổ trung, êm chân thoát mát',40,'loai03')," +
                "(4, 'sanpham04', 'Vỡ lười nam nữ 01', 'link img4', 20000, 'Vớ lười nam/nũ chất liệu cao cấp',60,'loai04')," +
                "(5, 'sanpham05', 'Vớ nữ họa tiết ô vuông 01', 'link img5', 25000, 'Vớ nũ họa tiết cá tính',45,'loai05')");

        db.execSQL("insert into HOADON values" +
                "(1, 'hoadon01', 'nguoidung01', '03/07/2023', 15000)," +
                "(2, 'hoadon02', 'nguoidung02', '04/07/2023', 25000)," +
                "(3, 'hoadon03', 'nguoidung03', '05/07/2023', 20000)," +
                "(4, 'hoadon04', 'nguoidung04', '06/07/2023', 30000)," +
                "(5, 'hoadon05', 'nguoidung05', '07/07/2023', 35000)");

        /*Trang thai don hang:
        * - 0: Order thanh con
        * - 1: Dang giao hang
        * - 2: da nhan hang
        *
        * Trang thai thanh toan:
        * - 0: Chua thanh toan
        * - 1: Da thanh toan */
        db.execSQL("insert into HOADONCHITIET values" +
                "(1, 'hoadonchitiet01', 'hoadon01', 'sanpham01', 1, 0)," +
                "(2, 'hoadonchitiet01', 'hoadon01', 'sanpham01', 2, 1)," +
                "(3, 'hoadonchitiet01', 'hoadon01', 'sanpham01', 0, 0)," +
                "(4, 'hoadonchitiet01', 'hoadon01', 'sanpham01', 1, 0)," +
                "(5, 'hoadonchitiet01', 'hoadon01', 'sanpham01', 2, 0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists NGUOIDUNG");
        db.execSQL("drop table if exists HOADON");
        db.execSQL("drop table if exists BINHLUAN");
        db.execSQL("drop table if exists LOAISANPHAM");
        db.execSQL("drop table if exists SANPHAM");
        db.execSQL("drop table if exists HOADONCHITIET");
        onCreate(db);
    }
}
