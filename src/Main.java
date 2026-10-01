import java.time.LocalDate;
import model.*;
import layanan.Inventarislab;
import transaksi.peminjaman;
import transaksi.DetailPeminjaman;

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
        
        // ===== BAGIAN BARU: uji Komponen + cetakStatus tanpa edit method =====
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

        // ===== BAGIAN BARU: uji relasi Asosiasi, Agregasi, Komposisi =====
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
    }
}