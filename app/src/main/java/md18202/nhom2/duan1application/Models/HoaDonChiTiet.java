package md18202.nhom2.duan1application.Models;

public class HoaDonChiTiet {
    private int hoaDon_id;
    private int sanPham_id;
    private int soLuong;
    private int trangThaiDonHang;
    private int trangThaiThanhToan;
    //---------
    private String tenSanPham;
    private String anhSanPham;
    private int giaSanPham;
    private String ngayMua;
    private String diaChi;
    private int tongTien;
    private int soTienDaMua;

    public HoaDonChiTiet(int hoaDon_id, int soLuong, int giaSanPham, int trangThaiDonHang, int trangThaiThanhToan) {
        this.hoaDon_id = hoaDon_id;
        this.soLuong = soLuong;
        this.giaSanPham = giaSanPham;
        this.trangThaiDonHang = trangThaiDonHang;
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public HoaDonChiTiet(int hoaDon_id, int sanPham_id, int soLuong, int trangThaiDonHang, int trangThaiThanhToan,
                         String tenSanPham, String anhSanPham, int giaSanPham,
                         String ngayMua, String diaChi, int tongTien) {
        this.hoaDon_id = hoaDon_id;
        this.sanPham_id = sanPham_id;
        this.soLuong = soLuong;
        this.trangThaiDonHang = trangThaiDonHang;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.tenSanPham = tenSanPham;
        this.anhSanPham = anhSanPham;
        this.giaSanPham = giaSanPham;
        this.ngayMua = ngayMua;
        this.diaChi = diaChi;
        this.tongTien = tongTien;
    }

    public HoaDonChiTiet() {
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getHoaDon_id() {
        return hoaDon_id;
    }

    public void setHoaDon_id(int hoaDon_id) {
        this.hoaDon_id = hoaDon_id;
    }

    public int getSanPham_id() {
        return sanPham_id;
    }

    public void setSanPham_id(int sanPham_id) {
        this.sanPham_id = sanPham_id;
    }

    public int getTrangThaiDonHang() {
        return trangThaiDonHang;
    }

    public void setTrangThaiDonHang(int trangThaiDonHang) {
        this.trangThaiDonHang = trangThaiDonHang;
    }

    public int getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(int trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoTienDaMua() {
        return soTienDaMua;
    }

    public void setSoTienDaMua(int soTienDaMua) {
        this.soTienDaMua = soTienDaMua;
    }
}
