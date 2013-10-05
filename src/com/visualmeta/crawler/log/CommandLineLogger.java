package com.visualmeta.crawler.log;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description the utility class for encapsulating commandline logs functions 
 */
public class CommandLineLogger {
  /**
     * log the whole process sate by showing the percentage of the progress done
     * @param fetched: number of links found yet
     * @param goal: number of links to be found
     */ 
  public static void logCrawlingStatus(int fetched,int goal){
    int percentage=((fetched*100)/goal);
    String percentageStr=String.valueOf(percentage);
    int length=percentageStr.length();
    System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
    System.out.print("Crawling Progress: "+(length==2?" ":(length==1?"  ":""))+percentageStr+"%");
  }
}
