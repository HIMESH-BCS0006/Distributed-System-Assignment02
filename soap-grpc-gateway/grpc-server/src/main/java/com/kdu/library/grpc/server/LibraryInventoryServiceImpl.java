package com.kdu.library.grpc.server;

import com.kdu.library.grpc.*;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The real backend. This is intentionally the ONLY place that knows the
 * actual catalog data and business rules. Neither the SOAP client nor the
 * Gateway's SOAP-facing code know anything about this implementation —
 * they only know the .proto contract.
 */
public class LibraryInventoryServiceImpl extends LibraryInventoryServiceGrpc.LibraryInventoryServiceImplBase {

    private static final class Book {
        String title;
        String author;
        String isbn;
        int totalCopies;
        int availableCopies;

        Book(String title, String author, String isbn, int totalCopies, int availableCopies) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.totalCopies = totalCopies;
            this.availableCopies = availableCopies;
        }
    }

    // Fake in-memory catalog — a real system would use a database here.
    private final Map<String, Book> catalog = new HashMap<>();

    public LibraryInventoryServiceImpl() {
        catalog.put("B001", new Book("Distributed Systems: Concepts and Design",
                "George Coulouris", "978-0132143011", 3, 1));
        catalog.put("B002", new Book("Designing Data-Intensive Applications",
                "Martin Kleppmann", "978-1449373320", 2, 0)); // no copies available on purpose
        catalog.put("B003", new Book("Clean Architecture",
                "Robert C. Martin", "978-0134494166", 4, 4));
    }

    @Override
    public void getBookDetails(BookRequest request, StreamObserver<BookDetailsResponse> responseObserver) {
        String bookId = request.getBookId();

        if (bookId == null || bookId.isBlank()) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("book_id must not be empty")
                    .asRuntimeException());
            return;
        }

        Book book = catalog.get(bookId);
        if (book == null) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Book with id '" + bookId + "' was not found")
                    .asRuntimeException());
            return;
        }

        BookDetailsResponse response = BookDetailsResponse.newBuilder()
                .setBookId(bookId)
                .setTitle(book.title)
                .setAuthor(book.author)
                .setIsbn(book.isbn)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void checkAvailability(BookRequest request, StreamObserver<AvailabilityResponse> responseObserver) {
        String bookId = request.getBookId();

        if (bookId == null || bookId.isBlank()) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("book_id must not be empty")
                    .asRuntimeException());
            return;
        }

        Book book = catalog.get(bookId);
        if (book == null) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Book with id '" + bookId + "' was not found")
                    .asRuntimeException());
            return;
        }

        AvailabilityResponse response = AvailabilityResponse.newBuilder()
                .setBookId(bookId)
                .setTotalCopies(book.totalCopies)
                .setAvailableCopies(book.availableCopies)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void reserveBook(ReserveRequest request, StreamObserver<ReservationResponse> responseObserver) {
        String bookId = request.getBookId();
        String memberId = request.getMemberId();

        if (bookId == null || bookId.isBlank()) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("book_id must not be empty")
                    .asRuntimeException());
            return;
        }
        if (memberId == null || memberId.isBlank()) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("member_id must not be empty")
                    .asRuntimeException());
            return;
        }

        Book book = catalog.get(bookId);
        if (book == null) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Book with id '" + bookId + "' was not found")
                    .asRuntimeException());
            return;
        }

        if (book.availableCopies <= 0) {
            responseObserver.onError(Status.FAILED_PRECONDITION
                    .withDescription("No copies available for book '" + bookId + "'")
                    .asRuntimeException());
            return;
        }

        book.availableCopies -= 1;

        ReservationResponse response = ReservationResponse.newBuilder()
                .setReservationId(UUID.randomUUID().toString())
                .setBookId(bookId)
                .setMemberId(memberId)
                .setStatus("CONFIRMED")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
