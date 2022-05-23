package com.xiaoxiong.news;

import com.xiaoxiong.sms.proto.SmsProto;
import com.xiaoxiong.sms.proto.SmsServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

/**
 * @author xiongliang
 * @version 1.0
 * @since 2022/5/17 16:10
 * 服务器端流式RPC通信
 */
public class GrpcClient2 {

    public static final int port = 9090;
    public static final String address = "localhost";

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext()
                .build();

        SmsServiceGrpc.SmsServiceBlockingStub smsService = SmsServiceGrpc.newBlockingStub(channel);
        SmsProto.SmsRequest.Builder builder = SmsProto.SmsRequest.newBuilder()
                .setContent("明天中午开会！");
        for (int i = 0; i < 10; i++) {
            builder.addPhoneNums(String.valueOf(10086 + i));
        }
        System.out.println(builder.build().getPhoneNumsCount());
        Iterator<SmsProto.SmsResponse> smsResponseIterator = smsService.sendSms(builder.build());
        while (smsResponseIterator.hasNext()) {
            System.out.println(smsResponseIterator.next().getResult());
        }
    }

}
