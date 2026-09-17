/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;
import java.time.LocalDate;
/**
 *
 * @author LENOVO
 */
public interface Dipesan {
    
///** Mengembalikan jumlah hari minimal pemesanan sebelum tanggal pakai. */
    int minimalHariPemesanan();

//    /** Mengecek apakah tanggal pemakaian yang diminta masih memenuhi syarat. */
    boolean bisaDipesanUntuk(LocalDate tanggalPakai);

//    /** Default method: menghasilkan informasi aturan pemesanan alat. */
    default String infoPemesanan() {
        return "Alat ini harus dipesan minimal " + minimalHariPemesanan()
                + " hari sebelum tanggal pemakaian.";
    }
}
