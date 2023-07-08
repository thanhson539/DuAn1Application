package md18202.nhom2.duan1application.Models;

public class LoaiSanPham {
    private int loaiSanPham_id;
    private String tenLoai;

    public LoaiSanPham(int loaiSanPham_id, String tenLoai) {
        this.loaiSanPham_id = loaiSanPham_id;
        this.tenLoai = tenLoai;
    }

    public LoaiSanPham() {
    }

    public int getLoaiSanPham_id() {
        return loaiSanPham_id;
    }

    public void setLoaiSanPham_id(int loaiSanPham_id) {
        this.loaiSanPham_id = loaiSanPham_id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
