// package com.xiaoxiong.google;
//
// import com.alibaba.dubbo.config.support.Parameter;
// import com.google.ads.googleads.lib.GoogleAdsClient;
// import com.google.ads.googleads.v12.errors.GoogleAdsError;
//
// import java.io.FileNotFoundException;
// import java.io.IOException;
//
// /**
//  * @Author 六月
//  * @Date 2023/8/22 16:14
//  * @Version 1.0
//  */
// public class GoogleAds {
//
//
//     public static void main(String[] args) throws IOException {
//         // GoogleAdsClient client = GoogleAdsClient.newBuilder()
//         //         .fromEnvironment()
//         //         .fromPropertiesFile(new File("./ads.properties"))
//         //         .build();
//
//         GoogleAdsClient googleAdsClient = null;
//         try {
//             googleAdsClient = GoogleAdsClient.newBuilder().fromPropertiesFile().build();
//         } catch (FileNotFoundException fnfe) {
//             System.err.printf(
//                     "Failed to load GoogleAdsClient configuration from file. Exception: %s%n", fnfe);
//             System.exit(1);
//         } catch (IOException ioe) {
//             System.err.printf("Failed to create GoogleAdsClient. Exception: %s%n", ioe);
//             System.exit(1);
//         }
//
//         try {
//             new GetKeywordStats().runExample(googleAdsClient, params.customerId);
//         } catch (GoogleAdsException gae) {
//             // GoogleAdsException is the base class for most exceptions thrown by an API request.
//             // Instances of this exception have a message and a GoogleAdsFailure that contains a
//             // collection of GoogleAdsErrors that indicate the underlying causes of the
//             // GoogleAdsException.
//             System.err.printf(
//                     "Request ID %s failed due to GoogleAdsException. Underlying errors:%n",
//                     gae.getRequestId());
//             int i = 0;
//             for (GoogleAdsError googleAdsError : gae.getGoogleAdsFailure().getErrorsList()) {
//                 System.err.printf("  Error %d: %s%n", i++, googleAdsError);
//             }
//             System.exit(1);
//         }
//
//     }
//
// }
