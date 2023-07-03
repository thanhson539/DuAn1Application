package md18202.nhom2.duan1application.Model;

public class NguoiDung {
    private String nguoiDung_id;
    private String hoTen;
    private String soDienThoai;
    private String email;
    private String taiKhoan;
    private String matKhau;
    private int loaiTaiKhoan;

    public NguoiDung(String nguoiDung_id, String hoTen, String soDienThoai, String email, String taiKhoan, String matKhau, int loaiTaiKhoan) {
        this.nguoiDung_id = nguoiDung_id;
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public NguoiDung() {
    }

    public String getNguoiDung_id() {
        return nguoiDung_id;
    }

    public void setNguoiDung_id(String nguoiDung_id) {
        this.nguoiDung_id = nguoiDung_id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(int loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
}
