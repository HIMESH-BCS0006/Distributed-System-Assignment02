package com.kdu.library.soap;

import javax.xml.ws.WebFault;

/**
 * Catch-all for gRPC statuses that are NOT the caller's fault
 * (UNAVAILABLE, DEADLINE_EXCEEDED, INTERNAL, UNKNOWN, ...).
 * Maps to soap:Receiver, since the problem is on the server/backend side.
 */
@WebFault(name = "ServiceUnavailableFault")
public class ServiceUnavailableFault extends Exception {
    public ServiceUnavailableFault(String message) {
        super(message);
    }
}
