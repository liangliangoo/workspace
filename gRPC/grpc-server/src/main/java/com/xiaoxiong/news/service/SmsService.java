package com.xiaoxiong.news.service;

import com.google.protobuf.ProtocolStringList;
import com.xiaoxiong.sms.proto.SmsProto;
import com.xiaoxiong.sms.proto.SmsServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/5/17 16:04
 * RPC通信模式----服务器端流式处理
 * 服务器端返回的数据是流式的数据
 */
public class SmsService extends SmsServiceGrpc.SmsServiceImplBase {

    @Override
    public void sendSms(SmsProto.SmsRequest request, StreamObserver<SmsProto.SmsResponse> responseObserver) {
        ProtocolStringList numsList = request.getPhoneNumsList();
        System.out.println(request.getPhoneNumsCount());
        numsList.forEach(str -> {
            SmsProto.SmsResponse smsResponse = SmsProto.SmsResponse.newBuilder()
                    .setResult(request.getContent() + "," + str + "收到消息")
                    .build();
            responseObserver.onNext(smsResponse);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        responseObserver.onCompleted();
    }

}
