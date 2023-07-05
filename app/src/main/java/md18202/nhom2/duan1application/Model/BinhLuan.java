package md18202.nhom2.duan1application.Model;

public class BinhLuan {
    private int binhLuan_id;
    private int nguoiDung_id;
    private String noiDung;
    private String thoiGian;

    public BinhLuan(int binhLuan_id, int nguoiDung_id, String noiDung, String thoiGian) {
        this.binhLuan_id = binhLuan_id;
        this.nguoiDung_id = nguoiDung_id;
        this.noiDung = noiDung;
        this.thoiGian = thoiGian;
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
