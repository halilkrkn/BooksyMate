/*
 * Booksy Mate API
 * Open API documentation for Spring Security Project
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: halilkrkn000@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openapitools.client.JSON;

/**
 * AuthenticateRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-01-07T15:07:35.337762+03:00[Europe/Istanbul]", comments = "Generator version: 7.7.0")
public class AuthenticateRequest {
  public static final String SERIALIZED_NAME_EMAIL = "email";
  @SerializedName(SERIALIZED_NAME_EMAIL)
  private String email;

  public static final String SERIALIZED_NAME_PASSWORD = "password";
  @SerializedName(SERIALIZED_NAME_PASSWORD)
  private String password;

  public AuthenticateRequest() {
  }

  public AuthenticateRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   */
  @javax.annotation.Nonnull
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public AuthenticateRequest password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
   */
  @javax.annotation.Nonnull
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthenticateRequest authenticateRequest = (AuthenticateRequest) o;
    return Objects.equals(this.email, authenticateRequest.email) &&
        Objects.equals(this.password, authenticateRequest.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthenticateRequest {\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("email");
    openapiFields.add("password");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("email");
    openapiRequiredFields.add("password");
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to AuthenticateRequest
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!AuthenticateRequest.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in AuthenticateRequest is not found in the empty JSON string", AuthenticateRequest.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!AuthenticateRequest.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `AuthenticateRequest` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : AuthenticateRequest.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("email").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `email` to be a primitive type in the JSON string but got `%s`", jsonObj.get("email").toString()));
      }
      if (!jsonObj.get("password").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `password` to be a primitive type in the JSON string but got `%s`", jsonObj.get("password").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!AuthenticateRequest.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'AuthenticateRequest' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<AuthenticateRequest> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(AuthenticateRequest.class));

       return (TypeAdapter<T>) new TypeAdapter<AuthenticateRequest>() {
           @Override
           public void write(JsonWriter out, AuthenticateRequest value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public AuthenticateRequest read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of AuthenticateRequest given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of AuthenticateRequest
   * @throws IOException if the JSON string is invalid with respect to AuthenticateRequest
   */
  public static AuthenticateRequest fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, AuthenticateRequest.class);
  }

  /**
   * Convert an instance of AuthenticateRequest to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

