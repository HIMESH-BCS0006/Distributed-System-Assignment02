package com.kdu.library.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The new, modern service. Only speaks Protocol Buffers over gRPC/HTTP2.
 * The legacy SOAP client (see gateway/soap-client) cannot call this directly.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: library_inventory.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class LibraryInventoryServiceGrpc {

  private LibraryInventoryServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "library.LibraryInventoryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kdu.library.grpc.BookRequest,
      com.kdu.library.grpc.BookDetailsResponse> getGetBookDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBookDetails",
      requestType = com.kdu.library.grpc.BookRequest.class,
      responseType = com.kdu.library.grpc.BookDetailsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kdu.library.grpc.BookRequest,
      com.kdu.library.grpc.BookDetailsResponse> getGetBookDetailsMethod() {
    io.grpc.MethodDescriptor<com.kdu.library.grpc.BookRequest, com.kdu.library.grpc.BookDetailsResponse> getGetBookDetailsMethod;
    if ((getGetBookDetailsMethod = LibraryInventoryServiceGrpc.getGetBookDetailsMethod) == null) {
      synchronized (LibraryInventoryServiceGrpc.class) {
        if ((getGetBookDetailsMethod = LibraryInventoryServiceGrpc.getGetBookDetailsMethod) == null) {
          LibraryInventoryServiceGrpc.getGetBookDetailsMethod = getGetBookDetailsMethod =
              io.grpc.MethodDescriptor.<com.kdu.library.grpc.BookRequest, com.kdu.library.grpc.BookDetailsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBookDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kdu.library.grpc.BookRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kdu.library.grpc.BookDetailsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LibraryInventoryServiceMethodDescriptorSupplier("GetBookDetails"))
              .build();
        }
      }
    }
    return getGetBookDetailsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kdu.library.grpc.BookRequest,
      com.kdu.library.grpc.AvailabilityResponse> getCheckAvailabilityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckAvailability",
      requestType = com.kdu.library.grpc.BookRequest.class,
      responseType = com.kdu.library.grpc.AvailabilityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kdu.library.grpc.BookRequest,
      com.kdu.library.grpc.AvailabilityResponse> getCheckAvailabilityMethod() {
    io.grpc.MethodDescriptor<com.kdu.library.grpc.BookRequest, com.kdu.library.grpc.AvailabilityResponse> getCheckAvailabilityMethod;
    if ((getCheckAvailabilityMethod = LibraryInventoryServiceGrpc.getCheckAvailabilityMethod) == null) {
      synchronized (LibraryInventoryServiceGrpc.class) {
        if ((getCheckAvailabilityMethod = LibraryInventoryServiceGrpc.getCheckAvailabilityMethod) == null) {
          LibraryInventoryServiceGrpc.getCheckAvailabilityMethod = getCheckAvailabilityMethod =
              io.grpc.MethodDescriptor.<com.kdu.library.grpc.BookRequest, com.kdu.library.grpc.AvailabilityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckAvailability"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kdu.library.grpc.BookRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kdu.library.grpc.AvailabilityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LibraryInventoryServiceMethodDescriptorSupplier("CheckAvailability"))
              .build();
        }
      }
    }
    return getCheckAvailabilityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kdu.library.grpc.ReserveRequest,
      com.kdu.library.grpc.ReservationResponse> getReserveBookMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReserveBook",
      requestType = com.kdu.library.grpc.ReserveRequest.class,
      responseType = com.kdu.library.grpc.ReservationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kdu.library.grpc.ReserveRequest,
      com.kdu.library.grpc.ReservationResponse> getReserveBookMethod() {
    io.grpc.MethodDescriptor<com.kdu.library.grpc.ReserveRequest, com.kdu.library.grpc.ReservationResponse> getReserveBookMethod;
    if ((getReserveBookMethod = LibraryInventoryServiceGrpc.getReserveBookMethod) == null) {
      synchronized (LibraryInventoryServiceGrpc.class) {
        if ((getReserveBookMethod = LibraryInventoryServiceGrpc.getReserveBookMethod) == null) {
          LibraryInventoryServiceGrpc.getReserveBookMethod = getReserveBookMethod =
              io.grpc.MethodDescriptor.<com.kdu.library.grpc.ReserveRequest, com.kdu.library.grpc.ReservationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReserveBook"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kdu.library.grpc.ReserveRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kdu.library.grpc.ReservationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LibraryInventoryServiceMethodDescriptorSupplier("ReserveBook"))
              .build();
        }
      }
    }
    return getReserveBookMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LibraryInventoryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LibraryInventoryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LibraryInventoryServiceStub>() {
        @java.lang.Override
        public LibraryInventoryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LibraryInventoryServiceStub(channel, callOptions);
        }
      };
    return LibraryInventoryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LibraryInventoryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LibraryInventoryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LibraryInventoryServiceBlockingStub>() {
        @java.lang.Override
        public LibraryInventoryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LibraryInventoryServiceBlockingStub(channel, callOptions);
        }
      };
    return LibraryInventoryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LibraryInventoryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LibraryInventoryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LibraryInventoryServiceFutureStub>() {
        @java.lang.Override
        public LibraryInventoryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LibraryInventoryServiceFutureStub(channel, callOptions);
        }
      };
    return LibraryInventoryServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The new, modern service. Only speaks Protocol Buffers over gRPC/HTTP2.
   * The legacy SOAP client (see gateway/soap-client) cannot call this directly.
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void getBookDetails(com.kdu.library.grpc.BookRequest request,
        io.grpc.stub.StreamObserver<com.kdu.library.grpc.BookDetailsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBookDetailsMethod(), responseObserver);
    }

    /**
     */
    default void checkAvailability(com.kdu.library.grpc.BookRequest request,
        io.grpc.stub.StreamObserver<com.kdu.library.grpc.AvailabilityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckAvailabilityMethod(), responseObserver);
    }

    /**
     */
    default void reserveBook(com.kdu.library.grpc.ReserveRequest request,
        io.grpc.stub.StreamObserver<com.kdu.library.grpc.ReservationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReserveBookMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service LibraryInventoryService.
   * <pre>
   * The new, modern service. Only speaks Protocol Buffers over gRPC/HTTP2.
   * The legacy SOAP client (see gateway/soap-client) cannot call this directly.
   * </pre>
   */
  public static abstract class LibraryInventoryServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return LibraryInventoryServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service LibraryInventoryService.
   * <pre>
   * The new, modern service. Only speaks Protocol Buffers over gRPC/HTTP2.
   * The legacy SOAP client (see gateway/soap-client) cannot call this directly.
   * </pre>
   */
  public static final class LibraryInventoryServiceStub
      extends io.grpc.stub.AbstractAsyncStub<LibraryInventoryServiceStub> {
    private LibraryInventoryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibraryInventoryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LibraryInventoryServiceStub(channel, callOptions);
    }

    /**
     */
    public void getBookDetails(com.kdu.library.grpc.BookRequest request,
        io.grpc.stub.StreamObserver<com.kdu.library.grpc.BookDetailsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBookDetailsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkAvailability(com.kdu.library.grpc.BookRequest request,
        io.grpc.stub.StreamObserver<com.kdu.library.grpc.AvailabilityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckAvailabilityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reserveBook(com.kdu.library.grpc.ReserveRequest request,
        io.grpc.stub.StreamObserver<com.kdu.library.grpc.ReservationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReserveBookMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service LibraryInventoryService.
   * <pre>
   * The new, modern service. Only speaks Protocol Buffers over gRPC/HTTP2.
   * The legacy SOAP client (see gateway/soap-client) cannot call this directly.
   * </pre>
   */
  public static final class LibraryInventoryServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<LibraryInventoryServiceBlockingStub> {
    private LibraryInventoryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibraryInventoryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LibraryInventoryServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.kdu.library.grpc.BookDetailsResponse getBookDetails(com.kdu.library.grpc.BookRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBookDetailsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.kdu.library.grpc.AvailabilityResponse checkAvailability(com.kdu.library.grpc.BookRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckAvailabilityMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.kdu.library.grpc.ReservationResponse reserveBook(com.kdu.library.grpc.ReserveRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReserveBookMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service LibraryInventoryService.
   * <pre>
   * The new, modern service. Only speaks Protocol Buffers over gRPC/HTTP2.
   * The legacy SOAP client (see gateway/soap-client) cannot call this directly.
   * </pre>
   */
  public static final class LibraryInventoryServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<LibraryInventoryServiceFutureStub> {
    private LibraryInventoryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LibraryInventoryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LibraryInventoryServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kdu.library.grpc.BookDetailsResponse> getBookDetails(
        com.kdu.library.grpc.BookRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBookDetailsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kdu.library.grpc.AvailabilityResponse> checkAvailability(
        com.kdu.library.grpc.BookRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckAvailabilityMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kdu.library.grpc.ReservationResponse> reserveBook(
        com.kdu.library.grpc.ReserveRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReserveBookMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BOOK_DETAILS = 0;
  private static final int METHODID_CHECK_AVAILABILITY = 1;
  private static final int METHODID_RESERVE_BOOK = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BOOK_DETAILS:
          serviceImpl.getBookDetails((com.kdu.library.grpc.BookRequest) request,
              (io.grpc.stub.StreamObserver<com.kdu.library.grpc.BookDetailsResponse>) responseObserver);
          break;
        case METHODID_CHECK_AVAILABILITY:
          serviceImpl.checkAvailability((com.kdu.library.grpc.BookRequest) request,
              (io.grpc.stub.StreamObserver<com.kdu.library.grpc.AvailabilityResponse>) responseObserver);
          break;
        case METHODID_RESERVE_BOOK:
          serviceImpl.reserveBook((com.kdu.library.grpc.ReserveRequest) request,
              (io.grpc.stub.StreamObserver<com.kdu.library.grpc.ReservationResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetBookDetailsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kdu.library.grpc.BookRequest,
              com.kdu.library.grpc.BookDetailsResponse>(
                service, METHODID_GET_BOOK_DETAILS)))
        .addMethod(
          getCheckAvailabilityMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kdu.library.grpc.BookRequest,
              com.kdu.library.grpc.AvailabilityResponse>(
                service, METHODID_CHECK_AVAILABILITY)))
        .addMethod(
          getReserveBookMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.kdu.library.grpc.ReserveRequest,
              com.kdu.library.grpc.ReservationResponse>(
                service, METHODID_RESERVE_BOOK)))
        .build();
  }

  private static abstract class LibraryInventoryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LibraryInventoryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kdu.library.grpc.LibraryInventory.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LibraryInventoryService");
    }
  }

  private static final class LibraryInventoryServiceFileDescriptorSupplier
      extends LibraryInventoryServiceBaseDescriptorSupplier {
    LibraryInventoryServiceFileDescriptorSupplier() {}
  }

  private static final class LibraryInventoryServiceMethodDescriptorSupplier
      extends LibraryInventoryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    LibraryInventoryServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LibraryInventoryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LibraryInventoryServiceFileDescriptorSupplier())
              .addMethod(getGetBookDetailsMethod())
              .addMethod(getCheckAvailabilityMethod())
              .addMethod(getReserveBookMethod())
              .build();
        }
      }
    }
    return result;
  }
}
