package com.visualmeta.crawler;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description Enum containing the crawling stratery types (BFS/DFS)
 */

public enum CrawlingStrategy {
  BFS ("Breadth First"),
  DFS ("Depth First");
  
  private final String value;
  CrawlingStrategy(String value){
    this.value=value;
  }
  public String value(){
    return this.value;
  }
}
