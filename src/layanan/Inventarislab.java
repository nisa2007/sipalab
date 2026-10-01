/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package layanan;
import java.util.ArrayList;
import java.util.List;
import model.Alat;
import model.Laptop;
import model.AlatUkur;
import model.Proyektor;
import model.Kalibrasiable;
/**
 *
 * @author LENOVO
 */
public class Inventarislab {
    private final List<Alat> daftarAlat = new ArrayList<>();
    
    public void tambah(Alat alat) {
        daftarAlat.add(alat);
    }
    public List<Alat> semuaAlat(){
        return daftarAlat;
    }

//public void cetakStatusBercabang() {
//    for (Alat a : daftarAlat) {
//        if (a instanceof Laptop){
//        Laptop l = (Laptop) a;
//        System.out.println(l.getNama() + "" + l.siapDipinjam());  
//        } else if (a instanceof Proyektor) {
//        Proyektor p = (Proyektor) a;
//        System.out.println(p.getNama() + "" + p.siapDipinjam());  
//        }else if (a instanceof AlatUkur) {
//        AlatUkur u = (AlatUkur) a;
//        System.out.println(u.getNama() + "" + u.siapDipinjam());  
//        }
//        Contoh menggunakan cetak bercabang
//    }
    public void cetakStatus (){
        for (Alat a : daftarAlat){
            System.out.println(a.laporanRingkas());
        }
    }
        public void cetakJadwalKalibrasi() {
        for (Alat a : daftarAlat) {
            if (a instanceof Kalibrasiable k) {
                System.out.println(a.getNama() + " - jatuh tempo: " + k.jatuhTempoKalibrasi()
                        + " - perlu kalibrasi: " + k.perluKalibrasi());
            }
        }
    }
}
    

