package md18202.nhom2.duan1application.Model;

public class BinhLuan {
    private String nguoiDung_id;
    private String noiDung;
    private String thoiGian;

    public BinhLuan(String nguoiDung_id, String noiDung, String thoiGian) {
        this.nguoiDung_id = nguoiDung_id;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
    }

    public BinhLuan() {
    }

    public String getNguoiDung_id() {
        return nguoiDung_id;
    }

    public void setNguoiDung_id(String nguoiDung_id) {
        this.nguoiDung_id = nguoiDung_id;
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
