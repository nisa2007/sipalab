package model;

public enum StatusAlat {
    TERSEDIA("Tersedia"),
    DIPINJAM("Dipinjam"),
    DALAM_PERBAIKAN("Dalam perbaikan");

    private final String label;

    StatusAlat(String label) {
        this.label = label;
    }

    public String getLabel() { return label; }

    public boolean bolehDipinjam() { return this == TERSEDIA; }

    public boolean sedangDiluar() { return this == DIPINJAM; }
}