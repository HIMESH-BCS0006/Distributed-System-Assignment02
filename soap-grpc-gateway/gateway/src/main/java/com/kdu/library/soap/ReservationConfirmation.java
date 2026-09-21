package com.kdu.library.soap;

public class ReservationConfirmation {
    private String reservationId;
    private String bookId;
    private String memberId;
    private String status;

    public ReservationConfirmation() {
    }

    public ReservationConfirmation(String reservationId, String bookId, String memberId, String status) {
        this.reservationId = reservationId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.status = status;
    }

    public String getReservationId() { return reservationId; }
    public void setReservationId(String reservationId) { this.reservationId = reservationId; }
    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
