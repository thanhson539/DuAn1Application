package md18202.nhom2.duan1application.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.ArrayList;

import md18202.nhom2.duan1application.DAO.BinhLuanDAO;
import md18202.nhom2.duan1application.DAO.HoaDonChiTietDAO;
import md18202.nhom2.duan1application.DAO.HoaDonDAO;
import md18202.nhom2.duan1application.DAO.LoaiSanPhamDAO;
import md18202.nhom2.duan1application.DAO.NguoiDungDAO;
import md18202.nhom2.duan1application.DAO.SanPhamDAO;
import md18202.nhom2.duan1application.Model.BinhLuan;
import md18202.nhom2.duan1application.Model.HoaDon;
import md18202.nhom2.duan1application.Model.HoaDonChiTiet;
import md18202.nhom2.duan1application.Model.LoaiSanPham;
import md18202.nhom2.duan1application.Model.NguoiDung;
import md18202.nhom2.duan1application.Model.SanPham;
import md18202.nhom2.duan1application.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check data
        NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(this);
        HoaDonDAO hoaDonDAO = new HoaDonDAO(this);
        BinhLuanDAO binhLuanDAO = new BinhLuanDAO(this);
        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(this);
        SanPhamDAO sanPhamSAO = new SanPhamDAO(this);
        HoaDonChiTietDAO hoaDonChiTietDAO = new HoaDonChiTietDAO(this);

        ArrayList<NguoiDung> listNguoiDung = nguoiDungDAO.getDsNguoiDung();
        ArrayList<HoaDon> listHoaDon = hoaDonDAO.getDsHoaDon();
        ArrayList<BinhLuan> listBinhLuan = binhLuanDAO.getDsBinhLuan();
        ArrayList<LoaiSanPham> listLoaiSanPham = loaiSanPhamDAO.getDsLoaiSanPham();
        ArrayList<SanPham> listSanPham = sanPhamSAO.getDsSanPham();
        ArrayList<HoaDonChiTiet> listHoaDonChiTiet = hoaDonChiTietDAO.getDsHoaDonChiTiet();

        Toast.makeText(this, listNguoiDung.size() + "", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, listHoaDon.size() + "", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, listBinhLuan.size() + "", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, listLoaiSanPham.size() + "", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, listSanPham.size() + "", Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, listHoaDonChiTiet.size() + "", Toast.LENGTH_SHORT).show();
    }
}