package transaksi;

import model.Alat;

public class DetailPeminjaman {
    // ASOSIASI: alat sudah ada sebelumnya, hanya dirujuk di sini
    private final Alat alat;
    private final int jumlah;

    public DetailPeminjaman(Alat alat, int jumlah) {
        this.alat = alat;
        this.jumlah = jumlah;
    }

    public Alat getAlat() { return alat; }
    public int getJumlah() { return jumlah; }

    public String baris() {
        return jumlah + " x " + alat.getNama();
    }
}