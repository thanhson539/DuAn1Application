package md18202.nhom2.duan1application.Model;

public class LoaiSanPham {
    private String loaiSanPham;
    private String tenLoai;

    public LoaiSanPham(String loaiSanPham, String tenLoai) {
        this.loaiSanPham = loaiSanPham;
        this.tenLoai = tenLoai;
    }

    public LoaiSanPham() {
    }

    public String getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(String loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
