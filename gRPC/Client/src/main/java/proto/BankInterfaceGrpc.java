package proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: bank.proto")
public final class BankInterfaceGrpc {

  private BankInterfaceGrpc() {}

  public static final String SERVICE_NAME = "BankInterface";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<proto.Bank.logInMessage,
      proto.Bank.logInReply> getLogInMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "logIn",
      requestType = proto.Bank.logInMessage.class,
      responseType = proto.Bank.logInReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.Bank.logInMessage,
      proto.Bank.logInReply> getLogInMethod() {
    io.grpc.MethodDescriptor<proto.Bank.logInMessage, proto.Bank.logInReply> getLogInMethod;
    if ((getLogInMethod = BankInterfaceGrpc.getLogInMethod) == null) {
      synchronized (BankInterfaceGrpc.class) {
        if ((getLogInMethod = BankInterfaceGrpc.getLogInMethod) == null) {
          BankInterfaceGrpc.getLogInMethod = getLogInMethod = 
              io.grpc.MethodDescriptor.<proto.Bank.logInMessage, proto.Bank.logInReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BankInterface", "logIn"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.Bank.logInMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.Bank.logInReply.getDefaultInstance()))
                  .setSchemaDescriptor(new BankInterfaceMethodDescriptorSupplier("logIn"))
                  .build();
          }
        }
     }
     return getLogInMethod;
  }

