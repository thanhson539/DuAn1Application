package md18202.nhom2.duan1application.Models;

public class HoaDonChiTiet {
    private int hoaDonChiTiet_id;
    private int hoaDon_id;
    private int sanPham_id;
    private int trangThaiDonHang;
    private int trangThaiThanhToan;

    public HoaDonChiTiet(int hoaDonChiTiet_id, int hoaDon_id, int sanPham_id, int trangThaiDonHang, int trangThaiThanhToan) {
        this.hoaDonChiTiet_id = hoaDonChiTiet_id;
        this.hoaDon_id = hoaDon_id;
        this.sanPham_id = sanPham_id;
        this.trangThaiDonHang = trangThaiDonHang;
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public HoaDonChiTiet() {
    }

    public int getHoaDonChiTiet_id() {
        return hoaDonChiTiet_id;
    }

    public void setHoaDonChiTiet_id(int hoaDonChiTiet_id) {
        this.hoaDonChiTiet_id = hoaDonChiTiet_id;
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
}
