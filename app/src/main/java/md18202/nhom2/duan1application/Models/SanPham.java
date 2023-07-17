package md18202.nhom2.duan1application.Models;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int sanPham_id;
    private int loaiSanPham_id;
    private   String tenloaisanpham;
    private String tenSanPham;
    private String anhSanPham;
    private int giaSanPham;
    private String moTa;
    private int soLuongConLai;
    private int isYeuThich;


    public SanPham(int sanPham_id, String tenloaisanpham, String tenSanPham, String anhSanPham, int giaSanPham, String moTa, int soLuongConLai) {
        this.sanPham_id = sanPham_id;
        this.tenloaisanpham = tenloaisanpham;
        this.tenSanPham = tenSanPham;
        this.anhSanPham = anhSanPham;
        this.giaSanPham = giaSanPham;
        this.moTa = moTa;
        this.soLuongConLai = soLuongConLai;
    }

    public SanPham(int sanPham_id, int loaiSanPham_id, String tenSanPham, String anhSanPham, int giaSanPham, String moTa, int soLuongConLai) {
        this.sanPham_id = sanPham_id;
        this.loaiSanPham_id = loaiSanPham_id;
        this.tenSanPham = tenSanPham;
        this.anhSanPham = anhSanPham;
        this.giaSanPham = giaSanPham;
        this.moTa = moTa;
        this.soLuongConLai = soLuongConLai;
    }

    //Contructor cho chuc nang lay san pham yeu thich
    public SanPham(int sanPham_id, int loaiSanPham_id, String tenSanPham, String anhSanPham, int giaSanPham, String moTa, int soLuongConLai, int isYeuThich) {
        this.sanPham_id = sanPham_id;
        this.loaiSanPham_id = loaiSanPham_id;
        this.tenSanPham = tenSanPham;
        this.anhSanPham = anhSanPham;
        this.giaSanPham = giaSanPham;
        this.moTa = moTa;
        this.soLuongConLai = soLuongConLai;
        this.isYeuThich = isYeuThich;
    }

    public SanPham() {
    }

    public int getIsYeuThich() {
        return isYeuThich;
    }

    public void setIsYeuThich(int isYeuThich) {
        this.isYeuThich = isYeuThich;
    }

    public int getSanPham_id() {
        return sanPham_id;
    }

    public void setSanPham_id(int sanPham_id) {
        this.sanPham_id = sanPham_id;
    }

    public int getLoaiSanPham_id() {
        return loaiSanPham_id;
    }

    public void setLoaiSanPham_id(int loaiSanPham_id) {
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


    public String getTenloaisanpham() {
        return tenloaisanpham;
    }

    public void setTenloaisanpham(String tenloaisanpham) {
        this.tenloaisanpham = tenloaisanpham;
    }
}
