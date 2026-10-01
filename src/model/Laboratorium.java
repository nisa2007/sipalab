package model;

import java.util.ArrayList;
import java.util.List;

public class Laboratorium {
    private final String kodeLab;
    private final String nama;

    // AGREGASI: alat dibuat di luar, Laboratorium hanya menampung
    private final List<Alat> koleksiAlat = new ArrayList<>();

    public Laboratorium(String kodeLab, String nama) {
        this.kodeLab = kodeLab;
        this.nama = nama;
    }

    public void tambahAlat(Alat alat) {
        koleksiAlat.add(alat);
    }

    public int jumlahAlat() {
        return koleksiAlat.size();
    }

    public List<Alat> getKoleksiAlat() { return koleksiAlat; }
    public String getKodeLab() { return kodeLab; }
    public String getNama() { return nama; }
}