package md18202.nhom2.duan1application.Models;

public class BinhLuan {
    private int binhLuan_id;
    private int nguoiDung_id;
    private int sanPham_id;
    private String noiDung;
    private String thoiGian;
    private String nguoiDung;

    private String anhSanPham;
    private String tenSanPham;
    private String tenNguoiDung;
    private int tongBinhLuan;

    public String getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(String nguoiDung) {
        this.nguoiDung = nguoiDung;
    }


    public BinhLuan(int binhLuan_id, int nguoiDung_id, int sanPham_id, String noiDung, String thoiGian, String nguoiDung) {
        this.binhLuan_id = binhLuan_id;
        this.nguoiDung_id = nguoiDung_id;
        this.sanPham_id = sanPham_id;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
        this.nguoiDung = nguoiDung;
    }

    public BinhLuan(int binhLuan_id, int nguoiDung_id, int sanPham_id, String noiDung, String thoiGian) {
        this.binhLuan_id = binhLuan_id;
        this.nguoiDung_id = nguoiDung_id;
        this.sanPham_id = sanPham_id;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
    }

    //Lay toan bo danh sach binh luan theo binhLLuan_id
    public BinhLuan(int binhLuan_id, int nguoiDung_id, int sanPham_id, String noiDung, String thoiGian, String anhSanPham, String tenSanPham, String tenNguoiDung) {
        this.binhLuan_id = binhLuan_id;
        this.nguoiDung_id = nguoiDung_id;
        this.sanPham_id = sanPham_id;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
        this.anhSanPham = anhSanPham;
        this.tenSanPham = tenSanPham;
        this.tenNguoiDung = tenNguoiDung;
    }

    //Lay danh binh luan nhóm theo sapPham_id
    public BinhLuan(int binhLuan_id, int nguoiDung_id, int sanPham_id, String anhSanPham, String tenSanPham, int tongBinhLuan) {
        this.binhLuan_id = binhLuan_id;
        this.nguoiDung_id = nguoiDung_id;
        this.sanPham_id = sanPham_id;
        this.anhSanPham = anhSanPham;
        this.tenSanPham = tenSanPham;
        this.tongBinhLuan = tongBinhLuan;
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

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public int getTongBinhLuan() {
        return tongBinhLuan;
    }

    public void setTongBinhLuan(int tongBinhLuan) {
        this.tongBinhLuan = tongBinhLuan;
    }

    public BinhLuan() {
    }

    public int getBinhLuan_id() {
        return binhLuan_id;
    }

    public void setBinhLuan_id(int binhLuan_id) {
        this.binhLuan_id = binhLuan_id;
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

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
}
