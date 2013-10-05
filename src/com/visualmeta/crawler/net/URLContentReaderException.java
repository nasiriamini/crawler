package com.visualmeta.crawler.net;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description Exception type thrown while fetching content of URLs
 */

public class URLContentReaderException extends Exception {
  public final static String MALFORMED_URL="err.01";
  public final static String NETWORK_FAILUR="err.02";
  public final static String STREAM_READ_FAILUR="err.03";
  public final static String INAPPROPRIATE_CONTENT_TYPE="err.04";
  public final static String UNKNOWN_CONTENT_TYPE="err.05";
  
  public URLContentReaderException(Throwable throwable) {
    super(throwable);
  }

  public URLContentReaderException(String string, Throwable throwable) {
    super(string, throwable);
  }

  public URLContentReaderException(String string) {
    super(string);
  }

  public URLContentReaderException() {
    super();
  }
}
