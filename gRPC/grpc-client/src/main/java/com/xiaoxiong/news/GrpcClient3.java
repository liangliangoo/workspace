package com.xiaoxiong.news;

import com.xiaoxiong.phone.proto.PhoneProto;
import com.xiaoxiong.phone.proto.PhoneServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/5/17 17:46
 * 客户端流式请求
 */
public class GrpcClient3 {

    private PhoneServiceGrpc.PhoneServiceStub asyncPhoneService = null;
    public static final int port = 9090;
    public static final String address = "localhost";

    public static void main(String[] args) {

        GrpcClient3 client = new GrpcClient3();

        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port)
                .usePlaintext()
                .build();
        client.asyncPhoneService = PhoneServiceGrpc.newStub(channel);
        try {
            client.createPhone();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            channel.shutdown();
        }

    }

    StreamObserver<PhoneProto.PhoneResponse> responseObserver = new StreamObserver<PhoneProto.PhoneResponse>() {
        @Override
        public void onNext(PhoneProto.PhoneResponse response) {
            System.out.println(response.getResult());
        }

        @Override
        public void onError(Throwable t) {
            System.out.println("出现异常，error:" + t.getMessage());
        }

        @Override
        public void onCompleted() {
            System.out.println("处理完毕");
        }
    };

    private void createPhone() throws InterruptedException {
        StreamObserver<PhoneProto.PhoneRequest> requestObserver = asyncPhoneService.createPhoneNum(responseObserver);
        for (int i = 0; i < 10; i++) {
            PhoneProto.PhoneRequest request = PhoneProto.PhoneRequest
                    .newBuilder()
                    .setPhoneNum(String.valueOf(10086 + i))
                    .build();
            requestObserver.onNext(request);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                requestObserver.onError(e);
                throw e;
            }


        }
        requestObserver.onCompleted();
    }

}
