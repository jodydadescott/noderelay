package noderelay.db;

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
@javax.annotation.processing.Generated(
    value = "by gRPC proto compiler (version 1.16.1)",
    comments = "Source: src/main/proto/NodeReflector.proto")
public final class NodeServiceGrpc {

  private NodeServiceGrpc() {}

  public static final String SERVICE_NAME = "noderelay.db.NodeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<noderelay.db.NodeReflector.NodeFilter,
      noderelay.db.NodeReflector.Node> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "search",
      requestType = noderelay.db.NodeReflector.NodeFilter.class,
      responseType = noderelay.db.NodeReflector.Node.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<noderelay.db.NodeReflector.NodeFilter,
      noderelay.db.NodeReflector.Node> getSearchMethod() {
    io.grpc.MethodDescriptor<noderelay.db.NodeReflector.NodeFilter, noderelay.db.NodeReflector.Node> getSearchMethod;
    if ((getSearchMethod = NodeServiceGrpc.getSearchMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getSearchMethod = NodeServiceGrpc.getSearchMethod) == null) {
          NodeServiceGrpc.getSearchMethod = getSearchMethod = 
              io.grpc.MethodDescriptor.<noderelay.db.NodeReflector.NodeFilter, noderelay.db.NodeReflector.Node>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "noderelay.db.NodeService", "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  noderelay.db.NodeReflector.NodeFilter.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  noderelay.db.NodeReflector.Node.getDefaultInstance()))
                  .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("search"))
                  .build();
          }
        }
     }
     return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<noderelay.db.NodeReflector.Node,
      noderelay.db.NodeReflector.Node> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = noderelay.db.NodeReflector.Node.class,
      responseType = noderelay.db.NodeReflector.Node.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<noderelay.db.NodeReflector.Node,
      noderelay.db.NodeReflector.Node> getGetMethod() {
    io.grpc.MethodDescriptor<noderelay.db.NodeReflector.Node, noderelay.db.NodeReflector.Node> getGetMethod;
    if ((getGetMethod = NodeServiceGrpc.getGetMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getGetMethod = NodeServiceGrpc.getGetMethod) == null) {
          NodeServiceGrpc.getGetMethod = getGetMethod = 
              io.grpc.MethodDescriptor.<noderelay.db.NodeReflector.Node, noderelay.db.NodeReflector.Node>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "noderelay.db.NodeService", "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  noderelay.db.NodeReflector.Node.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  noderelay.db.NodeReflector.Node.getDefaultInstance()))
                  .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("get"))
                  .build();
          }
        }
     }
     return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<noderelay.db.NodeReflector.Node,
      noderelay.db.NodeReflector.Node> getPutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "put",
      requestType = noderelay.db.NodeReflector.Node.class,
      responseType = noderelay.db.NodeReflector.Node.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<noderelay.db.NodeReflector.Node,
      noderelay.db.NodeReflector.Node> getPutMethod() {
    io.grpc.MethodDescriptor<noderelay.db.NodeReflector.Node, noderelay.db.NodeReflector.Node> getPutMethod;
    if ((getPutMethod = NodeServiceGrpc.getPutMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getPutMethod = NodeServiceGrpc.getPutMethod) == null) {
          NodeServiceGrpc.getPutMethod = getPutMethod = 
              io.grpc.MethodDescriptor.<noderelay.db.NodeReflector.Node, noderelay.db.NodeReflector.Node>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "noderelay.db.NodeService", "put"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  noderelay.db.NodeReflector.Node.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  noderelay.db.NodeReflector.Node.getDefaultInstance()))
                  .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("put"))
                  .build();
          }
        }
     }
     return getPutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<noderelay.db.NodeReflector.Node,
      noderelay.db.NodeReflector.Node> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = noderelay.db.NodeReflector.Node.class,
      responseType = noderelay.db.NodeReflector.Node.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<noderelay.db.NodeReflector.Node,
      noderelay.db.NodeReflector.Node> getDeleteMethod() {
    io.grpc.MethodDescriptor<noderelay.db.NodeReflector.Node, noderelay.db.NodeReflector.Node> getDeleteMethod;
    if ((getDeleteMethod = NodeServiceGrpc.getDeleteMethod) == null) {
      synchronized (NodeServiceGrpc.class) {
        if ((getDeleteMethod = NodeServiceGrpc.getDeleteMethod) == null) {
          NodeServiceGrpc.getDeleteMethod = getDeleteMethod = 
              io.grpc.MethodDescriptor.<noderelay.db.NodeReflector.Node, noderelay.db.NodeReflector.Node>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "noderelay.db.NodeService", "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  noderelay.db.NodeReflector.Node.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  noderelay.db.NodeReflector.Node.getDefaultInstance()))
                  .setSchemaDescriptor(new NodeServiceMethodDescriptorSupplier("delete"))
                  .build();
          }
        }
     }
     return getDeleteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NodeServiceStub newStub(io.grpc.Channel channel) {
    return new NodeServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NodeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NodeServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NodeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NodeServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NodeServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void search(noderelay.db.NodeReflector.NodeFilter request,
        io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    public void get(noderelay.db.NodeReflector.Node request,
        io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    public void put(noderelay.db.NodeReflector.Node request,
        io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node> responseObserver) {
      asyncUnimplementedUnaryCall(getPutMethod(), responseObserver);
    }

    /**
     */
    public void delete(noderelay.db.NodeReflector.Node request,
        io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSearchMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                noderelay.db.NodeReflector.NodeFilter,
                noderelay.db.NodeReflector.Node>(
                  this, METHODID_SEARCH)))
          .addMethod(
            getGetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                noderelay.db.NodeReflector.Node,
                noderelay.db.NodeReflector.Node>(
                  this, METHODID_GET)))
          .addMethod(
            getPutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                noderelay.db.NodeReflector.Node,
                noderelay.db.NodeReflector.Node>(
                  this, METHODID_PUT)))
          .addMethod(
            getDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                noderelay.db.NodeReflector.Node,
                noderelay.db.NodeReflector.Node>(
                  this, METHODID_DELETE)))
          .build();
    }
  }

  /**
   */
  public static final class NodeServiceStub extends io.grpc.stub.AbstractStub<NodeServiceStub> {
    private NodeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NodeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NodeServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(noderelay.db.NodeReflector.NodeFilter request,
        io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void get(noderelay.db.NodeReflector.Node request,
        io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void put(noderelay.db.NodeReflector.Node request,
        io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(noderelay.db.NodeReflector.Node request,
        io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NodeServiceBlockingStub extends io.grpc.stub.AbstractStub<NodeServiceBlockingStub> {
    private NodeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NodeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NodeServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<noderelay.db.NodeReflector.Node> search(
        noderelay.db.NodeReflector.NodeFilter request) {
      return blockingServerStreamingCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public noderelay.db.NodeReflector.Node get(noderelay.db.NodeReflector.Node request) {
      return blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public noderelay.db.NodeReflector.Node put(noderelay.db.NodeReflector.Node request) {
      return blockingUnaryCall(
          getChannel(), getPutMethod(), getCallOptions(), request);
    }

    /**
     */
    public noderelay.db.NodeReflector.Node delete(noderelay.db.NodeReflector.Node request) {
      return blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NodeServiceFutureStub extends io.grpc.stub.AbstractStub<NodeServiceFutureStub> {
    private NodeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NodeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NodeServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<noderelay.db.NodeReflector.Node> get(
        noderelay.db.NodeReflector.Node request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<noderelay.db.NodeReflector.Node> put(
        noderelay.db.NodeReflector.Node request) {
      return futureUnaryCall(
          getChannel().newCall(getPutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<noderelay.db.NodeReflector.Node> delete(
        noderelay.db.NodeReflector.Node request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH = 0;
  private static final int METHODID_GET = 1;
  private static final int METHODID_PUT = 2;
  private static final int METHODID_DELETE = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NodeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NodeServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEARCH:
          serviceImpl.search((noderelay.db.NodeReflector.NodeFilter) request,
              (io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node>) responseObserver);
          break;
        case METHODID_GET:
          serviceImpl.get((noderelay.db.NodeReflector.Node) request,
              (io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node>) responseObserver);
          break;
        case METHODID_PUT:
          serviceImpl.put((noderelay.db.NodeReflector.Node) request,
              (io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((noderelay.db.NodeReflector.Node) request,
              (io.grpc.stub.StreamObserver<noderelay.db.NodeReflector.Node>) responseObserver);
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

  private static abstract class NodeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NodeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return noderelay.db.NodeReflector.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NodeService");
    }
  }

  private static final class NodeServiceFileDescriptorSupplier
      extends NodeServiceBaseDescriptorSupplier {
    NodeServiceFileDescriptorSupplier() {}
  }

  private static final class NodeServiceMethodDescriptorSupplier
      extends NodeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NodeServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (NodeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NodeServiceFileDescriptorSupplier())
              .addMethod(getSearchMethod())
              .addMethod(getGetMethod())
              .addMethod(getPutMethod())
              .addMethod(getDeleteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
