package berkas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Alat;

public class PenyimpananBerkas {

    private final String namaBerkas;

    public PenyimpananBerkas(String namaBerkas) {
        this.namaBerkas = namaBerkas;
    }

    public void simpan(List<Alat> daftar) throws IOException {
        try (ObjectOutputStream keluar = new ObjectOutputStream(
                new FileOutputStream(namaBerkas))) {
            keluar.writeObject(new ArrayList<>(daftar));
        }
    }

    @SuppressWarnings("unchecked")
    public List<Alat> muat() throws IOException, ClassNotFoundException {
        try (ObjectInputStream masuk = new ObjectInputStream(
                new FileInputStream(namaBerkas))) {
            return (List<Alat>) masuk.readObject();
        }
    }
}