  private static volatile io.grpc.MethodDescriptor<proto.Bank.balanceMessage,
      proto.Bank.balanceReply> getRequestBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "requestBalance",
      requestType = proto.Bank.balanceMessage.class,
      responseType = proto.Bank.balanceReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.Bank.balanceMessage,
      proto.Bank.balanceReply> getRequestBalanceMethod() {
    io.grpc.MethodDescriptor<proto.Bank.balanceMessage, proto.Bank.balanceReply> getRequestBalanceMethod;
    if ((getRequestBalanceMethod = BankInterfaceGrpc.getRequestBalanceMethod) == null) {
      synchronized (BankInterfaceGrpc.class) {
        if ((getRequestBalanceMethod = BankInterfaceGrpc.getRequestBalanceMethod) == null) {
          BankInterfaceGrpc.getRequestBalanceMethod = getRequestBalanceMethod = 
              io.grpc.MethodDescriptor.<proto.Bank.balanceMessage, proto.Bank.balanceReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BankInterface", "requestBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.Bank.balanceMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.Bank.balanceReply.getDefaultInstance()))
                  .setSchemaDescriptor(new BankInterfaceMethodDescriptorSupplier("requestBalance"))
                  .build();
          }
        }
     }
     return getRequestBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<proto.Bank.withdrawMessage,
      proto.Bank.withdrawReply> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "withdraw",
      requestType = proto.Bank.withdrawMessage.class,
      responseType = proto.Bank.withdrawReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.Bank.withdrawMessage,
      proto.Bank.withdrawReply> getWithdrawMethod() {
    io.grpc.MethodDescriptor<proto.Bank.withdrawMessage, proto.Bank.withdrawReply> getWithdrawMethod;
    if ((getWithdrawMethod = BankInterfaceGrpc.getWithdrawMethod) == null) {
      synchronized (BankInterfaceGrpc.class) {
        if ((getWithdrawMethod = BankInterfaceGrpc.getWithdrawMethod) == null) {
          BankInterfaceGrpc.getWithdrawMethod = getWithdrawMethod = 
              io.grpc.MethodDescriptor.<proto.Bank.withdrawMessage, proto.Bank.withdrawReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BankInterface", "withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.Bank.withdrawMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.Bank.withdrawReply.getDefaultInstance()))
                  .setSchemaDescriptor(new BankInterfaceMethodDescriptorSupplier("withdraw"))
                  .build();
          }
        }
     }
     return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<proto.Bank.depositMessage,
      proto.Bank.depositReply> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deposit",
      requestType = proto.Bank.depositMessage.class,
      responseType = proto.Bank.depositReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.Bank.depositMessage,
      proto.Bank.depositReply> getDepositMethod() {
    io.grpc.MethodDescriptor<proto.Bank.depositMessage, proto.Bank.depositReply> getDepositMethod;
    if ((getDepositMethod = BankInterfaceGrpc.getDepositMethod) == null) {
      synchronized (BankInterfaceGrpc.class) {
        if ((getDepositMethod = BankInterfaceGrpc.getDepositMethod) == null) {
          BankInterfaceGrpc.getDepositMethod = getDepositMethod = 
              io.grpc.MethodDescriptor.<proto.Bank.depositMessage, proto.Bank.depositReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BankInterface", "deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.Bank.depositMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.Bank.depositReply.getDefaultInstance()))
                  .setSchemaDescriptor(new BankInterfaceMethodDescriptorSupplier("deposit"))
                  .build();
          }
        }
     }
     return getDepositMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BankInterfaceStub newStub(io.grpc.Channel channel) {
    return new BankInterfaceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BankInterfaceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BankInterfaceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BankInterfaceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BankInterfaceFutureStub(channel);
  }

  /**
   */
  public static abstract class BankInterfaceImplBase implements io.grpc.BindableService {

    /**
     */
    public void logIn(proto.Bank.logInMessage request,
        io.grpc.stub.StreamObserver<proto.Bank.logInReply> responseObserver) {
      asyncUnimplementedUnaryCall(getLogInMethod(), responseObserver);
    }

    /**
     */
    public void requestBalance(proto.Bank.balanceMessage request,
        io.grpc.stub.StreamObserver<proto.Bank.balanceReply> responseObserver) {
      asyncUnimplementedUnaryCall(getRequestBalanceMethod(), responseObserver);
    }

    /**
     */
    public void withdraw(proto.Bank.withdrawMessage request,
        io.grpc.stub.StreamObserver<proto.Bank.withdrawReply> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     */
    public void deposit(proto.Bank.depositMessage request,
        io.grpc.stub.StreamObserver<proto.Bank.depositReply> responseObserver) {
      asyncUnimplementedUnaryCall(getDepositMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLogInMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.Bank.logInMessage,
                proto.Bank.logInReply>(
                  this, METHODID_LOG_IN)))
          .addMethod(
            getRequestBalanceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.Bank.balanceMessage,
                proto.Bank.balanceReply>(
                  this, METHODID_REQUEST_BALANCE)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.Bank.withdrawMessage,
                proto.Bank.withdrawReply>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getDepositMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                proto.Bank.depositMessage,
                proto.Bank.depositReply>(
                  this, METHODID_DEPOSIT)))
          .build();
    }
  }

  /**
   */
  public static final class BankInterfaceStub extends io.grpc.stub.AbstractStub<BankInterfaceStub> {
    private BankInterfaceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BankInterfaceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BankInterfaceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BankInterfaceStub(channel, callOptions);
    }

    /**
     */
    public void logIn(proto.Bank.logInMessage request,
        io.grpc.stub.StreamObserver<proto.Bank.logInReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogInMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void requestBalance(proto.Bank.balanceMessage request,
        io.grpc.stub.StreamObserver<proto.Bank.balanceReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRequestBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void withdraw(proto.Bank.withdrawMessage request,
        io.grpc.stub.StreamObserver<proto.Bank.withdrawReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deposit(proto.Bank.depositMessage request,
        io.grpc.stub.StreamObserver<proto.Bank.depositReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BankInterfaceBlockingStub extends io.grpc.stub.AbstractStub<BankInterfaceBlockingStub> {
    private BankInterfaceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BankInterfaceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BankInterfaceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BankInterfaceBlockingStub(channel, callOptions);
    }

    /**
     */
    public proto.Bank.logInReply logIn(proto.Bank.logInMessage request) {
      return blockingUnaryCall(
          getChannel(), getLogInMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.Bank.balanceReply requestBalance(proto.Bank.balanceMessage request) {
      return blockingUnaryCall(
          getChannel(), getRequestBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.Bank.withdrawReply withdraw(proto.Bank.withdrawMessage request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.Bank.depositReply deposit(proto.Bank.depositMessage request) {
      return blockingUnaryCall(
          getChannel(), getDepositMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BankInterfaceFutureStub extends io.grpc.stub.AbstractStub<BankInterfaceFutureStub> {
    private BankInterfaceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BankInterfaceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BankInterfaceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BankInterfaceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.Bank.logInReply> logIn(
        proto.Bank.logInMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getLogInMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.Bank.balanceReply> requestBalance(
        proto.Bank.balanceMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getRequestBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.Bank.withdrawReply> withdraw(
        proto.Bank.withdrawMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.Bank.depositReply> deposit(
        proto.Bank.depositMessage request) {
      return futureUnaryCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOG_IN = 0;
  private static final int METHODID_REQUEST_BALANCE = 1;
  private static final int METHODID_WITHDRAW = 2;
  private static final int METHODID_DEPOSIT = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BankInterfaceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BankInterfaceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOG_IN:
          serviceImpl.logIn((proto.Bank.logInMessage) request,
              (io.grpc.stub.StreamObserver<proto.Bank.logInReply>) responseObserver);
          break;
        case METHODID_REQUEST_BALANCE:
          serviceImpl.requestBalance((proto.Bank.balanceMessage) request,
              (io.grpc.stub.StreamObserver<proto.Bank.balanceReply>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((proto.Bank.withdrawMessage) request,
              (io.grpc.stub.StreamObserver<proto.Bank.withdrawReply>) responseObserver);
          break;
        case METHODID_DEPOSIT:
          serviceImpl.deposit((proto.Bank.depositMessage) request,
              (io.grpc.stub.StreamObserver<proto.Bank.depositReply>) responseObserver);
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

  private static abstract class BankInterfaceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BankInterfaceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return proto.Bank.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BankInterface");
    }
  }

  private static final class BankInterfaceFileDescriptorSupplier
      extends BankInterfaceBaseDescriptorSupplier {
    BankInterfaceFileDescriptorSupplier() {}
  }

  private static final class BankInterfaceMethodDescriptorSupplier
      extends BankInterfaceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BankInterfaceMethodDescriptorSupplier(String methodName) {
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
      synchronized (BankInterfaceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BankInterfaceFileDescriptorSupplier())
              .addMethod(getLogInMethod())
              .addMethod(getRequestBalanceMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getDepositMethod())
              .build();
        }
      }
    }
    return result;
  }
}
