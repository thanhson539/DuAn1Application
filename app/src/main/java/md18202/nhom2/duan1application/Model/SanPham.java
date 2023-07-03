package md18202.nhom2.duan1application.Model;

public class SanPham {
    private String sanPham_id;
    private String loaiSanPham_id;
    private String tenSanPham;
    private String anhSanPham;
    private int giaSanPham;
    private String moTa;
    private int soLuongConLai;

    public SanPham(String sanPham_id, String loaiSanPham_id, String tenSanPham, String anhSanPham, int giaSanPham, String moTa, int soLuongConLai) {
        this.sanPham_id = sanPham_id;
        this.loaiSanPham_id = loaiSanPham_id;
        this.tenSanPham = tenSanPham;
        this.anhSanPham = anhSanPham;
        this.giaSanPham = giaSanPham;
        this.moTa = moTa;
        this.soLuongConLai = soLuongConLai;
    }

    public SanPham() {
    }

    public String getSanPham_id() {
        return sanPham_id;
    }

    public void setSanPham_id(String sanPham_id) {
        this.sanPham_id = sanPham_id;
    }

    public String getLoaiSanPham_id() {
        return loaiSanPham_id;
    }

    public void setLoaiSanPham_id(String loaiSanPham_id) {
        this.loaiSanPham_id = loaiSanPham_id;
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

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuongConLai() {
        return soLuongConLai;
    }

    public void setSoLuongConLai(int soLuongConLai) {
        this.soLuongConLai = soLuongConLai;
    }
}
