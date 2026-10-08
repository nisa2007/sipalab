package pembanding;

import java.util.Comparator;
import model.Alat;

public class UrutStatusLaluNama implements Comparator<Alat> {
    @Override
    public int compare(Alat a, Alat b) {
        // lapis pertama: yang siap dipinjam tampil lebih dulu
        // (b dan a sengaja dibalik supaya true berada di atas)
        int bandingStatus = Boolean.compare(b.siapDipinjam(), a.siapDipinjam());
        if (bandingStatus != 0) {
            return bandingStatus;
        }
        // lapis kedua (tie-breaker): bila status sama, urutkan per nama
        return a.getNama().compareToIgnoreCase(b.getNama());
    }
}