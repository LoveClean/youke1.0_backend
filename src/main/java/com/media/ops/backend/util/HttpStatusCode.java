package com.media.ops.backend.util;

import java.security.InvalidParameterException;

/**
 * HTTP status code
 *
 * <p>
 * The HTTP status code series can be retrieved via {@link #series()}.
 *
 * @see <a href="http://tools.ietf.org/html/rfc7231">http://tools.ietf.org/html/rfc7231</a>
 */
public enum HttpStatusCode {

  // 1xx Informational
  CONTINUE(100, "Continue"), 
  SWITCHING_PROTOCOLS(101, "Switching Protocols"), 
  PROCESSING(102, "Processing"), 
  CHECKPOINT(103, "Checkpoint"),

  // 2xx Success
  OK(200, "OK"), 
  CREATED(201, "Created"), 
  ACCEPTED(202, "Accepted"), 
  NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"), 
  NO_CONTENT(204, "No Content"), 
  RESET_CONTENT(205, "Reset Content"), 
  PARTIAL_CONTENT(206, "Partial Content"), 
  MULTI_STATUS(207, "Multi-Status"), 
  ALREADY_REPORTED(208, "Already Reported"), 
  IM_USED(226, "IM Used"),

  // 3xx Redirection
  MULTIPLE_CHOICES(300, "Multiple Choices"), 
  MOVED_PERMANENTLY(301, "Moved Permanently"), 
  FOUND(302, "Found"),
  @Deprecated
  MOVED_TEMPORARILY(302, "Moved Temporarily"), 
  SEE_OTHER(303, "See Other"), 
  NOT_MODIFIED(304, "Not Modified"), 
  @Deprecated
  USE_PROXY(305, "Use Proxy"), 
  TEMPORARY_REDIRECT(307, "Temporary Redirect"), 
  PERMANENT_REDIRECT(308, "Permanent Redirect"),

  // --- 4xx Client Error ---
  BAD_REQUEST(400, "Bad Request"), 
  UNAUTHORIZED(401, "Unauthorized"), 
  PAYMENT_REQUIRED(402, "Payment Required"), 
  FORBIDDEN(403, "Forbidden"), 
  NOT_FOUND(404, "Not Found"), 
  METHOD_NOT_ALLOWED(405, "Method Not Allowed"), 
  NOT_ACCEPTABLE(406, "Not Acceptable"), 
  PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"), 
  REQUEST_TIMEOUT(408, "Request Timeout"), 
  CONFLICT(409, "Conflict"), 
  GONE(410, "Gone"), 
  LENGTH_REQUIRED(411, "Length Required"), 
  PRECONDITION_FAILED(412, "Precondition Failed"), 
  PAYLOAD_TOO_LARGE(413, "Payload Too Large"), 
  @Deprecated
  REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
  URI_TOO_LONG(414, "URI Too Long"), 
  @Deprecated
  REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"), 
  UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"), 
  REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"), 
  EXPECTATION_FAILED(417, "Expectation Failed"), 
  I_AM_A_TEAPOT(418, "I'm a teapot"), 
  @Deprecated
  INSUFFICIENT_SPACE_ON_RESOURCE(419, "Insufficient Space On Resource"), 
  @Deprecated
  METHOD_FAILURE(420, "Method Failure"), 
  @Deprecated
  DESTINATION_LOCKED(421, "Destination Locked"), 
  UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"), 
  LOCKED(423, "Locked"), 
  FAILED_DEPENDENCY(424, "Failed Dependency"), 
  UPGRADE_REQUIRED(426, "Upgrade Required"), 
  PRECONDITION_REQUIRED(428, "Precondition Required"), 
  TOO_MANY_REQUESTS(429, "Too Many Requests"), 
  REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),

  // --- 5xx Server Error ---
  INTERNAL_SERVER_ERROR(500, "Internal Server Error"), 
  NOT_IMPLEMENTED(501, "Not Implemented"), 
  BAD_GATEWAY(502, "Bad Gateway"), 
  SERVICE_UNAVAILABLE(503, "Service Unavailable"), 
  GATEWAY_TIMEOUT(504, "Gateway Timeout"), 
  HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"), 
  VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"), 
  INSUFFICIENT_STORAGE(507, "Insufficient Storage"), 
  LOOP_DETECTED(508, "Loop Detected"), 
  BANDWIDTH_LIMIT_EXCEEDED(509, "Bandwidth Limit Exceeded"), 
  NOT_EXTENDED(510, "Not Extended"), 
  NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");

  private final int value;
  private final String reasonPhrase;

  private HttpStatusCode(int value, String reasonPhrase) {
    this.value = value;
    this.reasonPhrase = reasonPhrase;
  }

  public int value() {
    return this.value;
  }

  public String getReasonPhrase() {
    return reasonPhrase;
  }

  public boolean is1xxInformational() {
    return (Series.INFORMATIONAL.equals(series()));
  }

  public boolean is2xxSuccessful() {
    return (Series.SUCCESSFUL.equals(series()));
  }

  public boolean is3xxRedirection() {
    return (Series.REDIRECTION.equals(series()));
  }

  public boolean is4xxClientError() {
    return (Series.CLIENT_ERROR.equals(series()));
  }

  public boolean is5xxServerError() {
    return (Series.SERVER_ERROR.equals(series()));
  }

  public Series series() {
    return Series.valueOf(this);
  }

  @Override
  public String toString() {
    return Integer.toString(value);
  }


  /**
   * Return the enum constant of this type with the specified numeric value.
   * 
   * @param statusCode the numeric value of the enum to be returned
   * @return the enum constant with the specified numeric value
   * @throws InvalidParameterException if this enum has no constant for the specified numeric value
   */
  public static HttpStatusCode valueOf(int statusCode) {
    for (HttpStatusCode status : values()) {
      if (status.value == statusCode) {
        return status;
      }
    }
    throw new RuntimeException("No matching constant for [" + statusCode + "]");
  }


  public static enum Series {

    INFORMATIONAL(1), SUCCESSFUL(2), REDIRECTION(3), CLIENT_ERROR(4), SERVER_ERROR(5);

    private final int value;

    private Series(int value) {
      this.value = value;
    }

    /**
     * Return the integer value of this status series. Ranges from 1 to 5.
     */
    public int value() {
      return this.value;
    }

    public static Series valueOf(int status) {
      int seriesCode = status / 100;
      for (Series series : values()) {
        if (series.value == seriesCode) {
          return series;
        }
      }
      throw new RuntimeException("No matching constant for [" + status + "]");
    }

    public static Series valueOf(HttpStatusCode status) {
      return valueOf(status.value);
    }

  }

}
