package md18202.nhom2.duan1application.Models;

public class ThongBao {
    private int thongBao_id;
    private int nguoiDung_id;
    private int sanPham_id;
    private String tieuDe;
    private String noiDung;
    private String thoiGian;
    private int loaiThongBao;
    private int isRead;
    private String anhSanPham;
    public ThongBao() {
    }

    public ThongBao(int thongBao_id, int nguoiDung_id, int sanPham_id, String tieuDe, String noiDung, String thoiGian, int loaiThongBao, int isRead) {
        this.thongBao_id = thongBao_id;
        this.nguoiDung_id = nguoiDung_id;
        this.sanPham_id = sanPham_id;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
        this.loaiThongBao = loaiThongBao;
        this.isRead = isRead;
    }

    public ThongBao(int thongBao_id, int nguoiDung_id, int sanPham_id, String tieuDe, String noiDung, String thoiGian, int loaiThongBao, int isRead, String anhSanPham) {
        this.thongBao_id = thongBao_id;
        this.nguoiDung_id = nguoiDung_id;
        this.sanPham_id = sanPham_id;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
        this.loaiThongBao = loaiThongBao;
        this.isRead = isRead;
        this.anhSanPham = anhSanPham;
    }

    public ThongBao(int nguoiDung_id, int sanPham_id, String tieuDe, String noiDung, String thoiGian, int loaiThongBao, int isRead, String anhSanPham) {
        this.nguoiDung_id = nguoiDung_id;
        this.sanPham_id = sanPham_id;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
        this.loaiThongBao = loaiThongBao;
        this.isRead = isRead;
        this.anhSanPham = anhSanPham;
    }

    public String getAnhSanPham() {
        return anhSanPham;
    }

    public void setAnhSanPham(String anhSanPham) {
        this.anhSanPham = anhSanPham;
    }

    public int getSanPham_id() {
        return sanPham_id;
    }

    public void setSanPham_id(int sanPham_id) {
        this.sanPham_id = sanPham_id;
    }

    public int getThongBao_id() {
        return thongBao_id;
    }

    public void setThongBao_id(int thongBao_id) {
        this.thongBao_id = thongBao_id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
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

    public int getLoaiThongBao() {
        return loaiThongBao;
    }

    public void setLoaiThongBao(int loaiThongBao) {
        this.loaiThongBao = loaiThongBao;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getNguoiDung_id() {
        return nguoiDung_id;
    }

    public void setNguoiDung_id(int nguoiDung_id) {
        this.nguoiDung_id = nguoiDung_id;
    }
}
