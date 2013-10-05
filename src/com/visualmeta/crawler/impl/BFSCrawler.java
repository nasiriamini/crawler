package com.visualmeta.crawler.impl;

import com.visualmeta.crawler.Crawler;
import com.visualmeta.crawler.net.URLContentReader;
import com.visualmeta.crawler.net.URLContentReaderException;
import com.visualmeta.crawler.net.URLUtility;
import com.visualmeta.crawler.parser.URLExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description Breadth First implementation of the crawler
 */

public class BFSCrawler extends Crawler{
  public BFSCrawler(String seedURL,int goal) {
    super(seedURL,goal);
  }
  /**
     * implementation of the BFS crawler
     */
  public Set<String> crawl(){
    this.logCurrentStatus();
    List<String> urls=new ArrayList<String>();
    urls.add(this.getSeedURL());
    crawlRecursively(urls);
    int urlCount=this.getFetchedURLs().size();
    if(urlCount<this.getGoal()){
      System.out.println("\nAll links were crawled but only "+urlCount+" were found.");
    }
    else{
      System.out.println("\nAll links were fetched successfully");
    }
    return this.getFetchedURLs();
  }
  /**
     * Crawls recursively to fetch page contents and find the links within
     * @param urls: URLs to begin with
     */
  public void crawlRecursively(List<String> urls){
    List<String> nonDuplicateURLs=new ArrayList<String>();
    for(String pageURL:urls){
      String content=null;
      try{
        content = URLContentReader.fetchURLContent(pageURL);
      }
      catch(URLContentReaderException e){
        if(URLContentReaderException.NETWORK_FAILUR.equalsIgnoreCase(e.getMessage())){
          System.out.println("\nFetching url content of \""+pageURL+"\" failed due to network failur; Crawler skips this url anyway.");
          e.printStackTrace();
          this.logCurrentStatus();
        }
        else if(URLContentReaderException.INAPPROPRIATE_CONTENT_TYPE.equalsIgnoreCase(e.getMessage())){
          System.out.println("\nFetching url content of \""+pageURL+"\" canceled because the content type was not text/html.");
          this.logCurrentStatus();
        }
        else if(URLContentReaderException.UNKNOWN_CONTENT_TYPE.equalsIgnoreCase(e.getMessage())){
          System.out.println("\nCrawler skiped fetching url content of \""+pageURL+"\" because the content type shall not be detected.");
          this.logCurrentStatus();
        }
        else{
          System.out.println("\nFetching url content of \""+pageURL+"\" failed (probably the url is not a valid one) and crawler skips this url:");
          e.printStackTrace();
          this.logCurrentStatus();
        }
      }
      catch(Exception e){
        System.out.println("\nRuntime Exception: Unclassified error:  "+pageURL);
        e.printStackTrace();
        this.logCurrentStatus();
      }
      Set<String> contentURLs=null;
      if(content!=null && content.length()!=0){
        URLExtractor urlExtractor=new URLExtractor();
        contentURLs=urlExtractor.extractURLs(content);
      }
      else{
        continue;
      }
      if(contentURLs!=null && !contentURLs.isEmpty()){
        //Now extracted urls must be normalized (duplicate urls must be removed and all urls must be absolute)
        contentURLs=URLUtility.normalizeURLs(pageURL,contentURLs);
      }
      else{
        continue;
      }
      Set<String> newNonDuplicateURLs=null;
      if(contentURLs!=null && !contentURLs.isEmpty()){
        //Normalized urls should be added to the whole url list and non duplicate ones must be specified to be crawled
        newNonDuplicateURLs=this.addFetchedURLs(contentURLs);
      }
      if(newNonDuplicateURLs!=null && !newNonDuplicateURLs.isEmpty()){
        nonDuplicateURLs.addAll(newNonDuplicateURLs);
      }
      this.logCurrentStatus();
      if(this.getFetchedURLs().size()>=this.getGoal()){
        return;
      }
    }
    if(nonDuplicateURLs!=null && !nonDuplicateURLs.isEmpty()){
      if(this.getFetchedURLs().size()>=this.getGoal()){
        return;
      }
      else{
        if(nonDuplicateURLs!=null && !nonDuplicateURLs.isEmpty()){
          crawlRecursively(nonDuplicateURLs);
        }
      }
    }
  }
}
