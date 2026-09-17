package model;

public abstract class Alat {
    protected String KodeAlat;
    protected String nama;
    protected int tahunperolehan;

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

    public String getNama() {
        return nama;
    }
}