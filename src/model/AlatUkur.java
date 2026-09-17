package model;

import java.time.LocalDate;

public class AlatUkur extends Alat implements Kalibrasiable {
    private String satuan;
    private LocalDate kalibrasiTerakhir;

    public AlatUkur(String kodeAlat, String nama, int tahunPerolehan,
            String satuan, LocalDate kalibrasiTerakhir) {
        super(kodeAlat, nama, tahunPerolehan);
        this.satuan = satuan;
        this.kalibrasiTerakhir = kalibrasiTerakhir;
    }

    @Override
    public LocalDate jatuhTempoKalibrasi() {
        return kalibrasiTerakhir.plusMonths(12);
    }

    @Override
    public boolean perluKalibrasi() {
        return LocalDate.now().isAfter(jatuhTempoKalibrasi());
    }

    @Override
    
public boolean siapDipinjam() {
    return !perluKalibrasi();
    }

    @Override
    public String jenis() {
        return "AlatUkur";
    }

    @Override
    public String deskripsi() {
        return super.deskripsi() + " satuan " + satuan;
    }

    public String statusKalibrasi() {
        if (perluKalibrasi()) {
            return "Kalibrasi kadaluarsa, perlu kalibrasi ulang (jatuh tempo: " + jatuhTempoKalibrasi() + ")";
        } else {
            return "Kalibrasi masih berlaku (jatuh tempo: " + jatuhTempoKalibrasi() + ")";
        }
    }
}