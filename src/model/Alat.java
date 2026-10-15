package model;

import java.io.Serializable;

public abstract class Alat implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String KodeAlat;
    protected String nama;
    protected int tahunperolehan;
    protected StatusAlat status = StatusAlat.TERSEDIA;

    public Alat(String kodeAlat, String nama, int tahunperolehan) {
        this.KodeAlat = kodeAlat;
        this.nama = nama;
        this.tahunperolehan = tahunperolehan;
    }

    public String deskripsi() {
        return KodeAlat + " - " + nama + " (" + tahunperolehan + ")";
    }

    public abstract boolean siapDipinjam();
    public abstract String jenis();

    public String getNama() { return nama; }
    public String getKodeAlat() { return KodeAlat; }
    public int getTahunPerolehan() { return tahunperolehan; }
    public StatusAlat getStatus() { return status; }
    public void setStatus(StatusAlat status) { this.status = status; }
}