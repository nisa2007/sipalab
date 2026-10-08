package pembanding;

import java.util.Comparator;
import model.Alat;

public class UrutNama implements Comparator<Alat> {
    @Override
    public int compare(Alat a, Alat b) {
        return a.getNama().compareToIgnoreCase(b.getNama());
    }
}