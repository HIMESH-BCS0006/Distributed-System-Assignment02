package com.kdu.library.soap;

import javax.xml.ws.Endpoint;

/**
 * Publishes the Gateway's SOAP endpoint. Once running, its WSDL is
 * available at:
 *   http://localhost:8080/ws/library?wsdl
 *
 * Behind this same endpoint, LibraryCatalogServiceImpl is holding a live
 * gRPC connection to the backend (see GRPC_SERVER_HOST / GRPC_SERVER_PORT).
 */
public class GatewayServer {

    private static final String URL = "http://0.0.0.0:8080/ws/library";

    public static void main(String[] args) {
        Endpoint.publish(URL, new LibraryCatalogServiceImpl());
        System.out.println("Gateway SOAP endpoint published at " + URL);
        System.out.println("WSDL available at " + URL + "?wsdl");
    }
}
