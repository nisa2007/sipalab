package berkas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import model.Alat;

public class EksporLaporan {

    // E.2: tiga lapis pembungkus: FileWriter (ke cakram), BufferedWriter (penyangga),
    // PrintWriter (println dan printf). UTF-8 supaya karakter Indonesia tidak rusak.
    public void tulisInventaris(String namaBerkas, List<Alat> daftar) throws IOException {
        try (PrintWriter tulis = new PrintWriter(
                new BufferedWriter(new FileWriter(namaBerkas, StandardCharsets.UTF_8)))) {

            tulis.println("LAPORAN INVENTARIS ALAT LABORATORIUM");
            tulis.println("Tanggal cetak: " + LocalDate.now());
            tulis.println();
            tulis.printf("%-10s %-26s %-14s %-14s%n",
                    "KODE", "NAMA", "JENIS", "STATUS");
            tulis.println("-".repeat(68));
            for (Alat a : daftar) {
                tulis.printf("%-10s %-26s %-14s %-14s%n",
                        a.getKodeAlat(), a.getNama(), a.jenis(),
                        a.getStatus().getLabel());
            }
            tulis.println();
            tulis.println("Jumlah alat: " + daftar.size());

            // PrintWriter tidak melempar IOException sendiri, jadi dicek manual
            if (tulis.checkError()) {
                throw new IOException("Gagal menulis berkas " + namaBerkas);
            }
        }
    }

    // F.1 sampai F.3: CSV dipilih karena formatnya teks datar yang dibaca Excel/Numbers
    // langsung dan tidak bergantung pada struktur kelas Java. Serialisasi menghasilkan
    // biner yang hanya bisa dibuka oleh program Java dengan kelas yang sama persis.
    public void tulisCSV(String namaBerkas, List<Alat> daftar) throws IOException {
        try (PrintWriter tulis = new PrintWriter(
                new BufferedWriter(new FileWriter(namaBerkas, StandardCharsets.UTF_8)))) {

            tulis.print('\uFEFF');  // penanda UTF-8 agar Excel membaca huruf dengan benar
            tulis.println("Kode,Nama,Jenis,Tahun,Status,Siap Dipinjam");
            for (Alat a : daftar) {
                tulis.println(String.join(",",
                        kolomCSV(a.getKodeAlat()),
                        kolomCSV(a.getNama()),
                        kolomCSV(a.jenis()),
                        String.valueOf(a.getTahunPerolehan()),
                        kolomCSV(a.getStatus().getLabel()),
                        a.siapDipinjam() ? "Ya" : "Tidak"));
            }

            if (tulis.checkError()) {
                throw new IOException("Gagal menulis berkas " + namaBerkas);
            }
        }
    }

    // F.3: teks yang mengandung koma, tanda kutip, atau baris baru diapit tanda kutip;
    // tanda kutip di dalamnya digandakan sesuai aturan CSV
    private String kolomCSV(String teks) {
        if (teks.contains(",") || teks.contains("\"") || teks.contains("\n")) {
            return "\"" + teks.replace("\"", "\"\"") + "\"";
        }
        return teks;
    }
}