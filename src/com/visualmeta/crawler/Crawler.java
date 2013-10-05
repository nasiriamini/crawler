package com.visualmeta.crawler;

import com.visualmeta.crawler.log.CommandLineLogger;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;

import java.net.URL;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Set;

import sun.net.www.http.HttpClient;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description abstract class representing a generic crawler
 */

public abstract class Crawler {
  private String seedURL;
  private int goal;
  private Set<String> fetchedURLs;
  
  /**
     * Constructor of the class
     * @param seedURL: First URL to start crawling with
     * @param goal: Number of URLs to be fetched
     */
  public Crawler(String seedURL,int goal) {
    this.seedURL=seedURL;
    this.goal=goal;
    fetchedURLs=new HashSet<String>();
  }
  
  /**
     * adds only non-duplicate extracted urls to the whole list of urls yet found
     * @param contentURLs: URLs fetched from page content
     */
  protected Set<String> addFetchedURLs(Set<String> contentURLs){
    if(contentURLs!=null && !contentURLs.isEmpty()){
      Set<String> nonDuplicateURLs=new HashSet<String>();
      for(String url:contentURLs){
        if(url!=null){
          url=url.trim();
        }
        if(!fetchedURLs.contains(url) && fetchedURLs.size()<goal){
          fetchedURLs.add(url);
          nonDuplicateURLs.add(url);
        }
      }
      return nonDuplicateURLs;
    }
    else{
      return null;
    }
  }
  /**
     * Method which does the actual crawling which shall be implemented by any crawler
     */
  public abstract Set<String> crawl();
  
  /**
     * logs current status of the whole process
     */
  public void logCurrentStatus(){
    CommandLineLogger.logCrawlingStatus(this.fetchedURLs.size(),this.goal);
  }
  public void setSeedURL(String seedURL) {
    this.seedURL = seedURL;
  }

  public String getSeedURL() {
    return seedURL;
  }

  public void setGoal(int goal) {
    this.goal = goal;
  }

  public int getGoal() {
    return goal;
  }

  public void setFetchedURLs(Set<String> fetchedURLs) {
    this.fetchedURLs = fetchedURLs;
  }

  public Set<String> getFetchedURLs() {
    return fetchedURLs;
  }
}
