package eksepsi;

public class AlatSedangDipinjamException extends SipalabException {
    public AlatSedangDipinjamException(String kodeAlat) {
        super("Alat " + kodeAlat + " sedang dipinjam dan belum dikembalikan.");
    }
}