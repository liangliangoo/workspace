package com.xiaoxiong.flink.component;

import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author 六月
 * @Date 2023/8/24 19:58
 * @Version 1.0
 */
@Slf4j
public class OkHttpClientUtil {

    private static Integer connectTimeout = 30;

    private static Integer readTimeout = 30;

    private static Integer writeTimeout = 30;

    private static Integer maxIdleConnections = 30;

    private static Long keepAliveDuration = 300L;

    private static OkHttpClient okHttpClient;

    public static OkHttpClient okHttpClient() {
        if (Objects.isNull(okHttpClient)) {
            synchronized (OkHttpClientUtil.class) {
                if (Objects.isNull(okHttpClient)) {
                    okHttpClient = new OkHttpClient.Builder()
                            .sslSocketFactory(sslSocketFactory(), x509TrustManager())
                            // 是否开启缓存
                            .retryOnConnectionFailure(false)
                            .connectionPool(pool())
                            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                            .readTimeout(readTimeout, TimeUnit.SECONDS)
                            .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                            .hostnameVerifier((hostname, session) -> true)
                            // 设置代理
                            // .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888)))
                            // 拦截器
                            // .addInterceptor()
                            .build();
                }
            }

        }
        return okHttpClient;
    }

    public static X509TrustManager x509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    public static SSLSocketFactory sslSocketFactory() {
        try {
            // 信任任何链接
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509TrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error("sslSocketFactory error {}", e.getMessage());
        }
        return null;
    }

    public static ConnectionPool pool() {
        return new ConnectionPool(maxIdleConnections, keepAliveDuration, TimeUnit.SECONDS);
    }


}
