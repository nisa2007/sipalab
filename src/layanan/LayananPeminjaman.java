package layanan;

import eksepsi.AlatBelumSiapException;
import eksepsi.AlatSedangDipinjamException;
import eksepsi.KuotaPeminjamanHabisException;
import java.util.ArrayList;
import java.util.List;
import model.Alat;
import model.Mahasiswa;
import model.Petugas;
import model.StatusAlat;
import transaksi.peminjaman;

public class LayananPeminjaman {

    private final List<peminjaman> riwayat = new ArrayList<>();
    private int nomorUrut = 1;

    public peminjaman pinjam(Mahasiswa mhs, Petugas ptg, Alat alat, int jumlah)
            throws AlatSedangDipinjamException,
                   KuotaPeminjamanHabisException,
                   AlatBelumSiapException {

        // seluruh syarat diperiksa lebih dulu, sebelum apa pun diubah (fail fast)
        if (alat.getStatus().sedangDiluar()) {
            throw new AlatSedangDipinjamException(alat.getKodeAlat());
        }
        if (!alat.getStatus().bolehDipinjam()) {
            throw new AlatBelumSiapException(alat.getKodeAlat(),
                    "Status alat saat ini: " + alat.getStatus().getLabel() + ".");
        }
        if (!alat.siapDipinjam()) {
            throw new AlatBelumSiapException(alat.getKodeAlat(),
                    "Periksa kondisi alat.");
        }
        if (hitungPinjamanAktif(mhs) >= Mahasiswa.KUOTA_PEMINJAMAN) {
            throw new KuotaPeminjamanHabisException(mhs.getNama(),
                    Mahasiswa.KUOTA_PEMINJAMAN);
        }

        // baris di bawah hanya dijalankan bila semua syarat lolos
        String nomor = String.format("PJM-%04d", nomorUrut++);
        peminjaman p = new peminjaman(nomor, mhs, ptg);
        p.tambahDetail(alat, jumlah);
        alat.setStatus(StatusAlat.DIPINJAM);
        riwayat.add(p);
        return p;
    }

    public int hitungPinjamanAktif(Mahasiswa mhs) {
        int n = 0;
        for (peminjaman p : riwayat) {
            if (!p.sudahKembali()
                    && p.getPeminjam().getNim().equals(mhs.getNim())) {
                n += p.totalItem();
            }
        }
        return n;
    }
}