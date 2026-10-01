/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class Petugas {
    private final String nip;
    private final String nama;

    public Petugas(String nip, String nama) {
        this.nip = nip;
        this.nama = nama;
    }

    public String getNip() { return nip; }
    public String getNama() { return nama; }
}
