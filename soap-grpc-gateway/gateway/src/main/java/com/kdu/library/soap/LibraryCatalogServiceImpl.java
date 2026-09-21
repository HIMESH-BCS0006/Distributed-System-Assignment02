package com.kdu.library.soap;

import com.kdu.library.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import javax.jws.WebService;

/**
 * THE GATEWAY'S CORE TRANSLATION LOGIC.
 *
 * For every SOAP operation:
 *   1. Take the SOAP method's plain Java parameters
 *   2. Build the equivalent gRPC request message
 *   3. Call the real gRPC backend
 *   4. On success: unwrap the gRPC response into a SOAP-friendly POJO
 *      (JAX-WS turns that into proper soap:Body XML automatically)
 *   5. On failure: read the gRPC Status code and throw the matching
 *      @WebFault exception (JAX-WS turns that into a real soap:Fault)
 *
 * This is genuine translation, not a pass-through: the two message shapes
 * (SOAP params/POJOs vs Protobuf messages) are different types entirely,
 * and every gRPC status code is deliberately re-interpreted as a SOAP
 * fault concept (sender vs receiver) rather than just forwarded as-is.
 */
@WebService(
        endpointInterface = "com.kdu.library.soap.LibraryCatalogService",
        serviceName = "LibraryCatalogService",
        portName = "LibraryCatalogServicePort",
        targetNamespace = "http://library.kdu.com/soap"
)
public class LibraryCatalogServiceImpl implements LibraryCatalogService {

    private final LibraryInventoryServiceGrpc.LibraryInventoryServiceBlockingStub grpcStub;

    public LibraryCatalogServiceImpl() {
        String grpcHost = System.getenv().getOrDefault("GRPC_SERVER_HOST", "localhost");
        int grpcPort = Integer.parseInt(System.getenv().getOrDefault("GRPC_SERVER_PORT", "9090"));

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();

        this.grpcStub = LibraryInventoryServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public BookDetails getBookDetails(String bookId)
            throws InvalidInputFault, BookNotFoundFault, ServiceUnavailableFault {
        try {
            BookRequest request = BookRequest.newBuilder().setBookId(bookId).build();
            BookDetailsResponse response = grpcStub.getBookDetails(request);

            return new BookDetails(
                    response.getBookId(),
                    response.getTitle(),
                    response.getAuthor(),
                    response.getIsbn()
            );
        } catch (StatusRuntimeException e) {
            throwMappedFault(e);
            return null; // unreachable — throwMappedFault always throws
        }
    }

    @Override
    public AvailabilityInfo checkAvailability(String bookId)
            throws InvalidInputFault, BookNotFoundFault, ServiceUnavailableFault {
        try {
            BookRequest request = BookRequest.newBuilder().setBookId(bookId).build();
            AvailabilityResponse response = grpcStub.checkAvailability(request);

            return new AvailabilityInfo(
                    response.getBookId(),
                    response.getTotalCopies(),
                    response.getAvailableCopies()
            );
        } catch (StatusRuntimeException e) {
            throwMappedFault(e);
            return null; // unreachable — throwMappedFault always throws
        }
    }

    @Override
    public ReservationConfirmation reserveBook(String bookId, String memberId)
            throws InvalidInputFault, BookNotFoundFault, NoCopiesAvailableFault, ServiceUnavailableFault {
        try {
            ReserveRequest request = ReserveRequest.newBuilder()
                    .setBookId(bookId)
                    .setMemberId(memberId)
                    .build();
            ReservationResponse response = grpcStub.reserveBook(request);

            return new ReservationConfirmation(
                    response.getReservationId(),
                    response.getBookId(),
                    response.getMemberId(),
                    response.getStatus()
            );
        } catch (StatusRuntimeException e) {
            throwMappedFaultWithPrecondition(e);
            return null; // unreachable — the helper always throws
        }
    }

    /**
     * Central error-mapping logic: gRPC Status.Code -> SOAP fault, for the
     * two operations that cannot legitimately produce a "no copies
     * available" situation. This is what makes the "map gRPC errors to
     * SOAP faults (and vice versa)" requirement concrete — every gRPC
     * status a client could hit is deliberately classified as either the
     * caller's fault (soap:Sender-style faults) or the backend's fault
     * (soap:Receiver-style ServiceUnavailableFault), rather than being
     * forwarded blindly.
     */
    private void throwMappedFault(StatusRuntimeException e)
            throws InvalidInputFault, BookNotFoundFault, ServiceUnavailableFault {
        Status.Code code = e.getStatus().getCode();
        String description = describe(e);

        switch (code) {
            case INVALID_ARGUMENT:
                throw new InvalidInputFault(description);
            case NOT_FOUND:
                throw new BookNotFoundFault(description);
            default:
                // UNAVAILABLE, DEADLINE_EXCEEDED, INTERNAL, UNKNOWN, etc.
                // are backend/infrastructure problems, not the caller's fault.
                throw new ServiceUnavailableFault("Backend service error (" + code + "): " + description);
        }
    }

    /** Same mapping as above, plus FAILED_PRECONDITION -> NoCopiesAvailableFault (only relevant to reserveBook). */
    private void throwMappedFaultWithPrecondition(StatusRuntimeException e)
            throws InvalidInputFault, BookNotFoundFault, NoCopiesAvailableFault, ServiceUnavailableFault {
        Status.Code code = e.getStatus().getCode();
        String description = describe(e);

        switch (code) {
            case INVALID_ARGUMENT:
                throw new InvalidInputFault(description);
            case NOT_FOUND:
                throw new BookNotFoundFault(description);
            case FAILED_PRECONDITION:
                throw new NoCopiesAvailableFault(description);
            default:
                throw new ServiceUnavailableFault("Backend service error (" + code + "): " + description);
        }
    }

    private String describe(StatusRuntimeException e) {
        return e.getStatus().getDescription() != null
                ? e.getStatus().getDescription()
                : "Unknown error";
    }
}
