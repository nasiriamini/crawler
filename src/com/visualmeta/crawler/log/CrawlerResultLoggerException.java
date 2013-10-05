package com.visualmeta.crawler.log;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description Crawler result logger exception
 */

public class CrawlerResultLoggerException extends Exception {
  public static final String WRITE_LOG_FAILURE="err.01";
  public CrawlerResultLoggerException(Throwable throwable) {
    super(throwable);
  }

  public CrawlerResultLoggerException(String string, Throwable throwable) {
    super(string, throwable);
  }

  public CrawlerResultLoggerException(String string) {
    super(string);
  }

  public CrawlerResultLoggerException() {
    super();
  }
}
