package transaksi;

import eksepsi.PeminjamanSudahDitutupException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.Alat;
import model.Mahasiswa;
import model.Petugas;
import model.StatusAlat;

public class peminjaman {

    private static final long TARIF_DENDA_PER_HARI = 2000;

    private final String nomorPeminjaman;
    private final LocalDate tanggalPinjam;
    private LocalDate tanggalKembali;

    // ASOSIASI: mahasiswa dan petugas hidup mandiri, hanya dirujuk
    private final Mahasiswa peminjam;
    private final Petugas penyetuju;

    // KOMPOSISI: detail dibuat di dalam kelas ini dan ikut lenyap bersamanya
    private final List<DetailPeminjaman> detail = new ArrayList<>();

    public peminjaman(String nomorPeminjaman, Mahasiswa peminjam, Petugas penyetuju) {
        this.nomorPeminjaman = nomorPeminjaman;
        this.peminjam = peminjam;
        this.penyetuju = penyetuju;
        this.tanggalPinjam = LocalDate.now();
    }

    public void tambahDetail(Alat alat, int jumlah) {
        detail.add(new DetailPeminjaman(alat, jumlah));
    }

    public List<DetailPeminjaman> getDetail() {
        return Collections.unmodifiableList(detail);
    }

    public int totalItem() {
        int total = 0;
        for (DetailPeminjaman d : detail) {
            total += d.getJumlah();
        }
        return total;
    }

    public boolean sudahKembali() {
        return tanggalKembali != null;
    }

    // pemanggilan kedua ditolak dan menyebut nomor serta tanggal pengembalian pertama
    public void kembalikan() throws PeminjamanSudahDitutupException {
        if (sudahKembali()) {
            throw new PeminjamanSudahDitutupException(nomorPeminjaman, tanggalKembali);
        }
        this.tanggalKembali = LocalDate.now();
        for (DetailPeminjaman d : detail) {
            d.getAlat().setStatus(StatusAlat.TERSEDIA);
        }
    }

    public long hitungHariTerlambat() {
        if (tanggalKembali == null) {
            return 0;
        }
        long selisihHari = ChronoUnit.DAYS.between(tanggalPinjam, tanggalKembali);
        return selisihHari > 7 ? selisihHari - 7 : 0;
    }

    // tarif dua ribu rupiah per hari keterlambatan
    public long hitungDenda() {
        return hitungHariTerlambat() * TARIF_DENDA_PER_HARI;
    }

    public String getNomorPeminjaman() { return nomorPeminjaman; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public Mahasiswa getPeminjam() { return peminjam; }
    public Petugas getPenyetuju() { return penyetuju; }
}