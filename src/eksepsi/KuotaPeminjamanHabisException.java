package eksepsi;

public class KuotaPeminjamanHabisException extends SipalabException {
    public KuotaPeminjamanHabisException(String nama, int kuota) {
        super("Kuota peminjaman " + nama + " sudah penuh, batasnya " + kuota + " alat.");
    }
}