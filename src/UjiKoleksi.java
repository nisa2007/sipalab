import java.util.*;

public class UjiKoleksi {
    public static void main(String[] args) {
        int jumlah = 100000;
        List<String> daftar = new ArrayList<>();
        Set<String> himpunan = new HashSet<>();

        for (int i = 0; i < jumlah; i++) {
            daftar.add("AL-" + i);
            himpunan.add("AL-" + i);
        }

        String dicari = "AL-" + (jumlah - 1);

        long mulai = System.nanoTime();
        daftar.contains(dicari);
        long waktuList = System.nanoTime() - mulai;

        mulai = System.nanoTime();
        himpunan.contains(dicari);
        long waktuSet = System.nanoTime() - mulai;

        System.out.println("ArrayList : " + waktuList + " nanodetik");
        System.out.println("HashSet   : " + waktuSet + " nanodetik");
    }
}