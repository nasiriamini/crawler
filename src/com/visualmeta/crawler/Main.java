package com.visualmeta.crawler;

import com.visualmeta.crawler.log.CrawlerResultLogger;

import com.visualmeta.crawler.log.CrawlerResultLoggerException;
import com.visualmeta.crawler.log.PathDetector;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description Main method for testing the default crawler with seed url (the original assignment)
 */

public class Main {
  public Main() {
    super();
  }
  /**
     * Main method for testing the whole crawling process
     * @param args: Expected arguments are as follows respectively:
     *  -Seed URL: url from which the crawling begins
     *  -Goal: number of links to be fetched
     *  -Crawler Implementation: DFS(Depth-First) or BFS(Breadth-First)
     *  -Path: The path the fetched result is to be saved in
     */
  public static void main(String[] args) throws Exception{
    
    if(args==null || args.length==0){
      System.out.println("No argument is defiend.");
      System.out.println("List of availabler arguments is as follows:");
      System.out.println("  1.Seed URL                          Mandatory");
      System.out.println("  2.Number of links to be crawled     Default value is 1000");
      System.out.println("  3.Crawling Strategy                 Default is BFS and DFS shall be set also");
      System.out.println("  4.path to save the fetched result   Default on Windows c:\\ on Unix /opt/");
      
    }
    else{
      String seedURL=null;
      if(args!=null && args.length>0){
        seedURL=args[0];
        try{
          new URL(seedURL);
        }
        catch(MalformedURLException e){
          e.printStackTrace();
          System.out.println("Seed URL is not valid.");
          return;
        }
      }
      if(seedURL==null){
        System.out.println("Assigning the seed URL as the first argument is mandatory."); 
        return;
      }
      else{
        int goal=1000;
        if(args!=null && args.length>1){
          try{
            goal=Integer.parseInt(args[1]);
            if(goal<=0){
              System.out.println("The second argument must be a non negetive non zero integer.");
              return;
            }
          }
          catch(Exception e){
            System.out.println("The second argument must be a non negetive non zero integer.");
            return;
          }
        }
        
        CrawlingStrategy crawlingStrategy=CrawlingStrategy.BFS;
        if(args!=null && args.length>2){
          String strategy=args[2];
          if("DFS".equalsIgnoreCase(strategy)){
            crawlingStrategy=CrawlingStrategy.DFS;
          }
          else if("BFS".equalsIgnoreCase(strategy)){
            //Already assigned
          }
          else{
            System.out.println("The third argument is invalid. It must be eigther BFS or DFS.");
            return;
          }
        }
          
      
        String path=null;
        if(args!=null && args.length>3){
          path=args[3];
        }
        else{
          path=PathDetector.getDefaultPath();
        }
        
        System.out.println("");
        System.out.print("Now starting crawling process.");
        Thread.sleep(600);
        System.out.print(".");
        Thread.sleep(600);
        System.out.print(".");
        Thread.sleep(600);
        System.out.println("");
        
        Crawler crawler=CrawlerProvider.getCrawler(seedURL,goal,crawlingStrategy);
        Set<String> crawledURLs=crawler.crawl();
        
        if(crawledURLs!=null && !crawledURLs.isEmpty()){
          try{
            String where=CrawlerResultLogger.log(crawledURLs,path);
            if(where!=null){
              System.out.println("The fetched result is saved in the file: "+where);
            }
          }
          catch(CrawlerResultLoggerException e){
            if(CrawlerResultLoggerException.WRITE_LOG_FAILURE.equalsIgnoreCase(e.getMessage())){
              System.out.println("\n");
              System.out.println("Writing log file failed.");
              System.out.print("Program will write the result simply here");
              Thread.sleep(600);
              System.out.print(".");
              Thread.sleep(600);
              System.out.print(".");
              Thread.sleep(600);
              System.out.print(".");
              Thread.sleep(600);
              System.out.print(".");
              Thread.sleep(600);
              System.out.println("");
              System.out.println(CrawlerResultLogger.getLogContent(crawledURLs));
            }
          }
        }
        else{
          System.out.println("Begining with the seed url, "+seedURL+", crawler failed to find any URL!");
        }
      }
    }
  }
}
