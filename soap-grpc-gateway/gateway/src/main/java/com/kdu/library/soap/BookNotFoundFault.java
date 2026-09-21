package com.kdu.library.soap;

import javax.xml.ws.WebFault;

/** Maps from gRPC Status.NOT_FOUND. Client's fault (soap:Sender). */
@WebFault(name = "BookNotFoundFault")
public class BookNotFoundFault extends Exception {
    public BookNotFoundFault(String message) {
        super(message);
    }
}
