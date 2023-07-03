package md18202.nhom2.duan1application.Model;

public class HoaDonChiTiet {
    private String hoaDonChiTiet_id;
    private String hoaDon_id;
    private String sanPham_id;
    private int trangThaiDonHang;
    private int trangThaiThanhToan;

    public HoaDonChiTiet(String hoaDonChiTiet_id, String hoaDon_id, String sanPham_id, int trangThaiDonHang, int trangThaiThanhToan) {
        this.hoaDonChiTiet_id = hoaDonChiTiet_id;
        this.hoaDon_id = hoaDon_id;
        this.sanPham_id = sanPham_id;
        this.trangThaiDonHang = trangThaiDonHang;
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public HoaDonChiTiet() {
    }

    public String getHoaDonChiTiet_id() {
        return hoaDonChiTiet_id;
    }

    public void setHoaDonChiTiet_id(String hoaDonChiTiet_id) {
        this.hoaDonChiTiet_id = hoaDonChiTiet_id;
    }

    public String getHoaDon_id() {
        return hoaDon_id;
    }

    public void setHoaDon_id(String hoaDon_id) {
        this.hoaDon_id = hoaDon_id;
    }

    public String getSanPham_id() {
        return sanPham_id;
    }

    public void setSanPham_id(String sanPham_id) {
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
