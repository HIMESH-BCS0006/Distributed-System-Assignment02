package com.kdu.library.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * This is what the SOAP client actually sees: a normal-looking SOAP
 * contract with 3 operations, exactly mirroring the 3 gRPC RPCs. The SOAP
 * client has no idea gRPC exists anywhere behind this interface.
 */
@WebService(targetNamespace = "http://library.kdu.com/soap", name = "LibraryCatalogService")
public interface LibraryCatalogService {

    @WebMethod
    BookDetails getBookDetails(@WebParam(name = "bookId") String bookId)
            throws InvalidInputFault, BookNotFoundFault, ServiceUnavailableFault;

    @WebMethod
    AvailabilityInfo checkAvailability(@WebParam(name = "bookId") String bookId)
            throws InvalidInputFault, BookNotFoundFault, ServiceUnavailableFault;

    @WebMethod
    ReservationConfirmation reserveBook(@WebParam(name = "bookId") String bookId,
                                         @WebParam(name = "memberId") String memberId)
            throws InvalidInputFault, BookNotFoundFault, NoCopiesAvailableFault, ServiceUnavailableFault;
}
