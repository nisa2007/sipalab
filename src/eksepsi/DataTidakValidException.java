package eksepsi;

public class DataTidakValidException extends SipalabException {
    private final String namaKolom;

    public DataTidakValidException(String namaKolom, String pesan) {
        super(pesan);
        this.namaKolom = namaKolom;
    }

    public String getNamaKolom() { return namaKolom; }
}