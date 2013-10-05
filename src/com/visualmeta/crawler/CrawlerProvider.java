package com.visualmeta.crawler;

import com.visualmeta.crawler.impl.BFSCrawler;
import com.visualmeta.crawler.impl.DFSCrawler;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description provider of crawler's implementations (provider design-pattern)
 */

public class CrawlerProvider {
  /**
     * yelds default crawler implementation (which is in fact the actual assignment in "the email")
     * @param seedURL: First URL to start crawling with
     */
  public static Crawler getDefaultCrawler(String seedURL) {
    return new BFSCrawler(seedURL,1000);
  }
  /**
     * yelds default crawler implementation (which is in fact the actual assignment in "the email")
     * @param seedURL: First URL to start crawling with
     * @param goal: Number of URLs to be fetched
     * @param cs: The crawling strategy
     */
  public static Crawler getCrawler(String seedURL,int goal, CrawlingStrategy cs){
    switch(cs){
      case BFS:  
        return new BFSCrawler(seedURL,goal);
      case DFS:
        return new DFSCrawler(seedURL,goal);
      default:
        return new BFSCrawler(seedURL,goal);
    }
  }
}
