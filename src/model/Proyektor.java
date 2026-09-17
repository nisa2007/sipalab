package model;
import java.time.LocalDate;

/**
 * @author indi
 */

// Gemini AI: memberikan informasi varible


public class Proyektor extends Alat implements Terlacak, Dipesan {

    private static final long serialVersionUID = 1L;
    public static final int BATAS_JAM_LAMPU = 2000;
    private static final int MIN_HARI_PESAN = 3;
    private int lumen;
    private int jamPakaiLampu;
    private String nomorSeri;
    private String lokasiTerakhir;

    public Proyektor(String kodeAlat, String nama, int tahun, int lumen, int jamLampu) {
        this(kodeAlat, nama, tahun, lumen, jamLampu, "-", "Lab RPL");
    }

    public Proyektor(String kodeAlat, String nama, int tahun, int lumen, int jamLampu,
                      String nomorSeri, String lokasiTerakhir) {
        super(kodeAlat, nama, tahun);
        this.lumen = lumen;
        this.jamPakaiLampu = jamLampu;
        this.nomorSeri = nomorSeri;
        this.lokasiTerakhir = lokasiTerakhir;
    }

    @Override public String deskripsi() { return super.deskripsi() + " " + lumen + " lumen"; }
    @Override public boolean siapDipinjam() {
    return jamPakaiLampu <= BATAS_JAM_LAMPU;

    }
    @Override public String jenis() { return "Proyektor"; }
    @Override public String nomorSeri() { return nomorSeri; }
    @Override public String lokasiTerakhir() { return lokasiTerakhir; }
    @Override
    public int minimalHariPemesanan() {
        return MIN_HARI_PESAN;
    }

    @Override
    public boolean bisaDipesanUntuk(LocalDate tanggalPakai) {
        return siapDipinjam()
                && !tanggalPakai.isBefore(LocalDate.now().plusDays(MIN_HARI_PESAN));
    }
    public int getLumen() { return lumen; }
    public int getJamPakaiLampu() { return jamPakaiLampu; }
    public void tambahJamPakai(int jam) { this.jamPakaiLampu += jam; }
}