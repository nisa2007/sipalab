import java.time.LocalDate;
import java.util.Scanner;
import model.*;
import layanan.Inventarislab;
import layanan.LayananPeminjaman;
import layanan.ValidatorAlat;
import transaksi.peminjaman;
import transaksi.DetailPeminjaman;
import koleksi.Keranjang;
import pembanding.UrutNama;
import eksepsi.DataTidakValidException;
import eksepsi.SipalabException;

public class Main {

    public static void main(String[] args) {

        // Pakai constructor lengkap (dengan nomorSeri & lokasiTerakhir)
        Laptop lap = new Laptop("LP-002", "Lenovo ThinkPad", 2023,
                16, true, "SN-LP-002", "Lab RPL Lt.2");

        Proyektor pro = new Proyektor("PJ-003", "Epson EB-X51", 2022,
                3300, 100, "SN-PJ-003", "Ruang Multimedia");

        AlatUkur ukur = new AlatUkur("AU-004", "Multimeter Fluke", 2020,
                "volt", LocalDate.of(2024, 3, 10));

        System.out.println(lap.jenis() + " | " + lap.deskripsi() + " | siap: " + lap.siapDipinjam());
        System.out.println(lap.ringkasanLacak());
        System.out.println(lap.infoPemesanan());
        System.out.println("Bisa dipesan untuk besok? " + lap.bisaDipesanUntuk(LocalDate.now().plusDays(1)));
        System.out.println("Bisa dipesan untuk minggu depan? " + lap.bisaDipesanUntuk(LocalDate.now().plusDays(7)));

        System.out.println();
        System.out.println(pro.jenis() + " | " + pro.deskripsi() + " | siap: " + pro.siapDipinjam());
        System.out.println(pro.ringkasanLacak());
        System.out.println(pro.infoPemesanan());
        System.out.println("Bisa dipesan untuk 5 hari lagi? " + pro.bisaDipesanUntuk(LocalDate.now().plusDays(5)));

        System.out.println();
        System.out.println(ukur.jenis() + " | " + ukur.deskripsi() + " | siap: " + ukur.siapDipinjam());
        System.out.println(ukur.statusKalibrasi());

        // ===== Uji Komponen + cetakStatus tanpa edit method =====
        System.out.println();
        System.out.println("=== Uji Inventaris dengan Komponen baru ===");

        Komponen kabel = new Komponen("KP-005", "Kabel HDMI", 2023, 10, 3);
        Komponen resistor = new Komponen("KP-006", "Resistor 220 Ohm", 2022, 2, 5);

        Inventarislab inventaris = new Inventarislab();
        inventaris.tambah(lap);
        inventaris.tambah(pro);
        inventaris.tambah(ukur);
        inventaris.tambah(kabel);
        inventaris.tambah(resistor);

        // Method ini TIDAK diubah sama sekali meski ada kelas baru (Komponen)
        // -> bukti polymorphism: 0 baris disunting pada method pemroses
        inventaris.cetakStatus();

        // ===== Uji relasi Asosiasi, Agregasi, Komposisi =====
        System.out.println();
        System.out.println("=== Uji Relasi: Asosiasi, Agregasi, Komposisi ===");

        Mahasiswa mhs = new Mahasiswa("2205062001", "Rani Simatupang", "TRPL");
        Petugas ptg = new Petugas("198701012015041002", "Bapak Sutrisno");

        // AGREGASI: lap dan pro sudah ada duluan, Laboratorium cuma menitipkan referensinya
        // (bukan membuat objek Alat sendiri) -> alat tetap ada walau lab dibubarkan
        Laboratorium lab = new Laboratorium("LAB-RPL", "Laboratorium Rekayasa PL");
        lab.tambahAlat(lap);
        lab.tambahAlat(pro);
        System.out.println("Jumlah alat di lab " + lab.getNama() + ": " + lab.jumlahAlat());

        // ASOSIASI: mhs dan ptg dibuat di luar lalu diserahkan lewat konstruktor
        // -> keduanya tetap ada meski objek Peminjaman ini dihapus
        peminjaman pinjam = new peminjaman("PJM-0001", mhs, ptg);

        // KOMPOSISI: DetailPeminjaman dibuat dengan "new" DI DALAM tambahDetail()
        // -> detail tidak bermakna apa-apa tanpa Peminjaman induknya
        pinjam.tambahDetail(lap, 1);
        pinjam.tambahDetail(pro, 2);

        System.out.println("Peminjam: " + mhs.getNama() + " (" + mhs.getNim() + ")");
        System.out.println("Disetujui oleh: " + ptg.getNama());
        System.out.println("Total item dipinjam: " + pinjam.totalItem());

        for (DetailPeminjaman d : pinjam.getDetail()) {
            System.out.println("  " + d.baris());
        }

        // ===== JOB SHEET 5 =====
        System.out.println();
        System.out.println("=== Uji Keranjang generic ===");
        Keranjang<Alat> keranjang = new Keranjang<>(3);
        keranjang.tambah(lap);
        keranjang.tambah(pro);
        System.out.println("Isi: " + keranjang.ukuran() + ", sisa ruang: " + keranjang.sisaRuang());
        for (Alat a : keranjang) {
            System.out.println("  " + a.getNama());
        }
        // keranjang.tambah("Bukan alat");   // hapus // untuk melihat error kompilasi, catat, lalu beri // lagi

        System.out.println();
        System.out.println("=== Uji Comparator ===");
        System.out.println("-- Urut nama --");
        for (Alat a : inventaris.urut(new UrutNama())) {
            System.out.println("  " + a.getNama() + " | siap: " + a.siapDipinjam());
        }
        System.out.println("-- Urut status lalu nama --");
        for (Alat a : inventaris.urutBawaan()) {
            System.out.println("  " + a.getNama() + " | siap: " + a.siapDipinjam());
        }

        System.out.println();
        System.out.println("=== Uji pencarian dan penyaringan ===");
        System.out.println("-- Cari 'kabel' --");
        for (Alat a : inventaris.cari("kabel")) {
            System.out.println("  " + a.getNama());
        }
        inventaris.hapusYangTidakSiap();
        System.out.println("-- Setelah hapusYangTidakSiap --");
        for (Alat a : inventaris.semuaAlat()) {
            System.out.println("  " + a.getNama());
        }

        // ===== JOB SHEET 6 =====
        System.out.println();
        System.out.println("=== Uji Aturan Peminjaman ===");
        LayananPeminjaman layananPinjam = new LayananPeminjaman();
        Laptop laptopTanpaCharger = new Laptop("LP-009", "Laptop Tanpa Charger", 2021, 8, false);

        cobaPinjam(layananPinjam, mhs, ptg, laptopTanpaCharger); // belum siap
        cobaPinjam(layananPinjam, mhs, ptg, resistor);           // stok di bawah minimum
        cobaPinjam(layananPinjam, mhs, ptg, lap);                // berhasil
        cobaPinjam(layananPinjam, mhs, ptg, lap);                // sedang dipinjam
        // dua peminjaman lagi sampai kuota 3 alat penuh, lalu satu ditolak
        cobaPinjam(layananPinjam, mhs, ptg, pro);
        cobaPinjam(layananPinjam, mhs, ptg, kabel);
        cobaPinjam(layananPinjam, mhs, ptg,
                new Komponen("KP-008", "Kabel LAN 3 meter", 2023, 10, 3));

        System.out.println();
        System.out.println("=== Uji Validasi Data Alat ===");
        ValidatorAlat validator = new ValidatorAlat();
        try {
            validator.periksa(lap);
            System.out.println("  Data " + lap.getNama() + " valid.");
            validator.periksa(new Komponen("KP-009", "Kabel Lama", 1980, 5, 1));
            System.out.println("  Data valid.");
        } catch (DataTidakValidException e) {
            System.out.println("  Kolom " + e.getNamaKolom() + " ditolak: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== Uji Status Alat dan Pengembalian ===");
        Mahasiswa mhs2 = new Mahasiswa("2205062002", "Budi Santoso", "TRPL");
        Laptop cadangan = new Laptop("LP-010", "Laptop Cadangan", 2024, 16, true);
        System.out.println("  Status awal      : " + cadangan.getStatus().getLabel());
        try {
            peminjaman p = layananPinjam.pinjam(mhs2, ptg, cadangan, 1);
            System.out.println("  Setelah dipinjam : " + cadangan.getStatus().getLabel());
            p.kembalikan();
            System.out.println("  Setelah kembali  : " + cadangan.getStatus().getLabel()
                    + " (denda Rp" + p.hitungDenda()+ ")");
            cadangan.setStatus(StatusAlat.DALAM_PERBAIKAN);
            layananPinjam.pinjam(mhs2, ptg, cadangan, 1);   // ditolak: sedang diperbaiki
        } catch (SipalabException e) {
            System.out.println("  DITOLAK " + e.getMessage());
        }

        try {
            peminjaman p2 = layananPinjam.pinjam(mhs2, ptg,
                    new Laptop("LP-011", "Laptop Cadangan 2", 2024, 16, true), 1);
            p2.kembalikan();
            System.out.println("  Pengembalian pertama berhasil.");
            p2.kembalikan();                                // ditolak: sudah ditutup
        } catch (SipalabException e) {
            System.out.println("  DITOLAK " + e.getMessage());
        }

        System.out.println();
        // ujiRapuh();   // E.1: aktifkan sendiri, ketik huruf, catat pesannya, lalu beri // lagi
        ujiTangguh();
    }

    private static void cobaPinjam(LayananPeminjaman layanan, Mahasiswa mhs,
                                   Petugas ptg, Alat alat) {
        try {
            peminjaman p = layanan.pinjam(mhs, ptg, alat, 1);
            System.out.println("  BERHASIL " + p.getNomorPeminjaman());
        } catch (SipalabException e) {
            System.out.println("  DITOLAK " + e.getMessage());
        }
    }

    // E.1: versi rapuh, sengaja tanpa penanganan apa pun
    public static void ujiRapuh() {
        Scanner masukan = new Scanner(System.in);
        System.out.print("Masukkan tahun perolehan: ");
        int tahun = Integer.parseInt(masukan.nextLine());
        System.out.println("Tahun tercatat: " + tahun);
    }

    // E.4: versi tangguh
    public static void ujiTangguh() {
        Scanner masukan = new Scanner(System.in);
        System.out.print("Masukkan tahun perolehan: ");
        try {
            int tahun = Integer.parseInt(masukan.nextLine());
            System.out.println("Tahun tercatat: " + tahun);
        } catch (NumberFormatException e) {
            System.out.println("Tahun harus berupa angka, contoh 2024.");
        }
    }
}