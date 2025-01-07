# FeedbackApi

All URIs are relative to *http://localhost:8088/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**findAllFeedbacksByBook**](FeedbackApi.md#findAllFeedbacksByBook) | **GET** /feedbacks/book/{book-id} |  |
| [**saveFeedback**](FeedbackApi.md#saveFeedback) | **POST** /feedbacks |  |


<a id="findAllFeedbacksByBook"></a>
# **findAllFeedbacksByBook**
> PageResponseFeedbackResponse findAllFeedbacksByBook(bookId, page, size)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FeedbackApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    FeedbackApi apiInstance = new FeedbackApi(defaultClient);
    Integer bookId = 56; // Integer | 
    Integer page = 0; // Integer | 
    Integer size = 10; // Integer | 
    try {
      PageResponseFeedbackResponse result = apiInstance.findAllFeedbacksByBook(bookId, page, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FeedbackApi#findAllFeedbacksByBook");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **bookId** | **Integer**|  | |
| **page** | **Integer**|  | [optional] [default to 0] |
| **size** | **Integer**|  | [optional] [default to 10] |

### Return type

[**PageResponseFeedbackResponse**](PageResponseFeedbackResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="saveFeedback"></a>
# **saveFeedback**
> Integer saveFeedback(feedbackRequest)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.FeedbackApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    FeedbackApi apiInstance = new FeedbackApi(defaultClient);
    FeedbackRequest feedbackRequest = new FeedbackRequest(); // FeedbackRequest | 
    try {
      Integer result = apiInstance.saveFeedback(feedbackRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling FeedbackApi#saveFeedback");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **feedbackRequest** | [**FeedbackRequest**](FeedbackRequest.md)|  | |

### Return type

**Integer**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

