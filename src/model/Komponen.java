/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class Komponen extends Alat{
    private int jumlahStok;
    private int jumlahMinimum;
    
    public Komponen(String kodeAlat, String nama, int tahunPerolehan,
                     int jumlahStok, int jumlahMinimum) {
        super(kodeAlat, nama, tahunPerolehan);
        this.jumlahStok = jumlahStok;
        this.jumlahMinimum = jumlahMinimum;
    }
     public boolean siapDipinjam() {
        return jumlahStok > jumlahMinimum;
    }

    @Override
    public String jenis() {
        return "Komponen";
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + " Stok " + jumlahStok + "/" + jumlahMinimum;
    }

    public int getJumlahStok() {
        return jumlahStok;
    }

    public int getJumlahMinimum() {
        return jumlahMinimum;
    }

    public void setJumlahStok(int jumlahStok) {
        this.jumlahStok = jumlahStok;
    }
}
