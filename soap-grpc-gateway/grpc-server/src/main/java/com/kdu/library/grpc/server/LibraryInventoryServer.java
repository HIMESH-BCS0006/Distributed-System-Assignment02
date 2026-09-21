package com.kdu.library.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class LibraryInventoryServer {

    public static void main(String[] args) throws Exception {
        int port = 9090;

        Server server = ServerBuilder.forPort(port)
                .addService(new LibraryInventoryServiceImpl())
                .build()
                .start();

        System.out.println("gRPC LibraryInventoryService listening on port " + port);
        server.awaitTermination();
    }
}
