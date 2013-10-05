package com.visualmeta.crawler.log;

import java.io.FileOutputStream;
import java.io.OutputStream;

import java.util.Date;
import java.util.Set;

/**
 * @author Nerssi Nasiri Amini
 * @edited-by
 * @version 1.00
 * @Description Utility class which writes the crawler result into a log file
 */

public class CrawlerResultLogger {
  /**
     * Generates the text contaning each url in a separate line
     * @param urls: urls from which the log content must be generated
     */ 
  public static String getLogContent(Set<String> urls){
    String content="";
    if(urls!=null){
      for(String url:urls){
        content+=url+"\r\n";
      }
    }
    int length=content.length();
    if(length!=0){
      content=content.substring(0, length-2);
    }
    return content;
  }
  /**
     * Generates the log file name according to current time
     */ 
  private static String getLogFileName(){
    Date d=new Date();
    String s=d.toString();
    s = s.replaceAll(":", "-");
    s=s.substring(0, 19);
    return "Crawled_URLs_Log ("+s+").txt";
  }
  /**
     * Generates the text contaning each url in a separate line and saves it in the input path
     * @param urls: urls from which the log content must be generated
     * @param path: location to save the log file
     */ 
  public static String log(Set<String> urls,String path)throws CrawlerResultLoggerException{
    String content=getLogContent(urls);
    String logFileName=getLogFileName();
    String location=null;
    if(path!=null){
      if(PathDetector.isWindows()){
        if(!path.endsWith("\\")){
          path+="\\";
        }
      }
      else{
        if(!path.endsWith("/")){
          path+="/";
        }
      }
    }
    else{
      return null;
    }
    location=path+logFileName;
    if(content!=null && content.length()!=0){
      OutputStream out=null;
      try{
        out=new FileOutputStream(location);
        out.write(content.getBytes("utf-8"));
        out.flush();
      }
      catch(Exception e)
      {
        e.printStackTrace();
        throw new CrawlerResultLoggerException(CrawlerResultLoggerException.WRITE_LOG_FAILURE,e);
      }
      finally{
        if(out!=null){
          try{
            out.close();
          }
          catch(Exception e){
            
          }
        }
      }
      return path+logFileName;
    }
    else{
      System.out.println("There is nothing to log");
    }
    return null;
  }
}
