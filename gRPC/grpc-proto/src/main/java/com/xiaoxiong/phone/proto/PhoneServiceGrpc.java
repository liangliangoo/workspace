package com.xiaoxiong.phone.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.1)",
    comments = "Source: phone.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PhoneServiceGrpc {

  private PhoneServiceGrpc() {}

  public static final String SERVICE_NAME = "phone.PhoneService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.xiaoxiong.phone.proto.PhoneProto.PhoneRequest,
      com.xiaoxiong.phone.proto.PhoneProto.PhoneResponse> getCreatePhoneNumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createPhoneNum",
      requestType = com.xiaoxiong.phone.proto.PhoneProto.PhoneRequest.class,
      responseType = com.xiaoxiong.phone.proto.PhoneProto.PhoneResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.xiaoxiong.phone.proto.PhoneProto.PhoneRequest,
      com.xiaoxiong.phone.proto.PhoneProto.PhoneResponse> getCreatePhoneNumMethod() {
    io.grpc.MethodDescriptor<com.xiaoxiong.phone.proto.PhoneProto.PhoneRequest, com.xiaoxiong.phone.proto.PhoneProto.PhoneResponse> getCreatePhoneNumMethod;
    if ((getCreatePhoneNumMethod = PhoneServiceGrpc.getCreatePhoneNumMethod) == null) {
      synchronized (PhoneServiceGrpc.class) {
        if ((getCreatePhoneNumMethod = PhoneServiceGrpc.getCreatePhoneNumMethod) == null) {
          PhoneServiceGrpc.getCreatePhoneNumMethod = getCreatePhoneNumMethod =
              io.grpc.MethodDescriptor.<com.xiaoxiong.phone.proto.PhoneProto.PhoneRequest, com.xiaoxiong.phone.proto.PhoneProto.PhoneResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createPhoneNum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaoxiong.phone.proto.PhoneProto.PhoneRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.xiaoxiong.phone.proto.PhoneProto.PhoneResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PhoneServiceMethodDescriptorSupplier("createPhoneNum"))
              .build();
        }
      }
    }
    return getCreatePhoneNumMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PhoneServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PhoneServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PhoneServiceStub>() {
        @java.lang.Override
        public PhoneServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PhoneServiceStub(channel, callOptions);
        }
      };
    return PhoneServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PhoneServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PhoneServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PhoneServiceBlockingStub>() {
        @java.lang.Override
        public PhoneServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PhoneServiceBlockingStub(channel, callOptions);
        }
      };
    return PhoneServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PhoneServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PhoneServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PhoneServiceFutureStub>() {
        @java.lang.Override
        public PhoneServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PhoneServiceFutureStub(channel, callOptions);
        }
      };
    return PhoneServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class PhoneServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<com.xiaoxiong.phone.proto.PhoneProto.PhoneRequest> createPhoneNum(
        io.grpc.stub.StreamObserver<com.xiaoxiong.phone.proto.PhoneProto.PhoneResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getCreatePhoneNumMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreatePhoneNumMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                com.xiaoxiong.phone.proto.PhoneProto.PhoneRequest,
                com.xiaoxiong.phone.proto.PhoneProto.PhoneResponse>(
                  this, METHODID_CREATE_PHONE_NUM)))
          .build();
    }
  }

  /**
   */
  public static final class PhoneServiceStub extends io.grpc.stub.AbstractAsyncStub<PhoneServiceStub> {
    private PhoneServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PhoneServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PhoneServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.xiaoxiong.phone.proto.PhoneProto.PhoneRequest> createPhoneNum(
        io.grpc.stub.StreamObserver<com.xiaoxiong.phone.proto.PhoneProto.PhoneResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getCreatePhoneNumMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class PhoneServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<PhoneServiceBlockingStub> {
    private PhoneServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PhoneServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PhoneServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class PhoneServiceFutureStub extends io.grpc.stub.AbstractFutureStub<PhoneServiceFutureStub> {
    private PhoneServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PhoneServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PhoneServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_CREATE_PHONE_NUM = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PhoneServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PhoneServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_PHONE_NUM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.createPhoneNum(
              (io.grpc.stub.StreamObserver<com.xiaoxiong.phone.proto.PhoneProto.PhoneResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PhoneServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PhoneServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.xiaoxiong.phone.proto.PhoneProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PhoneService");
    }
  }

  private static final class PhoneServiceFileDescriptorSupplier
      extends PhoneServiceBaseDescriptorSupplier {
    PhoneServiceFileDescriptorSupplier() {}
  }

  private static final class PhoneServiceMethodDescriptorSupplier
      extends PhoneServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PhoneServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (PhoneServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PhoneServiceFileDescriptorSupplier())
              .addMethod(getCreatePhoneNumMethod())
              .build();
        }
      }
    }
    return result;
  }
}
