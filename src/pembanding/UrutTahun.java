package pembanding;

import java.util.Comparator;
import model.Alat;

public class UrutTahun implements Comparator<Alat> {
    @Override
    public int compare(Alat a, Alat b) {
        // terbaru lebih dulu: b dibandingkan terhadap a (dibalik)
        int bandingTahun = Integer.compare(b.getTahunPerolehan(), a.getTahunPerolehan());
        if (bandingTahun != 0) {
            return bandingTahun;
        }
        // tie-breaker: tahun sama, urutkan per nama
        return a.getNama().compareToIgnoreCase(b.getNama());
    }
}