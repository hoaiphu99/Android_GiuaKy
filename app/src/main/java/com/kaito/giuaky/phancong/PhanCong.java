package com.kaito.giuaky.phancong;

public class PhanCong {

    private String soPhieu;
    private String maXe;
    private String maTuyen;
    private String ngay;
    private String xuatPhat;

    public PhanCong() {

    }

    public PhanCong(String soPhieu, String maXe, String maTuyen, String ngay, String xuatPhat) {
        this.soPhieu = soPhieu;
        this.maXe = maXe;
        this.maTuyen = maTuyen;
        this.ngay = ngay;
        this.xuatPhat = xuatPhat;
    }

    public String getSoPhieu() {
        return soPhieu;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(String maTuyen) {
        this.maTuyen = maTuyen;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getXuatPhat() {
        return xuatPhat;
    }

    public void setXuatPhat(String xuatPhat) {
        this.xuatPhat = xuatPhat;
    }
}
