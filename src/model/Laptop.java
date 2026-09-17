package model;
import java.time.LocalDate;
/**
 * @author indi
 */


public class Laptop extends Alat implements Terlacak, Dipesan {

    private static final long serialVersionUID = 1L;
     private static final int MIN_HARI_PESAN = 2;

    private int ramGB;
    private boolean chargerLengkap;
    private String nomorSeri;
    private String lokasiTerakhir;

    public Laptop(String kodeAlat, String nama, int tahun, int ramGB, boolean charger) {
        this(kodeAlat, nama, tahun, ramGB, charger, "-", "Lab RPL");
    }

    public Laptop(String kodeAlat, String nama, int tahun, int ramGB, boolean charger,
                  String nomorSeri, String lokasiTerakhir) {
        super(kodeAlat, nama, tahun);
        this.ramGB = ramGB;
        this.chargerLengkap = charger;
        this.nomorSeri = nomorSeri;
        this.lokasiTerakhir = lokasiTerakhir;
    }

    @Override public String deskripsi() { return super.deskripsi() + " RAM " + ramGB + " GB"; }
    @Override public boolean siapDipinjam() { return chargerLengkap; }
    @Override public String jenis() { return "Laptop"; }
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
    public int getRamGB() { return ramGB; }
    public boolean isChargerLengkap() { return chargerLengkap; }
    public void setChargerLengkap(boolean v) { this.chargerLengkap = v; }
    public void setLokasiTerakhir(String l) { this.lokasiTerakhir = l; }
} 