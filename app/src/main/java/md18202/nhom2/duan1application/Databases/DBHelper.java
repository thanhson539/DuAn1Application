package md18202.nhom2.duan1application.Databases;

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
                "nguoiDung_id integer primary key autoincrement," +
                "hoTen text not null," +
                "soDienThoai text not null," +
                "email text not null," +
                "taiKhoan text not null," +
                "matKhau text not null," +
                "loaiTaiKhoan integer not null)";
        db.execSQL(createNguoiDung);

        String createBinhLuan = "create table BINHLUAN(" +
                "binhLuan_id integer primary key autoincrement," +
                "nguoiDung_id integer references NGUOIDUNG(nguoiDung_id)," +
                "sanPham_id integer references SANPHAM(sanPham_id)," +
                "noiDung text not null," +
                "thoiGian text not null)";
        db.execSQL(createBinhLuan);

        String createHoaDon = "create table HOADON(" +
                "hoaDon_id integer primary key autoincrement," +
                "nguoiDung_id integer references NGUOIDUNG(nguoiDung_id)," +
                "ngayMua text not null," +
                "tongTien integer not null)";
        db.execSQL(createHoaDon);

        String createLoaiSanPham = "create table LOAISANPHAM(" +
                "loaiSanPham_id integer primary key autoincrement," +
                "tenLoai text not null)";
        db.execSQL(createLoaiSanPham);

        String createSanPham = "create table SANPHAM(" +
                "sanPham_id integer primary key autoincrement," +
                "loaiSanPham_id integer references LOAISANPHAM(loaiSanPham_id)," +
                "tenSanPham text not null," +
                "anhSanPham text not null," +
                "giaSanPham integer not null," +
                "moTa text not null," +
                "soLuongConLai integer not null," +
                "isYeuThich integer not null)";
        db.execSQL(createSanPham);

        String createHoaDonChiTiet = "create table HOADONCHITIET(" +
                "hoaDonChiTiet_id integer primary key autoincrement," +
                "hoaDon_id integer references HOADON(hoaDon_id)," +
                "sanPham_id integer references SANPHAM(sanPham_id)," +
                "trangThaiDonHang integer not null," +
                "trangThaiThanhToan integer not null)";
        db.execSQL(createHoaDonChiTiet);

        //DATA ẢO

        /*1: Bang 'NGUOIDUNG'
         * Cấu trúc cột: nguoiDung_id, hoTen, soDienThoai, email, taiKhoan, matKhau, loaiTaiKhoan
         * Ghi chú:
         * - Giá trị tại cột 'loaiTaiKhoan': 1 --> admin, 0 --> người dùng */

        db.execSQL("insert into NGUOIDUNG values" +
                "(1, 'Nguyen Thanh Son', '0963943774', 'hoasua050399@mail.com','thanhson539','123456', 1)," +
                "(2, 'Nguyen Duy Tien', '0123456789', 'nguyenduytienbgg3@gmail.com','duytienbgg3','123456', 0)," +
                "(3, 'Phi Dinh Long', '0123456789', 'philongpdl@gmail.com','philongpdl','123456', 0)," +
                "(4, 'Hoang Minh Quan', '0123456789', 'mquann139@gmail.com','mquann139','123456', 0)," +
                "(5, 'Ha Manh Dung', '0375816024', 'hmdung26@gmail.com','hmdung26','123456', 0)");

        /*2: Bang 'BINHLUAN'
         * Cấu trúc cột: binhLuan_id, nguoiDung_id, noiDung, thoiGian */

        db.execSQL("insert into BINHLUAN values" +
                "(1,1, 3,'Thanh Son da comment ve san pham nay', '13:00 - 03/07/2023')," +
                "(2,2, 3,'Duy Tien da comment ve san pham nay', '14:00 - 04/07/2023')," +
                "(3,3, 4,'Dinh Long da comment ve san pham nay', '15:00 - 05/07/2023')," +
                "(4,4, 1,'Minh Quan da comment ve san pham nay', '16:00 - 06/07/2023')," +
                "(5,5, 2,'Manh Dung da comment ve san pham nay', '17:00 - 07/07/2023')");

        /*3: Bảng 'LOAISANPHAM'
        * Cấu trúc cột: loaiSanPham_id, tenLoai */

        db.execSQL("insert into LOAISANPHAM values" +
                "(1, 'Vớ cố thấp')," +
                "(2, 'Vớ cổ cao')," +
                "(3, 'Vớ cổ trung')," +
                "(4, 'Vớ lười')," +
                "(5, 'Vớ họa tiết')," +
                "(6, 'Vớ basic nam/nữ')");

        /*4: Bảng 'SANPHAM'
        * Cấu trúc cột: sanPham_id, loaiSanPham_id, tenSanPham, anhSanPham, giaSanPham, moTa, soLuongConLai, isYeuThich*/

        db.execSQL("insert into SANPHAM values" +
                // vo co thap
                "(1, 1, 'Vỡ nam nữ cố thấp 01', 'sanpham_cothap1', 15000, 'Vớ nam nữ cổ thấp chất liệu trơn thoáng mát',51, 0)," +
                "(2, 1, 'Vỡ nữ cố thấp 02', 'sanpham_cothap2', 25000, 'Vớ nữ cổ thấp chất liệu trơn thoáng mát',50, 1)," +
                "(3, 1, 'Vỡ nữ cố thấp 03', 'sanpham_cothap3', 5000, 'Vớ nữ cổ thấp chất liệu trơn thoáng mát',40, 0)," +
                "(4, 1, 'Vỡ nữ cố thấp 04', 'sanpham_cothap4', 65000, 'Vớ nữ cổ thấp chất liệu trơn thoáng mát',45, 0)," +
                "(5, 1, 'Vỡ nữ cố thấp 06', 'sanpham_cothap5', 10000, 'Vớ nữ cổ thấp chất liệu trơn thoáng mát',56, 0)," +
                "(6, 1, 'Vỡ nữ cố thấp 06', 'sanpham_cothap6', 32000, 'Vớ nữ cổ thấp chất liệu trơn thoáng mát',53, 1)," +


                // vo co cao
                "(7, 2, 'Vớ nam nữ cổ cao 01', 'sanpham_cocao1', 18000, 'Vớ nam nữ cổ cao chất liệu cotton khử mùi, thoáng mát',30, 1)," +
                "(8, 2, 'Vớ nũ cổ cao 02', 'sanpham_cocao2', 28000, 'Vớ nữ cổ cao chất liệu cotton khử mùi, thoáng mát',34, 0)," +
                "(9 , 2, 'Vớ nam nữ cổ cao 03', 'sanpham_cocao3', 13000, 'Vớ nam nữ cổ cao chất liệu cotton khử mùi, thoáng mát',31, 1)," +
                "(10, 2, 'Vớ nam nữ cổ cao 04', 'sanpham_cocao4', 16000, 'Vớ nam nữ cổ cao chất liệu cotton khử mùi, thoáng mát',36, 0)," +
                "(11, 2, 'Vớ nam nữ cổ cao 05', 'sanpham_cocao5', 19000, 'Vớ nam nữ cổ cao chất liệu cotton khử mùi, thoáng mát',39, 0)," +
                "(12, 2, 'Vớ nam nữ cổ cao 06', 'sanpham_cocao6', 24000, 'Vớ nam nữ cổ cao chất liệu cotton khử mùi, thoáng mát',33, 0)," +


                // vo co trung
                "(13, 3, 'Vỡ nữ thể thao cổ trung 01', 'sanpham_cotrung1', 17000, 'Vớ nữ cổ trung, êm chân thoát mát',40, 0)," +
                "(14, 3, 'Vỡ nữ thể thao cổ trung 02', 'sanpham_cotrung2', 16000, 'Vớ thể thao nữ cổ trung, êm chân thoát mát',41, 0)," +
                "(15, 3, 'Vỡ nữ thể thao cổ trung 03', 'sanpham_cotrung3', 15000, 'Vớ thể thao nữ cổ trung, êm chân thoát mát',42, 1)," +
                "(16, 3, 'Vỡ nữ thể thao cổ trung 04', 'sanpham_cotrung4', 14000, 'Vớ thể thao nữ cổ trung, êm chân thoát mát',45, 0)," +

                // vo luoi
                //Không dùng ảnh có độ phân giải cao Quân nhé. Vì nó sẽ ko load được và bị chết app - Anh Sơn gửi đến Quân.
                "(19, 4, 'Vớ lười nam nữ 03', 'sanpham_voluoi3', 20000, 'Vớ lười nam/nũ chất liệu cao cấp',60, 0)," +
                "(20, 4, 'Vớ lười nam nữ 04', 'sanpham_voluoi4', 20000, 'Vớ lười nam/nũ chất liệu cao cấp',60, 0)," +
                "(21, 4, 'Vớ lười nam nữ 05', 'sanpham_voluoi5', 20000, 'Vớ lười nam/nũ chất liệu cao cấp',60, 0)," +

                "(17, 4, 'Vỡ lười nam nữ 01', 'sanpham_hoa_tiet2', 20000, 'Vớ lười nam/nũ chất liệu cao cấp',60, 0)," +
                "(18, 5, 'Vớ nữ họa tiết ô vuông 01', 'sanpham_hoa_tiet3', 25000, 'Vớ nũ họa tiết cá tính',45, 0)," +

                //vo basic
                "(22, 5, 'Vớ basic nam nữ 01', 'sanpham_vobasic1', 20000, 'Vớ basic nam/nũ chất liệu cao cấp',60, 0)," +
                "(23, 5, 'Vớ basic nam nữ 02', 'sanpham_vobasic2', 20000, 'Vớ basic nam/nũ chất liệu cao cấp',60, 0)," +
                "(24, 5, 'Vớ basic nam nữ 03', 'sanpham_vobasic3', 20000, 'Vớ basic nam/nũ chất liệu cao cấp',60, 0)," +
                "(25, 5, 'Vớ basic nam nữ 04', 'sanpham_vobasic4', 20000, 'Vớ basic nam/nũ chất liệu cao cấp',60, 0)," +
                "(26, 5, 'Vớ basic nam nữ 05', 'sanpham_vobasic5', 20000, 'Vớ basic nam/nũ chất liệu cao cấp',60, 0)," +

                // vo hoa tiet
                "(27, 6, 'Vớ họa tiết nam nữ 01', 'sanpham_vohoatiet1', 20000, 'Vớ họa tiết nam/nũ chất liệu cao cấp',60, 0)," +
                "(28, 6, 'Vớ họa tiết nam nữ 02', 'sanpham_vohoatiet2', 20000, 'Vớ họa tiết nam/nũ chất liệu cao cấp',60, 0)," +
                "(29, 6, 'Vớ họa tiết nam nữ 03', 'sanpham_vohoatiet3', 20000, 'Vớ họa tiết nam/nũ chất liệu cao cấp',60, 0)," +
                "(30, 6, 'Vớ họa tiết nam nữ 04', 'sanpham_vohoatiet4', 20000, 'Vớ họa tiết nam/nũ chất liệu cao cấp',60, 0)");

        /*5: Bảng 'HOADON'
        *Cấu trúc cột: hoaDon_id, nguoiDung_id, thoiGian, tongTien */

        db.execSQL("insert into HOADON values" +
                "(1, 1, '03/07/2023', 15000)," +
                "(2, 2, '04/07/2023', 25000)," +
                "(3, 3, '05/07/2023', 20000)," +
                "(4, 4, '06/07/2023', 30000)," +
                "(5, 5, '07/07/2023', 35000)");

        /*6: Bảng 'HOADONCHITIET':
         * Cấu trúc bảng: hoaDonChiTiet_id, hoaDon_id, sanPham_id, trangThaiDonHang, trangThaiThanhToan
         * Ghi chú:
         * Trang thai don hang:
         * - 0: Order thanh con
         * - 1: Dang giao hang
         * - 2: da nhan hang
         *
         * Trang thai thanh toan:
         * - 0: Chua thanh toan
         * - 1: Da thanh toan */
        db.execSQL("insert into HOADONCHITIET values" +
                "(1, 1, 1, 1, 0)," +
                "(2, 2, 2, 2, 1)," +
                "(3, 3, 3, 0, 0)," +
                "(4, 4, 4, 1, 0)," +
                "(5, 5, 5, 2, 0)");
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
