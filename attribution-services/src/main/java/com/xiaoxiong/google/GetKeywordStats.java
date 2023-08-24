package com.xiaoxiong.google;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.ads.googleads.v14.common.Metrics;
import com.google.ads.googleads.v14.errors.GoogleAdsError;
import com.google.ads.googleads.v14.errors.GoogleAdsException;
import com.google.ads.googleads.v14.resources.AdGroup;
import com.google.ads.googleads.v14.resources.AdGroupCriterion;
import com.google.ads.googleads.v14.resources.Campaign;
import com.google.ads.googleads.v14.services.GoogleAdsRow;
import com.google.ads.googleads.v14.services.GoogleAdsServiceClient;
import com.google.ads.googleads.v14.services.SearchGoogleAdsStreamRequest;
import com.google.ads.googleads.v14.services.SearchGoogleAdsStreamResponse;
import com.google.api.gax.rpc.ServerStream;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @Author 六月
 * @Date 2023/8/22 16:24
 * @Version 1.0
 */
public class GetKeywordStats {


    public static void main(String[] args) throws IOException {

        GoogleAdsClient googleAdsClient = null;
        try {
            // 2、使用ClassPathResource
            URL classPathUrl =  new ClassPathResource("/ads.properties").getURL();
            googleAdsClient = GoogleAdsClient.newBuilder().fromPropertiesFile(new File(classPathUrl.toURI())).build();
        } catch (FileNotFoundException fnfe) {
            System.err.printf(
                    "Failed to load GoogleAdsClient configuration from file. Exception: %s%n", fnfe);
            System.exit(1);
        } catch (IOException ioe) {
            System.err.printf("Failed to create GoogleAdsClient. Exception: %s%n", ioe);
            System.exit(1);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try {
            new GetKeywordStats().runExample(googleAdsClient, 7528323849l);
        } catch (GoogleAdsException gae) {
            // GoogleAdsException is the base class for most exceptions thrown by an API request.
            // Instances of this exception have a message and a GoogleAdsFailure that contains a
            // collection of GoogleAdsErrors that indicate the underlying causes of the
            // GoogleAdsException.
            System.err.printf(
                    "Request ID %s failed due to GoogleAdsException. Underlying errors:%n",
                    gae.getRequestId());
            int i = 0;
            for (GoogleAdsError googleAdsError : gae.getGoogleAdsFailure().getErrorsList()) {
                System.err.printf("  Error %d: %s%n", i++, googleAdsError);
            }
            System.exit(1);
        }
    }

    /**
     * Runs the example.
     *
     * @param googleAdsClient the Google Ads API client.
     * @param customerId the client customer ID.
     * @throws GoogleAdsException if an API request failed with one or more service errors.
     */
    private void runExample(GoogleAdsClient googleAdsClient, long customerId) {
        try (GoogleAdsServiceClient googleAdsServiceClient =
                     googleAdsClient.getLatestVersion().createGoogleAdsServiceClient()) {
            String searchQuery =
                    "SELECT campaign.id, "
                            + "campaign.name, "
                            + "ad_group.id, "
                            + "ad_group.name, "
                            + "ad_group_criterion.criterion_id, "
                            + "ad_group_criterion.keyword.text, "
                            + "ad_group_criterion.keyword.match_type, "
                            + "metrics.impressions, "
                            + "metrics.clicks, "
                            + "metrics.cost_micros "
                            + "FROM keyword_view "
                            + "WHERE segments.date DURING LAST_7_DAYS "
                            + "AND campaign.advertising_channel_type = 'SEARCH' "
                            + "AND ad_group.status = 'ENABLED' "
                            + "AND ad_group_criterion.status IN ('ENABLED', 'PAUSED') "
                            // Limits to the 50 keywords with the most impressions in the date range.
                            + "ORDER BY metrics.impressions DESC "
                            + "LIMIT 50";
            // Constructs the SearchGoogleAdsStreamRequest.
            SearchGoogleAdsStreamRequest request =
                    SearchGoogleAdsStreamRequest.newBuilder()
                            .setCustomerId(Long.toString(customerId))
                            .setQuery(searchQuery)
                            .build();

            // Creates and issues a search Google Ads stream request that will retrieve all of the
            // requested field values for the keyword.
            ServerStream<SearchGoogleAdsStreamResponse> stream =
                    googleAdsServiceClient.searchStreamCallable().call(request);
            // googleAdsServiceClient.searchStreamCallable().call(request);

            // Iterates through the results in the stream response and prints all of the requested
            // field values for the keyword in each row.
            for (SearchGoogleAdsStreamResponse response : stream) {
                for (GoogleAdsRow googleAdsRow : response.getResultsList()) {
                    Campaign campaign = googleAdsRow.getCampaign();
                    AdGroup adGroup = googleAdsRow.getAdGroup();
                    AdGroupCriterion adGroupCriterion = googleAdsRow.getAdGroupCriterion();
                    Metrics metrics = googleAdsRow.getMetrics();

                    System.out.printf(
                            "Keyword text '%s' with "
                                    + "match type '%s' "
                                    + "and ID %d "
                                    + "in ad group '%s' "
                                    + "with ID %d "
                                    + "in campaign '%s' "
                                    + "with ID %d "
                                    + "had %d impression(s), "
                                    + "%d click(s), "
                                    + "and %d cost (in micros) "
                                    + "during the last 7 days.%n",
                            adGroupCriterion.getKeyword().getText(),
                            adGroupCriterion.getKeyword().getMatchType(),
                            adGroupCriterion.getCriterionId(),
                            adGroup.getName(),
                            adGroup.getId(),
                            campaign.getName(),
                            campaign.getId(),
                            metrics.getImpressions(),
                            metrics.getClicks(),
                            metrics.getCostMicros());
                }
            }
        }
    }

}
