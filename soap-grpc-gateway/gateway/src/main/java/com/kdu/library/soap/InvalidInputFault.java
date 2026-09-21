package com.kdu.library.soap;

import javax.xml.ws.WebFault;

/** Maps from gRPC Status.INVALID_ARGUMENT. Client's fault (soap:Sender). */
@WebFault(name = "InvalidInputFault")
public class InvalidInputFault extends Exception {
    public InvalidInputFault(String message) {
        super(message);
    }
}
