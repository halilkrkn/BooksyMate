# BookApi

All URIs are relative to *http://localhost:8088/api/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**approveReturnBorrowBook**](BookApi.md#approveReturnBorrowBook) | **PATCH** /books/borrow/return/approve/{book-id} |  |
| [**borrowBook**](BookApi.md#borrowBook) | **POST** /books/borrow/{book-id} |  |
| [**findAllBooks**](BookApi.md#findAllBooks) | **GET** /books |  |
| [**findAllBooksByOwner**](BookApi.md#findAllBooksByOwner) | **GET** /books/owner |  |
| [**findAllBorrowedBooks**](BookApi.md#findAllBorrowedBooks) | **GET** /books/borrowed |  |
| [**findAllReturnedBooks**](BookApi.md#findAllReturnedBooks) | **GET** /books/returned |  |
| [**findBookById**](BookApi.md#findBookById) | **GET** /books/{book-id} |  |
| [**returnBorrowBook**](BookApi.md#returnBorrowBook) | **PATCH** /books/borrow/return/{book-id} |  |
| [**saveBook**](BookApi.md#saveBook) | **POST** /books |  |
| [**updateArchivedStatus**](BookApi.md#updateArchivedStatus) | **PATCH** /books/archived/{book-id} |  |
| [**updateShareableStatus**](BookApi.md#updateShareableStatus) | **PATCH** /books/shareable/{book-id} |  |
| [**uploadBookCoverPicture**](BookApi.md#uploadBookCoverPicture) | **POST** /books/cover/{book-id} |  |


<a id="approveReturnBorrowBook"></a>
# **approveReturnBorrowBook**
> Integer approveReturnBorrowBook(bookId)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    Integer bookId = 56; // Integer | 
    try {
      Integer result = apiInstance.approveReturnBorrowBook(bookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#approveReturnBorrowBook");
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

### Return type

**Integer**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="borrowBook"></a>
# **borrowBook**
> Integer borrowBook(bookId)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    Integer bookId = 56; // Integer | 
    try {
      Integer result = apiInstance.borrowBook(bookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#borrowBook");
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

### Return type

**Integer**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="findAllBooks"></a>
# **findAllBooks**
> PageResponseBookResponse findAllBooks(page, size)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    Integer page = 0; // Integer | 
    Integer size = 10; // Integer | 
    try {
      PageResponseBookResponse result = apiInstance.findAllBooks(page, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#findAllBooks");
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
| **page** | **Integer**|  | [optional] [default to 0] |
| **size** | **Integer**|  | [optional] [default to 10] |

### Return type

[**PageResponseBookResponse**](PageResponseBookResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="findAllBooksByOwner"></a>
# **findAllBooksByOwner**
> PageResponseBookResponse findAllBooksByOwner(page, size)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    Integer page = 0; // Integer | 
    Integer size = 10; // Integer | 
    try {
      PageResponseBookResponse result = apiInstance.findAllBooksByOwner(page, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#findAllBooksByOwner");
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
| **page** | **Integer**|  | [optional] [default to 0] |
| **size** | **Integer**|  | [optional] [default to 10] |

### Return type

[**PageResponseBookResponse**](PageResponseBookResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="findAllBorrowedBooks"></a>
# **findAllBorrowedBooks**
> PageResponseBorrowedBookResponse findAllBorrowedBooks(page, size)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    Integer page = 0; // Integer | 
    Integer size = 10; // Integer | 
    try {
      PageResponseBorrowedBookResponse result = apiInstance.findAllBorrowedBooks(page, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#findAllBorrowedBooks");
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
| **page** | **Integer**|  | [optional] [default to 0] |
| **size** | **Integer**|  | [optional] [default to 10] |

### Return type

[**PageResponseBorrowedBookResponse**](PageResponseBorrowedBookResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="findAllReturnedBooks"></a>
# **findAllReturnedBooks**
> PageResponseBorrowedBookResponse findAllReturnedBooks(page, size)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    Integer page = 0; // Integer | 
    Integer size = 10; // Integer | 
    try {
      PageResponseBorrowedBookResponse result = apiInstance.findAllReturnedBooks(page, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#findAllReturnedBooks");
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
| **page** | **Integer**|  | [optional] [default to 0] |
| **size** | **Integer**|  | [optional] [default to 10] |

### Return type

[**PageResponseBorrowedBookResponse**](PageResponseBorrowedBookResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="findBookById"></a>
# **findBookById**
> BookResponse findBookById(bookId)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    Integer bookId = 56; // Integer | 
    try {
      BookResponse result = apiInstance.findBookById(bookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#findBookById");
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

### Return type

[**BookResponse**](BookResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="returnBorrowBook"></a>
# **returnBorrowBook**
> Integer returnBorrowBook(bookId)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    Integer bookId = 56; // Integer | 
    try {
      Integer result = apiInstance.returnBorrowBook(bookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#returnBorrowBook");
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

### Return type

**Integer**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="saveBook"></a>
# **saveBook**
> Integer saveBook(bookRequest)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    BookRequest bookRequest = new BookRequest(); // BookRequest | 
    try {
      Integer result = apiInstance.saveBook(bookRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#saveBook");
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
| **bookRequest** | [**BookRequest**](BookRequest.md)|  | |

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

<a id="updateArchivedStatus"></a>
# **updateArchivedStatus**
> Integer updateArchivedStatus(bookId)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    Integer bookId = 56; // Integer | 
    try {
      Integer result = apiInstance.updateArchivedStatus(bookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#updateArchivedStatus");
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

### Return type

**Integer**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="updateShareableStatus"></a>
# **updateShareableStatus**
> Integer updateShareableStatus(bookId)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    Integer bookId = 56; // Integer | 
    try {
      Integer result = apiInstance.updateShareableStatus(bookId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#updateShareableStatus");
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

### Return type

**Integer**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

<a id="uploadBookCoverPicture"></a>
# **uploadBookCoverPicture**
> Object uploadBookCoverPicture(bookId, _file)



### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.BookApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8088/api/v1");
    
    // Configure HTTP bearer authorization: bearerAuth
    HttpBearerAuth bearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("bearerAuth");
    bearerAuth.setBearerToken("BEARER TOKEN");

    BookApi apiInstance = new BookApi(defaultClient);
    Integer bookId = 56; // Integer | 
    File _file = new File("/path/to/file"); // File | 
    try {
      Object result = apiInstance.uploadBookCoverPicture(bookId, _file);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling BookApi#uploadBookCoverPicture");
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
| **_file** | **File**|  | |

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

