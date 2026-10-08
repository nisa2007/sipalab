package layanan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.Alat;
import model.Kalibrasiable;
import pembanding.UrutStatusLaluNama;

public class Inventarislab {

    // Map lebih tepat daripada List untuk pencarian berdasarkan kode:
    // List harus menelusuri elemen satu per satu (O(n)), sedangkan Map
    // menghitung letak data dari kuncinya sehingga langsung ketemu (rata-rata O(1)).
    // LinkedHashMap dipilih agar urutan masuk tetap terjaga.
    private final Map<String, Alat> daftarAlat = new LinkedHashMap<>();

    public void tambah(Alat alat) {
        daftarAlat.put(alat.getKodeAlat(), alat);
    }

    public List<Alat> semuaAlat() {
        return new ArrayList<>(daftarAlat.values());
    }

    public Alat cariKode(String kode) {
        return daftarAlat.get(kode);   // null bila tidak ada
    }

    public void cetakStatus() {
        for (Alat a : daftarAlat.values()) {
            System.out.println(a.getNama() + " " + a.siapDipinjam());
        }
    }

    public void cetakJadwalKalibrasi() {
        for (Alat a : daftarAlat.values()) {
            if (a instanceof Kalibrasiable k) {
                System.out.println(a.getNama() + " - jatuh tempo: " + k.jatuhTempoKalibrasi()
                        + " - perlu kalibrasi: " + k.perluKalibrasi());
            }
        }
    }

    public List<Alat> urut(Comparator<Alat> pembanding) {
        List<Alat> salinan = new ArrayList<>(daftarAlat.values());
        salinan.sort(pembanding);
        return salinan;
    }

    public List<Alat> urutBawaan() {
        return urut(new UrutStatusLaluNama());
    }

    public void hapusYangTidakSiap() {
        daftarAlat.values().removeIf(a -> !a.siapDipinjam());
    }

    public List<Alat> cari(String kata) {
        String k = kata.toLowerCase();
        List<Alat> hasil = new ArrayList<>();
        for (Alat a : daftarAlat.values()) {
            if (a.getNama().toLowerCase().contains(k)
                    || a.getKodeAlat().toLowerCase().contains(k)) {
                hasil.add(a);
            }
        }
        return hasil;
    }
}