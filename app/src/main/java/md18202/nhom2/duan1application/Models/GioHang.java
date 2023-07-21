package md18202.nhom2.duan1application.Models;

public class GioHang {
    private int gioHang_id;
    private int nguoiDung_id;
    private int sanPham_id;
    private int soLuong;
    private int giaSanPham;
    private String tenSanPham;
    private String anhSanPham;

    public GioHang() {
    }

    public GioHang(int gioHang_id, int nguoiDung_id, int sanPham_id, int soLuong) {
        this.gioHang_id = gioHang_id;
        this.nguoiDung_id = nguoiDung_id;
        this.sanPham_id = sanPham_id;
        this.soLuong = soLuong;
    }

    public GioHang(int gioHang_id, int nguoiDung_id, int sanPham_id, int soLuong, int giaSanPham, String tenSanPham, String anhSanPham) {
        this.gioHang_id = gioHang_id;
        this.nguoiDung_id = nguoiDung_id;
        this.sanPham_id = sanPham_id;
        this.soLuong = soLuong;
        this.giaSanPham = giaSanPham;
        this.tenSanPham = tenSanPham;
        this.anhSanPham = anhSanPham;
    }

    public int getGioHang_id() {
        return gioHang_id;
    }

    public void setGioHang_id(int gioHang_id) {
        this.gioHang_id = gioHang_id;
    }

    public int getNguoiDung_id() {
        return nguoiDung_id;
    }

    public void setNguoiDung_id(int nguoiDung_id) {
        this.nguoiDung_id = nguoiDung_id;
    }

    public int getSanPham_id() {
        return sanPham_id;
    }

    public void setSanPham_id(int sanPham_id) {
        this.sanPham_id = sanPham_id;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }

}