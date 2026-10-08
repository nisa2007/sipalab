package koleksi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import model.Alat;

public class Keranjang<T extends Alat> implements Iterable<T> {

    private final ArrayList<T> isi = new ArrayList<>();
    private final int kapasitas;

    public Keranjang(int kapasitas) {
        this.kapasitas = kapasitas;
    }

    public boolean tambah(T item) {
        if (isi.size() >= kapasitas) {
            return false;
        }
        return isi.add(item);
    }

    public boolean penuh() { return isi.size() >= kapasitas; }
    public int sisaRuang() { return kapasitas - isi.size(); }
    public int ukuran() { return isi.size(); }
    public List<T> semua() { return Collections.unmodifiableList(isi); }

    @Override
    public Iterator<T> iterator() { return isi.iterator(); }
}