/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public interface Terlacak {
    String nomorSeri();
    String lokasiTerakhir();
    
    default String ringkasanLacak() {
        return "Nomor Seri " + nomorSeri() + " terakhir berada di " + lokasiTerakhir();
    }
}
