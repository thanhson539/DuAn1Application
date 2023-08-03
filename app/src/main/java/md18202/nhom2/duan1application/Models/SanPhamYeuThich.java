package md18202.nhom2.duan1application.Models;

public class SanPhamYeuThich {
    private int sanPham_id;
    private int nguoiDung_id;
    private int trangThai;

    public SanPhamYeuThich(int sanPham_id, int nguoiDung_id) {
        this.sanPham_id = sanPham_id;
        this.nguoiDung_id = nguoiDung_id;
    }

    public SanPhamYeuThich() {
    }

    public int getSanPham_id() {
        return sanPham_id;
    }

    public void setSanPham_id(int sanPham_id) {
        this.sanPham_id = sanPham_id;
    }

    public int getNguoiDung_id() {
        return nguoiDung_id;
    }

    public void setNguoiDung_id(int nguoiDung_id) {
        this.nguoiDung_id = nguoiDung_id;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
