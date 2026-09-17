import java.time.LocalDate;
import model.*;

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
    }
}