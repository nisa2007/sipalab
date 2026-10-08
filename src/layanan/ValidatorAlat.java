package layanan;

import eksepsi.DataTidakValidException;
import java.time.Year;
import model.Alat;

public class ValidatorAlat {

    public void periksa(Alat alat) throws DataTidakValidException {
        if (alat.getKodeAlat() == null || alat.getKodeAlat().isBlank()) {
            throw new DataTidakValidException("Kode alat",
                    "Kode alat tidak boleh kosong.");
        }
        if (alat.getNama() == null || alat.getNama().isBlank()) {
            throw new DataTidakValidException("Nama",
                    "Nama alat tidak boleh kosong.");
        }
        int tahun = alat.getTahunPerolehan();
        if (tahun < 1990 || tahun > Year.now().getValue()) {
            throw new DataTidakValidException("Tahun perolehan",
                    "Tahun perolehan harus di antara 1990 dan tahun berjalan.");
        }
    }
}