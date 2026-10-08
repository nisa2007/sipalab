package eksepsi;

public class AlatBelumSiapException extends SipalabException {
    public AlatBelumSiapException(String kodeAlat, String alasan) {
        super("Alat " + kodeAlat + " belum siap dipinjam. " + alasan);
    }
}