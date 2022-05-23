package com.xiaoxiong.news.service;

import com.xiaoxiong.phone.proto.PhoneProto;
import com.xiaoxiong.phone.proto.PhoneServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/5/17 17:37
 */
public class PhoneService extends PhoneServiceGrpc.PhoneServiceImplBase {

    @Override
    public StreamObserver<PhoneProto.PhoneRequest> createPhoneNum(StreamObserver<PhoneProto.PhoneResponse> responseObserver) {

        /**
         * 异步通信，基于responseObserver事件回调实现
         */
        return new StreamObserver<PhoneProto.PhoneRequest>() {

            int counter = 0;

            /**
             * 当接收到新的request的时候，调用
             * {@inheritDoc}
             * @param request
             */
            @Override
            public void onNext(PhoneProto.PhoneRequest request) {
                System.out.println(request.getPhoneNum()+ "已登记");
                counter++;
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(PhoneProto.PhoneResponse.newBuilder()
                        .setResult("本次共导入了" + counter + "条数据").build());
                responseObserver.onCompleted();
            }
        };
    }
}
