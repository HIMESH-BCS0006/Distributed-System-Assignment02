package com.kdu.library.soap;

public class AvailabilityInfo {
    private String bookId;
    private int totalCopies;
    private int availableCopies;

    public AvailabilityInfo() {
    }

    public AvailabilityInfo(String bookId, int totalCopies, int availableCopies) {
        this.bookId = bookId;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }
    public int getTotalCopies() { return totalCopies; }
    public void setTotalCopies(int totalCopies) { this.totalCopies = totalCopies; }
    public int getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(int availableCopies) { this.availableCopies = availableCopies; }
}
