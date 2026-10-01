/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class Mahasiswa {
    private final String nim;
    private final String nama;
    private final String programStudi;

    public Mahasiswa(String nim, String nama, String programStudi) {
        this.nim = nim;
        this.nama = nama;
        this.programStudi = programStudi;
    }

    public String getNim() { return nim; }
    public String getNama() { return nama; }
    public String getProgramStudi() { return programStudi; }
}

