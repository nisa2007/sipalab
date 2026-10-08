package eksepsi;

public class SipalabException extends Exception {
    public SipalabException(String pesan) {
        super(pesan);
    }

    public SipalabException(String pesan, Throwable penyebab) {
        super(pesan, penyebab);
    }
}