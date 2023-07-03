package md18202.nhom2.duan1application.Model;

public class HoaDon {
    private String hoaDon_id;
    private String nguoiDung_id;
    private String thoiGian;
    private int tongTien;

    public HoaDon(String hoaDon_id, String nguoiDung_id, String thoiGian, int tongTien) {
        this.hoaDon_id = hoaDon_id;
        this.nguoiDung_id = nguoiDung_id;
        this.thoiGian = thoiGian;
        this.tongTien = tongTien;
    }

    public HoaDon() {
    }

    public String getHoaDon_id() {
        return hoaDon_id;
    }

    public void setHoaDon_id(String hoaDon_id) {
        this.hoaDon_id = hoaDon_id;
    }

    public String getNguoiDung_id() {
        return nguoiDung_id;
    }

    public void setNguoiDung_id(String nguoiDung_id) {
        this.nguoiDung_id = nguoiDung_id;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
