package com.kdu.library.soap;

import javax.xml.ws.WebFault;

/** Maps from gRPC Status.FAILED_PRECONDITION. Client's fault (soap:Sender). */
@WebFault(name = "NoCopiesAvailableFault")
public class NoCopiesAvailableFault extends Exception {
    public NoCopiesAvailableFault(String message) {
        super(message);
    }
}
